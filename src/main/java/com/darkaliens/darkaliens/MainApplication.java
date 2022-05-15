package com.darkaliens.darkaliens;

import com.darkaliens.auth.Firebase;
import com.darkaliens.darkaliens.Home.HomeController;
import com.darkaliens.mongo.Database;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {

  public static Stage stage;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    Firebase.init();
    Database.connect();

    MainApplication.stage = primaryStage;
//    SignUpController.showScene();
//    LoginController.showScene();
    HomeController.showScene();
  }
}