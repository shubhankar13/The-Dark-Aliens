package com.darkaliens.darkaliens.AddFlight;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class AddFlightAlert extends Alert {
  public final ButtonType buttonTypeOne = new ButtonType("Go home");
  public final ButtonType buttonTypeTwo = new ButtonType("Create a new flight");

  public AddFlightAlert() {
    super(AlertType.CONFIRMATION);

    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

    setTitle("Flight added successfully!");
    setHeaderText("What would you like to do next?");
    getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
  }
}
