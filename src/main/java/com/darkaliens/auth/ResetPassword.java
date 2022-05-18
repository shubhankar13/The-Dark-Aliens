package com.darkaliens.auth;

import javafx.scene.Node;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Collection;

public class ResetPassword {
  private static AuthSceneContainer authSceneContainer;

  public static void showScene() {
    Collection<Node> list = new ArrayList<>();
    authSceneContainer = new AuthSceneContainer("Log in", list);
    authSceneContainer.containerVBox.getChildren().remove(2);
    authSceneContainer.containerVBox.getChildren().remove(3);
    authSceneContainer.getAuthContinueButton().setOnAction(LoginController::login);
    authSceneContainer.getAuthContinueButton().setOnAction(event -> {
      resetPassword();
    });
  }

  public static void resetPassword () {
    String resetPasswordResponse = Firebase.resetPassword(authSceneContainer.getEmail().getText());

    switch (resetPasswordResponse) {
      case "INCOMPLETE_FORM" -> authSceneContainer.showError("Please enter your email.");
      case "EMAIL_NOT_FOUND" -> authSceneContainer.showError("The email provided does not exist.");
      case "INVALID_EMAIL" -> authSceneContainer.showError("Your email is incorrect.");
      case "SUCCESS" -> {
        LoginController.showScene();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Email sent!");
        alert.setHeaderText("Use the link we sent to your email to reset your password");
        alert.showAndWait();
      }
    }
  }
}
