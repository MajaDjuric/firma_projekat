package JWD64.Test.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import JWD64.Test.model.Ulaz;

public interface UlazService {

	Ulaz findOne (Long id);
	Page<Ulaz> search (String brojFakture, String brojOtpremnice, LocalDate minDatumUlaza, LocalDate maxDatumUlaza, Long proizvodjacId, Long robaId, int pageNo);
	Ulaz save (Ulaz ulaz);
	Ulaz update (Ulaz ulaz);
	void delete (Long id);
}
