package Projekat.Firma.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Projekat.Firma.dto.VoziloDto;
import Projekat.Firma.model.Vozilo;

@Component
public class VoziloToVoziloDto implements Converter<Vozilo, VoziloDto> {

	@Override
	public VoziloDto convert(Vozilo v) {
		VoziloDto dto = new VoziloDto();
		dto.setId(v.getId());
		dto.setMarkaITip(v.getMarkaITip());
		dto.setRegistracija(v.getRegistracija());
		return dto;
	}
	
	public List<VoziloDto> convert (List<Vozilo> vozila){
		List<VoziloDto> vozilaDto = new ArrayList<>();
		for (Vozilo vozilo : vozila) {
			vozilaDto.add(convert(vozilo));
		}
		return vozilaDto;
	}

	
	
}
