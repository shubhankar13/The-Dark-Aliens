package com.darkaliens.auth;

import javafx.geometry.Insets;
import javafx.scene.control.PasswordField;

public class AuthPasswordField extends PasswordField {
  public AuthPasswordField() {
    setPromptText("Password");
    setStyle("-fx-background-color: transparent; -fx-border-radius: 5px; -fx-border-color: #44474e; -fx-text-fill: white;");
    setPadding(new Insets(12));
  }
}
