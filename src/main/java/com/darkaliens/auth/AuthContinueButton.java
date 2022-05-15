package com.darkaliens.auth;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class AuthContinueButton extends Button {
  public AuthContinueButton() {

    setText("Continue");
    setStyle("-fx-background-color: #4687ff; -fx-cursor: hand;");
    setPadding(new Insets(10));
    setTextFill(Color.web("white"));
    setMaxWidth(Double.MAX_VALUE);
  }
}
