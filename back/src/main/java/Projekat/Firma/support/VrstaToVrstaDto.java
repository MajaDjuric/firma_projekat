package Projekat.Firma.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Projekat.Firma.dto.VrstaRobeDto;
import Projekat.Firma.model.VrstaRobe;

@Component
public class VrstaToVrstaDto implements Converter<VrstaRobe, VrstaRobeDto> {

	@Override
	public VrstaRobeDto convert(VrstaRobe vrsta) {
		VrstaRobeDto dto = new VrstaRobeDto();
		dto.setId(vrsta.getId());
		dto.setNaziv(vrsta.getNaziv());
		dto.setPdv(vrsta.getPdv());
		return dto;
	}
	
	public List<VrstaRobeDto> convert (List<VrstaRobe> vrste){
		List<VrstaRobeDto> vrsteDto = new ArrayList<>();
		for (VrstaRobe vrstaRobe : vrste) {
			vrsteDto.add(convert(vrstaRobe));
		}
		return vrsteDto;
	}

	
}
