package uy.edu.ude.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import uy.edu.ude.constant.Rol;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    // @formatter:off
    auth.inMemoryAuthentication().
        withUser("vendedor").password("Pass01.").roles(Rol.VENDEDOR).and().
        withUser("vend").password("pass").roles(Rol.VENDEDOR).and().
        withUser("operario").password("Pass02.").roles(Rol.OPERARIO).and().
        withUser("oper").password("pass").roles(Rol.OPERARIO);        
    // @formatter:on
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    // @formatter:off
    httpSecurity.authorizeRequests()
        .antMatchers(HttpMethod.POST, "/cliente/**")
          .hasRole(Rol.VENDEDOR)
        .antMatchers(HttpMethod.PUT, "/cliente/**")
          .hasRole(Rol.VENDEDOR)
        .antMatchers(HttpMethod.DELETE, "/cliente/**")
          .hasRole(Rol.VENDEDOR)
        .antMatchers(HttpMethod.PATCH, "/cliente/**")
          .hasRole(Rol.VENDEDOR)
        .antMatchers(HttpMethod.GET, "/cliente/**")
          .hasAnyRole(Rol.VENDEDOR, Rol.OPERARIO)
        .antMatchers(HttpMethod.GET, "/departamento/**")
          .hasAnyRole(Rol.VENDEDOR, Rol.OPERARIO)
        .antMatchers(HttpMethod.GET, "/usuario/**")
          .hasAnyRole(Rol.VENDEDOR, Rol.OPERARIO)
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers("/browser/**").permitAll();
    // @formatter:on
    httpSecurity.httpBasic();
    httpSecurity.csrf().disable();
    httpSecurity.headers().frameOptions().disable();
  }
}
