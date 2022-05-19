package com.darkaliens.darkaliens;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class BackButton extends Button {
  public BackButton() {
    super("Go back");
    VBox.setMargin(this, new Insets(0,0,20,0));
  }
}
