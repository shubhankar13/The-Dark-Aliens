package com.darkaliens.darkaliens;

import javafx.fxml.FXML;

public class LoginController {

    @FXML
    protected void onLoginButtonClick() {
        SceneUtils.goToScene("home-view.fxml", "Search flights");
    }

    @FXML
    public void onSignUpButtonClick() {
        SceneUtils.goToScene("signup-view.fxml", "Sign up");
    }

    @FXML
    public void onForgotPasswordButtonClick() {
        SceneUtils.goToScene("forgot-password-view.fxml", "Reset your password");
    }

}