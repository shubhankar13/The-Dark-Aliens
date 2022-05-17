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

    AddFlightSectionTitle departureInformationTitle = new AddFlightSectionTitle("Departure information");
    AddFlightTextField departureAirportCode = new AddFlightTextField("Departure airport code", "BUD");
    AddFlightTextField departureAirportLocation = new AddFlightTextField("Departure location", "Budapest, Hungary");
    AddFlightDatePicker departureDatePicker = new AddFlightDatePicker("Departure date");
    AddFlightTextField departureTerminal = new AddFlightTextField("Departure terminal", "Terminal 1");
    AddFlightTextField departureGate = new AddFlightTextField("Departure gate", "A50");

    AddFlightSectionTitle arrivalInformationTitle = new AddFlightSectionTitle("Arrival information");
    AddFlightTextField arrivalAirportCode = new AddFlightTextField("Arrival airport code", "VIE");
    AddFlightTextField arrivalAirportLocation = new AddFlightTextField("Arrival location", "Vienna, Austria");
    AddFlightDatePicker arrivalDatePicker = new AddFlightDatePicker("Arrival date");
    AddFlightTextField arrivalTerminal = new AddFlightTextField("Arrival terminal", "Terminal 3");
    AddFlightTextField arrivalGate = new AddFlightTextField("Arrival gate", "A2");

    AddFlightSectionTitle pricesInformationTitle = new AddFlightSectionTitle("Prices (USD)");
    AddFlightTextField firstClassPrice = new AddFlightTextField("First class", "4000");
    AddFlightTextField businessClassPrice = new AddFlightTextField("Business Class", "2000");
    AddFlightTextField premiumEconomyPrice = new AddFlightTextField("Premium Economy", "1500");
    AddFlightTextField economyPrice = new AddFlightTextField("Economy", "700");

    Button submitButton = new Button("Add flight");
    submitButton.setAlignment(Pos.CENTER);
    submitButton.setOnAction(event -> {
      String departureAirportCodeValue = departureAirportCode.getText();
      String departureAirportLocationValue = departureAirportLocation.getText();
      String departureDatePickerValue = departureDatePicker.getDate();
      String departureTerminalValue = departureTerminal.getText();
      String departureGateValue = departureGate.getText();

      String arrivalAirportCodeValue = arrivalAirportCode.getText();
      String arrivalAirportLocationValue = arrivalAirportLocation.getText();
      String arrivalDatePickerValue = arrivalDatePicker.getDate();
      String arrivalTerminalValue = arrivalTerminal.getText();
      String arrivalGateValue = arrivalGate.getText();

      String firstClassPriceValue = firstClassPrice.getText();
      String businessClassPriceValue = businessClassPrice.getText();
      String premiumEconomyClassPriceValue = premiumEconomyPrice.getText();
      String economyClassPriceValue = economyPrice.getText();

      if (
        departureAirportCodeValue.isEmpty()
          || departureAirportLocationValue.isEmpty()
          || departureDatePickerValue.isEmpty()
          || departureTerminalValue.isEmpty()
          || departureGateValue.isEmpty()
          || arrivalAirportCodeValue.isEmpty()
          || arrivalAirportLocationValue.isEmpty()
          || arrivalDatePickerValue.isEmpty()
          || arrivalTerminalValue.isEmpty()
          || arrivalGateValue.isEmpty()
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
