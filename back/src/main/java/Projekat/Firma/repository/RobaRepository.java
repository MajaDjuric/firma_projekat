package Projekat.Firma.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Projekat.Firma.model.Roba;

@Repository
public interface RobaRepository extends JpaRepositoryImplementation<Roba, Long> {

	Roba findONeById (Long id);
	@Query("SELECT r FROM Roba r WHERE "
			+ "(:naziv IS NULL OR r.naziv LIKE %:naziv%) AND "
			+ "(:proizvodjacId IS NULL OR r.proizvodjac.id = :proizvodjacId) AND "
			+ "(:pakovanje IS NULL OR r.pakovanje = :pakovanje) AND "
			+ "(:tretman IS NULL OR r.tretman LIKE %:tretman%) AND"
			+ "(:vrstaId IS NULL OR r.vrsta.id = :vrstaId)")
			Page<Roba> search (@Param ("naziv") String naziv,
							   @Param ("proizvodjacId") Long proizvodjacId,
							   @Param ("pakovanje") Double pakovanje,
							   @Param ("tretman") String tretman,
							   @Param ("vrstaId") Long vrstaId,
							   Pageable pageable);
	
    List<Roba> findByIdIn(List<Long> ids);
    List<Roba> findByUlaziId (Long id);

			
}
