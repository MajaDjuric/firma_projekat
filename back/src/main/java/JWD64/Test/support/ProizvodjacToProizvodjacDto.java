package JWD64.Test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import JWD64.Test.dto.ProizvodjacDto;
import JWD64.Test.model.Proizvodjac;

@Component
public class ProizvodjacToProizvodjacDto implements Converter<Proizvodjac, ProizvodjacDto> {

	@Override
	public ProizvodjacDto convert(Proizvodjac p) {
		ProizvodjacDto dto = new ProizvodjacDto();
		dto.setId(p.getId());
		dto.setMb(p.getMb());
		dto.setNaziv(p.getNaziv());
		dto.setPib(p.getPib());
		return dto;
	}
	
	public List<ProizvodjacDto> convert (List<Proizvodjac> proizvodjaci){
		List<ProizvodjacDto> proizvodjaciDto = new ArrayList<>();
		for (Proizvodjac proizvodjac : proizvodjaci) {
			proizvodjaciDto.add(convert(proizvodjac));
		}
		return proizvodjaciDto;
	}

}
