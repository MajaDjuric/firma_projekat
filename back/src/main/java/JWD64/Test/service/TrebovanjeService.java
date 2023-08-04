package JWD64.Test.service;

import java.util.List;

import org.springframework.data.domain.Page;

import JWD64.Test.model.Trebovanje;

public interface TrebovanjeService {

	Trebovanje findOne (Long id);
	List<Trebovanje> findAll ();
	Trebovanje save (Trebovanje trebovanje);
	Trebovanje update (Trebovanje trebovanje);
	void delete (Long id);
	Page<Trebovanje> search (String teren, Long komercijalistaId, Long kupacId, int pageNo);
	List<Trebovanje> findByIdIn (List<Long> ids);

}
