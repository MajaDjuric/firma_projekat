package Projekat.Firma.service;

import java.util.List;

import Projekat.Firma.model.Proizvodjac;

public interface ProizvodjacService {

	Proizvodjac findOne (Long id);
	List<Proizvodjac> findAll ();
	
}
