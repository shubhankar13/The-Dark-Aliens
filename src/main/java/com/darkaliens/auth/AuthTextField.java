package com.darkaliens.auth;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;

public class AuthTextField extends TextField {
  public AuthTextField(String promptText) {
    setPromptText(promptText);
    setStyle("-fx-background-color: transparent; -fx-border-radius: 5px; -fx-border-color: #44474e; -fx-text-fill: white;");
    setPadding(new Insets(12));
  }
}
