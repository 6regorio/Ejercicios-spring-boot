package uy.edu.ude.classserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uy.edu.ude.classserver.entity.Departamento;
import uy.edu.ude.classserver.entity.Usuario;

@RepositoryRestResource(collectionResourceRel = "usuario", path = "usuario")
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
