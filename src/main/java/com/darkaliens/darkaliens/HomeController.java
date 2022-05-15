package com.darkaliens.darkaliens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;

public class HomeController {
  private static final Stage primaryStage = MainApplication.stage;

  public static void showScene() {
    VBox root = new VBox();
    root.setId("root-background");
    root.setSpacing(10);
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
    bodyContainer.setStyle("-fx-background-color: maroon;");
    bodyContainer.setAlignment(Pos.CENTER_LEFT);
    VBox.setVgrow(bodyContainer, Priority.ALWAYS);

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
}
