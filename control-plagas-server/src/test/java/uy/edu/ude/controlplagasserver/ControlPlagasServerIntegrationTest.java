package uy.edu.ude.controlplagasserver;

import static org.junit.Assert.assertTrue;

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

import uy.edu.ude.ControlPlagasServerApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ControlPlagasServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControlPlagasServerIntegrationTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void givenAuthUsuario_whenUriIsCliente_thenGetCliente() throws JSONException {
        String username = "vend";
        String password = "pass";
        ResponseEntity<String> response = template.withBasicAuth(username, password)
            .getForEntity("/cliente/1", String.class);
        JSONObject input = new JSONObject(response.getBody());
        assertTrue(null != input.getString("nombre"));
        assertTrue(HttpStatus.OK == response.getStatusCode());
    }

    @Test
    public void givenNonValidUsuario_whenUriIsCliente_thenGetUnauthorized() throws JSONException {
        String username = "vend";
        String password = "pass1";
        ResponseEntity<String> response = template.withBasicAuth(username, password)
            .getForEntity("/cliente/1", String.class);
        assertTrue(HttpStatus.UNAUTHORIZED == response.getStatusCode());
    }

    @Test
    public void givenNonAuthRequest_whenUriIsCliente_thenGetUnauthorized() {
        ResponseEntity<String> response = template.getForEntity("/cliente/1", String.class);
        assertTrue(HttpStatus.UNAUTHORIZED == response.getStatusCode());
    }

    @Test
    public void givenAuthVendedor_whenUriIsUsuario_thenGetRolVendedor() throws JSONException {
        String username = "vend";
        String password = "pass";
        ResponseEntity<String> response = template.withBasicAuth(username, password)
            .getForEntity("/usuario", String.class);
        JSONObject input = new JSONObject(response.getBody());
        JSONArray authorities = input.getJSONArray("authorities");
        assertTrue("ROLE_VENDEDOR".equals(authorities.getJSONObject(0)
            .getString("authority")));
        assertTrue(HttpStatus.OK == response.getStatusCode());
    }

    @Test
    public void givenAuthVendedor_whenUriIsCliente_thenPostCliente() throws JSONException {
        String username = "vend";
        String password = "pass";
        // Nuevo cliente
        JSONObject newClienteJson = new JSONObject("{\"nombre\": \"Carlos3000\",\"telefono\": \"092651651\",\"email\": \"carlos2@test.com\",\"direccion\": \"Mi Casa\"}");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Busqueda de departamentos activos
        ResponseEntity<String> responseDepartamentos = template.withBasicAuth(username, password)
            .getForEntity("/departamento", String.class);
        JSONObject embedded = new JSONObject(responseDepartamentos.getBody()).getJSONObject("_embedded");
        JSONArray departamentos = embedded.getJSONArray("departamentoes");
        String firstDepartamentoUrl = departamentos.getJSONObject(0)
            .getJSONObject("_links")
            .getJSONObject("departamento")
            .getString("href");
        // Agrego departamento
        newClienteJson.put("departamento", firstDepartamentoUrl);

        HttpEntity<String> entity = new HttpEntity<String>(newClienteJson.toString(), headers);
        ResponseEntity<String> response = template.withBasicAuth(username, password)
            .exchange("/cliente", HttpMethod.POST, entity, String.class);
        JSONObject output = new JSONObject(response.getBody());

        assertTrue("Carlos3000".equals(output.getString("nombre")));
        assertTrue(HttpStatus.CREATED == response.getStatusCode());
    }

}
