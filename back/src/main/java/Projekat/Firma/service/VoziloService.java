package Projekat.Firma.service;

import java.util.List;

import Projekat.Firma.model.Vozilo;

public interface VoziloService {

	Vozilo findOne (Long id);
	List<Vozilo> findAll();
	Vozilo save (Vozilo vozilo);
	Vozilo update (Vozilo vozilo);
	void delete (Long id);
	
}
