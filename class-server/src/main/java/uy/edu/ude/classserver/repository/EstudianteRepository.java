package uy.edu.ude.classserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uy.edu.ude.classserver.entity.Estudiante;

@RepositoryRestResource(collectionResourceRel = "estudiante", path = "estudiante")
public interface EstudianteRepository extends CrudRepository<Estudiante, Long> {

}
