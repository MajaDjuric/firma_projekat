package JWD64.Test.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import JWD64.Test.model.Proizvodjac;

@Repository
public interface ProizvodjacRepository extends JpaRepositoryImplementation<Proizvodjac, Long> {

	Proizvodjac findOneById (Long id);

}
