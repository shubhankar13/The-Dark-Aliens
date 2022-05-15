package com.darkaliens.darkaliens;

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
    SignUpController.showScene();
  }
}