package com.darkaliens.darkaliens;

import com.darkaliens.auth.Firebase;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {

  public static Stage stage;

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage primaryStage) {
    Firebase.init();

    MainApplication.stage = primaryStage;
//    SignUpController.showScene();
//    LoginController.showScene();
    HomeController.showScene();
  }
}