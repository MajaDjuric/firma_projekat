package JWD64.Test.controller;

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

import JWD64.Test.dto.RobaDto;
import JWD64.Test.model.Roba;
import JWD64.Test.service.RobaService;
import JWD64.Test.support.RobaDtoToRoba;
import JWD64.Test.support.RobaToRobaDto;

@RestController
@RequestMapping(value = "api/roba", produces = MediaType.APPLICATION_JSON_VALUE)
public class RobaController {

	@Autowired
	private RobaService robaService;
	
	@Autowired
	private RobaToRobaDto toRobaDto;
	
	@Autowired
	private RobaDtoToRoba toRoba;

//    @PreAuthorize("permitAll()")
	@GetMapping
	public ResponseEntity<List<RobaDto>> getAll (@RequestParam (required = false) String naziv,
												 @RequestParam (required = false) Long proizvodjacId,
												 @RequestParam (required = false) Double pakovanje,
												 @RequestParam (required = false) String tretman,
												 @RequestParam (required = false) Long  vrstaId,
												 @RequestParam (required = false, defaultValue = "0") int pageNo){
		Page<Roba> page = robaService.search(naziv, proizvodjacId, pakovanje, tretman, vrstaId, pageNo);
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
	
    @PreAuthorize("permitAll()")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RobaDto> create (@Valid @RequestBody RobaDto robaDto){
		Roba roba = toRoba.convert(robaDto);
		Roba novaRoba = robaService.save(roba);
		return new ResponseEntity<>(toRobaDto.convert(novaRoba), HttpStatus.CREATED);
	}
	
    @PreAuthorize("permitAll()")
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RobaDto> update (@PathVariable Long id, @RequestBody RobaDto robaDto){
		if (robaDto.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Roba roba = toRoba.convert(robaDto);
		Roba izmenjenaRoba = robaService.update(roba);
		return new ResponseEntity<>(toRobaDto.convert(izmenjenaRoba), HttpStatus.OK);	
	}
    

	 
	}
