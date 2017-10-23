package uy.edu.ude.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import uy.edu.ude.constant.Roles;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    // @formatter:off
    auth.inMemoryAuthentication().
        withUser("vendedor").password("Pass01.").roles(Roles.VENDEDOR).and().
        withUser("vend").password("pass").roles(Roles.VENDEDOR).and().
        withUser("operario").password("Pass02.").roles(Roles.OPERARIO).and().
        withUser("oper").password("pass").roles(Roles.OPERARIO);
        
    // @formatter:on
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    // @formatter:off
    httpSecurity.authorizeRequests()
        .antMatchers(HttpMethod.POST, "/cliente/**")
          .hasRole(Roles.VENDEDOR)
        .antMatchers(HttpMethod.PUT, "/cliente/**")
          .hasRole(Roles.VENDEDOR)
        .antMatchers(HttpMethod.DELETE, "/cliente/**")
          .hasRole(Roles.VENDEDOR)
        .antMatchers(HttpMethod.PATCH, "/cliente/**")
          .hasRole(Roles.VENDEDOR)
        .antMatchers(HttpMethod.GET, "/cliente/**")
          .hasAnyRole(Roles.VENDEDOR, Roles.OPERARIO)
        .antMatchers(HttpMethod.GET, "/departamento/**")
          .hasAnyRole(Roles.VENDEDOR, Roles.OPERARIO)
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers("/browser/**").permitAll();
    // @formatter:on
    httpSecurity.httpBasic();
    httpSecurity.csrf().disable();
    httpSecurity.headers().frameOptions().disable();
  }
}
