package uy.edu.ude.classserver.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Profesor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final Long id;
  private final String nombre;
  private final String cargo;
  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  private final Usuario usuario;

  public Profesor(final Long id, final String nombre, final String cargo, final Usuario usuario) {
    this.id = id;
    this.nombre = nombre;
    this.cargo = cargo;
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
}
