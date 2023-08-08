package Projekat.Firma.service;

import java.util.List;

import org.springframework.data.domain.Page;

import Projekat.Firma.model.TrebovanjeRobe;

public interface TrebovanjeRobeService {

	TrebovanjeRobe findOne (Long id);
	Page<TrebovanjeRobe> search (String grad, String teren, String komercijalistaIme, String kupacIme, Long robaId, int pageNo);
	TrebovanjeRobe save (TrebovanjeRobe tr);
	TrebovanjeRobe update (TrebovanjeRobe tr);
	void delete (Long id);
	List<TrebovanjeRobe> findByTrebovanjeId (Long id);
	List<TrebovanjeRobe> findByIdIn (List<Long> ids);
	List<TrebovanjeRobe> findByDispozicijaId (Long id);
	
}
