package com.darkaliens.darkaliens.AddFlight;

import com.darkaliens.darkaliens.StageManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class AddFlightController {
  public static void showScene() {

    Label title = new Label("Add a new flight");

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
      System.out.println("Add a new flight to database");
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
