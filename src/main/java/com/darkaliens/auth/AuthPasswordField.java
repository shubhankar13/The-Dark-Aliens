package com.darkaliens.auth;

import javafx.geometry.Insets;
import javafx.scene.control.PasswordField;

public class AuthPasswordField extends PasswordField {
  public AuthPasswordField() {
    setPromptText("Password");
    setStyle("-fx-border-radius: 5px;");
    setPadding(new Insets(12));
  }
}
