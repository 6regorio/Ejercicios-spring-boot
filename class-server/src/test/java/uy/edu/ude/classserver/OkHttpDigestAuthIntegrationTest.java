package uy.edu.ude.classserver;

import java.io.IOException;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import org.junit.Test;

public class OkHttpDigestAuthIntegrationTest {

  @Test
  public void test() throws IOException {
    final OkHttpClient client = new OkHttpClient.Builder()
      .authenticator((route, response) -> {
        if (response.request().header("Authorization") != null) {
          return null; // Give up, we've already attempted to authenticate.
        }
        System.out.println("Authenticating for response: " + response);
        System.out.println("Challenges: " + response.challenges());
        String credential = Credentials.basic("estudiante", "estudiante");
        return response.request().newBuilder()
          .header("Authorization", credential)
          .build();
      })
      .build();

    Request request = new Request.Builder()
      .url("http://localhost:8080/departamento")
      .build();
    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Unexpected code " + response);
      }

      System.out.println(response.body().string());
    }
  }
}
