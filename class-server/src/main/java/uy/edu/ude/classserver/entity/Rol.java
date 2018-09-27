package uy.edu.ude.classserver.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Rol {

  @Id
  private Long id;
  @NotNull
  @Enumerated(EnumType.STRING)
  private RolConstant nombre;
  @NotNull
  private String descripcion;

  public Rol() {
  }

  public Rol(final Long id, final RolConstant nombre, final String descripcion) {
    this.id = id;
    this.nombre = nombre;
    this.descripcion = descripcion;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setNombre(RolConstant nombre) {
    this.nombre = nombre;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Long getId() {
    return id;
  }

  public RolConstant getNombre() {
    return nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Rol)) {
      return false;
    }
    Rol rol = (Rol) o;
    return id != null && id.equals(rol.id);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return "Rol{" +
      "id=" + id +
      ", nombre=" + nombre +
      ", descripcion='" + descripcion + '\'' +
      '}';
  }
}
