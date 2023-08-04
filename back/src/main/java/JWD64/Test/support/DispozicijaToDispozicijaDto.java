package JWD64.Test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import JWD64.Test.dto.DispozicijaDto;
import JWD64.Test.model.Dispozicija;
import JWD64.Test.model.TrebovanjeRobe;
import JWD64.Test.service.TrebovanjeRobeService;
import JWD64.Test.service.VoziloService;

@Component
public class DispozicijaToDispozicijaDto implements Converter<Dispozicija, DispozicijaDto>{

	@Autowired
	private VoziloToVoziloDto toVoziloDto;
	
	@Autowired
	private TrebovanjeRobeToTrebodavnjeRobeDto toTrebovanjeRobeDto;
	
	@Autowired
	private TrebovanjeRobeService trebovanjeRobeService;
	
	@Autowired
	private VoziloService voziloService;
	
	@Override
	public DispozicijaDto convert(Dispozicija d) {
		DispozicijaDto dto = new DispozicijaDto();
		dto.setDatumIsporuke(d.getDatumIsporuke().toString());
		dto.setId(d.getId());
		dto.setIsporuceno(d.isIsporuceno());
		dto.setVozacId(d.getVozac().getId());
		dto.setVozacImeIPrezime(d.getVozac().getIme() + " " + d.getVozac().getPrezime() );
		dto.setVozilo(toVoziloDto.convert(d.getVozilo()));
		List<TrebovanjeRobe> trebovanjaRobe  = new ArrayList<>();
		for (TrebovanjeRobe trebovanjeRobe: d.getTrebovanjaRobe()) {
			TrebovanjeRobe trebovanjeRobe1 = trebovanjeRobeService.findOne(trebovanjeRobe.getId());
			trebovanjaRobe.add(trebovanjeRobe1);
		}
		dto.setTrebovanjaRobe(toTrebovanjeRobeDto.convert(trebovanjaRobe));
		return dto;
	}
	
	public List<DispozicijaDto> convert (List<Dispozicija> dispozicije){
		List<DispozicijaDto> dispozicijeDto = new ArrayList<>();
		for (Dispozicija dispozicija : dispozicije) {
			dispozicijeDto.add(convert(dispozicija));
		}
		return dispozicijeDto;
	}

}
