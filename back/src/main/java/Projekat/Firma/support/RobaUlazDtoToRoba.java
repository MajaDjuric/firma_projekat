package Projekat.Firma.support;
//package JWD64.Test.support;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import JWD64.Test.dto.RobaUlazDto;
//import JWD64.Test.model.Roba;
//import JWD64.Test.service.RobaService;
//
//@Component
//public class RobaUlazDtoToRoba implements Converter<RobaUlazDto, Roba> {
//
//	@Autowired
//	private RobaService robaService;
//	
//	@Override
//	public Roba convert(RobaUlazDto dto) {
//		Roba r;
//		if (dto.getRoba().getId() == null) {
//			r = new Roba();
//		}else {
//			r = robaService.findOne(dto.getRoba().getId());
//		}
//		if(r != null) {
//			r.se
//		}
//		return null;
//	}
//
//	
//}
