package com.darkaliens.darkaliens.Home;

import com.darkaliens.darkaliens.MainApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

public class HomeController {
  private static final Stage primaryStage = MainApplication.stage;
  private static final Integer minHeight = 40;

  public static void showScene() {
    VBox root = new VBox();
    root.setId("root-background");
    root.setSpacing(10);
    root.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(20));
    String css = Objects.requireNonNull(MainApplication.class.getResource("home.css")).toExternalForm();

    HBox headerContainer = new HBox();
    Button viewAccountButton = new Button("View account");
    viewAccountButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
    viewAccountButton.setTextFill(Color.web("white"));
    headerContainer.getChildren().add(viewAccountButton);
    headerContainer.setAlignment(Pos.CENTER_RIGHT);
    root.getChildren().add(headerContainer);

    VBox bodyContainer = new VBox();
    VBox.setMargin(bodyContainer, new Insets(0,100, 0, 100));
    VBox.setVgrow(bodyContainer, Priority.ALWAYS);

    RadioButton returnRadioButton = new RadioButton("Return");
    RadioButton oneWayRadioButton = new RadioButton("One way");
    ToggleGroup toggleGroup = new ToggleGroup();

    returnRadioButton.setToggleGroup(toggleGroup);
    oneWayRadioButton.setToggleGroup(toggleGroup);
    returnRadioButton.setTextFill(Color.web("white"));
    oneWayRadioButton.setTextFill(Color.web("white"));
    returnRadioButton.setSelected(true);

    HBox ticketTypeContainer = new HBox(returnRadioButton, oneWayRadioButton);
    ticketTypeContainer.setSpacing(20);

    VBox searchContainer = new VBox();
    searchContainer.getChildren().add(ticketTypeContainer);
    searchContainer.setPadding(new Insets(20));
    searchContainer.setStyle("-fx-background-color: #0e1016; -fx-background-radius: 5px");
    searchContainer.setMinHeight(100);
    searchContainer.setSpacing(20);

    HBox searchFieldsContainer = new HBox();
    VBox fromField = createLocationTextField("From");
    VBox toField = createLocationTextField("To");
    VBox departDatePicker = createDatePicker("Depart");
    VBox returnDatePicker = createDatePicker("Return");

    Label cabinClassLabel = new Label("Cabin class");
    MenuItem firstClass = new MenuItem("First class");
    MenuItem businessClass = new MenuItem("Business class");
    MenuItem premiumEconomy = new MenuItem("Premium economy");
    MenuItem economy = new MenuItem("Economy");
    MenuButton menuButton = new MenuButton("Select a class", null, firstClass, businessClass, premiumEconomy, economy);
    VBox classContainer = new VBox(cabinClassLabel, menuButton);
    HBox.setHgrow(classContainer, Priority.ALWAYS);
    menuButton.setMaxWidth(Double.MAX_VALUE);
    cabinClassLabel.setTextFill(Color.web("white"));

    menuButton.setPrefHeight(minHeight);
    classContainer.setSpacing(10);

    searchFieldsContainer.getChildren().add(fromField);
    searchFieldsContainer.getChildren().add(toField);
    searchFieldsContainer.getChildren().add(departDatePicker);
    searchFieldsContainer.getChildren().add(returnDatePicker);
    searchFieldsContainer.getChildren().add(classContainer);

    Button searchButton = new Button("Search flights");
    searchButton.setPadding(new Insets(10,20,10,20));
    searchButton.setTextFill(Color.web("white"));
    searchButton.setStyle("-fx-background-color: #00a698; -fx-cursor: hand");
    HBox searchButtonContainer = new HBox(searchButton);
    searchButtonContainer.setAlignment(Pos.CENTER_RIGHT);

    searchContainer.getChildren().add(searchFieldsContainer);
    searchContainer.getChildren().add(searchButtonContainer);

    Label largeTitle = new Label("One more step towards your dream destination");
    largeTitle.setFont(Font.font(Font.getDefault().getFamily(), 33));
    largeTitle.setTextFill(Color.web("white"));

    bodyContainer.setSpacing(minHeight);
    bodyContainer.getChildren().add(largeTitle);
    bodyContainer.getChildren().add(searchContainer);
    bodyContainer.setPadding(new Insets(20));
    bodyContainer.setMaxWidth(1000);
    bodyContainer.setAlignment(Pos.CENTER_LEFT);
    root.getChildren().add(bodyContainer);

    primaryStage.show();
    primaryStage.setWidth(1200);
    primaryStage.setMinWidth(1000);
    primaryStage.setHeight(800);
    primaryStage.setMinHeight(800);
    Scene scene = new Scene(root);
    scene.getStylesheets().add(css);
    primaryStage.setScene(scene);
    primaryStage.centerOnScreen();
  }

  private static VBox createDatePicker(String title) {
    DatePicker datePicker = new DatePicker();
    Label label = new Label(title);
    VBox vBox = new VBox(label, datePicker);
    HBox.setHgrow(vBox, Priority.ALWAYS);
    VBox.setVgrow(datePicker, Priority.ALWAYS);


    vBox.setSpacing(10);

    label.setTextFill(Color.web("white"));

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

    label.setTextFill(Color.web("white"));

    vBox.setSpacing(10);

    textField.setPromptText("Country, city or airport");
    textField.setMinHeight(minHeight);
    textField.setStyle("-fx-border-radius: 0px; -fx-background-radius: 0px");

    return vBox;
  }
}
