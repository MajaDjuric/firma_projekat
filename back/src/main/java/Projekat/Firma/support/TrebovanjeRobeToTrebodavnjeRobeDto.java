package Projekat.Firma.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Projekat.Firma.dto.TrebovanjeRobeDto;
import Projekat.Firma.model.TrebovanjeRobe;

@Component
public class TrebovanjeRobeToTrebodavnjeRobeDto implements Converter<TrebovanjeRobe, TrebovanjeRobeDto> {

	@Autowired
	private KupacToKupacDto toKupacDto;
	
	@Override
	public TrebovanjeRobeDto convert(TrebovanjeRobe tr) {
		TrebovanjeRobeDto dto = new TrebovanjeRobeDto();
		dto.setId(tr.getId());
		dto.setKolicina(tr.getKolicina());
		dto.setRobaId(tr.getRoba().getId());
		dto.setRobaJedinicaMere(tr.getRoba().getJedinicaMere());
		dto.setRobaNaziv(tr.getRoba().getNaziv());
		dto.setRobaPakovanje(tr.getRoba().getPakovanje());
		dto.setTrebovanjeId(tr.getTrebovanje().getId());
		dto.setDisponirano(tr.isDisponirano());
		dto.setIsporuceno(tr.isIsporuceno());
		dto.setKupac(toKupacDto.convert(tr.getTrebovanje().getKupac()));
		if (tr.getDispozicija() != null) {
			dto.setDispozicijaId(tr.getDispozicija().getId());
		}
		return dto;
	}
	
	public List<TrebovanjeRobeDto> convert (List<TrebovanjeRobe> trebovanjaRobe){
		List<TrebovanjeRobeDto> trebovanjaRobeDto = new ArrayList<>();
		for (TrebovanjeRobe trebovanjeRobe : trebovanjaRobe) {
			trebovanjaRobeDto.add(convert(trebovanjeRobe));
		}
		return trebovanjaRobeDto;
	}

	
}
