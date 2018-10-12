package uy.edu.ude.classserver.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uy.edu.ude.classserver.entity.Departamento;
import uy.edu.ude.classserver.entity.Estudiante;

@RepositoryRestResource(collectionResourceRel = "estudiante", path = "estudiante")
public interface EstudianteRepository extends CrudRepository<Estudiante, Long> {

  Optional<Estudiante> findByEmail(@Param("email") String email);
  Optional<Estudiante> findByNombreContains(@Param("nombre") String nombre);
}
