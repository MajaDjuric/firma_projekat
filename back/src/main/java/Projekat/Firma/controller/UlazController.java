package Projekat.Firma.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
import Projekat.Firma.dto.UlazDto;
import Projekat.Firma.model.Roba;
import Projekat.Firma.model.Ulaz;
import Projekat.Firma.service.RobaService;
import Projekat.Firma.service.UlazService;
import Projekat.Firma.support.RobaToRobaDto;
import Projekat.Firma.support.UlazDToToUlaz;
import Projekat.Firma.support.UlazToUlazDto;

@RestController
@RequestMapping(value = "api/ulazi", produces = MediaType.APPLICATION_JSON_VALUE)
public class UlazController {

	@Autowired
	private UlazService ulazService;
	
	@Autowired
	private UlazToUlazDto toUlazDto;
	
	@Autowired
	private UlazDToToUlaz toUlaz;
	
	@Autowired
	private RobaService robaService;
	
	@Autowired
	private RobaToRobaDto toRobaDto;

//    @PreAuthorize("permitAll()")
	@GetMapping
	public ResponseEntity<List<UlazDto>> getAll (@RequestParam (required = false) String brojFakture,
												 @RequestParam (required = false) String brojOtpremnice,
												 @RequestParam (required = false) String minDatumUlaza,
												 @RequestParam (required = false) String maxDatumUlaza,
												 @RequestParam (required = false) Long  proizvodjacId,
												 @RequestParam (required = false) Long  robaId,
												 @RequestParam (required = false, defaultValue = "0") int pageNo){
		LocalDate minDatum = null;
		LocalDate maxDatum = null;
		if (minDatumUlaza == null) {
			minDatumUlaza = "2000-01-01";
		}
		if (maxDatumUlaza == null) {
			maxDatumUlaza = "3000-01-01";
		}
		minDatum = getLocalDate(minDatumUlaza);
		maxDatum = getLocalDate(maxDatumUlaza);
		
		Page<Ulaz> page = ulazService.search(brojFakture, brojOtpremnice, minDatum, maxDatum, proizvodjacId, robaId, pageNo);
		HttpHeaders headers = new HttpHeaders();
		headers.add("total-pages", Integer.toString(page.getTotalPages()));
		return new ResponseEntity<>(toUlazDto.convert(page.getContent()), headers, HttpStatus.OK);
	}
	
    @PreAuthorize("permitAll()")
	@GetMapping(value = "/{id}")
	public ResponseEntity<UlazDto> getOne (@PathVariable Long id){
		Ulaz ulaz = ulazService.findOne(id);
		if (ulaz == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toUlazDto.convert(ulaz), HttpStatus.OK);
	}
	
    @PreAuthorize("permitAll()")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id){
		Ulaz ulaz = ulazService.findOne(id);
		if (ulaz == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ulazService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
    @PreAuthorize("permitAll()")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UlazDto> create (@Valid @RequestBody UlazDto ulazDto){
		Ulaz ulaz = toUlaz.convert(ulazDto);
		Ulaz noviUlaz = ulazService.save(ulaz);
		return new ResponseEntity<>(toUlazDto.convert(noviUlaz), HttpStatus.CREATED);
	}
	
    @PreAuthorize("permitAll()")
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UlazDto> update (@PathVariable Long id, @RequestBody UlazDto ulazDto){
		if (ulazDto.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Ulaz ulaz = toUlaz.convert(ulazDto);
		Ulaz izmenjeniUlaz = ulazService.update(ulaz);
		return new ResponseEntity<>(toUlazDto.convert(izmenjeniUlaz), HttpStatus.OK);	
	}
    
    @PreAuthorize("permitAll()")
	@PutMapping(value ="/dodavanje/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> dodavanjeRobeUlaz (@Valid @RequestBody List<RobaDto> robaDto, @PathVariable Long id){
    	Ulaz ulaz = ulazService.findOne(id);
    	List<Roba> listaUpdatedRobe = new ArrayList<>();
    	for (RobaDto robaDto2 : robaDto) {
    		Roba roba = robaService.findOne(robaDto2.getId());
    		roba.setUlaz(roba.getUlaz() + robaDto2.getKolicina());
    		roba.setStanje(roba.getStanje() + robaDto2.getKolicina());
    		Roba UpdatedRoba = robaService.update(roba);
    		listaUpdatedRobe.add(UpdatedRoba);
    		List<Ulaz> ulaziRobe = UpdatedRoba.getUlazi();
    		ulaziRobe.add(ulaz);
		}
		ulaz.setRoba(listaUpdatedRobe);
		Ulaz updatedUlaz = ulazService.save(ulaz);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
    
	@GetMapping (value = "{id}/roba")
	public ResponseEntity<List<RobaDto>> getAll (@PathVariable Long id) {
		List<Roba> roba = robaService.findByUlaziId(id);
		return new ResponseEntity<>(toRobaDto.convert(roba), HttpStatus.OK);
	}
    
	public LocalDate getLocalDate (String datunString) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate datum = LocalDate.parse(datunString, dtf);
		return datum;
	}
	 
	}
