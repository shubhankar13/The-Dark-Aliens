package com.darkaliens.darkaliens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class LoginController {

    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {
        SceneUtils.goToScene("home-view.fxml", "Search flight");
    }

    @FXML
    public void onSignUpButtonClick(ActionEvent event) throws IOException {
        SceneUtils.goToScene("signup-view.fxml", "Sign up");
    }

    @FXML
    public void onForgotPasswordButtonClick(ActionEvent event) throws IOException {
        SceneUtils.goToScene("forgot-password-view.fxml", "Reset your password");
    }

}