package uy.edu.ude.classserver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
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

    try (OutputStreamWriter out = new OutputStreamWriter(httpURLConnection.getOutputStream())) {
      out.write(json);
    }

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
    try (OutputStreamWriter out = new OutputStreamWriter(httpURLConnection.getOutputStream())) {
      out.write(json);
    }

    try (Scanner in = new Scanner(httpURLConnection.getInputStream())) {
      while (in.hasNext()) {
        result.append(in.nextLine());
      }
    }

    assertThat(httpURLConnection.getResponseCode()).isEqualTo(201);
  }


  @Test
  public void givenProfesor_whenPutEstudiante_thenGetResponse200() throws IOException {
    String json = " {"
        + "    \"nombre\": \"CarlosHttp\","
        + "    \"telefono\": \"09200000\","
        + "    \"email\": \"carlos.http@test.com\","
        + "    \"direccion\": \"Mi Casa http\","
        + "    \"departamento\":\"http://localhost:8080/departamento/3\""
        + "}";
    StringBuffer result = new StringBuffer();
    URL url = new URL("http://localhost:8080/estudiante/1");
    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
    httpURLConnection.setDoOutput(true);
    httpURLConnection.setRequestMethod("PUT");
    httpURLConnection.setRequestProperty("Content-Type", "application/json");
    httpURLConnection.setRequestProperty("charset", "utf-8");
    httpURLConnection.setRequestProperty("authorization", "Basic cHJvZmVzb3I6cHJvZmVzb3I=");
    try (OutputStreamWriter out = new OutputStreamWriter(httpURLConnection.getOutputStream())) {
      out.write(json);
    }

    try (Scanner in = new Scanner(httpURLConnection.getInputStream())) {
      while (in.hasNext()) {
        result.append(in.nextLine());
      }
    }

    assertSoftly(softly -> {
      try {
        softly.assertThat(httpURLConnection.getResponseCode()).isEqualTo(200);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      softly.assertThat(result).isNotBlank();
    });
  }

  @Test
  public void givenProfesor_whenDeleteEstudiante_thenGetResponse204()
      throws IOException {
    URL url = new URL("http://localhost:8080/estudiante/1");
    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
    httpURLConnection.setRequestMethod("DELETE");
    httpURLConnection.setRequestProperty("Content-Type", "application/json");
    httpURLConnection.setRequestProperty("charset", "utf-8");
    //Generación de HTTP Basic encoding
    String basicAuth = "Basic " +
        Base64.getEncoder().encodeToString("profesor:profesor".getBytes());
    httpURLConnection.setRequestProperty("Authorization", basicAuth);
    //En Android se puede usar así:
    //String basicAuth = "Basic " +
    //Base64.encodeToString("user:password".getBytes(),Base64.NO_WRAP);

    httpURLConnection.setRequestProperty("authorization", basicAuth);

    assertThat(httpURLConnection.getResponseCode()).isEqualTo(204);
  }
}
