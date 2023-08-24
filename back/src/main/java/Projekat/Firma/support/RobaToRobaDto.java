package Projekat.Firma.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Projekat.Firma.dto.RobaDto;
import Projekat.Firma.model.Roba;

@Component
public class RobaToRobaDto implements Converter<Roba, RobaDto> {

	@Override
	public RobaDto convert(Roba roba) {
		RobaDto dto = new RobaDto();
		if (roba.getDatumProizvodnje() != null) {
			dto.setDatumProizvodnje(roba.getDatumProizvodnje().toString());
		}
		dto.setId(roba.getId());
		dto.setIzlaz(roba.getIzlaz());
		dto.setJedinicaMere(roba.getJedinicaMere());
		dto.setNaziv(roba.getNaziv());
		dto.setPakovanje(roba.getPakovanje());
		dto.setProdajnaCena(roba.getProdajnaCena());
		dto.setProizvodjacId(roba.getProizvodjac().getId());
		dto.setProizvodjacNaziv(roba.getProizvodjac().getNaziv());
		dto.setRokTrajanja(roba.getRokTrajanja());
		dto.setTipId(roba.getTip().getId());
		dto.setTipNaziv(roba.getTip().getNaziv());
		if (roba.getTretman() != null) {
			dto.setTretman(roba.getTretman());
		}
		dto.setUlaz(roba.getUlaz());
		dto.setVrstaId(roba.getVrsta().getId());
		dto.setVrstaNaziv(roba.getVrsta().getNaziv());
		dto.setStanje(roba.getStanje());
		return dto;
	}

	public List<RobaDto> convert (List<Roba> roba){
		List<RobaDto> robaDto = new ArrayList<>();
		for (Roba roba1 : roba) {
			robaDto.add(convert(roba1));
		}
		return robaDto;
	}
	
}
