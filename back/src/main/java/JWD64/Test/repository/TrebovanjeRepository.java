package JWD64.Test.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import JWD64.Test.model.Trebovanje;

@Repository
public interface TrebovanjeRepository extends JpaRepositoryImplementation<Trebovanje, Long>{

	Trebovanje findOneById (Long id);
	@Query("SELECT t FROM Trebovanje t WHERE "
			+ "(:teren IS NULL OR t.kupac.teren LIKE %:teren%) AND "
			+ "(:komercijalistaId IS NULL OR t.komercijalista.id = :komercijalistaId) AND "
			+ "(:kupacId IS NULL OR t.kupac.id = :kupacId)")
			Page<Trebovanje> search (@Param ("teren") String teren,
							   	     @Param ("komercijalistaId") Long komercijalistaId,
							   		 @Param ("kupacId") Long kupacId,
							   		 Pageable pageable);
	List<Trebovanje> findByIdIn (List<Long> ids);
}
