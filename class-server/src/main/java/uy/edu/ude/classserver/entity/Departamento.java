package uy.edu.ude.classserver.entity;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Entity
@DynamicUpdate
public class Departamento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  private String nombre;
  @OneToOne(mappedBy = "departamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @LazyToOne(LazyToOneOption.NO_PROXY)
  private Estudiante estudiante;

  public Departamento() {
  }

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
      ", estudiante=" + estudiante +
      '}';
  }

}
