package com.darkaliens.darkaliens;

import javafx.fxml.FXML;

public class HomeController {

    @FXML
    public void onProfileButtonClick() {
        SceneUtils.goToScene("profile-view.fxml", "Profile");
    }
}
