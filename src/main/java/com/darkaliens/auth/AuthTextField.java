package com.darkaliens.auth;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;

public class AuthTextField extends TextField {
  public AuthTextField(String promptText) {
    setPromptText(promptText);
    setStyle("-fx-border-radius: 5px;");
    setPadding(new Insets(12));
  }
}
