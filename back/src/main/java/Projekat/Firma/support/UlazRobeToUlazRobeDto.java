package Projekat.Firma.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Projekat.Firma.dto.UlazRobeDto;
import Projekat.Firma.model.UlazRobe;

@Component
public class UlazRobeToUlazRobeDto implements Converter<UlazRobe, UlazRobeDto>{

	@Override
	public UlazRobeDto convert(UlazRobe ur) {
		UlazRobeDto dto = new UlazRobeDto();
		dto.setCenaPoJediniciMere(ur.getCenaPoJediniciMere());
		dto.setId(ur.getId());
		dto.setKolicina(ur.getKolicina());
		dto.setKrajnjaCena(ur.getKrajnjaCena());
		dto.setPdv(ur.getPdv());
		dto.setRabat(ur.getRabat());
		dto.setRobaId(ur.getRoba().getId());
		dto.setUlazId(ur.getUlaz().getId());
		dto.setRobaJedinicaMere(ur.getRoba().getJedinicaMere());
		dto.setRobaNaziv(ur.getRoba().getNaziv());
		dto.setRobaPakovanje(ur.getRoba().getPakovanje());
		dto.setUlazBrojFakture(ur.getUlaz().getBrojFakture());
		return dto;
	}
	
	public List<UlazRobeDto> convert (List<UlazRobe> ulaziRobe){
		List<UlazRobeDto> ulaziRobeDto = new ArrayList<>();
		for (UlazRobe ulazRobe : ulaziRobe) {
			ulaziRobeDto.add(convert(ulazRobe));
		}
		return ulaziRobeDto;
	}

}
