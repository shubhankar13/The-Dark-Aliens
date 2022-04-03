package com.darkaliens.darkaliens;

public class ProfileController {

    public void onLogOutButtonClick() {
        SceneUtils.goToLogin();
    }

    public void goBackToHomeScene() {
        SceneUtils.goToScene("home-view.fxml", "Home");
    }
}
