module com.darkaliens.darkaliens {
  requires javafx.controls;
  requires javafx.fxml;
  requires com.google.auth.oauth2;
  requires com.google.auth;
  requires com.google.api.apicommon;
  requires google.cloud.storage;
  requires google.cloud.firestore;
  requires firebase.admin;
  requires google.api.client;
  requires google.cloud.core;
  requires com.google.api.client.auth;
  requires org.apache.httpcomponents.httpclient;
  requires org.apache.httpcomponents.httpcore;
  requires org.json;
  requires org.mongodb.bson;
  requires org.mongodb.driver.sync.client;
  requires org.mongodb.driver.core;

  opens com.darkaliens.darkaliens to javafx.fxml;
  exports com.darkaliens.darkaliens;
  exports com.darkaliens.auth;
  opens com.darkaliens.auth to javafx.fxml;
  exports com.darkaliens.darkaliens.Home;
  opens com.darkaliens.darkaliens.Home to javafx.fxml;
}