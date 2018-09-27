package uy.edu.ude.classserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uy.edu.ude.classserver.entity.Profesor;

@RepositoryRestResource(collectionResourceRel = "profesor", path = "profesor")
public interface ProfesorRepository extends CrudRepository<Profesor, Long> {

}
