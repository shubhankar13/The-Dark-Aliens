package com.darkaliens.darkaliens;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageManager {
  static Stage primaryStage = MainApplication.stage;

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
}
