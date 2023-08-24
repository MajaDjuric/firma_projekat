package Projekat.Firma.service;

import java.util.List;

import Projekat.Firma.model.UlazRobe;

public interface UlazRobeService {

	UlazRobe findOne (Long id);
	List<UlazRobe> findOneByUlazId (Long id);
	List<UlazRobe> findAll ();
	UlazRobe save (UlazRobe ulazRobe);
	UlazRobe update (UlazRobe ulazRobe);
	void delete (Long id);
}
