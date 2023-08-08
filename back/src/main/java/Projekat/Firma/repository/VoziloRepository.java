package Projekat.Firma.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import Projekat.Firma.model.Vozilo;

@Repository
public interface VoziloRepository extends JpaRepositoryImplementation<Vozilo, Long>{
	
	Vozilo findOneById (Long id);
}
