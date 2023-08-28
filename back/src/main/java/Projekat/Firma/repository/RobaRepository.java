package Projekat.Firma.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Projekat.Firma.model.Roba;
import Projekat.Firma.model.VrstaRobe;

@Repository
public interface RobaRepository extends JpaRepositoryImplementation<Roba, Long> {

	Roba findONeById (Long id);
	@Query("SELECT r FROM Roba r WHERE "
			+ "(:vrsta IS NULL OR r.vrsta = :vrsta) AND "
			+ "(:naziv IS NULL OR r.naziv LIKE %:naziv%) AND "
			+ "(:proizvodjacId IS NULL OR r.proizvodjac.id = :proizvodjacId) AND "
			+ "(:pakovanje IS NULL OR r.pakovanje = :pakovanje) AND "
			+ "(:tretman IS NULL OR r.tretman LIKE %:tretman%)")
			Page<Roba> search (@Param ("vrsta") VrstaRobe vrsta,
							   @Param ("naziv") String naziv,
							   @Param ("proizvodjacId") Long proizvodjacId,
							   @Param ("pakovanje") Double pakovanje,
							   @Param ("tretman") String tretman,
							   Pageable pageable);
	
    List<Roba> findByIdIn(List<Long> ids);
    List<Roba> findByUlaziId (Long id);

			
}
