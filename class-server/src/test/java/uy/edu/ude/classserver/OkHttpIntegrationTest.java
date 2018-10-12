package uy.edu.ude.classserver;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.Test;

public class OkHttpIntegrationTest {

  @Test
  public void givenAuthUser_whenGetDepartamentos_thenGetResponseOk() throws IOException {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
        .url("http://localhost:8080/departamento")
        .header("Authorization", Credentials.basic("estudiante", "estudiante"))
        .build();

    Response response = client.newCall(request).execute();

    assertThat(response.code()).isEqualTo(200);
    response.close();
  }

  @Test
  public void givenUnAuthUser_whenGetDepartamento_thenGetResponse401() throws IOException {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
        .url("http://localhost:8080/departamento")
        .header("Authorization", Credentials.basic("bad", "wrong"))
        .build();

    Response response = client.newCall(request).execute();
    assertThat(response.code()).isEqualTo(401);
    response.close();
  }

  @Test
  public void givenEstudiante_whenPostDepartamento_thenGetResponse403() throws IOException {
    OkHttpClient client = new OkHttpClient();
    MediaType JSON
        = MediaType.parse("application/json; charset=utf-8");
    String json = "{\"nombre\":\"Prueba\"}";
    RequestBody body = RequestBody.create(JSON, json);
    Request request = new Request.Builder()
        .url("http://localhost:8080/departamento")
        .header("Authorization", Credentials.basic("estudiante", "estudiante"))
        .post(body).build();

    Response response = client.newCall(request).execute();
    assertThat(response.code()).isEqualTo(403);
    response.close();
  }

  @Test
  public void givenProfesor_whenPostEstudiante_thenGetResponse201() throws IOException {
    OkHttpClient client = new OkHttpClient();
    MediaType JSON
        = MediaType.parse("application/json; charset=utf-8");
    String json = " {"
        + "    \"nombre\": \"Carlos3000\","
        + "    \"telefono\": \"092651651\","
        + "    \"email\": \"carlos2@test.com\","
        + "    \"direccion\": \"Mi Casa\","
        + "    \"departamento\":\"http://localhost:8080/departamento/3\""
        + "}";
    RequestBody body = RequestBody.create(JSON, json);
    Request request = new Request.Builder()
        .url("http://localhost:8080/estudiante")
        .header("Authorization", Credentials.basic("profesor", "profesor"))
        .post(body).build();
    Response response = client.newCall(request).execute();

    assertThat(response.code()).isEqualTo(201);
    response.close();
  }
}
