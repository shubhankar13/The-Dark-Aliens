package com.darkaliens.darkaliens;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StageManager {
  static Stage primaryStage = MainApplication.stage;

  public static void setScene(String sceneName, String stageTile) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(sceneName));
      Scene scene = new Scene(fxmlLoader.load(), 800, 600);
      String css = Objects.requireNonNull(MainApplication.class.getResource("global.css")).toExternalForm();

      scene.getStylesheets().add(css);
      primaryStage.setTitle(stageTile);
      primaryStage.setScene(scene);
      primaryStage.show();
      primaryStage.centerOnScreen();
    } catch (IOException ignored) {
      System.out.println("Could not load " + sceneName);
    }
  }

  public static void newSetScene(Parent root, String title) {
    Scene scene = new Scene(root);

    setSceneGeneral(title, scene);
  }

  public static void newSetScene(Parent root, String title, String css) {
    Scene scene = new Scene(root);
    scene.getStylesheets().add(css);

    setSceneGeneral(title, scene);
  }

  private static void setSceneGeneral(String title, Scene scene) {
    primaryStage.show();
    primaryStage.setWidth(1200);
    primaryStage.setMinWidth(1000);
    primaryStage.setHeight(800);
    primaryStage.setMinHeight(800);
    primaryStage.setScene(scene);
    primaryStage.centerOnScreen();
    primaryStage.setTitle(title);
  }

  public static void goToLoginScene() {
    StageManager.setScene("login-view.fxml", "Log in");
  }
}
