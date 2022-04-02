package com.darkaliens.darkaliens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setTitle("Home page");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onSignUpButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signup-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setTitle("Sign up page");
        stage.setScene(scene);
        stage.show();
    }
}