package com.darkaliens.darkaliens.AddFlight;

import com.darkaliens.darkaliens.BackButton;
import com.darkaliens.darkaliens.ComboboxWithTitle;
import com.darkaliens.darkaliens.ErrorMessage;
import com.darkaliens.darkaliens.Home.HomeController;
import com.darkaliens.darkaliens.StageManager;
import com.darkaliens.mongo.Database;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.bson.Document;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AddFlightController {

  public static Map<String, String> cabinClasses = new HashMap<>() {{
    put("first_class", "First class");
    put("business_class", "Business class");
    put("premium_economy", "Premium economy");
    put("economy", "Economy");
  }};

  public static Map<String, String> inverseCabinClasses = new HashMap<>() {{
    put("First class", "first_class");
    put("Business class", "business_class");
    put("Premium economy", "premium_economy");
    put("Economy", "economy");
  }};


  public static Map<String, String> createSingletonMap() {
    return Collections.singletonMap("username1", "password1");
  }

  public static void showScene() {


    BackButton backButton = new BackButton();

    backButton.setOnAction(event -> {
      HomeController.showScene();
    });


    Label title = new Label("Add a new flight");
    ErrorMessage errorMessage = new ErrorMessage();

    String[] airportCodes = new String[]{"DEB", "BUD", "VIE", "FRA", "BER"};

    String[] departureTerminals = new String[]{"1", "2", "3"};
    String[] departureGates = new String[]{"A1", "A2", "B1", "B2"};

    String[] arrivalTerminals = new String[]{"1", "2", "3"};
    String[] arrivalGates = new String[]{"A1", "A2", "B1", "B2"};

    AddFlightSectionTitle departureInformationTitle = new AddFlightSectionTitle("Departure information");
    ComboboxWithTitle departureAirportCode = new ComboboxWithTitle("Departure airport code", airportCodes);
    AddFlightDatePicker departureDatePicker = new AddFlightDatePicker("Departure date");
    ComboboxWithTitle departureTerminal = new ComboboxWithTitle("Departure terminal", departureTerminals);
    ComboboxWithTitle departureGate = new ComboboxWithTitle("Departure gate", departureGates);

    AddFlightSectionTitle arrivalInformationTitle = new AddFlightSectionTitle("Arrival information");
    ComboboxWithTitle arrivalAirportCode = new ComboboxWithTitle("Arrival airport code", airportCodes);
    AddFlightDatePicker arrivalDatePicker = new AddFlightDatePicker("Arrival date");
    ComboboxWithTitle arrivalTerminal = new ComboboxWithTitle("Arrival terminal", arrivalTerminals);
    ComboboxWithTitle arrivalGate = new ComboboxWithTitle("Arrival gate", arrivalGates);

    AddFlightSectionTitle pricesInformationTitle = new AddFlightSectionTitle("Prices (USD)");
    AddFlightTextField firstClassPrice = new AddFlightTextField(cabinClasses.get("first_class"), "4000");
    AddFlightTextField businessClassPrice = new AddFlightTextField(cabinClasses.get("business_class"), "2000");
    AddFlightTextField premiumEconomyPrice = new AddFlightTextField(cabinClasses.get("premium_economy"), "1500");
    AddFlightTextField economyPrice = new AddFlightTextField(cabinClasses.get("economy"), "700");

    Button submitButton = new Button("Add flight");
    submitButton.setAlignment(Pos.CENTER);
    submitButton.setOnAction(event -> {
      String departureAirportCodeValue = departureAirportCode.getValue();
      String departureDatePickerValue = departureDatePicker.getDate();
      String departureTerminalValue = departureTerminal.getValue();
      String departureGateValue = departureGate.getValue();

      String arrivalAirportCodeValue = arrivalAirportCode.getValue();
      String arrivalDatePickerValue = arrivalDatePicker.getDate();
      String arrivalTerminalValue = arrivalTerminal.getValue();
      String arrivalGateValue = arrivalGate.getValue();

      String firstClassPriceValue = firstClassPrice.getText();
      String businessClassPriceValue = businessClassPrice.getText();
      String premiumEconomyClassPriceValue = premiumEconomyPrice.getText();
      String economyClassPriceValue = economyPrice.getText();

      System.out.println(departureAirportCodeValue);

      if (
        departureAirportCodeValue == null
          || departureDatePickerValue.isEmpty()
          || departureTerminalValue == null
          || departureGateValue == null
          || arrivalAirportCodeValue == null
          || arrivalDatePickerValue.isEmpty()
          || arrivalTerminalValue == null
          || arrivalGateValue == null
          || firstClassPriceValue.isEmpty()
          || businessClassPriceValue.isEmpty()
          || premiumEconomyClassPriceValue.isEmpty()
          || economyClassPriceValue.isEmpty()
      ) {
        errorMessage.setManaged(true);
        errorMessage.setVisible(true);
      } else {

        errorMessage.setManaged(false);
        errorMessage.setVisible(false);

        MongoCollection<Document> collection = Database.getFlightsCollection();
        Document document = new Document();
        InsertOneResult result = collection.insertOne(document
                                                        .append("departure_airport_code", departureAirportCodeValue)
                                                        .append("departure_date", departureDatePickerValue)
                                                        .append("departure_terminal", departureTerminalValue)
                                                        .append("departure_gate", departureGateValue)
                                                        .append("arrival_airport_code", arrivalAirportCodeValue)
                                                        .append("arrival_date", arrivalDatePickerValue)
                                                        .append("arrival_terminal", arrivalTerminalValue)
                                                        .append("arrival_gate", arrivalGateValue)
                                                        .append("first_class_price", Integer.valueOf(firstClassPriceValue))
                                                        .append("business_class_price", Integer.valueOf(businessClassPriceValue))
                                                        .append("premium_economy_price", Integer.valueOf(premiumEconomyClassPriceValue))
                                                        .append("economy_price", Integer.valueOf(economyClassPriceValue))
        );

        System.out.println("Success! Inserted flight with id: " + result.getInsertedId());

        AddFlightAlert addFlightAlert = new AddFlightAlert();
        Optional<ButtonType> alertResult = addFlightAlert.showAndWait();

        if (alertResult.isPresent()) {
          if (alertResult.get() == addFlightAlert.buttonTypeOne) {
            HomeController.showScene();
          } else {
            LocalDate now = LocalDate.now();

            departureAirportCode.comboBox.getSelectionModel().clearSelection();
            departureGate.comboBox.getSelectionModel().clearSelection();
            departureTerminal.comboBox.getSelectionModel().clearSelection();
            departureDatePicker.datePicker.setValue(now);

            arrivalAirportCode.comboBox.getSelectionModel().clearSelection();
            arrivalGate.comboBox.getSelectionModel().clearSelection();
            arrivalTerminal.comboBox.getSelectionModel().clearSelection();
            arrivalDatePicker.datePicker.setValue(now);

            firstClassPrice.textField.setText("");
            businessClassPrice.textField.setText("");
            premiumEconomyPrice.textField.setText("");
            economyPrice.textField.setText("");
          }
        }
      }

    });

    VBox container = new VBox(
      backButton,
      title,
      departureInformationTitle,
      departureAirportCode,
      departureDatePicker,
      departureTerminal,
      departureGate,
      arrivalInformationTitle,
      arrivalAirportCode,
      arrivalDatePicker,
      arrivalTerminal,
      arrivalGate,
      pricesInformationTitle,
      firstClassPrice,
      businessClassPrice,
      premiumEconomyPrice,
      economyPrice,
      errorMessage,
      submitButton
    );

    title.setAlignment(Pos.CENTER);
    title.setMaxWidth(Double.MAX_VALUE);
    title.setStyle("-fx-font-size: 25px");

    container.setMaxWidth(350);
    container.setSpacing(20);
    container.setAlignment(Pos.CENTER);

    VBox scrollContentContainer = new VBox(container);
    scrollContentContainer.setAlignment(Pos.CENTER);
    scrollContentContainer.setPadding(new Insets(50, 25, 50, 25));

    ScrollPane root = new ScrollPane();
    root.setContent(scrollContentContainer);
    scrollContentContainer.prefWidthProperty().bind(root.widthProperty());

    StageManager.newSetScene(root, "Add a new flight");
  }
}
