package com.darkaliens.auth;

import com.darkaliens.User;
import com.darkaliens.darkaliens.Home.HomeController;
import com.darkaliens.darkaliens.SystemProperties;
import com.darkaliens.mongo.Database;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import javafx.scene.control.Alert;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
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

      MongoCollection<Document> collection = Database.getUsersCollection();
      Document document = new Document();
      InsertOneResult result = collection.insertOne(document
                                                      .append("first_name", firstName)
                                                      .append("last_name", lastName)
                                                      .append("email", email)
                                                      .append("token", token)
                                                      .append("refresh_token", refreshToken)
                                                      .append("uid", uid)
      );

      if (result.wasAcknowledged()) {
        User user = User.getInstance();
        Boolean success = user.getUser(uid);

        if (success) {
          HomeController.showScene();
          System.out.println("Sign up inserted id.");
          System.out.println(result.getInsertedId());

          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("You're signed up!");
          alert.showAndWait();
        }
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return "SUCCESS";

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

      String token = jsonObject.getString("idToken");
      String uid = jsonObject.getString("localId");
      String refreshToken = jsonObject.getString("refreshToken");

      Document query = new Document().append("uid", uid);
      Bson updates = Updates.combine(
        Updates.set("refresh_token", refreshToken),
        Updates.set("token", token)
      );

      System.out.println(uid);

      try {
        MongoCollection<Document> collection = Database.getUsersCollection();
        collection.updateOne(query, updates);
        System.setProperty("uid", uid);

        User user = User.getInstance();

        Boolean success = user.getUser(uid);

        if (success) {
          HomeController.showScene();
        }

      } catch (MongoException me) {
        System.err.println("Unable to update due to an error: " + me);
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return "SUCCESS";
  }

  public static String resetPassword(String email) {
    if (email.isEmpty()) {
      return "INCOMPLETE_FORM";
    }

    HttpPost post = new HttpPost("https://identitytoolkit.googleapis.com/v1/accounts:sendOobCode?key=" + apiKey);

    setPostParameters(email, post);

    try (CloseableHttpClient httpClient = HttpClients.createDefault();
         CloseableHttpResponse response = httpClient.execute(post)) {
      String jsonString = EntityUtils.toString(response.getEntity());
      JSONObject jsonObject = new JSONObject(jsonString);

      boolean hasError = jsonObject.has(errorKeyName);

      if (hasError) {
        String errorMessage = (String) jsonObject.getJSONObject(errorKeyName).get("message");
        if (errorMessage.contains("EMAIL_NOT_FOUND")) {
          return "EMAIL_NOT_FOUND";
        }

        return errorMessage;
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return "SUCCESS";
  }

  private static void setPostParameters(String email, String password, HttpPost post) {
    List<NameValuePair> urlParameters = new ArrayList<>();

    urlParameters.add(new BasicNameValuePair("email", email));
    urlParameters.add(new BasicNameValuePair("password", password));
    urlParameters.add(new BasicNameValuePair("returnSecureToken", "true"));

    try {
      post.setEntity(new UrlEncodedFormEntity(urlParameters));
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  private static void setPostParameters(String email, HttpPost post) {
    List<NameValuePair> urlParameters = new ArrayList<>();

    urlParameters.add(new BasicNameValuePair("email", email));
    urlParameters.add(new BasicNameValuePair("requestType", "PASSWORD_RESET"));

    try {
      post.setEntity(new UrlEncodedFormEntity(urlParameters));
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }


}
