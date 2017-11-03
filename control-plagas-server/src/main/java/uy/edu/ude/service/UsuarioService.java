package uy.edu.ude.service;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioService {

  @RequestMapping(method = RequestMethod.GET, path = "/usuario")
  public User getAuthorities(@AuthenticationPrincipal User activeUser) {
    return activeUser;
  }
}
