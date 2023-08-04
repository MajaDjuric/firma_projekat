package JWD64.Test.service;

import java.util.List;

import JWD64.Test.model.VrstaRobe;

public interface VrstaService {

	VrstaRobe findOne (Long id);
	List<VrstaRobe> findAll ();
}
