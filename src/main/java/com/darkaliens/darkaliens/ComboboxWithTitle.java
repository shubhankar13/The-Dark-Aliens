package com.darkaliens.darkaliens;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ComboboxWithTitle extends VBox {

  public ComboBox<String> comboBox;

  public ComboboxWithTitle(String labelTitle, String... list) {
    Label label = new Label(labelTitle);
    ObservableList<String> options = FXCollections.observableArrayList(list);

    comboBox = new ComboBox<>(options);
    comboBox.setPrefHeight(40);
    comboBox.setMaxWidth(Double.MAX_VALUE);
    comboBox.setMinWidth(170);

    setSpacing(10);
    getChildren().addAll(label, comboBox);
  }

  public String getValue() {
    return comboBox.getValue();
  }
}
