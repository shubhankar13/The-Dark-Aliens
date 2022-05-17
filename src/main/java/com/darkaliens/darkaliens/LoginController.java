package com.darkaliens.darkaliens;

import com.darkaliens.auth.AuthSceneContainer;
import com.darkaliens.auth.AuthTextField;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Collection;

public class LoginController {
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

    TextField email = new AuthTextField("Email");

    Collection<Node> list = new ArrayList<>();
    list.add(signUpContainer);
    list.add(email);

    new AuthSceneContainer("Log in", list);
  }

}