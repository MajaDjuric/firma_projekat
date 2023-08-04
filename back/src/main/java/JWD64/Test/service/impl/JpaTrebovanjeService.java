package JWD64.Test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import JWD64.Test.model.Trebovanje;
import JWD64.Test.repository.TrebovanjeRepository;
import JWD64.Test.service.TrebovanjeService;

@Service
public class JpaTrebovanjeService implements TrebovanjeService {

	@Autowired
	private TrebovanjeRepository trebovanjeRepository;
	
	@Override
	public Trebovanje findOne(Long id) {
		return trebovanjeRepository.findOneById(id);
	}

	@Override
	public Trebovanje save(Trebovanje trebovanje) {
		return trebovanjeRepository.save(trebovanje);
	}

	@Override
	public List<Trebovanje> findAll() {
		return trebovanjeRepository.findAll();
	}

	@Override
	public Page<Trebovanje> search(String teren, Long komercijalistaId, Long kupacId, int pageNo) {
		return trebovanjeRepository.search(teren, komercijalistaId, kupacId, PageRequest.of(pageNo, 10));
	}

	@Override
	public Trebovanje update(Trebovanje trebovanje) {
		return trebovanjeRepository.save(trebovanje);
	}

	@Override
	public void delete(Long id) {
		trebovanjeRepository.deleteById(id);
	}

	@Override
	public List<Trebovanje> findByIdIn(List<Long> ids) {
		return trebovanjeRepository.findByIdIn(ids);
	}

}
