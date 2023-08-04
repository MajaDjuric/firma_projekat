package JWD64.Test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import JWD64.Test.dto.TrebovanjeDto;
import JWD64.Test.model.Kupac;
import JWD64.Test.model.Roba;
import JWD64.Test.model.Trebovanje;
import JWD64.Test.service.KupacService;

@Component
public class TrebovanjeToTrebovanjeDto implements Converter<Trebovanje, TrebovanjeDto> {

	@Autowired
	private KupacService kupacService;
	
	@Autowired
	private KupacToKupacDto toKupacDto;
	
	@Override
	public TrebovanjeDto convert(Trebovanje tr) {
		TrebovanjeDto dto = new TrebovanjeDto();
		dto.setId(tr.getId());
		dto.setKomercijalistaId(tr.getKomercijalista().getId());
		dto.setKomercijalistaIme(tr.getKomercijalista().getIme());
		dto.setKomercijlistaPrezime(tr.getKomercijalista().getPrezime());
		Kupac kupac = kupacService.findOne(tr.getKupac().getId());
		dto.setKupacDto(toKupacDto.convert(kupac));
		dto.setDisponirano(tr.isDisponirano());
		dto.setIsporuceno(tr.isIsporuceno());
		dto.setDatumTrebovanja(tr.getDatumTrebovanja().toString());
//		List<Roba> roba = tr.getRoba();
//		List<Long> ids = new ArrayList<>();
//		for (Roba proizvod : roba) {
//			ids.add(proizvod.getId());
//		}
//		dto.setRobaIds(ids);
		return dto;
	}
	
	public List<TrebovanjeDto> convert (List<Trebovanje> trebovanja) {
		List<TrebovanjeDto> trebovanjaDto = new ArrayList<>();
		for (Trebovanje trebovanje : trebovanja) {
			trebovanjaDto.add(convert(trebovanje));
		}
		return trebovanjaDto;
	}

	
}
