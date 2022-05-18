package com.darkaliens.darkaliens;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class ErrorMessage extends Label {
  public ErrorMessage() {
    super("All fields are required.");

    setStyle("-fx-background-color: #FFEBE9; -fx-border-color: rgba(255,129,130,0.4); -fx-border-radius: 5px;");
    setPrefWidth(Double.MAX_VALUE);
    setPadding(new Insets(16));
    setManaged(false);
    setVisible(false);
  }
}
