package Projekat.Firma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Projekat.Firma.model.UlazRobe;

@Repository
public interface UlazRobeRepository extends JpaRepositoryImplementation<UlazRobe, Long>{

	UlazRobe findOneById (Long id);
	List<UlazRobe> findOneByUlazId (Long id);
	
	@Query ("SELECT u FROM UlazRobe u WHERE "
			+ "(:ulazId IS NULL OR u.ulaz.id = :ulazId) AND "
			+ "(:robaId IS NULL OR u.roba.id = :robaId)")
	UlazRobe search (@Param ("ulazId") Long ulazId,
						  @Param ("robaId") Long robaId);
	
}
