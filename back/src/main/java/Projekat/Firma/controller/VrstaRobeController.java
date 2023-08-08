package Projekat.Firma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Projekat.Firma.dto.VrstaRobeDto;
import Projekat.Firma.model.VrstaRobe;
import Projekat.Firma.service.VrstaService;
import Projekat.Firma.support.VrstaToVrstaDto;

@RestController
@RequestMapping(value = "api/vrsteRobe", produces = MediaType.APPLICATION_JSON_VALUE)
public class VrstaRobeController {
	
	@Autowired
	private VrstaService vrstaService;
	
	@Autowired
	private VrstaToVrstaDto toVrstaDto;
	
	@GetMapping
	public ResponseEntity<List<VrstaRobeDto>> getAll (){
		List<VrstaRobe> vrste = vrstaService.findAll();
		return new ResponseEntity<> (toVrstaDto.convert(vrste), HttpStatus.OK);
	}

}
