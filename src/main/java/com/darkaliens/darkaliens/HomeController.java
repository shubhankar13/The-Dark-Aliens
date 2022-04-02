package com.darkaliens.darkaliens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeController {

    @FXML
    public void onLogOutButtonClick(ActionEvent event) throws IOException {
        SceneUtils.goToScene("profile-view.fxml", "Profile");
    }
}
