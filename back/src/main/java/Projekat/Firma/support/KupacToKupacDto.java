package Projekat.Firma.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Projekat.Firma.dto.KupacDto;
import Projekat.Firma.model.Kupac;

@Component
public class KupacToKupacDto implements Converter<Kupac, KupacDto>{

	@Override
	public KupacDto convert(Kupac k) {
		KupacDto dto = new KupacDto();
		dto.setAdresa(k.getAdresa());
		dto.setGrad(k.getGrad());
		dto.setId(k.getId());
		dto.setKomercijalistaId(k.getKomercijalista().getId());
		dto.setKomercijalistaImeIPrezime(k.getKomercijalista().getIme() + " " + k.getKomercijalista().getPrezime());
		dto.setMb(k.getMb());
		dto.setNaziv(k.getNaziv());
		dto.setPib(k.getPib());
		dto.setTeren(k.getTeren());
		return dto; 
	}
	
	public List<KupacDto> convert (List<Kupac> kupci) {
		List<KupacDto> kupciDto = new ArrayList<>();
		for (Kupac kupac : kupci) {
			kupciDto.add(convert(kupac));
		}
		return kupciDto;
	}
	
	

	
}
