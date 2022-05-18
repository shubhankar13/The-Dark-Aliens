package com.darkaliens.darkaliens.FlightSearchResults;

import com.darkaliens.darkaliens.StageManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class FlightSearchResultsController {
  public static void showScene() {

    Label title = new Label("Flight search results");

    title.setAlignment(Pos.CENTER);
    title.setMaxWidth(Double.MAX_VALUE);
    title.setStyle("-fx-font-size: 25px");

    VBox container = new VBox(title);

    container.setMaxWidth(350);
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
