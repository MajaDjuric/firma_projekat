package JWD64.Test.service;

import java.util.List;

import JWD64.Test.model.Kupac;

public interface KupacService {

	Kupac findOne (Long id);
	List<Kupac> findAll();
	Kupac save (Kupac kupac);
	Kupac update (Kupac kupac);
	void delete (Long id);
	List<Kupac> findByKomercijalistaId (Long id);

	
}
