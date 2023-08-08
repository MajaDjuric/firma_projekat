package Projekat.Firma.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import Projekat.Firma.dto.DispozicijaDto;
import Projekat.Firma.dto.VoziloDto;
import Projekat.Firma.model.Dispozicija;
import Projekat.Firma.model.Vozilo;
import Projekat.Firma.service.VoziloService;
import Projekat.Firma.support.VoziloDtoToVozilo;
import Projekat.Firma.support.VoziloToVoziloDto;

@RestController
@RequestMapping(value = "api/vozila", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoziloController {

	@Autowired
	private VoziloService voziloService;
	
	@Autowired
	private VoziloToVoziloDto toVoziloDto;
	
	@Autowired
	private VoziloDtoToVozilo toVozilo;

	@GetMapping
	public ResponseEntity<List<VoziloDto>> getAll (){
		List<Vozilo> vozila = voziloService.findAll();
		return new ResponseEntity<>(toVoziloDto.convert(vozila),  HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<VoziloDto> getAll (@PathVariable Long id){
		Vozilo vozilo  = voziloService.findOne(id);
		return new ResponseEntity<>(toVoziloDto.convert(vozilo), HttpStatus.OK);
	}
	
//    @PreAuthorize("permitAll()")
//	@DeleteMapping(value = "/{id}")
//	public ResponseEntity<Void> delete (@PathVariable Long id){
//		Dispozicija dispozicija = dispozicijaService.findOne(id);
//		if (dispozicija == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		dispozicijaService.delete(id);
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}
//	
//	@PreAuthorize("permitAll()")
//		@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//		public ResponseEntity<DispozicijaDto> create (@Valid @RequestBody DispozicijaDto dispozicijaDto){
//			Dispozicija dispozicija = toDispozicija.convert(dispozicijaDto);
//			Dispozicija novaDispozicija = dispozicijaService.save(dispozicija);
//			return new ResponseEntity<>(toDispozicijaDto.convert(novaDispozicija), HttpStatus.CREATED);
//		}
//		
//	@PreAuthorize("permitAll()")
// 	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//		public ResponseEntity<DispozicijaDto> update (@PathVariable Long id, @RequestBody DispozicijaDto dispozicijaDto){
//			if (dispozicijaDto.getId() != id) {
//				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//			}
//			Dispozicija dispozicija = toDispozicija.convert(dispozicijaDto);
//			Dispozicija izmenjenaDispozicija = dispozicijaService.update(dispozicija);
//			return new ResponseEntity<>(toDispozicijaDto.convert(izmenjenaDispozicija), HttpStatus.OK);	
//		}
//	
//	public LocalDate getLocalDate (String datunString) {
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate datum = LocalDate.parse(datunString, dtf);
//		return datum;
//	}	
	
	
	
}
