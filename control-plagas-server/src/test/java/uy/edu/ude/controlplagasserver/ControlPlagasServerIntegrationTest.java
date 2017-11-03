package uy.edu.ude.controlplagasserver;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import uy.edu.ude.ControlPlagasServerApplication;
import uy.edu.ude.entity.Cliente;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ControlPlagasServerApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControlPlagasServerIntegrationTest {

  @Autowired
  private TestRestTemplate template;

  @Test
  public void givenUsuarioAuth_whenUriIsCliente_thenGetCliente() {
    String username = "vend";
    String password = "pass";
    ResponseEntity<Cliente> response =
        template.withBasicAuth(username, password).getForEntity("/cliente/1", Cliente.class);
    assertTrue(null != response.getBody().getNombre());
    assertTrue(response.getStatusCode() == HttpStatus.OK);
  }

  @Test
  public void givenUsuarioNonAuth_whenUriIsCliente_thenGetUnauthorized() {
    String username = "vend";
    String password = "pass1";
    ResponseEntity<Cliente> response =
        template.withBasicAuth(username, password).getForEntity("/cliente/1", Cliente.class);
    assertTrue(null == response.getBody().getNombre());
    assertTrue(response.getStatusCode() == HttpStatus.UNAUTHORIZED);
  }

}
