package com.darkaliens.darkaliens;

import com.darkaliens.User;
import com.darkaliens.darkaliens.Home.HomeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.File;

public class ViewAccount {
  public static void showScene() {
    User user = User.getInstance();
    Label name = new Label(user.getFirstName() + " " + user.getLastName());
    Button signOutButton = new Button("Sign out");

    signOutButton.setOnAction(event -> {

      File newFile = new File("/tmp/uid.ser");
      newFile.delete();
      user.resetAccountData();
      HomeController.showScene();

    });
    name.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
    Label email = new Label(user.getEmail());

    BackButton backButton = new BackButton();

    backButton.setOnAction(event -> {
      HomeController.showScene();
    });

    VBox root = new VBox(backButton, name, email, signOutButton);

    root.setSpacing(10);
    root.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(20));

    StageManager.newSetScene(root, "Account details");
  }
}
