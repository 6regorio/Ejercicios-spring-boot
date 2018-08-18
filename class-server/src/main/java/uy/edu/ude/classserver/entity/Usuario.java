package uy.edu.ude.classserver.entity;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Embeddable
public class Usuario {

  @NotNull
  @Basic(optional = false)
  private final String cedula;
  @NotNull
  @Basic(optional = false)
  private final char[] password;
  @ManyToMany
  private final Set<Rol> roles;

  public Usuario(final String cedula, final char[] password,
    final Set<Rol> roles) {
    this.cedula = cedula;
    this.password = password;
    this.roles = roles;
  }

  public String getCedula() {
    return cedula;
  }

  public char[] getPassword() {
    return password;
  }

  public Set<Rol> getRoles() {
    return roles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Usuario usuario = (Usuario) o;
    return Objects.equals(cedula, usuario.cedula);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cedula);
  }

  @Override
  public String toString() {
    return "Usuario{" +
      "cedula='" + cedula + '\'' +
      ", roles=" + roles +
      '}';
  }
}
