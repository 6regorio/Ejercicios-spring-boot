package uy.edu.ude.classserver.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Profesor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @Size(max = 256)
  private String nombre;
  @NotNull
  @Size(max = 256)
  private String cargo;

  public Profesor() {
  }

  public Profesor(final Long id, final String nombre, final String cargo) {
    this.id = id;
    this.nombre = nombre;
    this.cargo = cargo;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setCargo(String cargo) {
    this.cargo = cargo;
  }

  public Long getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public String getCargo() {
    return cargo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Profesor)) {
      return false;
    }
    Profesor profesor = (Profesor) o;
    return id != null && id.equals(profesor.id);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return "Profesor{" +
      "id=" + id +
      ", nombre='" + nombre + '\'' +
      ", cargo='" + cargo + '\'' +
      '}';
  }
}
