package JWD64.Test.controller;

import java.time.LocalDate;
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

import JWD64.Test.dto.TrebovanjeDto;
import JWD64.Test.dto.TrebovanjeRobeDto;
import JWD64.Test.model.Roba;
import JWD64.Test.model.Trebovanje;
import JWD64.Test.model.TrebovanjeRobe;
import JWD64.Test.service.RobaService;
import JWD64.Test.service.TrebovanjeRobeService;
import JWD64.Test.service.TrebovanjeService;
import JWD64.Test.support.TrebovanjeDtoToTrebovanje;
import JWD64.Test.support.TrebovanjeRobeDtoToTrebovanjeRobe;
import JWD64.Test.support.TrebovanjeRobeToTrebodavnjeRobeDto;
import JWD64.Test.support.TrebovanjeToTrebovanjeDto;

@RestController
@RequestMapping(value = "api/trebovanja", produces = MediaType.APPLICATION_JSON_VALUE)
public class TrebovanjeController {

	@Autowired
	private TrebovanjeService trebovanjeService;
	
	@Autowired
	private TrebovanjeDtoToTrebovanje toTrebovanje;
	
	@Autowired
	private TrebovanjeToTrebovanjeDto toTrebovanjeDto;
	
	@Autowired
	private RobaService robaService;
	
	@Autowired
	private TrebovanjeRobeService trebovanjeRobeService;
	
	@Autowired
	private TrebovanjeRobeDtoToTrebovanjeRobe toTrebovanjeRobe;
	
	@Autowired
	private TrebovanjeRobeToTrebodavnjeRobeDto toTrebovanjeRobeDto;

	@GetMapping
	public ResponseEntity<List<TrebovanjeDto>> getAll (@RequestParam (required = false) String teren,
													   @RequestParam (required = false) Long komercijalistaId,
													   @RequestParam (required = false) Long kupacId,
													   @RequestParam (required = false, defaultValue = "0") int pageNo){
		
		Page<Trebovanje> page = trebovanjeService.search(teren, komercijalistaId, kupacId, pageNo);
		List<Trebovanje> trebovanja = page.getContent();
		for (Trebovanje trebovanje : trebovanja) {
			List<TrebovanjeRobe> trebovanjaRobe = new ArrayList<>();
			for (TrebovanjeRobe trebovanjeRobe : trebovanje.getTrebovanjeRobeList()) {
				if (trebovanjeRobe.isDisponirano()) {
					trebovanjaRobe.add(trebovanjeRobe);
				}
			}
			if (trebovanje.getTrebovanjeRobeList().size() == trebovanjaRobe.size()) {
				trebovanje.setDisponirano(true);
				Trebovanje updatedTrebovanje = trebovanjeService.update(trebovanje);
			}
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("total-pages", Integer.toString(page.getTotalPages()));
		return new ResponseEntity<>(toTrebovanjeDto.convert(page.getContent()), headers, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TrebovanjeDto> getAll (@PathVariable Long id){
		Trebovanje trebovanje = trebovanjeService.findOne(id);
		return new ResponseEntity<>(toTrebovanjeDto.convert(trebovanje), HttpStatus.OK);
	}
	
    @PreAuthorize("permitAll()")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id){
		Trebovanje trebovanje  = trebovanjeService.findOne(id);
		if (trebovanje == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Roba> roba = trebovanje.getRoba();
		for (TrebovanjeRobe trebovanjeRobe : trebovanje.getTrebovanjeRobeList()) {
			Roba roba2 = trebovanjeRobe.getRoba();
			roba2.setStanje(roba2.getStanje() + trebovanjeRobe.getKolicina());
			roba2.getTrebovanja().remove(trebovanje);
			Roba updatedRoba = robaService.update(roba2);
		}
   		
		List<TrebovanjeRobe> trebovanjaRobe = trebovanje.getTrebovanjeRobeList();
		for (TrebovanjeRobe trebovanjeRobe : trebovanjaRobe) {
			trebovanjeRobeService.delete(trebovanjeRobe.getId());
		}
 
		trebovanjeService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PreAuthorize("permitAll()")
		@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<TrebovanjeDto> create (@Valid @RequestBody TrebovanjeDto trebovanjeDto){
			Trebovanje trebovanje = toTrebovanje.convert(trebovanjeDto);
			trebovanje.setDatumTrebovanja(LocalDate.now());
			Trebovanje novoTrebovanje = trebovanjeService.save(trebovanje);
			return new ResponseEntity<>(toTrebovanjeDto.convert(novoTrebovanje), HttpStatus.CREATED);
		}
		
	@PreAuthorize("permitAll()")
 	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<TrebovanjeDto> update (@PathVariable Long id, @RequestBody TrebovanjeRobeDto trebovanjeRobeDto){
			TrebovanjeRobe trebovanjeRobe = toTrebovanjeRobe.convert(trebovanjeRobeDto);
			TrebovanjeRobe novoTrebovanjeRobe = trebovanjeRobeService.save(trebovanjeRobe);
			Trebovanje trebovanje = trebovanjeService.findOne(trebovanjeRobeDto.getTrebovanjeId());
			trebovanje.getTrebovanjeRobeList().add(novoTrebovanjeRobe);
//			if (trebovanjeDto.getId() != id) {
//				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//			}
			Trebovanje izmenjenoTrebovanje = trebovanjeService.update(trebovanje);
			return new ResponseEntity<>(toTrebovanjeDto.convert(izmenjenoTrebovanje), HttpStatus.OK);	
		}
	
	
	
	
	
}
