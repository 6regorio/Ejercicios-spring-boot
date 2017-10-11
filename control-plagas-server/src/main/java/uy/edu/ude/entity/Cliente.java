package uy.edu.ude.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String nombre;
  private String telefono;
  private String email;
  // @OneToOne(fetch = FetchType.LAZY)
  // @MapsId
  // private Departamento departamento;
  private String direccion;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  // public Departamento getDepartamento() {
  // return departamento;
  // }

  // public void setDepartamento(Departamento departamento) {
  // this.departamento = departamento;
  // }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

}
