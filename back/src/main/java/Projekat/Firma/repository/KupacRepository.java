package Projekat.Firma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import Projekat.Firma.model.Kupac;

@Repository
public interface KupacRepository extends JpaRepositoryImplementation<Kupac, Long>{

	Kupac findOneById (Long id);
	List<Kupac> findByKomercijalistaId (Long id);
	
}
