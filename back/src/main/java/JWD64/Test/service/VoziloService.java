package JWD64.Test.service;

import java.util.List;

import JWD64.Test.model.Vozilo;

public interface VoziloService {

	Vozilo findOne (Long id);
	List<Vozilo> findAll();
	Vozilo save (Vozilo vozilo);
	Vozilo update (Vozilo vozilo);
	void delete (Long id);
	
}
