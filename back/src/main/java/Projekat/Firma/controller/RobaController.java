package Projekat.Firma.controller;

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

import Projekat.Firma.dto.RobaDto;
import Projekat.Firma.model.Roba;
import Projekat.Firma.model.VrstaRobe;
import Projekat.Firma.repository.VrstaRepository;
import Projekat.Firma.service.RobaService;
import Projekat.Firma.service.VrstaService;
import Projekat.Firma.support.RobaDtoToRoba;
import Projekat.Firma.support.RobaToRobaDto;

@RestController
@RequestMapping(value = "api/roba", produces = MediaType.APPLICATION_JSON_VALUE)
public class RobaController {

	@Autowired
	private RobaService robaService;
	
	@Autowired
	private VrstaService vrstaService;
	
	@Autowired
	private RobaToRobaDto toRobaDto;
	
	@Autowired
	private RobaDtoToRoba toRoba;

//    @PreAuthorize("permitAll()")
	@GetMapping
	public ResponseEntity<List<RobaDto>> getAll (@RequestParam (required = false) String vrsta,
												 @RequestParam (required = false) String naziv,
												 @RequestParam (required = false) Long proizvodjacId,
												 @RequestParam (required = false) Double pakovanje,
												 @RequestParam (required = false) String tretman,
												 @RequestParam (required = false, defaultValue = "0") int pageNo){
		VrstaRobe vrstaRobe = vrstaService.findByNaziv(vrsta);
		Page<Roba> page = robaService.search(vrstaRobe, naziv, proizvodjacId, pakovanje, tretman, pageNo);
		HttpHeaders headers = new HttpHeaders();
		headers.add("total-pages", Integer.toString(page.getTotalPages()));
		return new ResponseEntity<>(toRobaDto.convert(page.getContent()), headers, HttpStatus.OK);
	}
	
    @PreAuthorize("permitAll()")
	@GetMapping(value = "/{id}")
	public ResponseEntity<RobaDto> getOne (@PathVariable Long id){
		Roba roba = robaService.findOne(id);
		if (roba == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toRobaDto.convert(roba), HttpStatus.OK);
	}
	
    @PreAuthorize("permitAll()")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id){
		Roba roba = robaService.findOne(id);
		if (roba == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		robaService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PreAuthorize("hasRole('FINANSIJE')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RobaDto> create (@Valid @RequestBody RobaDto robaDto){
		Roba roba = toRoba.convert(robaDto);
		Roba novaRoba = robaService.save(roba);
		return new ResponseEntity<>(toRobaDto.convert(novaRoba), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('FINANSIJE')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<RobaDto> update (@PathVariable Long id, @RequestBody Map <String, Double> requestBody){
		Roba roba = robaService.findOne(id);
		Double prodajnaCena = requestBody.get("prodajnaCena");
		roba.setProdajnaCena(prodajnaCena);
		Roba izmenjenaRoba = robaService.update(roba);
		return new ResponseEntity<>(toRobaDto.convert(izmenjenaRoba), HttpStatus.OK);	
	}
	 
	}
