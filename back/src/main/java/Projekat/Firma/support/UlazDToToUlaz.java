package Projekat.Firma.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Projekat.Firma.dto.RobaDto;
import Projekat.Firma.dto.UlazDto;
import Projekat.Firma.model.Roba;
import Projekat.Firma.model.Ulaz;
import Projekat.Firma.service.ProizvodjacService;
import Projekat.Firma.service.RobaService;
import Projekat.Firma.service.UlazService;

@Component
public class UlazDToToUlaz implements Converter<UlazDto, Ulaz> {

	@Autowired
	private UlazService ulazService;
	
	@Autowired
	private ProizvodjacService proizvodjacService;
	
	@Autowired
	private RobaService robaService;
	
	@Autowired
	private RobaDtoToRoba toRoba;
	
	@Override
	public Ulaz convert(UlazDto dto) {
		Ulaz u;
		if (dto.getId() == null) {
			u = new Ulaz();
		}else {
			u = ulazService.findOne(dto.getId());
		}
		if (u != null) {
			u.setBrojFakture(dto.getBrojFakture());
			u.setBrojOtpremnice(dto.getBrojOtpremnice());
//			u.setCenaPoJediniciMere(dto.getCenaPoJediniciMere());
			u.setDatumUlaza(getLocalDate(dto.getDatumUlaza()));
			u.setId(dto.getId());
//			u.setKolicina(dto.getKolicina());
			u.setProizvodjac(proizvodjacService.findOne(dto.getProizvodjacId()));
//			u.setRabat(dto.getRabat());
			List<RobaDto> robaDto = dto.getRoba();
			List<Long> ids = new ArrayList<>();
			for (RobaDto roba1 : robaDto) {
				ids.add(roba1.getId());
			}
			if (!dto.getRoba().isEmpty()) {
				List<Roba> roba = robaService.findByIdIn(ids);
				u.setRoba(roba);
			}
		}
		return u;
	}
	
	public LocalDate getLocalDate (String datunString) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate datum = LocalDate.parse(datunString, dtf);
		return datum;
	}

	
}
