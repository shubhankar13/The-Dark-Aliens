package com.darkaliens.darkaliens;

public class SignUpController {
    public void onSignupButtonClick() {
        StageManager.setScene("home-view.fxml","Search Flight");
    }

    public void onLoginButtonClick() {
        StageManager.goToLoginScene();
    }
}
