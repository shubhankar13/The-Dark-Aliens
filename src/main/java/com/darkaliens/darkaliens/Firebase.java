package com.darkaliens.darkaliens;

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

  public static void init() {
    String currentPath = new File(".").getAbsolutePath();
    File file = new File(currentPath + "/ServiceAccountKey.json");
    FileInputStream serviceAccount = null;

    try {
      serviceAccount = new FileInputStream(file);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    FirebaseOptions options = null;

    try {
      options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .build();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    FirebaseApp.initializeApp(options);
  }

  public static void signUp(String firstName, String lastName, String email, String password) {
    HttpPost post = new HttpPost("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + apiKey);

    setPostParameters(email, password, post);

    try (CloseableHttpClient httpClient = HttpClients.createDefault();
         CloseableHttpResponse response = httpClient.execute(post)) {

      String jsonString = EntityUtils.toString(response.getEntity());
      JSONObject jsonObject = new JSONObject(jsonString);

      System.out.println(jsonString);
      System.out.println(jsonObject.getJSONObject("error").get("message"));

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void login(String email, String password) {
    HttpPost post = new HttpPost("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + apiKey);

    setPostParameters(email, password, post);

    try (CloseableHttpClient httpClient = HttpClients.createDefault();
         CloseableHttpResponse response = httpClient.execute(post)) {

      System.out.println(EntityUtils.toString(response.getEntity()));

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
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
