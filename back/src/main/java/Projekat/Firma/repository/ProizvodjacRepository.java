package Projekat.Firma.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import Projekat.Firma.model.Proizvodjac;

@Repository
public interface ProizvodjacRepository extends JpaRepositoryImplementation<Proizvodjac, Long> {

	Proizvodjac findOneById (Long id);

}
