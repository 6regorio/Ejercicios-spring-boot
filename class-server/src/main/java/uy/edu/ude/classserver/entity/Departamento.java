package uy.edu.ude.classserver.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Departamento {

  @Id
  private Long id;
  @NotNull
  private String nombre;
  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  private Estudiante estudiante;

  public Departamento(){}

  public Departamento(final Long id, final String nombre, final Estudiante estudiante) {
    this.id = id;
    this.nombre = nombre;
    this.estudiante = estudiante;
  }


  public Long getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public Estudiante getEstudiante() {
    return estudiante;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Departamento that = (Departamento) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Departamento{" +
      "id=" + id +
      ", nombre='" + nombre + '\'' +
      ", estudiante=" + estudiante +
      '}';
  }

}
