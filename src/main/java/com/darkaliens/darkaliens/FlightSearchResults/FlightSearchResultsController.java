package com.darkaliens.darkaliens.FlightSearchResults;

import com.darkaliens.User;
import com.darkaliens.auth.LoginController;
import com.darkaliens.darkaliens.AddFlight.AddFlightController;
import com.darkaliens.darkaliens.Home.HomeController;
import com.darkaliens.darkaliens.StageManager;
import com.darkaliens.mongo.Database;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.InsertOneResult;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;

import static com.mongodb.client.model.Filters.*;

public class FlightSearchResultsController {
  static FlightSearchData flightSearchData = FlightSearchData.getInstance();

  public static void showScene() {

    Label title = new Label("Flight search results");

    title.setAlignment(Pos.CENTER);
    title.setMaxWidth(Double.MAX_VALUE);
    title.setStyle("-fx-font-size: 25px");
    VBox.setMargin(title, new Insets(0, 0, 20, 0));

    VBox container = new VBox(title);

    String cabinClassKey = AddFlightController.inverseCabinClasses.get(flightSearchData.cabinClass) + "_price";

    Bson filter = and(
      eq("departure_airport_code", flightSearchData.from),
      eq("arrival_airport_code", flightSearchData.to),
      gt(cabinClassKey, 0)
    );

    try (MongoCursor<Document> cursor = Database.getFlightsCollection().find(filter).iterator()) {
      while (cursor.hasNext()) {

        DropShadow ds1 = new DropShadow();
        ds1.setOffsetY(0.0f);
        ds1.setOffsetX(0.0f);
        ds1.setColor(Color.web("#dddddd"));

        Document document = cursor.next();
        Label departureAirportCode = new Label("(" + document.get("departure_airport_code") + ")");
        Label departureGate = new Label("Gate " + document.get("departure_gate"));
        Label departureTerminal = new Label("Terminal " + document.get("departure_terminal"));
        Label departureDate = new Label((String) document.get("departure_date"));
        Label arrivalAirportCode = new Label((String) document.get("arrival_airport_code"));
        Label arrivalGate = new Label("Gate " + document.get("arrival_gate"));
        Label arrivalTerminal = new Label("Terminal " + document.get("arrival_terminal"));
        Label arrivalDate = new Label((String) document.get("arrival_date"));

        Text departureTime = new Text("9:50");
        Text departureLocation = new Text("Debrecen, Hungary");

        Text arrivalTime = new Text("21:50");
        Text arrivalLocation = new Text("Vienna, Austria");

        Label flightDuration = new Label("10h 25m");

        flightDuration.setPadding(new Insets(5, 8, 5, 8));
        flightDuration.setStyle("-fx-background-color: rgb(232, 244, 253); -fx-border-color: rgb(208, 233, 251); -fx-border-width: 1px; -fx-border-style: solid; -fx-border-radius: 12px; -fx-background-radius: 12px;");
        flightDuration.setTextFill(Color.web("rgb(0, 90, 163)"));
        departureLocation.setStyle("-fx-font-weight: bold;");
        departureTime.setStyle("-fx-font-weight: bold;");

        HBox departureInfoContainer = new HBox(departureTime, departureLocation, departureAirportCode, departureTerminal, departureGate);
        HBox arrivalInfoContainer = new HBox(arrivalTime, arrivalLocation, arrivalAirportCode, arrivalTerminal, arrivalGate);
        departureInfoContainer.setSpacing(5);
        arrivalInfoContainer.setSpacing(5);

        BigDecimal valueToFormat = new BigDecimal((Integer) document.get(cabinClassKey));
        NumberFormat franceFormat = NumberFormat.getCurrencyInstance(Locale.US);
        String formattedString = franceFormat.format(valueToFormat);

        Label price = new Label(formattedString);
        Button buyButton = new Button("Buy ticket");
        User user = User.getInstance();

        buyButton.setOnAction(event -> {
          if (user.uid.isEmpty()) {
            ButtonType buttonTypeOne = new ButtonType("Log in");
            ButtonType buttonTypeTwo = new ButtonType("Cancel");

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("You're not logged in.");
            alert.setTitle("You must be logged in to buy a ticket.");
            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

            Optional<ButtonType> alertResult = alert.showAndWait();

            if (alertResult.isPresent()) {
              if (alertResult.get() == buttonTypeOne) {
                LoginController.showScene();
              }
            }

          } else {
            MongoCollection<Document> collection = Database.getTicketPurchasesCollection();
            Document ticketPurchaseDocument = new Document();
            InsertOneResult result = collection.insertOne(ticketPurchaseDocument
                                                            .append("ticket_id", document.get("_id"))
                                                            .append("uid", new ObjectId(user._id)));

            if (result.wasAcknowledged()) {
              HomeController.showScene();
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
              alert.setTitle("You ticket was booked!");
              alert.setHeaderText("Thank you for your purchase!");
              alert.showAndWait();
            }
          }
        });

        VBox rightResultContainer = new VBox(price, buyButton);
        rightResultContainer.setPrefWidth(150);
        rightResultContainer.setAlignment(Pos.CENTER);
        rightResultContainer.setSpacing(10);
        VBox leftResultContainer = new VBox(departureDate, departureInfoContainer, flightDuration, arrivalInfoContainer, arrivalDate);
        leftResultContainer.setMaxWidth(Double.MAX_VALUE);
        leftResultContainer.setSpacing(10);
        leftResultContainer.setPadding(new Insets(20));

        HBox resultContainer = new HBox(leftResultContainer, rightResultContainer);
        resultContainer.setStyle("-fx-background-color: white; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        resultContainer.setEffect(ds1);

        HBox.setHgrow(leftResultContainer, Priority.ALWAYS);
        container.getChildren().add(resultContainer);
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