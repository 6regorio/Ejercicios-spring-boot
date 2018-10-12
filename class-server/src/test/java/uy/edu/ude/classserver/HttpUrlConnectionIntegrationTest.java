package uy.edu.ude.classserver;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.junit.Test;

public class HttpUrlConnectionIntegrationTest {

  @Test
  public void givenAuthUser_whenGetDepartamentos_thenGetResponseOk() throws IOException {
    URL url = new URL("http://localhost:8080/departamento");
    StringBuffer result = new StringBuffer();
    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
    httpURLConnection.setRequestMethod("GET");
    httpURLConnection.setRequestProperty("Content-Type", "application/json");
    httpURLConnection.setRequestProperty("charset", "utf-8");
    httpURLConnection.setRequestProperty("authorization", "Basic ZXN0dWRpYW50ZTplc3R1ZGlhbnRl");

    assertThat(httpURLConnection.getResponseCode()).isEqualTo(200);
  }

  @Test
  public void givenUnAuthUser_whenGetDepartamento_thenGetResponse401() throws IOException {
    String json = "{\"nombre\":\"Prueba\"}";
    URL url = new URL("http://localhost:8080/departamento");
    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
    httpURLConnection.setRequestMethod("GET");
    httpURLConnection.setRequestProperty("Content-Type", "application/json");
    httpURLConnection.setRequestProperty("charset", "utf-8");

    assertThat(httpURLConnection.getResponseCode()).isEqualTo(401);
  }

  @Test
  public void givenEstudiante_whenPostDepartamento_thenGetResponse403() throws IOException {
    String json = "{\"nombre\":\"Prueba\"}";
    URL url = new URL("http://localhost:8080/departamento");
    StringBuffer result = new StringBuffer();
    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
    httpURLConnection.setDoOutput(true);
    httpURLConnection.setRequestMethod("POST");
    httpURLConnection.setRequestProperty("Content-Type", "application/json");
    httpURLConnection.setRequestProperty("charset", "utf-8");
    httpURLConnection.setRequestProperty("authorization", "Basic ZXN0dWRpYW50ZTplc3R1ZGlhbnRl");
    OutputStreamWriter out = new OutputStreamWriter(httpURLConnection.getOutputStream());
    out.write(json);
    out.close();

    assertThat(httpURLConnection.getResponseCode()).isEqualTo(403);
  }

  @Test
  public void givenProfesor_whenPostEstudiante_thenGetResponse201() throws IOException {
    String json = " {"
        + "    \"nombre\": \"Carlos3000\","
        + "    \"telefono\": \"092651651\","
        + "    \"email\": \"carlos2@test.com\","
        + "    \"direccion\": \"Mi Casa\","
        + "    \"departamento\":\"http://localhost:8080/departamento/3\""
        + "}";
    StringBuffer result = new StringBuffer();
    URL url = new URL("http://localhost:8080/estudiante");
    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
    httpURLConnection.setDoOutput(true);
    httpURLConnection.setRequestMethod("POST");
    httpURLConnection.setRequestProperty("Content-Type", "application/json");
    httpURLConnection.setRequestProperty("charset", "utf-8");
    httpURLConnection.setRequestProperty("authorization", "Basic cHJvZmVzb3I6cHJvZmVzb3I=");
    OutputStreamWriter out = new OutputStreamWriter(httpURLConnection.getOutputStream());
    out.write(json);
    out.close();

    try (Scanner in = new Scanner(httpURLConnection.getInputStream())) {
      while (in.hasNext()) {
        result.append(in.nextLine());
      }
    }

    assertThat(result.toString()).isNotNull();
  }
}
