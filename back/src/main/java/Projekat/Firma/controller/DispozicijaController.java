package Projekat.Firma.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Projekat.Firma.dto.DispozicijaDto;
import Projekat.Firma.model.Dispozicija;
import Projekat.Firma.model.Trebovanje;
import Projekat.Firma.model.TrebovanjeRobe;
import Projekat.Firma.service.DispozicijaService;
import Projekat.Firma.service.TrebovanjeRobeService;
import Projekat.Firma.service.TrebovanjeService;
import Projekat.Firma.support.DispozicijaDtoToDispozicija;
import Projekat.Firma.support.DispozicijaToDispozicijaDto;

@RestController
@RequestMapping(value = "api/dispozicije", produces = MediaType.APPLICATION_JSON_VALUE)
public class DispozicijaController {

	@Autowired
	private DispozicijaService dispozicijaService;
	
	@Autowired
	private DispozicijaDtoToDispozicija toDispozicija;
	
	@Autowired
	private DispozicijaToDispozicijaDto toDispozicijaDto;
	
	@Autowired
	private TrebovanjeRobeService trebovanjeRobeService;
	
	@Autowired
	private TrebovanjeService trebovanjeService;

    @PreAuthorize("permitAll()")
	@GetMapping
	public ResponseEntity<List<DispozicijaDto>> getAll (@RequestParam (required = false) String datumIsporuke,
													    @RequestParam (required = false, defaultValue = "0") int pageNo){
		
		Page<Dispozicija> page;

		if (datumIsporuke == null || datumIsporuke.equals("")) {
			page = dispozicijaService.findAll(pageNo);
		}else {
			LocalDate datumIsporukeDatum = getLocalDate(datumIsporuke);
			page = dispozicijaService.search(datumIsporukeDatum, pageNo);
		}

		List<Dispozicija> dispozicije = page.getContent();
		for (Dispozicija dispozicija : dispozicije) {
			List<TrebovanjeRobe> isporucenaTrebovanjaRobe = new ArrayList<>();
			for (TrebovanjeRobe trebovanjeRobe : dispozicija.getTrebovanjaRobe()) {
				if (trebovanjeRobe.isIsporuceno()) {
					isporucenaTrebovanjaRobe.add(trebovanjeRobe);
				}
			}
			if (dispozicija.getTrebovanjaRobe().size() == isporucenaTrebovanjaRobe.size() && !dispozicija.getTrebovanjaRobe().isEmpty()) {
				dispozicija.setIsporuceno(true);
				Dispozicija updatedDispozicija = dispozicijaService.update(dispozicija);
			}
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("total-pages", Integer.toString(page.getTotalPages()));
		return new ResponseEntity<>(toDispozicijaDto.convert(page.getContent()), headers, HttpStatus.OK);
	}
	
    @PreAuthorize("permitAll()")
	@GetMapping(value = "/{id}")
	public ResponseEntity<DispozicijaDto> getAll (@PathVariable Long id){
		Dispozicija dispozicija = dispozicijaService.findOne(id);
		return new ResponseEntity<>(toDispozicijaDto.convert(dispozicija), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('LOGISTIKA')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id){
		Dispozicija dispozicija = dispozicijaService.findOne(id);
		if (dispozicija == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		dispozicijaService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PreAuthorize("hasRole('LOGISTIKA')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<DispozicijaDto> create (@Valid @RequestBody DispozicijaDto dispozicijaDto){
			Dispozicija dispozicija = toDispozicija.convert(dispozicijaDto);
			Dispozicija novaDispozicija = dispozicijaService.save(dispozicija);
			return new ResponseEntity<>(toDispozicijaDto.convert(novaDispozicija), HttpStatus.CREATED);
		}
		
	@PreAuthorize("hasRole('LOGISTIKA')")
 	@PutMapping(value = "/{id}")
		public ResponseEntity<DispozicijaDto> dodavanjeTrebovanjaUDispoziciju (@PathVariable Long id, @RequestBody Map<String, List<String>> requestBody){
			Dispozicija dispozicija = dispozicijaService.findOne(id);
			if (dispozicija == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			List<String> trebovanjaRobeIds = requestBody.get("trebovanjaRobeIds");
			List<Long> trebovanjaRobeIdsLong = new ArrayList<>();
			for (String trebovanjeRobeId : trebovanjaRobeIds) {
				Long idLong = Long.parseLong(trebovanjeRobeId);
				trebovanjaRobeIdsLong.add(idLong);
			}
			
			List<TrebovanjeRobe> trebovanjaRobeList = new ArrayList<>();
			for (Long idTrebovanja : trebovanjaRobeIdsLong) {
				TrebovanjeRobe  trebovanjeRobe = trebovanjeRobeService.findOne(idTrebovanja);
				if (trebovanjeRobe != null) {
				trebovanjeRobe.setDisponirano(true);
				trebovanjeRobe.setDispozicija(dispozicija);
				dispozicija.getTrebovanjaRobe().add(trebovanjeRobe);
				TrebovanjeRobe izmenjenoTrebovanjeRobe = trebovanjeRobeService.save(trebovanjeRobe);
				}
			}
		    dispozicija.getTrebovanjaRobe().addAll(trebovanjaRobeList);
			Dispozicija izmenjenaDispozicija = dispozicijaService.update(dispozicija);
			return new ResponseEntity<>(toDispozicijaDto.convert(izmenjenaDispozicija), HttpStatus.OK);	
		}

	
	@PreAuthorize("hasRole('MAGACIN')")
 	@PutMapping(value = "/{id}/isporuceno")
		public ResponseEntity<Void> setIsporucenaRoba (@PathVariable Long id, @RequestBody Map<String, List<String>> requestBody){
			Dispozicija dispozicija = dispozicijaService.findOne(id);
			if (dispozicija == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			List<String> trebovanjaRobeIds = requestBody.get("trebovanjaRobeIds");
			List<Long> trebovanjaRobeIdsLong = new ArrayList<>();
			for (String trebovanjeRobeId : trebovanjaRobeIds) {
				Long idLong = Long.parseLong(trebovanjeRobeId);
				trebovanjaRobeIdsLong.add(idLong);
			}
			
			List<TrebovanjeRobe> trebovanjaRobeList = new ArrayList<>();
			for (Long idTrebovanjaRobe : trebovanjaRobeIdsLong) {
				TrebovanjeRobe  trebovanjeRobe = trebovanjeRobeService.findOne(idTrebovanjaRobe);
				if (trebovanjeRobe != null) {
				trebovanjeRobe.setIsporuceno(true);
				TrebovanjeRobe izmenjenoTrebovanjeRobe = trebovanjeRobeService.save(trebovanjeRobe);
				}
				Trebovanje trebovanje = trebovanjeRobe.getTrebovanje();
				boolean svaTrebovanjaRobeIzTrebovanjaIsporuceno = true;
				for (TrebovanjeRobe trebovanjeRobe2 : trebovanje.getTrebovanjeRobeList()) {
					if (!trebovanjeRobe2.isIsporuceno()) {
						svaTrebovanjaRobeIzTrebovanjaIsporuceno = false;
					}
				}
				if (svaTrebovanjaRobeIzTrebovanjaIsporuceno) {
					trebovanje.setIsporuceno(true);
					Trebovanje updatedTrebovanje = trebovanjeService.update(trebovanje);
				}
			}
			
			dispozicija.setIsporuceno(true);
			List<TrebovanjeRobe> svaTrebovanjaRobeUDispoziciji = dispozicija.getTrebovanjaRobe();
			for (TrebovanjeRobe trebovanjeRobe : svaTrebovanjaRobeUDispoziciji) {
				if (!trebovanjeRobe.isIsporuceno()) {
					trebovanjeRobe.setDisponirano(false);
					TrebovanjeRobe updated = trebovanjeRobeService.update(trebovanjeRobe);
				}
			}
			Dispozicija updatedDispozicija = dispozicijaService.update(dispozicija);
			return new ResponseEntity<>(HttpStatus.OK);	
		}
	
	@PreAuthorize("hasRole('LOGISTIKA')")
	@DeleteMapping(value = "/brisanjeTrebovanjaRobe/{id}")
	public ResponseEntity<Void> deleteTrebovanjeRobeIzDispozicije (@PathVariable Long id){
    	TrebovanjeRobe trebovanjeRobe = trebovanjeRobeService.findOne(id);
    	if (trebovanjeRobe == null || trebovanjeRobe.getDispozicija() == null) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Dispozicija dispozicija = trebovanjeRobe.getDispozicija();
		dispozicija.getTrebovanjaRobe().remove(trebovanjeRobe);
		trebovanjeRobe.setDisponirano(false);
		trebovanjeRobe.setDispozicija(null);
		TrebovanjeRobe updatedTrebovanjeRobe = trebovanjeRobeService.update(trebovanjeRobe);
		Dispozicija updatedDispozicija = dispozicijaService.update(dispozicija);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	public LocalDate getLocalDate (String datunString) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate datum = LocalDate.parse(datunString, dtf);
		return datum;
	}	
	
}
