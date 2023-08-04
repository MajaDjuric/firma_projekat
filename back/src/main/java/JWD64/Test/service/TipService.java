package JWD64.Test.service;

import java.util.List;

import JWD64.Test.model.TipRobe;

public interface TipService {

	TipRobe findOne (Long id);
	List<TipRobe> findByVrstaId (Long id);

}
