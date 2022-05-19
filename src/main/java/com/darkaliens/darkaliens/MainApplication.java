package com.darkaliens.darkaliens;

import com.darkaliens.User;
import com.darkaliens.auth.Firebase;
import com.darkaliens.darkaliens.Home.HomeController;
import com.darkaliens.mongo.Database;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Locale;

public class MainApplication extends Application {

  public static Stage stage;

  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    Firebase.init();
    Database.connect();
    User user = User.getInstance();

    SystemProperties e;

    try {
      FileInputStream fileIn = new FileInputStream("/tmp/uid.ser");
      ObjectInputStream in = new ObjectInputStream(fileIn);
      e = (SystemProperties) in.readObject();
      in.close();
      fileIn.close();

      if (e.uid != null && !e.uid.isEmpty()) {
        user.getUser(e.uid);
      }


    } catch (IOException i) {
//      i.printStackTrace();
    } catch (ClassNotFoundException c) {
      System.out.println("Class not found");
      c.printStackTrace();
    }


    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    MainApplication.stage = primaryStage;
//    SignUpController.showScene();
//    LoginController.showScene();
    HomeController.showScene();
//    AddFlightController.showScene();
//    FlightSearchResultsController.showScene();
//    ViewAccount.showScene();
  }
}