package uy.edu.ude.classserver.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
public class Estudiante {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final Long id;
  @NotNull
  private final String nombre;
  @NotNull
  private final String telefono;
  @Email
  @NotNull
  private final String email;
  @NotNull
  private final String direccion;
  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  private final Usuario usuario;

  public Estudiante(final Long id, @NotNull final String nombre,
    @NotNull final String telefono,
    @Email @NotNull final String email, @NotNull final String direccion,
    final Usuario usuario) {
    this.id = id;
    this.nombre = nombre;
    this.telefono = telefono;
    this.email = email;
    this.direccion = direccion;
    this.usuario = usuario;
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
}
