package JWD64.Test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JWD64.Test.model.Kupac;
import JWD64.Test.repository.KupacRepository;
import JWD64.Test.service.KupacService;

@Service
public class JpaKupacService implements KupacService {
 
	@Autowired
	private KupacRepository kupacRepository;

	@Override
	public Kupac findOne(Long id) {
		return kupacRepository.findOneById(id);
	}

	@Override
	public List<Kupac> findAll() {
		return kupacRepository.findAll();
	}

	@Override
	public Kupac save(Kupac kupac) {
		return kupacRepository.save(kupac);
	}

	@Override
	public Kupac update(Kupac kupac) {
		return kupacRepository.save(kupac);
	}

	@Override
	public void delete(Long id) {
		kupacRepository.deleteById(id);
	}

	@Override
	public List<Kupac> findByKomercijalistaId(Long id) {
		return kupacRepository.findByKomercijalistaId(id);
	}
	
	
}
