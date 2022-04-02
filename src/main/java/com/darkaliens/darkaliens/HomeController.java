package com.darkaliens.darkaliens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    public void onLogOutButtonClick(ActionEvent event) throws IOException {
        SignUpController.showLogin(event);
    }

    public void onProfileClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("profile-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
}
