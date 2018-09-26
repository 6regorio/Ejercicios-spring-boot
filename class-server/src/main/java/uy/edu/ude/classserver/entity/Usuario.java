package uy.edu.ude.classserver.entity;

import java.util.Arrays;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Entity
@DynamicUpdate
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @Basic(optional = false)
  private String login;
  @NotNull
  @Basic(optional = false)
  private char[] password;
  @ManyToMany
  private Set<Rol> roles;
  @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @LazyToOne(LazyToOneOption.NO_PROXY)
  private Estudiante estudiante;

  public Usuario(final Long id, @NotNull final String login, @NotNull final char[] password,
    final Estudiante estudiante, final Set<Rol> roles) {
    this.id = id;
    this.login = login;
    this.password = password;
    this.roles = roles;
    this.estudiante = estudiante;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public void setPassword(char[] password) {
    this.password = password;
  }

  public void setRoles(Set<Rol> roles) {
    this.roles = roles;
  }

  public void setEstudiante(Estudiante estudiante) {
    this.estudiante = estudiante;
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
    if (!(o instanceof Usuario)) {
      return false;
    }
    Usuario usuario = (Usuario) o;
    return id != null && id.equals(usuario.id);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return "Usuario{" +
      "id=" + id +
      ", login='" + login + '\'' +
      ", password=" + Arrays.toString(password) +
      ", roles=" + roles +
      ", estudiante=" + estudiante +
      '}';
  }
}
