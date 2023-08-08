package Projekat.Firma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Projekat.Firma.model.TipRobe;
import Projekat.Firma.repository.TipRepository;
import Projekat.Firma.service.TipService;

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
