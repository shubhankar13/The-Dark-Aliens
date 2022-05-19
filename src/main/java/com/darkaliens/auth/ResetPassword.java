package com.darkaliens.auth;

import com.darkaliens.darkaliens.BackButton;
import com.darkaliens.darkaliens.Home.HomeController;
import javafx.scene.Node;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Collection;

public class ResetPassword {
  private static AuthSceneContainer authSceneContainer;

  public static void showScene() {

    BackButton backButton = new BackButton();

    backButton.setOnAction(event -> {
      LoginController.showScene();
    });

    Collection<Node> list = new ArrayList<>();
    authSceneContainer = new AuthSceneContainer("Reset your password", list);
    authSceneContainer.containerVBox.getChildren().add(0, backButton);
    authSceneContainer.containerVBox.getChildren().remove(5);
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
