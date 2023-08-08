package Projekat.Firma.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Projekat.Firma.dto.KupacDto;
import Projekat.Firma.model.Kupac;
import Projekat.Firma.service.KorisnikService;
import Projekat.Firma.service.KupacService;

@Component
public class KupacDtoToKupac implements Converter<KupacDto, Kupac> {

	@Autowired
	private KupacService kupacService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Override
	public Kupac convert(KupacDto dto) {
		Kupac k;
		if (dto.getId() == null) {
			k = new Kupac();
		}else {
			k = kupacService.findOne(dto.getId());
		}
		if (k != null) {
			k.setId(dto.getId());
			k.setAdresa(dto.getAdresa());
			k.setGrad(dto.getGrad());
			k.setKomercijalista(korisnikService.findOne(dto.getKomercijalistaId()).get());
			k.setMb(dto.getMb());
			k.setNaziv(dto.getNaziv());
			k.setPib(dto.getPib());
			k.setTeren(dto.getTeren());
		}
		return k;
	}

}
