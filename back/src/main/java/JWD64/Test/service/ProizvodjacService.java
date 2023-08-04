package JWD64.Test.service;

import java.util.List;

import JWD64.Test.model.Proizvodjac;

public interface ProizvodjacService {

	Proizvodjac findOne (Long id);
	List<Proizvodjac> findAll ();
	
}
