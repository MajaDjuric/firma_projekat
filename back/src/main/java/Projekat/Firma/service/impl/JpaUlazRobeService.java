package Projekat.Firma.service.impl;

import java.util.List;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import Projekat.Firma.model.UlazRobe;
import Projekat.Firma.repository.UlazRobeRepository;
import Projekat.Firma.service.UlazRobeService;

@Service
public class JpaUlazRobeService implements UlazRobeService{

	@Autowired
	private UlazRobeRepository ulazRobeRepository;
	
	@Override
	public UlazRobe findOne(Long id) {
		return ulazRobeRepository.findOneById(id);
	}

	@Override
	public List<UlazRobe> findAll() {
		return ulazRobeRepository.findAll();
	}

	@Override
	public UlazRobe save(UlazRobe ulazRobe) {
		return ulazRobeRepository.save(ulazRobe);
	}

	@Override
	public UlazRobe update(UlazRobe ulazRobe) {
		return ulazRobeRepository.save(ulazRobe);
	}

	@Override
	public void delete(Long id) {
		ulazRobeRepository.deleteById(id);
	}

	@Override
	public 	List<UlazRobe> findOneByUlazId(Long id) {
		return ulazRobeRepository.findOneByUlazId(id);
	}

	@Override
	public UlazRobe search(Long ulazId, Long robaId) {
		return ulazRobeRepository.search(ulazId, robaId);
	}

}
