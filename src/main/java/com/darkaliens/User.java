package com.darkaliens;

import com.darkaliens.darkaliens.Home.HomeController;
import com.darkaliens.darkaliens.SystemProperties;
import com.darkaliens.mongo.Database;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class User {
  private static User instance = null;

  public String firstName = "";
  public String lastName = "";
  public String email = "";
  public String _id = "";
  public String uid  = "";
  public String token = "";
  public String refreshToken = "";

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String get_id() {
    return _id;
  }

  public String getUid() {
    return uid;
  }

  public String getToken() {
    return token;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public static User getInstance() {
    if (instance == null) {
      instance = new User();
    }
    return instance;
  }

  public Boolean getUser(String uid) {
    try {
      MongoCollection<Document> collection = Database.getUsersCollection();
      Document userDoc = collection.find(Filters.eq("uid", uid))
        .first();
      assert userDoc != null;

      this.setFirstName((String) userDoc.get("first_name"));
      this.setLastName((String) userDoc.get("last_name"));
      this.setUid((String) userDoc.get("uid"));
      this.setEmail((String) userDoc.get("email"));
      this.setToken((String) userDoc.get("token"));
      this.setRefreshToken((String) userDoc.get("refresh_token"));

      SystemProperties e = new SystemProperties();
      e.uid = uid;

      try {
        FileOutputStream fileOut =
          new FileOutputStream("/tmp/uid.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(e);
        out.close();
        fileOut.close();
        System.out.print("Serialized data is saved in /tmp/uid.ser");
      } catch (IOException i) {
        i.printStackTrace();
      }

      return true;

    } catch (MongoException me) {
      System.err.println("Unable to update due to an error: " + me);

      return false;
    }
  }


}
