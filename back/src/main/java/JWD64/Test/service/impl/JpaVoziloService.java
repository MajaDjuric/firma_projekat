package JWD64.Test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JWD64.Test.model.Vozilo;
import JWD64.Test.repository.VoziloRepository;
import JWD64.Test.service.VoziloService;

@Service
public class JpaVoziloService implements VoziloService {

	@Autowired
	private VoziloRepository voziloRepository;
	
	@Override
	public List<Vozilo> findAll() {
		return voziloRepository.findAll();
	}

	@Override
	public Vozilo findOne(Long id) {
		return voziloRepository.findOneById(id);
	}

	@Override
	public Vozilo save(Vozilo vozilo) {
		return voziloRepository.save(vozilo);
	}

	@Override
	public Vozilo update(Vozilo vozilo) {
		return voziloRepository.save(vozilo);
	}

	@Override
	public void delete(Long id) {
		voziloRepository.deleteById(id);
	}

	
}
