package JWD64.Test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import JWD64.Test.dto.UlazDto;
import JWD64.Test.model.Ulaz;
import JWD64.Test.service.RobaService;

@Component
public class UlazToUlazDto implements Converter<Ulaz, UlazDto>{

	@Autowired
	private RobaToRobaDto toRobaDto;
	
	@Override
	public UlazDto convert(Ulaz u) {
		UlazDto dto = new UlazDto();
		dto.setBrojFakture(u.getBrojFakture());
		dto.setBrojOtpremnice(u.getBrojOtpremnice());
//		dto.setCenaPoJediniciMere(u.getCenaPoJediniciMere());
		dto.setDatumUlaza(u.getDatumUlaza().toString());
		dto.setId(u.getId());
//		dto.setKolicina(u.getKolicina());
		dto.setProizvodjacId(u.getProizvodjac().getId());
		dto.setProizvodjacNaziv(u.getProizvodjac().getNaziv());
//		dto.setRabat(u.getRabat());
		if (!u.getRoba().isEmpty()) {
			dto.setRoba(toRobaDto.convert(u.getRoba()));
		}
		return dto;
	}
	
	public List<UlazDto> convert (List<Ulaz> ulazi){
		List<UlazDto> ulaziDto = new ArrayList<>();
		for (Ulaz ulaz : ulazi) {
			ulaziDto.add(convert(ulaz));
		}
		return ulaziDto;
	}

	
}
