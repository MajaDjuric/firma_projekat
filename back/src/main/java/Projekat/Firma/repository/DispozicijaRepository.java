package Projekat.Firma.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Projekat.Firma.model.Dispozicija;

@Repository
public interface DispozicijaRepository extends JpaRepositoryImplementation<Dispozicija, Long>{

	Dispozicija findOneById (Long id);
	@Query("SELECT d FROM Dispozicija d WHERE "
			+ "(:datumIsporuke IS NULL OR d.datumIsporuke = :datumIsporuke)")
			Page<Dispozicija> search (@Param ("datumIsporuke") LocalDate datumIsporuke,
							   		  Pageable pageable);
}
