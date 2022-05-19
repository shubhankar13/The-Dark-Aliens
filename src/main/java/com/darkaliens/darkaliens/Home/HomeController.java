package com.darkaliens.darkaliens.Home;

import com.darkaliens.User;
import com.darkaliens.auth.LoginController;
import com.darkaliens.darkaliens.AddFlight.AddFlightController;
import com.darkaliens.darkaliens.AddFlight.AddFlightDatePicker;
import com.darkaliens.darkaliens.ComboboxWithTitle;
import com.darkaliens.darkaliens.ErrorMessage;
import com.darkaliens.darkaliens.FlightSearchResults.FlightSearchData;
import com.darkaliens.darkaliens.FlightSearchResults.FlightSearchResultsController;
import com.darkaliens.darkaliens.StageManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HomeController {
  private static final Integer minHeight = 40;

  public static void showScene() {
    User user = User.getInstance();

    VBox root = new VBox();
    root.setId("root-background");
    root.setSpacing(10);
    root.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(20));

    HBox headerContainer = new HBox();
    Button viewAccountButton = new Button("View account");
    Button loginButton = new Button("Log in");
    Button addAFlightButton = new Button("Add a flight");
    addAFlightButton.setOnAction(event -> {
      AddFlightController.showScene();
    });

    headerContainer.setSpacing(10);
    headerContainer.getChildren().add(addAFlightButton);
    if (user.getUid().isEmpty()) {
      loginButton.setOnAction(event -> {
        LoginController.showScene();
      });
      headerContainer.getChildren().add(loginButton);
    } else {
      headerContainer.getChildren().add(viewAccountButton);
    }
    headerContainer.setAlignment(Pos.CENTER_RIGHT);
    root.getChildren().add(headerContainer);

    VBox bodyContainer = new VBox();
    VBox.setMargin(bodyContainer, new Insets(0, 100, 0, 100));
    VBox.setVgrow(bodyContainer, Priority.ALWAYS);

    VBox searchContainer = new VBox();
    searchContainer.setStyle("-fx-background-radius: 5px");
    searchContainer.setMinHeight(100);
    searchContainer.setSpacing(20);

    HBox searchFieldsContainer = new HBox();
    String[] airportCodes = new String[]{"DEB", "BUD", "VIE", "FRA", "BER"};
    ComboboxWithTitle fromField = new ComboboxWithTitle("From", airportCodes);
    ComboboxWithTitle toField = new ComboboxWithTitle("To", airportCodes);
    AddFlightDatePicker departDatePicker = new AddFlightDatePicker("Depart");

    HBox.setHgrow(departDatePicker, Priority.ALWAYS);

    String[] cabinClasses = new String[]{"First class", "Business class", "Premium economy", "Economy"};
    ComboboxWithTitle classComboBox = new ComboboxWithTitle("Cabin class", cabinClasses);
    classComboBox.comboBox.getSelectionModel().select(2);

    searchFieldsContainer.getChildren().add(fromField);
    searchFieldsContainer.getChildren().add(toField);
    searchFieldsContainer.getChildren().add(departDatePicker);
    searchFieldsContainer.getChildren().add(classComboBox);

    ErrorMessage errorMessage = new ErrorMessage();

    Button searchButton = new Button("Search flights");
    searchButton.setPadding(new Insets(10, 20, 10, 20));
    searchButton.setTextFill(Color.web("white"));
    searchButton.setStyle("-fx-background-color: #00a698;");
    HBox searchButtonContainer = new HBox(searchButton);
    searchButtonContainer.setAlignment(Pos.CENTER_RIGHT);
    searchButton.setOnAction(event -> {

      if (toField.getValue() == null || fromField.getValue() == null) {
        errorMessage.setManaged(true);
        errorMessage.setVisible(true);
      } else {
        FlightSearchData flightSearchData = FlightSearchData.getInstance();

        flightSearchData.setFrom(fromField.getValue());
        flightSearchData.setTo(toField.getValue());
        flightSearchData.setDate(departDatePicker.getDate());
        flightSearchData.setCabinClass(classComboBox.getValue());
        FlightSearchResultsController.showScene();
      }
    });

    searchContainer.getChildren().add(searchFieldsContainer);
    searchContainer.getChildren().add(searchButtonContainer);
    Label largeTitle = new Label( (user.firstName.isBlank() ? "" : "Hey, " + user.firstName + "! ")+ "One more step towards your dream destination");
    largeTitle.setFont(Font.font(Font.getDefault().getFamily(), 25));
    largeTitle.setWrapText(true);

    bodyContainer.setSpacing(minHeight);
    bodyContainer.getChildren().add(largeTitle);
    bodyContainer.getChildren().add(errorMessage);
    bodyContainer.getChildren().add(searchContainer);
    bodyContainer.setPadding(new Insets(20));
    bodyContainer.setMaxWidth(800);
    bodyContainer.setAlignment(Pos.CENTER_LEFT);
    root.getChildren().add(bodyContainer);

    StageManager.newSetScene(root, "Home");
  }
}
