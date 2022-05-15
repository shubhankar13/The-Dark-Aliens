package com.darkaliens.auth;

import com.darkaliens.darkaliens.MainApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Collection;

public class AuthSceneContainer extends VBox {

  public static final PasswordField passwordField = new AuthPasswordField();
  public static final AuthContinueButton authContinueButton = new AuthContinueButton();
  public static final TextField email = new AuthTextField("Email");
  public static final Label errorMessage = new Label();
  private static final Stage primaryStage = MainApplication.stage;
  static VBox containerVBox = new VBox();

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
    projectName.setPadding(new Insets(0, 0, 20, 0));
    containerVBox.getChildren().add(projectName);

    errorMessage.setMaxWidth(Double.MAX_VALUE);
    toggleErrorMessage(false);
    errorMessage.setPadding(new Insets(10));
    errorMessage.setStyle("-fx-background-color: #a18282; -fx-background-radius: 5px; -fx-border-radius: 5px; -fx-border-color: rgba(246,79,100,.4);");
    containerVBox.getChildren().add(errorMessage);

    Label sceneTitleLabel = new Label(sceneTitle);
    sceneTitleLabel.setTextFill(Color.web("white"));
    sceneTitleLabel.setFont(Font.font("", FontWeight.BOLD, 20));
    containerVBox.getChildren().add(sceneTitleLabel);

    containerVBox.getChildren().addAll(others);

    email.setText("woyongegbara@yahoo.com");

    containerVBox.getChildren().add(email);
    containerVBox.getChildren().add(passwordField);

    VBox.setMargin(authContinueButton, new Insets(10, 0, 0, 0));
    containerVBox.getChildren().add(authContinueButton);

    primaryStage.show();
    Scene scene = new Scene(this, 800, 600);
    primaryStage.setScene(scene);
    primaryStage.centerOnScreen();
  }

  public void showError(String error, Node field) {
    errorMessage.setText(error);
    toggleErrorMessage(true);
    field.setStyle(field.getStyle() + "-fx-border-color: rgba(246,79,100,.4);");
  }

  public void toggleErrorMessage(Boolean shouldShowError) {
    errorMessage.setVisible(shouldShowError);
    errorMessage.setManaged(shouldShowError);
  }

  public void showError(String error) {
    errorMessage.setText(error);
    toggleErrorMessage(true);
  }

  public PasswordField getPasswordField() {
    return passwordField;
  }

  public AuthContinueButton getAuthContinueButton() {
    return authContinueButton;
  }

  public TextField getEmail() {
    return email;
  }
}
