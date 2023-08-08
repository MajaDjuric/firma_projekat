package Projekat.Firma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import Projekat.Firma.model.TipRobe;

@Repository
public interface TipRepository extends JpaRepositoryImplementation<TipRobe, Long> {

	TipRobe findOneById (Long id);
	List<TipRobe> findByVrstaId (Long id);
}
