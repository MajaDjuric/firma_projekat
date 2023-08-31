package Projekat.Firma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Projekat.Firma.dto.VoziloDto;
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
	
}
