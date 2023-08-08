package Projekat.Firma.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Projekat.Firma.dto.TipDto;
import Projekat.Firma.model.TipRobe;

@Component
public class TipToTipDto implements Converter<TipRobe, TipDto> {

	@Override
	public TipDto convert(TipRobe tip) {
		TipDto dto = new TipDto();
		dto.setId(tip.getId());
		dto.setNaziv(tip.getNaziv());
		return dto;
	}
	
	public List<TipDto> convert (List<TipRobe> tipovi){
		List<TipDto> tipoviDto = new ArrayList<>();
		for (TipRobe tip : tipovi) {
			tipoviDto.add(convert(tip));
		}
		return tipoviDto;
	}

}
