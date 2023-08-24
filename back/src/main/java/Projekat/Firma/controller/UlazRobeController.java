package Projekat.Firma.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Projekat.Firma.dto.UlazRobeDto;
import Projekat.Firma.model.Roba;
import Projekat.Firma.model.Ulaz;
import Projekat.Firma.model.UlazRobe;
import Projekat.Firma.service.RobaService;
import Projekat.Firma.service.UlazRobeService;
import Projekat.Firma.service.UlazService;
import Projekat.Firma.support.UlazRobeDtoToUlazRobe;
import Projekat.Firma.support.UlazRobeToUlazRobeDto;

@RestController
@RequestMapping(value = "api/ulaziRobe", produces = MediaType.APPLICATION_JSON_VALUE)
public class UlazRobeController {
	
	@Autowired
	private UlazRobeService ulazRobeService;
	
	@Autowired
	private UlazService ulazService;
	
	@Autowired
	private RobaService robaService;
	
	@Autowired
	private UlazRobeDtoToUlazRobe toUlazRobe;
	
	@Autowired
	private UlazRobeToUlazRobeDto toUlazRobeDto;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UlazRobeDto> getOne (@PathVariable Long id){
		UlazRobe ulazRobe = ulazRobeService.findOne(id);
		if (ulazRobe == null) {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<> (toUlazRobeDto.convert(ulazRobe), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}/ulazi")
	public ResponseEntity< List<UlazRobeDto> > getAll (@PathVariable Long id){
		List<UlazRobe> ulaziRobe = ulazRobeService.findOneByUlazId(id);
		return new ResponseEntity<> (toUlazRobeDto.convert(ulaziRobe), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id){
		UlazRobe ulazRobe = ulazRobeService.findOne(id);
		if (ulazRobe == null) {
			return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
		}
		ulazRobeService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);		
	}
	
	@PostMapping (value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UlazRobeDto> create (@PathVariable Long id, @Valid @RequestBody UlazRobeDto ulazRobeDto){
		Ulaz ulaz = ulazService.findOne(id);
		Roba roba = robaService.findOne(ulazRobeDto.getRobaId());
		ulaz.getRoba().add(roba);
		Ulaz updatedUlaz = ulazService.update(ulaz);
		UlazRobe ulazRobe = toUlazRobe.convert(ulazRobeDto); 
		UlazRobe noviUlazRobe = ulazRobeService.save(ulazRobe);
		return new ResponseEntity<UlazRobeDto>(toUlazRobeDto.convert(noviUlazRobe), HttpStatus.CREATED);
	}
	
	@PutMapping (value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UlazRobeDto> update (@PathVariable Long id, @Valid @RequestBody UlazRobeDto ulazRobeDto){
		if(ulazRobeDto.getId() != id) {
			return new ResponseEntity<UlazRobeDto>(HttpStatus.BAD_REQUEST);
		}
		UlazRobe ulazRobe = toUlazRobe.convert(ulazRobeDto);
		UlazRobe izmenjenUlazRobe = ulazRobeService.update(ulazRobe);
		return new ResponseEntity<UlazRobeDto>(toUlazRobeDto.convert(izmenjenUlazRobe), HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
}
