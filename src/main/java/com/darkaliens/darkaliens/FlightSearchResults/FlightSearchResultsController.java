package com.darkaliens.darkaliens.FlightSearchResults;

import com.darkaliens.darkaliens.AddFlight.AddFlightController;
import com.darkaliens.darkaliens.StageManager;
import com.darkaliens.mongo.Database;
import com.mongodb.client.MongoCursor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.*;

public class FlightSearchResultsController {
  static FlightSearchData flightSearchData = FlightSearchData.getInstance();

  public static void showScene() {

    Label title = new Label("Flight search results");

    title.setAlignment(Pos.CENTER);
    title.setMaxWidth(Double.MAX_VALUE);
    title.setStyle("-fx-font-size: 25px");

    VBox container = new VBox(title);

    Bson filter = and(
      eq("departure_airport_code", flightSearchData.from),
      eq("arrival_airport_code", flightSearchData.to),
      gt(AddFlightController.inverseCabinClasses.get(flightSearchData.cabinClass) + "_price", 0)
    );

    try (MongoCursor<Document> cursor = Database.getFlightsCollection().find(filter).iterator()) {
      while (cursor.hasNext()) {

        DropShadow ds1 = new DropShadow();
        ds1.setOffsetY(0.0f);
        ds1.setOffsetX(0.0f);
        ds1.setColor(Color.web("#cccccc"));

        Document document = cursor.next();
        Label departureAirportCode = new Label((String) document.get("departure_airport_code"));
        Label departureGate = new Label((String) document.get("departure_gate"));
        Label departureTerminal = new Label((String) document.get("departure_terminal"));
        Label arrivalAirportCode = new Label((String) document.get("arrival_airport_code"));
        Label arrivalGate = new Label((String) document.get("arrival_gate"));
        Label arrivalTerminal = new Label((String) document.get("arrival_terminal"));
        VBox searchResultContainer = new VBox(departureAirportCode, departureGate, departureTerminal, arrivalAirportCode, arrivalGate, arrivalTerminal);
        searchResultContainer.setMaxWidth(Double.MAX_VALUE);
        searchResultContainer.setEffect(ds1);
        searchResultContainer.setPadding(new Insets(20));
        searchResultContainer.setStyle("-fx-background-color: white; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        container.getChildren().add(searchResultContainer);
      }
    }

    container.setMaxWidth(500);
    container.setSpacing(20);
    container.setAlignment(Pos.CENTER);

    VBox scrollContentContainer = new VBox(container);
    scrollContentContainer.setAlignment(Pos.CENTER);
    scrollContentContainer.setPadding(new Insets(50, 25, 50, 25));

    ScrollPane root = new ScrollPane();
    root.setContent(scrollContentContainer);
    scrollContentContainer.prefWidthProperty().bind(root.widthProperty());

    StageManager.newSetScene(root, "Search results");
  }
}