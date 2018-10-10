package uy.edu.ude.classserver.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@DynamicUpdate
public class Estudiante {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  private String nombre;
  @NotNull
  private String telefono;
  @Email
  @NotNull
  private String email;
  @NotNull
  private String direccion;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usuario_id")
  @NotNull
  private Usuario usuario;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "departamento_id")
  @NotNull
  private Departamento departamento;

  public Estudiante() {
  }

  public Estudiante(final Long id, @NotNull final String nombre,
    @NotNull final String telefono,
    @Email @NotNull final String email, @NotNull final String direccion,
    final Usuario usuario, final Departamento departamento) {
    this.id = id;
    this.nombre = nombre;
    this.telefono = telefono;
    this.email = email;
    this.direccion = direccion;
    this.usuario = usuario;
    this.departamento = departamento;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public Long getId() {
    return id;
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

  public Usuario getUsuario() {
    return usuario;
  }

  public Departamento getDepartamento() {
    return departamento;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Estudiante)) {
      return false;
    }
    Estudiante estudiante = (Estudiante) o;
    return id != null && id.equals(estudiante.id);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return "Estudiante{" +
      "id=" + id +
      ", nombre='" + nombre + '\'' +
      ", telefono='" + telefono + '\'' +
      ", email='" + email + '\'' +
      ", direccion='" + direccion + '\'' +
      ", departamento='" + departamento + '\'' +
      ", usuario=" + usuario +
      '}';
  }
}
