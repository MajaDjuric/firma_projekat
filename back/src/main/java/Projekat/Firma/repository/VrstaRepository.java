package Projekat.Firma.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import Projekat.Firma.model.VrstaRobe;

@Repository
public interface VrstaRepository extends JpaRepositoryImplementation<VrstaRobe, Long> {

	VrstaRobe findOneById (Long id);
	VrstaRobe findByNaziv (String naziv);
}
