package Projekat.Firma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Projekat.Firma.model.Vozilo;
import Projekat.Firma.repository.VoziloRepository;
import Projekat.Firma.service.VoziloService;

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
