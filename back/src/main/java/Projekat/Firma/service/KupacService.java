package Projekat.Firma.service;

import java.util.List;

import Projekat.Firma.model.Kupac;

public interface KupacService {

	Kupac findOne (Long id);
	List<Kupac> findAll();
	Kupac save (Kupac kupac);
	Kupac update (Kupac kupac);
	void delete (Long id);
	List<Kupac> findByKomercijalistaId (Long id);

	
}
