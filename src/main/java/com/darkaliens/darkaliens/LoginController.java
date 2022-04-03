package com.darkaliens.darkaliens;

import javafx.fxml.FXML;

public class LoginController {

    @FXML
    protected void onLoginButtonClick() {
        StageManager.setScene("home-view.fxml", "Search flights");
    }

    @FXML
    public void onSignUpButtonClick() {
        StageManager.setScene("signup-view.fxml", "Sign up");
    }

    @FXML
    public void onForgotPasswordButtonClick() {
        StageManager.setScene("forgot-password-view.fxml", "Reset your password");
    }

}