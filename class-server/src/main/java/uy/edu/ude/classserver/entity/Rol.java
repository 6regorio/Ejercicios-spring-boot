package uy.edu.ude.classserver.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Rol {

  @Id
  @GeneratedValue
  private final Long id;
  @Enumerated(EnumType.STRING)
  private final RolConstant nombre;
  @NotNull
  private final String descripcion;

  public Rol(final Long id, final RolConstant nombre, final String descripcion) {
    this.id = id;
    this.nombre = nombre;
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
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rol rol = (Rol) o;
    return Objects.equals(id, rol.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
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
