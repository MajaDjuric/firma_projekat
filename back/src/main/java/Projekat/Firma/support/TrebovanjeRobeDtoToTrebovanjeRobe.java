package Projekat.Firma.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Projekat.Firma.dto.TrebovanjeRobeDto;
import Projekat.Firma.model.TrebovanjeRobe;
import Projekat.Firma.service.RobaService;
import Projekat.Firma.service.TrebovanjeRobeService;
import Projekat.Firma.service.TrebovanjeService;

@Component
public class TrebovanjeRobeDtoToTrebovanjeRobe implements Converter<TrebovanjeRobeDto, TrebovanjeRobe> {

	@Autowired
	private TrebovanjeRobeService trebovanjeRobeService;
	
	@Autowired
	private RobaService robaService;
	
	@Autowired
	private TrebovanjeService trebovanjeService;
	
	@Override
	public TrebovanjeRobe convert(TrebovanjeRobeDto dto) {
		TrebovanjeRobe tr;
		if (dto.getId() == null) {
			tr = new TrebovanjeRobe();
		}else {
			tr = trebovanjeRobeService.findOne(dto.getId());
		}
		if (tr != null) {
			tr.setId(dto.getId());
			tr.setKolicina(dto.getKolicina());
			tr.setRoba(robaService.findOne(dto.getRobaId()));
			tr.setTrebovanje(trebovanjeService.findOne(dto.getTrebovanjeId()));
		}
		return tr;
	}

}
