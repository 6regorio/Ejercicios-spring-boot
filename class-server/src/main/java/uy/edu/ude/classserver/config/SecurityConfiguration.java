package uy.edu.ude.classserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication().dataSource(jdbcTemplate.getDataSource())
      .usersByUsernameQuery(
        "select login, password, enabled from usuario where login=?")
      .authoritiesByUsernameQuery(
        "select u.login, r.nombre from usuario u left join usuario_roles ur "
          + "on ur.usuario_id=u.id left join rol r "
          + "on ur.roles_id=r.id where u.login=?")
      .passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeRequests()
      .antMatchers(HttpMethod.POST, "/estudiante/**")
      .hasRole("PROFESOR")
      .antMatchers(HttpMethod.PUT, "/estudiante/**")
      .hasRole("PROFESOR")
      .antMatchers(HttpMethod.DELETE, "/estudiante/**")
      .hasRole("PROFESOR")
      .antMatchers(HttpMethod.PATCH, "/estudiante/**")
      .hasRole("PROFESOR")
      .antMatchers(HttpMethod.GET, "/estudiante/**")
      .hasAnyRole("PROFESOR", "ESTUDIANTE")
      .antMatchers(HttpMethod.GET, "/profesor/**")
      .hasRole("PROFESOR")
      .antMatchers(HttpMethod.GET, "/departamento/**")
      .hasAnyRole("PROFESOR", "ESTUDIANTE")
      .antMatchers(HttpMethod.POST, "/departamento/**")
      .denyAll()
      .antMatchers("/h2-console/**").permitAll()
      .antMatchers("/browser/**").permitAll();
    httpSecurity.httpBasic();
    httpSecurity.csrf().disable();
    httpSecurity.headers().frameOptions().disable();
  }

  @Bean
  @Override
  public JdbcUserDetailsManager userDetailsService() {
    JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
    manager.setJdbcTemplate(jdbcTemplate);
    return manager;
  }
}
