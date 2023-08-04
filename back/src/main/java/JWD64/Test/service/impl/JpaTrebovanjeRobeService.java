package JWD64.Test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import JWD64.Test.model.TrebovanjeRobe;
import JWD64.Test.repository.TrebovanjeRobeRepository;
import JWD64.Test.service.TrebovanjeRobeService;

@Service
public class JpaTrebovanjeRobeService implements TrebovanjeRobeService{

	@Autowired
	private TrebovanjeRobeRepository trebovanjeRobeRepository;

	@Override
	public TrebovanjeRobe findOne(Long id) {
		return trebovanjeRobeRepository.findOneById(id);
	}

	@Override
	public Page<TrebovanjeRobe> search(String grad, String teren, String komercijalistaIme, String kupacIme,
			Long robaId, int pageNo) {
		return trebovanjeRobeRepository.search(grad, teren, komercijalistaIme, kupacIme, robaId, PageRequest.of(pageNo, 10));
	}

	@Override
	public TrebovanjeRobe save(TrebovanjeRobe tr) {
		return trebovanjeRobeRepository.save(tr);
	}

	@Override
	public TrebovanjeRobe update(TrebovanjeRobe tr) {
		return trebovanjeRobeRepository.save(tr);
	}

	@Override
	public void delete(Long id) {
		trebovanjeRobeRepository.deleteById(id);
	}

	@Override
	public List<TrebovanjeRobe> findByTrebovanjeId(Long id) {
		return trebovanjeRobeRepository.findByTrebovanjeId(id);
	}

	@Override
	public List<TrebovanjeRobe> findByIdIn(List<Long> ids) {
		return trebovanjeRobeRepository.findByIdIn(ids);
	}

	@Override
	public List<TrebovanjeRobe> findByDispozicijaId(Long id) {
		return trebovanjeRobeRepository.findByDispozicijaId(id);
	}

	

	
	
}
