package uy.edu.ude.classserver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

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
  public void givenAuthUser_whenGetUsuario_thenGetResponseOk() throws IOException {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
        .url("http://localhost:8080/usuario")
        .header("Authorization", Credentials.basic("estudiante", "estudiante"))
        .build();
    String rolExpected = "ROLE_ESTUDIANTE";

    Response response = client.newCall(request).execute();

    assertSoftly(softly -> {
      softly.assertThat(response.code()).isEqualTo(200);
      try {
        softly.assertThat(response.body().string()).contains(rolExpected);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
    response.close();
  }

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
  public void givenAuthUser_whenGetWrongUrl_thenGetResponse404() throws IOException {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
        .url("http://localhost:8080/departamentoxyz")
        .header("Authorization", Credentials.basic("estudiante", "estudiante"))
        .build();

    Response response = client.newCall(request).execute();

    assertThat(response.code()).isEqualTo(404);
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

  @Test
  public void givenProfesor_whenPostEstudianteWithInvalidEmail_thenGetResponse500()
      throws IOException {
    OkHttpClient client = new OkHttpClient();
    MediaType JSON
        = MediaType.parse("application/json; charset=utf-8");
    String json = " {"
        + "    \"nombre\": \"Carlos3000\","
        + "    \"telefono\": \"092651651\","
        + "    \"email\": \"carlos2-test*\","
        + "    \"direccion\": \"Mi Casa\","
        + "    \"departamento\":\"http://localhost:8080/departamento/3\""
        + "}";
    RequestBody body = RequestBody.create(JSON, json);
    Request request = new Request.Builder()
        .url("http://localhost:8080/estudiante")
        .header("Authorization", Credentials.basic("profesor", "profesor"))
        .post(body).build();
    Response response = client.newCall(request).execute();

    assertThat(response.code()).isEqualTo(500);
    response.close();
  }

  @Test
  public void givenProfesor_whenPostEstudianteWithInvalidTelefono_thenGetResponse500()
      throws IOException {
    OkHttpClient client = new OkHttpClient();
    MediaType JSON
        = MediaType.parse("application/json; charset=utf-8");
    String json = " {"
        + "    \"nombre\": \"Carlos3000\","
        + "    \"telefono\": \"\","
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

    assertThat(response.code()).isEqualTo(500);
    response.close();
  }

  @Test
  public void givenProfesor_whenPutEstudiante_thenGetResponse204()
      throws IOException {
    OkHttpClient client = new OkHttpClient();
    MediaType JSON
        = MediaType.parse("application/json; charset=utf-8");
    String json = " {"
        + "    \"nombre\": \"CarlosUpdated\","
        + "    \"telefono\": \"09200000\","
        + "    \"email\": \"carlos.updated@test.com\","
        + "    \"direccion\": \"Mi Casa Updated\","
        + "    \"departamento\":\"http://localhost:8080/departamento/3\""
        + "}";
    RequestBody body = RequestBody.create(JSON, json);
    Request request = new Request.Builder()
        .url("http://localhost:8080/estudiante/1")
        .header("Authorization", Credentials.basic("profesor", "profesor"))
        .put(body).build();
    Response response = client.newCall(request).execute();

    assertThat(response.code()).isEqualTo(204);
    response.close();
  }

  @Test
  public void givenProfesor_whenPatchEstudiante_thenGetResponse204()
      throws IOException {
    OkHttpClient client = new OkHttpClient();
    MediaType JSON
        = MediaType.parse("application/json; charset=utf-8");
    String json = "{\"nombre\": \"Carlos\"}";
    RequestBody body = RequestBody.create(JSON, json);
    Request request = new Request.Builder()
        .url("http://localhost:8080/estudiante/1")
        .header("Authorization", Credentials.basic("profesor", "profesor"))
        .patch(body).build();
    Response response = client.newCall(request).execute();

    assertThat(response.code()).isEqualTo(204);
    response.close();
  }

  @Test
  public void givenProfesor_whenDeleteEstudiante_thenGetResponse204()
      throws IOException {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
        .url("http://localhost:8080/estudiante/1")
        .header("Authorization", Credentials.basic("profesor", "profesor"))
        .delete().build();

    Response response = client.newCall(request).execute();

    assertThat(response.code()).isEqualTo(204);
    response.close();
  }
}
