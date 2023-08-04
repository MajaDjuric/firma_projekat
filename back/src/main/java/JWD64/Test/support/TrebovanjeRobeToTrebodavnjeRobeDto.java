package JWD64.Test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import JWD64.Test.dto.TrebovanjeRobeDto;
import JWD64.Test.model.TrebovanjeRobe;

@Component
public class TrebovanjeRobeToTrebodavnjeRobeDto implements Converter<TrebovanjeRobe, TrebovanjeRobeDto> {

	@Autowired
	private KupacToKupacDto toKupacDto;
	
	@Override
	public TrebovanjeRobeDto convert(TrebovanjeRobe tr) {
		TrebovanjeRobeDto dto = new TrebovanjeRobeDto();
		dto.setId(tr.getId());
		dto.setKolicina(tr.getKolicina());
		dto.setRobaId(tr.getRoba().getId());
		dto.setRobaJedinicaMere(tr.getRoba().getJedinicaMere());
		dto.setRobaNaziv(tr.getRoba().getNaziv());
		dto.setRobaPakovanje(tr.getRoba().getPakovanje());
		dto.setTrebovanjeId(tr.getTrebovanje().getId());
		dto.setDisponirano(tr.isDisponirano());
		dto.setIsporuceno(tr.isIsporuceno());
		dto.setKupac(toKupacDto.convert(tr.getTrebovanje().getKupac()));
//		dto.setKomercijalistaTeren(tr.getTrebovanje().getKomercijalista().getTeren());
//		dto.setKupacTeren(tr.getTrebovanje().getKupac().getTeren());
//		dto.setKupacGrad(tr.getTrebovanje().getKupac().getGrad());
//		dto.setKupacAdresa(tr.getTrebovanje().getKupac().getAdresa());
//		dto.setKupacNaziv(tr.getTrebovanje().getKupac().getNaziv());
//		dto.setKomercijalistaIme(tr.getTrebovanje().getKomercijalista().getIme());
//		dto.setKomercijalistaPrezime(tr.getTrebovanje().getKomercijalista().getPrezime());
		return dto;
	}
	
	public List<TrebovanjeRobeDto> convert (List<TrebovanjeRobe> trebovanjaRobe){
		List<TrebovanjeRobeDto> trebovanjaRobeDto = new ArrayList<>();
		for (TrebovanjeRobe trebovanjeRobe : trebovanjaRobe) {
			trebovanjaRobeDto.add(convert(trebovanjeRobe));
		}
		return trebovanjaRobeDto;
	}

	
}
