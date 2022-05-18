package com.darkaliens.auth;

import javafx.geometry.Insets;
import javafx.scene.control.Button;

public class AuthContinueButton extends Button {
  public AuthContinueButton() {

    setText("Continue");
    setPadding(new Insets(10));
    setMaxWidth(Double.MAX_VALUE);
  }
}
