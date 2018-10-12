package uy.edu.ude.classserver.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uy.edu.ude.classserver.entity.Departamento;

@RepositoryRestResource(collectionResourceRel = "departamento", path = "departamento")
public interface DepartamentoRepository extends CrudRepository<Departamento, Long> {

  Optional<Departamento> findByNombre(@Param("nombre") String nombre);
}
