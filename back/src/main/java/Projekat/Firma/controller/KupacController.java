package Projekat.Firma.controller;

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

import Projekat.Firma.dto.KupacDto;
import Projekat.Firma.dto.ProizvodjacDto;
import Projekat.Firma.dto.RobaDto;
import Projekat.Firma.model.Kupac;
import Projekat.Firma.model.Proizvodjac;
import Projekat.Firma.model.Roba;
import Projekat.Firma.service.KupacService;
import Projekat.Firma.service.ProizvodjacService;
import Projekat.Firma.support.KupacDtoToKupac;
import Projekat.Firma.support.KupacToKupacDto;
import Projekat.Firma.support.ProizvodjacToProizvodjacDto;

@RestController
@RequestMapping (value = "api/kupci", produces = MediaType.APPLICATION_JSON_VALUE)
public class KupacController {
	
	@Autowired
	private KupacService kupacService;
	
	@Autowired
	private KupacDtoToKupac toKupac;
	
	@Autowired
	private KupacToKupacDto toKupacDto;

	@GetMapping
	public ResponseEntity<List<KupacDto>> getAll (){
		List<Kupac> kupci= kupacService.findAll();
		return new ResponseEntity<> (toKupacDto.convert(kupci), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}/komercijala")
	public ResponseEntity<List<KupacDto>> getByKomercijalistaId (@PathVariable Long id){
		List<Kupac> kupci= kupacService.findByKomercijalistaId(id);
		return new ResponseEntity<> (toKupacDto.convert(kupci), HttpStatus.OK);
	}

    @PreAuthorize("permitAll()") 
	@GetMapping(value = "/{id}")
	public ResponseEntity<KupacDto> getOne (@PathVariable Long id){
		Kupac kupac = kupacService.findOne(id);
		if (kupac == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<>(toKupacDto.convert(kupac), HttpStatus.OK);
	}
	
    @PreAuthorize("permitAll()")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id){
		Kupac kupac = kupacService.findOne(id);
		if (kupac == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		kupacService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
    @PreAuthorize("permitAll()")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KupacDto> create (@Valid @RequestBody KupacDto kupacDto){
		Kupac kupac = toKupac.convert(kupacDto);
		Kupac noviKupac = kupacService.save(kupac);
		return new ResponseEntity<>(toKupacDto.convert(noviKupac), HttpStatus.CREATED);
	}
	
    @PreAuthorize("permitAll()")
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KupacDto> update (@PathVariable Long id, @RequestBody KupacDto kupacDto){
		if (kupacDto.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Kupac kupac = toKupac.convert(kupacDto);
		Kupac izmenjeniKupac = kupacService.update(kupac);
		return new ResponseEntity<>(toKupacDto.convert(izmenjeniKupac), HttpStatus.OK);	
	}
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
