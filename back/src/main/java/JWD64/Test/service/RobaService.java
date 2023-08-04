package JWD64.Test.service;

import java.util.List;

import org.springframework.data.domain.Page;

import JWD64.Test.model.Roba;

public interface RobaService {

	Roba findOne (Long id);
	Page<Roba> search (String naziv, Long proizvodjacId, Double pakovanje, String tretman, Long vrstaId, int pageNo);
	Roba save (Roba roba);
	Roba update (Roba roba);
	void delete (Long id);
	Page<Roba> findAll (int pageNo);
	List<Roba> findByIdIn (List<Long> ids);
    List<Roba> findByUlaziId (Long id);

}
