package Projekat.Firma.service;

import java.util.List;

import Projekat.Firma.model.TipRobe;

public interface TipService {

	TipRobe findOne (Long id);
	List<TipRobe> findByVrstaId (Long id);

}
