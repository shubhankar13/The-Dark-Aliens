package com.darkaliens.darkaliens.Home;

import com.darkaliens.darkaliens.AddFlight.AddFlightController;
import com.darkaliens.darkaliens.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.time.LocalDate;

public class HomeController {
  private static final Integer minHeight = 40;

  public static void showScene() {
    VBox root = new VBox();
    root.setId("root-background");
    root.setSpacing(10);
    root.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(20));

    HBox headerContainer = new HBox();
    Button viewAccountButton = new Button("View account");

    Button addAFlightButton = new Button("Add a flight");
    addAFlightButton.setOnAction(event -> {
      AddFlightController.showScene();
    });

    headerContainer.setSpacing(10);
    headerContainer.getChildren().add(addAFlightButton);
    headerContainer.getChildren().add(viewAccountButton);
    headerContainer.setAlignment(Pos.CENTER_RIGHT);
    root.getChildren().add(headerContainer);

    VBox bodyContainer = new VBox();
    VBox.setMargin(bodyContainer, new Insets(0,100, 0, 100));
    VBox.setVgrow(bodyContainer, Priority.ALWAYS);

    VBox searchContainer = new VBox();
    searchContainer.setStyle("-fx-background-radius: 5px");
    searchContainer.setMinHeight(100);
    searchContainer.setSpacing(20);

    HBox searchFieldsContainer = new HBox();
    VBox fromField = createLocationTextField("From");
    VBox toField = createLocationTextField("To");
    VBox departDatePicker = createDatePicker();

    Label cabinClassLabel = new Label("Cabin class");
    ObservableList<String> options = FXCollections.observableArrayList("First class", "Business class", "Premium economy", "Economy");
    ComboBox<String> classComboBox = new ComboBox<>(options);
    classComboBox.getSelectionModel().select(2);

    VBox classContainer = new VBox(cabinClassLabel, classComboBox);
    classComboBox.setPrefHeight(minHeight);
    classContainer.setSpacing(10);

    searchFieldsContainer.getChildren().add(fromField);
    searchFieldsContainer.getChildren().add(toField);
    searchFieldsContainer.getChildren().add(departDatePicker);
    searchFieldsContainer.getChildren().add(classContainer);

    Button searchButton = new Button("Search flights");
    searchButton.setPadding(new Insets(10,20,10,20));
    searchButton.setTextFill(Color.web("white"));
    searchButton.setStyle("-fx-background-color: #00a698;");
    HBox searchButtonContainer = new HBox(searchButton);
    searchButtonContainer.setAlignment(Pos.CENTER_RIGHT);

    searchContainer.getChildren().add(searchFieldsContainer);
    searchContainer.getChildren().add(searchButtonContainer);
    Label largeTitle = new Label("One more step towards your dream destination");
    largeTitle.setFont(Font.font(Font.getDefault().getFamily(), 30));

    bodyContainer.setSpacing(minHeight);
    bodyContainer.getChildren().add(largeTitle);
    bodyContainer.getChildren().add(searchContainer);
    bodyContainer.setPadding(new Insets(20));
    bodyContainer.setMaxWidth(800);
    bodyContainer.setAlignment(Pos.CENTER_LEFT);
    root.getChildren().add(bodyContainer);

    StageManager.newSetScene(root, "Home");
  }

  private static VBox createDatePicker() {
    DatePicker datePicker = new DatePicker();
    Label label = new Label("Depart");
    VBox vBox = new VBox(label, datePicker);
    HBox.setHgrow(vBox, Priority.ALWAYS);
    VBox.setVgrow(datePicker, Priority.ALWAYS);

    vBox.setSpacing(10);

    datePicker.setValue(LocalDate.now());
    datePicker.setEditable(false);
    datePicker.setMinHeight(minHeight);
    datePicker.setStyle("-fx-border-radius: 0px; -fx-background-radius: 0px");
    datePicker.setMaxWidth(Double.MAX_VALUE);

    return vBox;
  }

  private static VBox createLocationTextField(String title) {
    TextField textField = new TextField();
    Label label = new Label(title);
    VBox vBox = new VBox(label, textField);
    HBox.setHgrow(vBox, Priority.ALWAYS);

    vBox.setSpacing(10);

    textField.setPromptText("Country, city or airport");
    textField.setMinHeight(minHeight);
    textField.setStyle("-fx-border-radius: 0px; -fx-background-radius: 0px");

    return vBox;
  }
}
