package com.darkaliens.auth;

import com.darkaliens.darkaliens.MainApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Collection;

public class AuthSceneContainer extends VBox {
  private static final Stage primaryStage = MainApplication.stage;
  private final VBox containerVBox = new VBox();

  public AuthSceneContainer(String sceneTitle, Collection<Node> others) {
    setAlignment(Pos.CENTER);
    setStyle("-fx-background-color: #0e1016");

    containerVBox.setStyle("-fx-background-color: #151922; -fx-background-radius: 5px");
    containerVBox.setSpacing(10);
    containerVBox.setMaxWidth(350);
    containerVBox.setAlignment(Pos.CENTER);
    containerVBox.setPadding(new Insets(25));
    getChildren().add(containerVBox);

    Label projectName = new Label("UDAAN");
    projectName.setTextFill(Color.web("#7f8183"));
    projectName.setPadding(new Insets(0, 0, 30, 0));
    containerVBox.getChildren().add(projectName);

    Label sceneTitleLabel = new Label(sceneTitle);
    sceneTitleLabel.setTextFill(Color.web("white"));
    sceneTitleLabel.setFont(Font.font("", FontWeight.BOLD, 20));
    containerVBox.getChildren().add(sceneTitleLabel);

    containerVBox.getChildren().addAll(others);

    PasswordField passwordField = new AuthPasswordField();
    containerVBox.getChildren().add(passwordField);

    AuthContinueButton authContinueButton = new AuthContinueButton();
    VBox.setMargin(authContinueButton, new Insets(10, 0, 0, 0));
    containerVBox.getChildren().add(authContinueButton);

    primaryStage.show();
    Scene scene = new Scene(this, 800, 600);
    primaryStage.setScene(scene);
    primaryStage.centerOnScreen();
  }

  public VBox getContainerVBox() {
    return containerVBox;
  }
}
