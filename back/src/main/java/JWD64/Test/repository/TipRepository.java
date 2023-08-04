package JWD64.Test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import JWD64.Test.model.TipRobe;

@Repository
public interface TipRepository extends JpaRepositoryImplementation<TipRobe, Long> {

	TipRobe findOneById (Long id);
	List<TipRobe> findByVrstaId (Long id);
}
