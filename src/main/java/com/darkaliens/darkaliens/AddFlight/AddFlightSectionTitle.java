package com.darkaliens.darkaliens.AddFlight;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AddFlightSectionTitle extends Label {
  public AddFlightSectionTitle(String text) {
    super(text);

    VBox.setMargin(this, new Insets(10, 0, 0, 0));

    setPrefWidth(Double.MAX_VALUE);
    setStyle("-fx-font-size: 20px");
  }
}
