package com.darkaliens.darkaliens.AddFlight;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddFlightTextField extends VBox {
  public TextField textField;

  public AddFlightTextField(String labelTitle, String textFieldPlaceholder) {
    Label label = new Label(labelTitle);

    textField = new TextField();
    textField.setPromptText(textFieldPlaceholder);
    textField.setStyle("-fx-border-radius: 5px;");
    textField.setPadding(new Insets(12));

    setSpacing(10);
    getChildren().addAll(label, textField);
  }

  public String getText() {
    return textField.getText();
  }
}
