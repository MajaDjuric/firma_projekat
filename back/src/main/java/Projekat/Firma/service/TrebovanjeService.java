package Projekat.Firma.service;

import java.util.List;

import org.springframework.data.domain.Page;

import Projekat.Firma.model.Trebovanje;

public interface TrebovanjeService {

	Trebovanje findOne (Long id);
	List<Trebovanje> findAll ();
	Trebovanje save (Trebovanje trebovanje);
	Trebovanje update (Trebovanje trebovanje);
	void delete (Long id);
	Page<Trebovanje> searchWithId (String teren,Long komercijalistaId, Long kupacId,  Long robaId, int pageNo);
	Page<Trebovanje> search (String teren, Long kupacId, Long robaId, int pageNo);
	List<Trebovanje> findByIdIn (List<Long> ids);

}
