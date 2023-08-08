package Projekat.Firma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Projekat.Firma.dto.ProizvodjacDto;
import Projekat.Firma.model.Proizvodjac;
import Projekat.Firma.service.ProizvodjacService;
import Projekat.Firma.support.ProizvodjacToProizvodjacDto;

@RestController
@RequestMapping (value = "api/proizvodjaci", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProizvodjacController {
	
	@Autowired
	private ProizvodjacService proizvodjacService;
	
	@Autowired
	private ProizvodjacToProizvodjacDto toProizvodjacDto;
	
	@GetMapping
	public ResponseEntity<List<ProizvodjacDto>> getAll (){
		List<Proizvodjac> proizvodjaci = proizvodjacService.findAll();
		return new ResponseEntity<> (toProizvodjacDto.convert(proizvodjaci), HttpStatus.OK);
	}

}
