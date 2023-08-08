package Projekat.Firma.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Projekat.Firma.dto.VoziloDto;
import Projekat.Firma.model.Vozilo;
import Projekat.Firma.service.VoziloService;

@Component
public class VoziloDtoToVozilo implements Converter<VoziloDto, Vozilo>{

	@Autowired
	private VoziloService voziloService;
	
	@Override
	public Vozilo convert(VoziloDto dto) {
		Vozilo v;
		if (dto.getId() == null) {
			v = new Vozilo();
		}else {
			v = voziloService.findOne(dto.getId());
		}
		if (v != null) {
			v.setId(dto.getId());
			v.setMarkaITip(dto.getMarkaITip());
			v.setRegistracija(dto.getRegistracija());
		}
		return v;
	}

	
}
