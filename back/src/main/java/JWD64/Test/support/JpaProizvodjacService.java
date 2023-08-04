package JWD64.Test.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JWD64.Test.model.Proizvodjac;
import JWD64.Test.repository.ProizvodjacRepository;
import JWD64.Test.service.ProizvodjacService;

@Service
public class JpaProizvodjacService  implements ProizvodjacService{

	@Autowired
	private ProizvodjacRepository proizvodjacRepository;

	@Override
	public Proizvodjac findOne(Long id) {
		return proizvodjacRepository.findOneById(id);
	}

	@Override
	public List<Proizvodjac> findAll() {
		return proizvodjacRepository.findAll();
	}
	
	
}
