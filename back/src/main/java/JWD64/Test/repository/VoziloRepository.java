package JWD64.Test.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import JWD64.Test.model.Vozilo;

@Repository
public interface VoziloRepository extends JpaRepositoryImplementation<Vozilo, Long>{
	
	Vozilo findOneById (Long id);
}
