package JWD64.Test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import JWD64.Test.model.Kupac;

@Repository
public interface KupacRepository extends JpaRepositoryImplementation<Kupac, Long>{

	Kupac findOneById (Long id);
	List<Kupac> findByKomercijalistaId (Long id);
	
}
