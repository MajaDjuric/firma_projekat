package Projekat.Firma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import Projekat.Firma.model.Roba;
import Projekat.Firma.repository.RobaRepository;
import Projekat.Firma.service.RobaService;

@Service
public class JpaRobaService implements RobaService{

	@Autowired
	private RobaRepository robaRepository;

	@Override
	public Roba findOne(Long id) {
		return robaRepository.findONeById(id);
	}

	@Override
	public Page<Roba> search(String naziv, Long proizvodjacId, Double pakovanje, String tretman, Long  vrstaId, int pageNo) {
		return robaRepository.search(naziv, proizvodjacId, pakovanje, tretman, vrstaId, PageRequest.of(pageNo, 5));
	}

	@Override
	public Roba save(Roba roba) {
		return robaRepository.save(roba);
	}

	@Override
	public Roba update(Roba roba) {
		return robaRepository.save(roba);

	}

	@Override
	public void delete(Long id) {
		robaRepository.deleteById(id);
	}

	@Override
	public Page<Roba> findAll(int pageNo) {
		return robaRepository.findAll(PageRequest.of(pageNo, 4));
	}

	@Override
	public List<Roba> findByIdIn(List<Long> ids) {
		return robaRepository.findByIdIn(ids);
	}

	@Override
	public List<Roba> findByUlaziId(Long id) {
		return robaRepository.findByUlaziId(id);
	}
	
	
}
