package Projekat.Firma.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Projekat.Firma.model.Proizvodjac;
import Projekat.Firma.repository.ProizvodjacRepository;
import Projekat.Firma.service.ProizvodjacService;

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
