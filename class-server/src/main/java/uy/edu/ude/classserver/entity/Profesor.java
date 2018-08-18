package uy.edu.ude.classserver.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Profesor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final Long id;
  private final Usuario usuario;

  public Profesor(final Long id, final Usuario usuario) {
    this.id = id;
    this.usuario = usuario;
  }

  public Long getId() {
    return id;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Profesor profesor = (Profesor) o;
    return Objects.equals(id, profesor.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Profesor{" +
      "id=" + id +
      ", usuario=" + usuario +
      '}';
  }
}
