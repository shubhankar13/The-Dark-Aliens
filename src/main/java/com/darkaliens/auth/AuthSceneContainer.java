package com.darkaliens.auth;

import com.darkaliens.darkaliens.ErrorMessage;
import com.darkaliens.darkaliens.MainApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Collection;

public class AuthSceneContainer extends VBox {

  public static PasswordField passwordField;
  public static AuthContinueButton authContinueButton;
  public static TextField email;
  public static ErrorMessage errorMessage;
  private static final Stage primaryStage = MainApplication.stage;
  public VBox containerVBox;

  public AuthSceneContainer(String sceneTitle, Collection<Node> others) {
    errorMessage = new ErrorMessage();
    containerVBox = new VBox();
    passwordField = new AuthPasswordField();
    authContinueButton = new AuthContinueButton();
    email = new AuthTextField("Email");
    setAlignment(Pos.CENTER);

    containerVBox.setStyle("-fx-background-radius: 5px");
    containerVBox.setSpacing(10);
    containerVBox.setMaxWidth(350);
    containerVBox.setAlignment(Pos.CENTER);
    containerVBox.setPadding(new Insets(25));
    getChildren().add(containerVBox);

    Label projectName = new Label("UDAAN");
    projectName.setPadding(new Insets(0, 0, 20, 0));
    projectName.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
    containerVBox.getChildren().add(projectName);

    toggleErrorMessage(false);
    containerVBox.getChildren().add(errorMessage);

    Label sceneTitleLabel = new Label(sceneTitle);
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
