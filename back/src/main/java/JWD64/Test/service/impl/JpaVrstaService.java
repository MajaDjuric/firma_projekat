package JWD64.Test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JWD64.Test.model.VrstaRobe;
import JWD64.Test.repository.VrstaRepository;
import JWD64.Test.service.VrstaService;

@Service
public class JpaVrstaService implements VrstaService {

	@Autowired
	private VrstaRepository vrstaRepository;

	@Override
	public VrstaRobe findOne(Long id) {
		return vrstaRepository.findOneById(id);
	}

	@Override
	public List<VrstaRobe> findAll() {
		return vrstaRepository.findAll();
	}
	
	
}
