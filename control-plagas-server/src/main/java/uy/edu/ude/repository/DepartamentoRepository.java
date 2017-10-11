package uy.edu.ude.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import uy.edu.ude.entity.Departamento;

@RepositoryRestResource(path = "departamento")
public interface DepartamentoRepository extends PagingAndSortingRepository<Departamento, Long> {

}
