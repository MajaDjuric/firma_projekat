package Projekat.Firma.service;

import java.util.List;

import Projekat.Firma.model.VrstaRobe;

public interface VrstaService {

	VrstaRobe findOne (Long id);
	List<VrstaRobe> findAll ();
	VrstaRobe findByNaziv (String naziv);

}
