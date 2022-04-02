package com.darkaliens.darkaliens;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {
    public void goBackToLogin(ActionEvent event) throws IOException {
        showLogin(event);
    }

    public static void showLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
}
