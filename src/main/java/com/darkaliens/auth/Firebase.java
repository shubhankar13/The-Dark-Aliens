package com.darkaliens.auth;

import com.darkaliens.darkaliens.SystemProperties;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Firebase {
  private static final String apiKey = "AIzaSyCj_z3Ubhf6ckuANEVW1RTk6GH6ysMzSyw";
  private static final String errorKeyName = "error";

  public static void init() {
    String currentPath = new File(".").getAbsolutePath();
    File file = new File(currentPath + "/ServiceAccountKey.json");
    FileInputStream serviceAccount = null;

    try {
      serviceAccount = new FileInputStream(file);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    FirebaseOptions options;

    try {
      options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .build();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    FirebaseApp.initializeApp(options);
  }

  public static String signUp(String firstName, String lastName, String email, String password) {
    if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
      return "INCOMPLETE_FORM";
    }

    HttpPost post = new HttpPost("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + apiKey);

    setPostParameters(email, password, post);

    try (CloseableHttpClient httpClient = HttpClients.createDefault();
         CloseableHttpResponse response = httpClient.execute(post)) {

      String jsonString = EntityUtils.toString(response.getEntity());
      JSONObject jsonObject = new JSONObject(jsonString);
      boolean hasError = jsonObject.has(errorKeyName);

      if (hasError) {
        String errorMessage = (String) jsonObject.getJSONObject(errorKeyName).get("message");
        if (errorMessage.contains("WEAK_PASSWORD")) {
          return "WEAK_PASSWORD";
        }
        return errorMessage;
      }

      SystemProperties systemProperties = new SystemProperties();
      String token = jsonObject.getString("idToken");
      String uid = jsonObject.getString("localId");
      String refreshToken = jsonObject.getString("refreshToken");

      systemProperties.setProperties(token, uid, refreshToken);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return "UNKNOWN";
  }

  public static String login(String email, String password) {
    if (email.isEmpty() || password.isEmpty()) {
      return "INCOMPLETE_FORM";
    }

    HttpPost post = new HttpPost("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + apiKey);

    setPostParameters(email, password, post);

    try (CloseableHttpClient httpClient = HttpClients.createDefault();
         CloseableHttpResponse response = httpClient.execute(post)) {
      String jsonString = EntityUtils.toString(response.getEntity());
      JSONObject jsonObject = new JSONObject(jsonString);

      boolean hasError = jsonObject.has(errorKeyName);

      if (hasError) {
        String errorMessage = (String) jsonObject.getJSONObject(errorKeyName).get("message");
        if (errorMessage.contains("INVALID_PASSWORD")) {
          return "INVALID_PASSWORD";
        }
        if (errorMessage.contains("TOO_MANY_ATTEMPTS_TRY_LATER")) {
          return "TOO_MANY_ATTEMPTS_TRY_LATER";
        }

        return errorMessage;
      }
      System.out.println(jsonObject);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return "UNKNOWN";
  }

  private static void setPostParameters(String email, String password, HttpPost post) {
    List<NameValuePair> urlParameters = new ArrayList<>();

    urlParameters.add(new BasicNameValuePair("email", email));
    urlParameters.add(new BasicNameValuePair("password", password));

    try {
      post.setEntity(new UrlEncodedFormEntity(urlParameters));
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

}
