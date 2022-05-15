package com.darkaliens.darkaliens;

import com.darkaliens.auth.AuthSceneContainer;
import com.darkaliens.auth.AuthTextField;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collection;

public class SignUpController {

  public static void showScene() {
    AuthTextField firstName = new AuthTextField("First name");
    AuthTextField lastName = new AuthTextField("Last name");

    HBox loginContainer = new HBox();
    Label haveAnAccount = new Label("Have an account?");
    Button loginButton = new Button("Login");
    haveAnAccount.setTextFill(Color.web("#8a8c91"));
    loginButton.setTextFill(Color.web("#77a7ff"));
    loginButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand");
    loginButton.setOnAction(event -> {
      LoginController.showScene();
    });
    loginContainer.setAlignment(Pos.CENTER);
    loginContainer.getChildren().add(haveAnAccount);
    loginContainer.getChildren().add(loginButton);

    TextField email = new AuthTextField("Email");

    Collection<Node> list = new ArrayList<>();
    list.add(loginContainer);
    list.add(firstName);
    list.add(lastName);
    list.add(email);

    new AuthSceneContainer("Sign up", list);
  }
}
