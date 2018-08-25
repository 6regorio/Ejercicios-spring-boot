package uy.edu.ude.classserver.entity;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final Long id;
  @NotNull
  @Basic(optional = false)
  private final String login;
  @NotNull
  @Basic(optional = false)
  private final char[] password;
  @ManyToMany
  private final Set<Rol> roles;

  public Usuario(final Long id, @NotNull final String login, @NotNull final char[] password,
    final Set<Rol> roles) {
    this.id = id;
    this.login = login;
    this.password = password;
    this.roles = roles;
  }

  public Long getId() {
    return id;
  }

  public String getLogin() {
    return login;
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
    return id == usuario.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Usuario{" +
      "id=" + id +
      ", login='" + login + '\'' +
      ", roles=" + roles +
      '}';
  }
}
