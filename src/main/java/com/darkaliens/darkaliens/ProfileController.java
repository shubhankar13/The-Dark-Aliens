package com.darkaliens.darkaliens;

public class ProfileController {

  public void onLogOutButtonClick() {
    StageManager.goToLoginScene();
  }

  public void goBackToHomeScene() {
    StageManager.setScene("home-view.fxml", "Home");
  }
}
