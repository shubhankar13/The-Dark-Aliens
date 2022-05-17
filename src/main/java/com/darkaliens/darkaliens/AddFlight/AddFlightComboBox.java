package com.darkaliens.darkaliens.AddFlight;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AddFlightComboBox extends VBox {

  ComboBox<String> comboBox;

  public AddFlightComboBox(String labelTitle, String... list) {
    Label label = new Label(labelTitle);
    ObservableList<String> options = FXCollections.observableArrayList(list);

    comboBox = new ComboBox<>(options);
    comboBox.setPrefWidth(Double.MAX_VALUE);

    setSpacing(10);
    getChildren().addAll(label, comboBox);
  }

  public String getValue() {
    return comboBox.getValue();
  }
}
