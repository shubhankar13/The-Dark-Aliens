package com.darkaliens.auth;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Collection;

public class SignUpController {

  private static final AuthTextField firstNameField = new AuthTextField("First name");
  private static final AuthTextField lastNameField = new AuthTextField("Last name");
  private static AuthSceneContainer authSceneContainer;

  public static void showScene() {
    HBox loginContainer = new HBox();
    Label haveAnAccount = new Label("Have an account?");
    Button loginButton = new Button("Login");
    loginButton.setOnAction(event -> {
      LoginController.showScene();
    });
    loginContainer.setAlignment(Pos.CENTER);
    loginContainer.getChildren().add(haveAnAccount);
    loginContainer.getChildren().add(loginButton);
    loginContainer.setSpacing(10);

    Collection<Node> list = new ArrayList<>();
    list.add(loginContainer);
    list.add(firstNameField);
    list.add(lastNameField);

    authSceneContainer = new AuthSceneContainer("Sign up", list);
    Button continueButton = authSceneContainer.getAuthContinueButton();
    continueButton.setOnAction(SignUpController::signUp);
  }

  public static void signUp(ActionEvent eventEventHandler) {
    authSceneContainer.toggleErrorMessage(false);

    PasswordField passwordField = authSceneContainer.getPasswordField();
    TextField emailField = authSceneContainer.getEmail();
    String firstName = firstNameField.getText();
    String lastName = lastNameField.getText();
    String email = emailField.getText();
    String password = passwordField.getText();
    String signUpResponse = Firebase.signUp(firstName, lastName, email, password);

    switch (signUpResponse) {
      case "INCOMPLETE_FORM" -> authSceneContainer.showError("All fields are required.");
      case "WEAK_PASSWORD" -> authSceneContainer.showError("Your password is missing or is too weak.");
      case "INVALID_EMAIL" -> authSceneContainer.showError("Your email is missing or is incorrect.");
      case "EMAIL_EXISTS" -> authSceneContainer.showError("The email provided already exists.");
    }
  }
}
