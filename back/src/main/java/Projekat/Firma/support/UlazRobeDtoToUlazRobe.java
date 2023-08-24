package Projekat.Firma.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Projekat.Firma.dto.UlazRobeDto;
import Projekat.Firma.model.UlazRobe;
import Projekat.Firma.service.RobaService;
import Projekat.Firma.service.UlazRobeService;
import Projekat.Firma.service.UlazService;

@Component
public class UlazRobeDtoToUlazRobe implements Converter<UlazRobeDto, UlazRobe> {

	@Autowired
	private UlazRobeService ulazRobeService;
	
	@Autowired
	private RobaService robaService;
	
	@Autowired
	private UlazService ulazService;
	
	@Override
	public UlazRobe convert(UlazRobeDto dto) {
		UlazRobe ur;
		if (dto.getId() == null) {
			ur = new UlazRobe();
		}else {
			ur = ulazRobeService.findOne(dto.getId());
		}
		ur.setId(dto.getId());
		ur.setCenaPoJediniciMere(dto.getCenaPoJediniciMere());
		ur.setKolicina(dto.getKolicina());
		ur.setKrajnjaCena(dto.getKrajnjaCena());
		ur.setPdv(dto.getPdv());
		ur.setRabat(dto.getRabat());
		ur.setRoba(robaService.findOne(dto.getRobaId()));
		ur.setUlaz(ulazService.findOne(dto.getUlazId()));
		return ur;
	}

	
}
