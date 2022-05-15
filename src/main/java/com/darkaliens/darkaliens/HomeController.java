package com.darkaliens.darkaliens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomeController {

  @FXML
  public void onProfileButtonClick() {
    StageManager.setScene("profile-view.fxml", "Profile");
  }

  public void backButtonClick(ActionEvent actionEvent) {
    StageManager.setScene("login-view.fxml", "Login");
  }
}
