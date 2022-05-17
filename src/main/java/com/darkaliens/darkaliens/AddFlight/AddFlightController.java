package com.darkaliens.darkaliens.AddFlight;

import com.darkaliens.darkaliens.StageManager;
import com.darkaliens.mongo.Database;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.bson.Document;

public class AddFlightController {
  public static void showScene() {

    Label title = new Label("Add a new flight");

    Label errorMessage = new Label("All fields are required.");

    errorMessage.setStyle("-fx-background-color: #FFEBE9; -fx-border-color: rgba(255,129,130,0.4); -fx-border-radius: 5px;");
    errorMessage.setPrefWidth(Double.MAX_VALUE);
    errorMessage.setPadding(new Insets(16));
    errorMessage.setManaged(false);
    errorMessage.setVisible(false);

    String[] airportCodes = new String[]{"DEB", "BUD", "VIE", "FRA", "BER"};
    String[] airportLocations = new String[]{"Debrecen, Hungary", "Budapest, Hungary", "Vienna, Austra", "Frankfurt, Germany", "Berlin, Germany"};
    
    String[] departureTerminals = new String[]{"1", "2", "3"};
    String[] departureGates = new String[]{"A1", "A2", "B1", "B2"};

    String[] arrivalTerminals = new String[]{"1", "2", "3"};
    String[] arrivalGates = new String[]{"A1", "A2", "B1", "B2"};

    AddFlightSectionTitle departureInformationTitle = new AddFlightSectionTitle("Departure information");
    AddFlightComboBox departureAirportCode = new AddFlightComboBox("Departure airport code", airportCodes);
    AddFlightComboBox departureAirportLocation = new AddFlightComboBox("Departure airport location", airportLocations);
    AddFlightDatePicker departureDatePicker = new AddFlightDatePicker("Departure date");
    AddFlightComboBox departureTerminal = new AddFlightComboBox("Departure terminal", departureTerminals);
    AddFlightComboBox departureGate = new AddFlightComboBox("Departure gate", departureGates);

    AddFlightSectionTitle arrivalInformationTitle = new AddFlightSectionTitle("Arrival information");
    AddFlightComboBox arrivalAirportCode = new AddFlightComboBox("Arrival airport code", airportCodes);
    AddFlightComboBox arrivalAirportLocation = new AddFlightComboBox("Arrival airport location", airportLocations);
    AddFlightDatePicker arrivalDatePicker = new AddFlightDatePicker("Arrival date");
    AddFlightComboBox arrivalTerminal = new AddFlightComboBox("Arrival terminal", arrivalTerminals);
    AddFlightComboBox arrivalGate = new AddFlightComboBox("Arrival gate", arrivalGates);

    AddFlightSectionTitle pricesInformationTitle = new AddFlightSectionTitle("Prices (USD)");
    AddFlightTextField firstClassPrice = new AddFlightTextField("First class", "4000");
    AddFlightTextField businessClassPrice = new AddFlightTextField("Business Class", "2000");
    AddFlightTextField premiumEconomyPrice = new AddFlightTextField("Premium Economy", "1500");
    AddFlightTextField economyPrice = new AddFlightTextField("Economy", "700");

    Button submitButton = new Button("Add flight");
    submitButton.setAlignment(Pos.CENTER);
    submitButton.setOnAction(event -> {
      String departureAirportCodeValue = departureAirportCode.getValue();
      String departureAirportLocationValue = departureAirportLocation.getValue();
      String departureDatePickerValue = departureDatePicker.getDate();
      String departureTerminalValue = departureTerminal.getValue();
      String departureGateValue = departureGate.getValue();

      String arrivalAirportCodeValue = arrivalAirportCode.getValue();
      String arrivalAirportLocationValue = arrivalAirportLocation.getValue();
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
          || departureAirportLocationValue == null
          || departureDatePickerValue.isEmpty()
          || departureTerminalValue == null
          || departureGateValue == null
          || arrivalAirportCodeValue == null
          || arrivalAirportLocationValue == null
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
          .append("departure_airport_location", departureAirportLocationValue)
          .append("departure_date", departureDatePickerValue)
          .append("departure_terminal", departureTerminalValue)
          .append("departure_gate", departureGateValue)
          .append("arrival_airport_code", arrivalAirportCodeValue)
          .append("arrival_airport_location", arrivalAirportLocationValue)
          .append("arrival_date", arrivalDatePickerValue)
          .append("arrival_terminal", arrivalTerminalValue)
          .append("arrival_gate", arrivalGateValue)
          .append("first_class_price", firstClassPriceValue)
          .append("business_class_price", businessClassPriceValue)
          .append("premium_economy_price", premiumEconomyClassPriceValue)
          .append("economy_price", economyClassPriceValue)
        );

        System.out.println("Success! Inserted flight with id: " + result.getInsertedId());
      }

    });

    VBox container = new VBox(
      title,
      departureInformationTitle,
      departureAirportCode,
      departureAirportLocation,
      departureDatePicker,
      departureTerminal,
      departureGate,
      arrivalInformationTitle,
      arrivalAirportCode,
      arrivalAirportLocation,
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
    root.getStyleClass().add("scroll-pane");
    scrollContentContainer.prefWidthProperty().bind(root.widthProperty());

    StageManager.newSetScene(root, "Add a new flight");
  }
}
