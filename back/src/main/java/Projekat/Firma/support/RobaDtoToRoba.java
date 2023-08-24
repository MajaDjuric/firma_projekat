package Projekat.Firma.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Projekat.Firma.dto.RobaDto;
import Projekat.Firma.model.Roba;
import Projekat.Firma.service.ProizvodjacService;
import Projekat.Firma.service.RobaService;
import Projekat.Firma.service.TipService;
import Projekat.Firma.service.VrstaService;

@Component
public class RobaDtoToRoba implements Converter<RobaDto, Roba> {

	@Autowired
	private RobaService robaService;
	
	@Autowired
	private VrstaService vrstaService;
	
	@Autowired
	private TipService tipService;

	@Autowired
	private ProizvodjacService proizvodjacService;
	
	@Override
	public Roba convert(RobaDto dto) {
		Roba roba;
		if (dto.getId() == null) {
			roba = new Roba();
		}else {
			roba = robaService.findOne(dto.getId());
		}
		if (roba != null) {
			if (dto.getDatumProizvodnje() != null) {
				roba.setDatumProizvodnje(getLocalDate(dto.getDatumProizvodnje()));
			}
			roba.setId(dto.getId());
			roba.setIzlaz(dto.getIzlaz());
			roba.setJedinicaMere(dto.getJedinicaMere());
			roba.setNaziv(dto.getNaziv());
			roba.setPakovanje(dto.getPakovanje());
			roba.setProdajnaCena(dto.getProdajnaCena());
			roba.setProizvodjac(proizvodjacService.findOne(dto.getProizvodjacId()));
			roba.setRokTrajanja(dto.getRokTrajanja());
			roba.setStanje(dto.getStanje());
			if (dto.getTretman() != null) {
				roba.setTretman(dto.getTretman());
			}
			roba.setUlaz(dto.getUlaz());
			roba.setTip(tipService.findOne(dto.getTipId()));
			roba.setVrsta(vrstaService.findOne(dto.getVrstaId()));
		}
		return roba;
	}
	
	
	public LocalDate getLocalDate (String datunString) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate datum = LocalDate.parse(datunString, dtf);
		return datum;
	}
	
}
