package JWD64.Test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JWD64.Test.model.TipRobe;
import JWD64.Test.repository.TipRepository;
import JWD64.Test.service.TipService;

@Service
public class JpaTipService implements TipService {

	@Autowired
	private TipRepository tipRepository;

	@Override
	public TipRobe findOne(Long id) {
		return tipRepository.findOneById(id);
	}

	@Override
	public List<TipRobe> findByVrstaId(Long id) {
		return tipRepository.findByVrstaId(id);
	}
	
	
	
}
