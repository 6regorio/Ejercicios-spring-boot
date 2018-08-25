package uy.edu.ude.classserver.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uy.edu.ude.classserver.entity.Departamento;

@RepositoryRestResource(collectionResourceRel = "departamento", path = "departamento")
public interface DepartamentoRepository extends ReadOnlyRepository<Departamento, Long> {

}
