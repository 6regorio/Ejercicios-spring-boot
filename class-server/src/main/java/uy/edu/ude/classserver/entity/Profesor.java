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
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Profesor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  private String nombre;
  @NotNull
  private String cargo;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  public Profesor() {
  }

  public Profesor(final Long id, final String nombre, final String cargo, final Usuario usuario) {
    this.id = id;
    this.nombre = nombre;
    this.cargo = cargo;
    this.usuario = usuario;
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

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
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

  public Usuario getUsuario() {
    return usuario;
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
      ", usuario=" + usuario +
      '}';
  }
}
