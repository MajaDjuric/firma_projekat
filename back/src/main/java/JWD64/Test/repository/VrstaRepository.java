package JWD64.Test.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import JWD64.Test.model.VrstaRobe;

@Repository
public interface VrstaRepository extends JpaRepositoryImplementation<VrstaRobe, Long> {

	VrstaRobe findOneById (Long id);
}
