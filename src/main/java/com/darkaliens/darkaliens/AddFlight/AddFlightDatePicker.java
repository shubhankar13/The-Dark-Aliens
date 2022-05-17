package com.darkaliens.darkaliens.AddFlight;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class AddFlightDatePicker extends VBox {
  public static DatePicker datePicker;
  public static Label label;

  public AddFlightDatePicker(String labelTitle) {
    LocalDate now = LocalDate.now();

    label = new Label();
    label.setText(labelTitle);

    datePicker = new DatePicker();
    datePicker.setValue(now);
    datePicker.setPromptText(now.toString());
    datePicker.setMaxWidth(Double.MAX_VALUE);
    datePicker.setPrefHeight(40);

    setSpacing(10);
    getChildren().addAll(label, datePicker);
  }

  public String getDate() {
    return datePicker.getValue().toString();
  }
}
