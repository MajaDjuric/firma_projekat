package Projekat.Firma.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Projekat.Firma.model.Trebovanje;

@Repository
public interface TrebovanjeRepository extends JpaRepositoryImplementation<Trebovanje, Long>{

	Trebovanje findOneById (Long id);
	
	@Query("SELECT t FROM Trebovanje t WHERE "
			+ "(:teren IS NULL OR t.kupac.teren LIKE %:teren%) AND "
	        + "(:robaId IS NULL OR EXISTS (SELECT 1 FROM t.roba r WHERE r.id = :robaId)) AND "
			+ "(:kupacId IS NULL OR t.kupac.id = :kupacId)")
			Page<Trebovanje> search (@Param ("teren") String teren,
									 @Param ("kupacId") Long kupacId,
									 @Param ("robaId") Long robaId,
							   		 Pageable pageable);
	 
	@Query("SELECT t FROM Trebovanje t WHERE "
			+ "(:teren IS NULL OR t.kupac.teren LIKE %:teren%) AND "
	        + "(:robaId IS NULL OR EXISTS (SELECT 1 FROM t.roba r WHERE r.id = :robaId)) AND "
			+ "(t.komercijalista.id = :komercijalistaId) AND "
			+ "(:kupacId IS NULL OR t.kupac.id = :kupacId)")
			Page<Trebovanje> searchWithId (@Param ("teren") String teren,
							   	     @Param ("komercijalistaId") Long komercijalistaId,
							   		 @Param ("kupacId") Long kupacId,
									 @Param ("robaId") Long robaId,
							   		 Pageable pageable);
	
	List<Trebovanje> findByIdIn (List<Long> ids);
}
