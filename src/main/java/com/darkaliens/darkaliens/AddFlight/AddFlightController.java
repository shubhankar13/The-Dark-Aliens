package com.darkaliens.darkaliens.AddFlight;

import com.darkaliens.darkaliens.StageManager;
import javafx.scene.layout.VBox;

public class AddFlightController {
  public static void showScene() {
    VBox root = new VBox();
    root.setStyle("-fx-background-color: #0e1016");
    StageManager.newSetScene(root, "Add a new flight");
  }
}
