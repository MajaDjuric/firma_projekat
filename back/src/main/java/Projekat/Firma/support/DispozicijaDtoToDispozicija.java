package Projekat.Firma.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Projekat.Firma.dto.DispozicijaDto;
import Projekat.Firma.model.Dispozicija;
import Projekat.Firma.model.TrebovanjeRobe;
import Projekat.Firma.model.Vozilo;
import Projekat.Firma.service.DispozicijaService;
import Projekat.Firma.service.KorisnikService;
import Projekat.Firma.service.TrebovanjeRobeService;
import Projekat.Firma.service.VoziloService;

@Component
public class DispozicijaDtoToDispozicija implements Converter<DispozicijaDto, Dispozicija>{

	@Autowired
	private DispozicijaService dispozicijaService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private VoziloDtoToVozilo toVozilo;
	
	@Autowired
	private VoziloService voziloService;
	
	@Autowired
	private TrebovanjeRobeService trebovanjeRobeService;
	
	
	@Override
	public Dispozicija convert(DispozicijaDto dto) {
		Dispozicija d;
		if (dto.getId() == null) {
			d = new Dispozicija();
		}else {
			d = dispozicijaService.findOne(dto.getId());
		}
		if (d != null) {
			d.setDatumIsporuke(getLocalDate(dto.getDatumIsporuke()));
			d.setId(dto.getId());
			d.setVozac(korisnikService.findOne(dto.getVozacId()).get());
			Vozilo vozilo = voziloService.findOne(dto.getVozilo().getId());
			d.setVozilo(vozilo);
//			List<Long> ids = dto.getTrebovanjaIds();
//			List<TrebovanjeRobe> trebovanjaRobe = trebovanjeRobeService.findByIdIn(ids);
//			d.setTrebovanjaRobe(trebovanjaRobe);
		}
		return d;
	}

	
	public LocalDate getLocalDate (String datunString) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate datum = LocalDate.parse(datunString, dtf);
		return datum;
	}

}
