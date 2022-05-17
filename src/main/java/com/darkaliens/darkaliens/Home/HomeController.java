package com.darkaliens.darkaliens.Home;

import com.darkaliens.darkaliens.AddFlight.AddFlightController;
import com.darkaliens.darkaliens.ComboboxWithTitle;
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
    String[] airportCodes = new String[]{"DEB", "BUD", "VIE", "FRA", "BER"};
    ComboboxWithTitle fromField = new ComboboxWithTitle("From", airportCodes);
    ComboboxWithTitle toField = new ComboboxWithTitle("To", airportCodes);
    VBox departDatePicker = createDatePicker();

    String[] cabinClasses = new String[]{"First class", "Business class", "Premium economy", "Economy"};
    ComboboxWithTitle classComboBox = new ComboboxWithTitle("Cabin class", cabinClasses);
    classComboBox.comboBox.getSelectionModel().select(2);

    searchFieldsContainer.getChildren().add(fromField);
    searchFieldsContainer.getChildren().add(toField);
    searchFieldsContainer.getChildren().add(departDatePicker);
    searchFieldsContainer.getChildren().add(classComboBox);

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
}
