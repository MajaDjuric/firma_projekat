package JWD64.Test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import JWD64.Test.dto.ProizvodjacDto;
import JWD64.Test.model.Proizvodjac;
import JWD64.Test.service.ProizvodjacService;
import JWD64.Test.support.ProizvodjacToProizvodjacDto;

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
