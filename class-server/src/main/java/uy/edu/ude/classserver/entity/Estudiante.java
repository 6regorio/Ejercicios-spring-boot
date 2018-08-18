package uy.edu.ude.classserver.entity;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Estudiante {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final Long id;
  @Embedded
  private final Usuario usuario;
  @NotNull
  private final String nombre;
  @NotNull
  private final String telefono;
  @Email
  @NotNull
  private final String email;
  @NotNull
  private final String direccion;

  public Estudiante(final Long id, final Usuario usuario, final String nombre,
    final String telefono, final String email,
    final String direccion) {
    this.id = id;
    this.usuario = usuario;
    this.nombre = nombre;
    this.telefono = telefono;
    this.email = email;
    this.direccion = direccion;
  }

  public Long getId() {
    return id;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public String getNombre() {
    return nombre;
  }

  public String getTelefono() {
    return telefono;
  }

  public String getEmail() {
    return email;
  }

  public String getDireccion() {
    return direccion;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Estudiante that = (Estudiante) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Estudiante{" +
      "id=" + id +
      ", usuario=" + usuario +
      ", nombre='" + nombre + '\'' +
      ", telefono='" + telefono + '\'' +
      ", email='" + email + '\'' +
      ", direccion='" + direccion + '\'' +
      '}';
  }
}
