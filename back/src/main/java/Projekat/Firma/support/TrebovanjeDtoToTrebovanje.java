package Projekat.Firma.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Projekat.Firma.dto.TrebovanjeDto;
import Projekat.Firma.model.Korisnik;
import Projekat.Firma.model.Roba;
import Projekat.Firma.model.Trebovanje;
import Projekat.Firma.service.KorisnikService;
import Projekat.Firma.service.KupacService;
import Projekat.Firma.service.RobaService;
import Projekat.Firma.service.TrebovanjeService;

@Component
public class TrebovanjeDtoToTrebovanje implements Converter<TrebovanjeDto, Trebovanje>{

	@Autowired
	private TrebovanjeService trebovanjeService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private KupacService kupacService;
	
	@Autowired
	private RobaService robaService;
	
	@Override
	public Trebovanje convert(TrebovanjeDto dto) {
		Trebovanje tr;
		if (dto.getId() == null) {
			tr = new Trebovanje();
		}else {
			tr = trebovanjeService.findOne(dto.getId());
		}
		if (tr != null) {
			tr.setId(dto.getId());
			Korisnik komercijalista = null;
			if (dto.getKomercijalistaId() != null) {
				komercijalista = korisnikService.findOne(dto.getKomercijalistaId()).get();
				tr.setKomercijalista(komercijalista);
			}
			tr.setKupac(kupacService.findOne(dto.getKupacDto().getId()));
//			List<Roba> roba = robaService.findByIdIn(dto.getRobaIds());
//			tr.setRoba(roba);
		}
		return tr;
	}
	
	
	public List<Trebovanje> convert (List<TrebovanjeDto> trebovanjaDto){
		List<Trebovanje> trebovanja = new ArrayList<>();
		for (TrebovanjeDto trebovanjeDto : trebovanjaDto) {
			trebovanja.add(convert(trebovanjeDto));
		}
		return trebovanja;
	}
}
