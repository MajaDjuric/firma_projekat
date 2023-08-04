package JWD64.Test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import JWD64.Test.dto.TrebovanjeRobeDto;
import JWD64.Test.model.TrebovanjeRobe;
import JWD64.Test.service.RobaService;
import JWD64.Test.service.TrebovanjeRobeService;
import JWD64.Test.service.TrebovanjeService;

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
