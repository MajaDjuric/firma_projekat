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

import Projekat.Firma.dto.TipDto;
import Projekat.Firma.model.TipRobe;
import Projekat.Firma.service.TipService;
import Projekat.Firma.support.TipToTipDto;

@RestController
@RequestMapping(value = "api/tipoviRobe", produces = MediaType.APPLICATION_JSON_VALUE)
public class TipoviRobeController {

	@Autowired
	private TipService tipService;
	
	@Autowired
	private TipToTipDto toTipDto;
	
	@GetMapping(value =  "/{id}")
	public ResponseEntity<List<TipDto>> getAll (@PathVariable Long id){
		List<TipRobe> tipovi = tipService.findByVrstaId(id);
		return new ResponseEntity<> (toTipDto.convert(tipovi), HttpStatus.OK);
	}

}
