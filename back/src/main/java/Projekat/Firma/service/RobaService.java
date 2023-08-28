package Projekat.Firma.service;

import java.util.List;

import org.springframework.data.domain.Page;

import Projekat.Firma.model.Roba;
import Projekat.Firma.model.VrstaRobe;

public interface RobaService {

	Roba findOne (Long id);
	Page<Roba> search (VrstaRobe vrsta, String naziv, Long proizvodjacId, Double pakovanje, String tretman, int pageNo);
	Roba save (Roba roba);
	Roba update (Roba roba);
	void delete (Long id);
	Page<Roba> findAll (int pageNo);
	List<Roba> findByIdIn (List<Long> ids);
    List<Roba> findByUlaziId (Long id);

}
