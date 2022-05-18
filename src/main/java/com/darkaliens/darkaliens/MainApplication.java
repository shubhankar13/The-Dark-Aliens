package com.darkaliens.darkaliens;

import com.darkaliens.auth.Firebase;
import com.darkaliens.darkaliens.AddFlight.AddFlightController;
import com.darkaliens.darkaliens.FlightSearchResults.FlightSearchResultsController;
import com.darkaliens.darkaliens.Home.HomeController;
import com.darkaliens.mongo.Database;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Locale;

public class MainApplication extends Application {

  public static Stage stage;

  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    Firebase.init();
    Database.connect();

    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    MainApplication.stage = primaryStage;
//    SignUpController.showScene();
//    LoginController.showScene();
//    HomeController.showScene();
//    AddFlightController.showScene();
    FlightSearchResultsController.showScene();
  }
}