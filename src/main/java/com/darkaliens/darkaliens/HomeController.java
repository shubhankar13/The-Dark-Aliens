package com.darkaliens.darkaliens;

import javafx.fxml.FXML;

public class HomeController {

  @FXML
  public void onProfileButtonClick() {
    StageManager.setScene("profile-view.fxml", "Profile");
  }
}
