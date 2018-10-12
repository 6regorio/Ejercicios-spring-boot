package uy.edu.ude.classserver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClassServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClassServerIntegrationTest {

  @Autowired
  private TestRestTemplate template;

  @Test
  public void givenValidUsuario_whenGetEstudiante_thenGetEstudiante() throws JSONException {
    String username = "est";
    String password = "est";

    ResponseEntity<String> response = template.withBasicAuth(username, password)
      .getForEntity("/estudiante/1", String.class);
    JSONObject input = new JSONObject(response.getBody());

    assertSoftly(softly -> {
      softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
      try {
        softly.assertThat(input.getString("nombre")).isNotNull();
      } catch (JSONException e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Test
  public void givenNonValidUsuario_whenGetEstudiante_thenGetUnauthorized() throws JSONException {
    String username = "est";
    String password = "est1";

    ResponseEntity<String> response = template.withBasicAuth(username, password)
      .getForEntity("/estudiante/1", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
  }

  @Test
  public void givenNonAuthRequest_whenGetEstudiante_thenGetUnauthorized() {
    ResponseEntity<String> response = template.getForEntity("/estudiante/1", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
  }

  @Test
  public void givenAuthEstudiante_whenGetUsuario_thenGetRolEstudiante() throws JSONException {
    String username = "est";
    String password = "est";

    ResponseEntity<String> response = template.withBasicAuth(username, password)
      .getForEntity("/usuario", String.class);
    JSONObject input = new JSONObject(response.getBody());
    JSONArray authorities = input.getJSONArray("authorities");

    assertSoftly(softly -> {
      softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
      try {
        softly.assertThat(authorities.getJSONObject(0)
          .getString("authority")).isEqualTo("ROLE_ESTUDIANTE");
      } catch (JSONException e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Test
  public void givenAuthProfesor_whenPostEstudiante_thenGetCreatedCode() throws JSONException {
    String username = "prof";
    String password = "prof";
    // Nuevo cliente
    JSONObject newClienteJson = new JSONObject(
      "{\"nombre\": \"Carlos3000\",\"telefono\": \"092651651\",\"email\": \"carlos2@test.com\",\"direccion\": \"Mi Casa\"}");
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    // Busqueda de departamentos activos
    ResponseEntity<String> responseDepartamentos = template.withBasicAuth(username, password)
      .getForEntity("/departamento", String.class);
    JSONObject embedded = new JSONObject(responseDepartamentos.getBody())
      .getJSONObject("_embedded");
    JSONArray departamentos = embedded.getJSONArray("departamento");
    String firstDepartamentoUrl = departamentos.getJSONObject(0)
      .getJSONObject("_links")
      .getJSONObject("departamento")
      .getString("href");
    // Agrego departamento
    newClienteJson.put("departamento", firstDepartamentoUrl);

    HttpEntity<String> entity = new HttpEntity<>(newClienteJson.toString(), headers);
    ResponseEntity<String> response = template.withBasicAuth(username, password)
      .exchange("/estudiante", HttpMethod.POST, entity, String.class);
    JSONObject output = new JSONObject(response.getBody());

    assertSoftly(softly -> {
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
      try {
        softly.assertThat(output.getString("nombre")).isEqualTo("Carlos3000");
      } catch (JSONException e) {
        throw new RuntimeException(e);
      }
    });
  }

}
