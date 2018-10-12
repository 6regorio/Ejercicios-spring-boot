package uy.edu.ude.classserver.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Departamento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @NotBlank
  @Size(max = 256)
  private String nombre;

  public Departamento() {
  }

  public Departamento(final Long id, final String nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  public Long getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Departamento)) {
      return false;
    }
    Departamento departamento = (Departamento) o;
    return id != null && id.equals(departamento.id);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return "Departamento{" +
      "id=" + id +
      ", nombre='" + nombre + '\'' +
      '}';
  }

}
