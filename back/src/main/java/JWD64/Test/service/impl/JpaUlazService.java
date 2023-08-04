package JWD64.Test.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import JWD64.Test.model.Ulaz;
import JWD64.Test.repository.UlazRepository;
import JWD64.Test.service.UlazService;

@Service
public class JpaUlazService implements UlazService{

	@Autowired
	private UlazRepository ulazRepository;

	@Override
	public Ulaz findOne(Long id) {
		return ulazRepository.findONeById(id);
	}

	@Override
	public Page<Ulaz> search(String brojFakture, String brojOtpremnice, LocalDate minDatumUlaza, LocalDate maxDatumUlaza, Long proizvodjacId, Long robaId,  int pageNo) {
		return ulazRepository.search(brojFakture, brojOtpremnice, minDatumUlaza, maxDatumUlaza, proizvodjacId, robaId, PageRequest.of(pageNo, 5));
	}

	@Override
	public Ulaz save(Ulaz ulaz) {
		return ulazRepository.save(ulaz);
	}

	@Override
	public Ulaz update(Ulaz ulaz) {
		return ulazRepository.save(ulaz);
	}

	@Override
	public void delete(Long id) {
		ulazRepository.deleteById(id);
	}



	
	
}
