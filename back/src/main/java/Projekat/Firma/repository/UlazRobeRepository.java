package Projekat.Firma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import Projekat.Firma.model.UlazRobe;

@Repository
public interface UlazRobeRepository extends JpaRepositoryImplementation<UlazRobe, Long>{

	UlazRobe findOneById (Long id);
	List<UlazRobe> findOneByUlazId (Long id);
	
}
