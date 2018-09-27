package uy.edu.ude.classserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import uy.edu.ude.classserver.entity.Departamento;

@RepositoryRestResource(collectionResourceRel = "departamento", path = "departamento")
public interface DepartamentoRepository extends CrudRepository<Departamento, Long> {

  @Override
  @RestResource(exported = false)
  <S extends Departamento> Iterable<S> saveAll(Iterable<S> iterable);

  @Override
  @RestResource(exported = false)
  <S extends Departamento> S save(S s);
}
