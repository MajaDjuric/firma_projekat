package Projekat.Firma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Projekat.Firma.model.VrstaRobe;
import Projekat.Firma.repository.VrstaRepository;
import Projekat.Firma.service.VrstaService;

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

	@Override
	public VrstaRobe findByNaziv(String naziv) {
		return vrstaRepository.findByNaziv(naziv);
	}
	
	
}
