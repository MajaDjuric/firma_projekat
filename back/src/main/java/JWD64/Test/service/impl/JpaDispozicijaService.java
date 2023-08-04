package JWD64.Test.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import JWD64.Test.model.Dispozicija;
import JWD64.Test.repository.DispozicijaRepository;
import JWD64.Test.service.DispozicijaService;

@Service
public class JpaDispozicijaService implements DispozicijaService{

	@Autowired
	private DispozicijaRepository dispozicijaRepository;

	@Override
	public Dispozicija findOne(Long id) {
		return dispozicijaRepository.findOneById(id);
	}

	@Override
	public Page<Dispozicija> search(LocalDate datumIsporuke, int pageNo) {
		return dispozicijaRepository.search(datumIsporuke, PageRequest.of(pageNo, 6));
	}

	@Override
	public Dispozicija save(Dispozicija dispozicija) {
		return dispozicijaRepository.save(dispozicija);
	}

	@Override
	public Dispozicija update(Dispozicija dispozicija) {
		return dispozicijaRepository.save(dispozicija);
	}

	@Override
	public void delete(Long id) {
		dispozicijaRepository.deleteById(id);
	}

	@Override
	public Page<Dispozicija> findAll(int pageNo) {
		return dispozicijaRepository.findAll(PageRequest.of(pageNo, 6));
	}
	
}
