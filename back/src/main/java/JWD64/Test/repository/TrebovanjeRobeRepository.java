package JWD64.Test.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import JWD64.Test.model.Trebovanje;
import JWD64.Test.model.TrebovanjeRobe;

@Repository
public interface TrebovanjeRobeRepository extends JpaRepositoryImplementation<TrebovanjeRobe, Long>{

	TrebovanjeRobe findOneById (Long id);
	List<TrebovanjeRobe> findByTrebovanjeId (Long id);
	@Query("SELECT t FROM TrebovanjeRobe t WHERE "
			+ "(:grad IS NULL OR t.trebovanje.kupac.grad LIKE %:grad%) AND "
			+ "(:teren IS NULL OR t.trebovanje.kupac.teren LIKE %:teren%) AND "
			+ "(:komercijalistaIme IS NULL OR t.trebovanje.komercijalista.ime LIKE %:komercijalistaIme%) AND "
			+ "(:kupacNaziv IS NULL OR t.trebovanje.kupac.naziv LIKE %:kupacNaziv%) AND "
			+ "(:robaId IS NULL OR t.roba.id = :robaId)")
			Page<TrebovanjeRobe> search (@Param ("grad") String grad,
										 @Param ("teren") String teren,
							   			 @Param ("komercijalistaIme") String komercijalistaIme,
							   			 @Param ("kupacNaziv") String kupacNaziv,
							   			 @Param ("robaId") Long robaId,
							   			 Pageable pageable);
	List<TrebovanjeRobe> findByIdIn (List<Long> ids);
	List<TrebovanjeRobe> findByDispozicijaId (Long id);
}
