package com.darkaliens.darkaliens;

import com.darkaliens.auth.AuthSceneContainer;
import com.darkaliens.auth.Firebase;
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

public class LoginController {
  private static AuthSceneContainer authSceneContainer;

  public static void showScene() {
    HBox signUpContainer = new HBox();
    Label noAccount = new Label("Don't have an account?");
    Button signUpButton = new Button("Sign up");
    signUpButton.setOnAction(event -> {
      SignUpController.showScene();
    });
    signUpContainer.setAlignment(Pos.CENTER);
    signUpContainer.getChildren().add(noAccount);
    signUpContainer.getChildren().add(signUpButton);
    signUpContainer.setSpacing(10);

    Collection<Node> list = new ArrayList<>();
    list.add(signUpContainer);

    authSceneContainer = new AuthSceneContainer("Log in", list);
    authSceneContainer.getAuthContinueButton().setOnAction(LoginController::login);
  }

  public static void login (ActionEvent eventEventHandler) {
    PasswordField passwordField = authSceneContainer.getPasswordField();
    TextField emailField = authSceneContainer.getEmail();
    String email = emailField.getText();
    String password = passwordField.getText();
    String signInResponse = Firebase.login(email, password);

    switch (signInResponse) {
      case "INCOMPLETE_FORM" -> authSceneContainer.showError("All fields are required.");
      case "INVALID_PASSWORD" -> authSceneContainer.showError("Your password is incorrect");
      case "INVALID_EMAIL" -> authSceneContainer.showError("Your email is missing or is incorrect.");
      case "TOO_MANY_ATTEMPTS_TRY_LATER" -> authSceneContainer.showError("Access to this account has been temporarily disabled due to too may failed attempts to login. Try again later.");
    }

    System.out.println(signInResponse);
  }
}