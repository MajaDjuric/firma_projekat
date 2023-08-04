package JWD64.Test.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import JWD64.Test.model.Ulaz;

@Repository
public interface UlazRepository extends JpaRepositoryImplementation<Ulaz, Long> {

	Ulaz findONeById (Long id);
	@Query("SELECT u FROM Ulaz u WHERE "
			+ "(:brojFakture IS NULL OR u.brojFakture LIKE %:brojFakture%) AND "
			+ "(:brojOtpremnice IS NULL OR u.brojOtpremnice LIKE %:brojOtpremnice%) AND "
			+ "(:minDatumUlaza IS NULL OR u.datumUlaza >= :minDatumUlaza) AND "
			+ "(:maxDatumUlaza IS NULL OR u.datumUlaza <= :maxDatumUlaza) AND "
			+ "(:proizvodjacId IS NULL OR u.proizvodjac.id = :proizvodjacId) AND "
			+ "(:robaId IS NULL OR :robaId IN (SELECT r.id FROM u.roba r))")
			Page<Ulaz> search (@Param ("brojFakture") String brojFakture,
							   @Param ("brojOtpremnice") String brojOtpremnice,
							   @Param ("minDatumUlaza") LocalDate minDatumUlaza,
							   @Param ("maxDatumUlaza") LocalDate maxDatumUlaza,
							   @Param ("proizvodjacId") Long proizvodjacId,
							   @Param ("robaId") Long robaId,
							   Pageable pageable);
			
}
