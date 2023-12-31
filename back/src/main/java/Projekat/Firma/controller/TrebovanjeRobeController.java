package Projekat.Firma.controller;

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
import Projekat.Firma.dto.TrebovanjeRobeDto;
import Projekat.Firma.model.Dispozicija;
import Projekat.Firma.model.Roba;
import Projekat.Firma.model.Trebovanje;
import Projekat.Firma.model.TrebovanjeRobe;
import Projekat.Firma.service.DispozicijaService;
import Projekat.Firma.service.RobaService;
import Projekat.Firma.service.TrebovanjeRobeService;
import Projekat.Firma.support.RobaToRobaDto;
import Projekat.Firma.support.TrebovanjeRobeDtoToTrebovanjeRobe;
import Projekat.Firma.support.TrebovanjeRobeToTrebodavnjeRobeDto;

@RestController
@RequestMapping(value = "api/trebovanjaRobe", produces = MediaType.APPLICATION_JSON_VALUE)
public class TrebovanjeRobeController {

	@Autowired
	private TrebovanjeRobeService trebovanjeRobeService;
	
	@Autowired
	private TrebovanjeRobeDtoToTrebovanjeRobe toTrebovanjeRobe;;
	
	@Autowired
	private TrebovanjeRobeToTrebodavnjeRobeDto toTrebovanjeRobeDto;;
	
	@Autowired
	private RobaService robaService;
	
	@Autowired
	private RobaToRobaDto toRobaDto;

//    @PreAuthorize("permitAll()")
	@GetMapping
	public ResponseEntity<List<TrebovanjeRobeDto>> getAll (@RequestParam (required = false) String grad,
														   @RequestParam (required = false) String teren,
														   @RequestParam (required = false) String komercijalistaIme,
														   @RequestParam (required = false) String kupacNaziv,
														   @RequestParam (required = false) Long  robaId,
														   @RequestParam (required = false, defaultValue = "0") int pageNo){
		
		Page<TrebovanjeRobe> page = trebovanjeRobeService.search(grad, teren, komercijalistaIme, kupacNaziv, robaId, pageNo);
		HttpHeaders headers = new HttpHeaders();
		headers.add("total-pages", Integer.toString(page.getTotalPages()));
		return new ResponseEntity<>(toTrebovanjeRobeDto.convert(page.getContent()), headers, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{id}/trebovanja")
	public ResponseEntity<List<TrebovanjeRobeDto>> getAll (@PathVariable Long id){
		List<TrebovanjeRobe> trebovanjaRobe = trebovanjeRobeService.findByTrebovanjeId(id);
		return new ResponseEntity<>(toTrebovanjeRobeDto.convert(trebovanjaRobe),HttpStatus.OK);
	}
	

	@GetMapping(value = "dispozicija/{id}")
	public ResponseEntity<List<TrebovanjeRobeDto>> getByDispozicijaId (@PathVariable Long id){
		List<TrebovanjeRobe> trebovanjaRobe = trebovanjeRobeService.findByDispozicijaId(id);
		return new ResponseEntity<>(toTrebovanjeRobeDto.convert(trebovanjaRobe),HttpStatus.OK);
	}
	
    @PreAuthorize("permitAll()")
	@GetMapping(value = "/{id}")
	public ResponseEntity<TrebovanjeRobeDto> getOne (@PathVariable Long id){
		TrebovanjeRobe trebovanjeRobe = trebovanjeRobeService.findOne(id);
		if (trebovanjeRobe == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toTrebovanjeRobeDto.convert(trebovanjeRobe), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('KOMERCIJALA')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id){
		TrebovanjeRobe trebovanjeRobe  = trebovanjeRobeService.findOne(id);
		if (trebovanjeRobe == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Roba roba = trebovanjeRobe.getRoba();
		roba.setStanje(roba.getStanje() + trebovanjeRobe.getKolicina());
		Roba updatedRoba = robaService.update(roba);
		Trebovanje trebovanje = trebovanjeRobe.getTrebovanje();
		trebovanje.getTrebovanjeRobeList().remove(trebovanjeRobe);
		trebovanjeRobeService.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PreAuthorize("hasRole('KOMERCIJALA')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TrebovanjeRobeDto> create (@Valid @RequestBody TrebovanjeRobeDto trebovanjeRobeDto){
		TrebovanjeRobe trebovanjeRobe = toTrebovanjeRobe.convert(trebovanjeRobeDto);
		Roba roba = robaService.findOne(trebovanjeRobeDto.getRobaId());
		if (roba.getStanje() < trebovanjeRobeDto.getKolicina()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Trebovanje trebovanje = trebovanjeRobe.getTrebovanje();
		for (TrebovanjeRobe trebovanjeRobe1 : trebovanje.getTrebovanjeRobeList()) {
			if (trebovanjeRobe1.getRoba().getId() == trebovanjeRobeDto.getRobaId()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		TrebovanjeRobe novoTrebovanjeRobe = trebovanjeRobeService.save(trebovanjeRobe);
		trebovanje.getTrebovanjeRobeList().add(novoTrebovanjeRobe);
		
		roba.setStanje(roba.getStanje() - trebovanjeRobeDto.getKolicina());
		Roba updatedRoba = robaService.update(roba);
		trebovanje.getRoba().add(roba);
		roba.getTrebovanjeRobeList().add(novoTrebovanjeRobe);
		roba.getTrebovanja().add(trebovanje);
		return new ResponseEntity<>(toTrebovanjeRobeDto.convert(novoTrebovanjeRobe), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('KOMERCIJALA')")
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TrebovanjeRobeDto> update (@PathVariable Long id,  @RequestBody Map < String, Integer > requestBody){
		TrebovanjeRobe trebovanjeRobe = trebovanjeRobeService.findOne(id);
		if (trebovanjeRobe == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Roba roba = trebovanjeRobe.getRoba();
		int stanje = roba.getStanje();
		int novaKolicina = requestBody.get("kolicina");
		int staraKolicina = trebovanjeRobe.getKolicina();
		int razlikaUKolicini = staraKolicina - novaKolicina;
		if (razlikaUKolicini > 0) {
			roba.setStanje(stanje + razlikaUKolicini);
		}else {
			roba.setStanje(stanje + razlikaUKolicini);
		}
		 
		System.out.println();
		System.out.println("stara kolicina" + staraKolicina);
		System.out.println("nova kolicina" + novaKolicina);
		System.out.println("razlika" + razlikaUKolicini);
		System.out.println("roba stanje" + stanje);
		System.out.println();
		
		Roba updatedRoba = robaService.update(roba);
    	trebovanjeRobe.setKolicina(novaKolicina);
		TrebovanjeRobe izmenjenoTrebovanjeRobe = trebovanjeRobeService.update(trebovanjeRobe);
		return new ResponseEntity<>(toTrebovanjeRobeDto.convert(izmenjenoTrebovanjeRobe), HttpStatus.OK);	
	}
    

	 
	}
