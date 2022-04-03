package com.darkaliens.darkaliens;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageManager {
    public static void setScene(String sceneName, String stageTile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(sceneName));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
            Stage stage = MainApplication.stage;

            stage.setTitle(stageTile);
            stage.setScene(scene);
            stage.show();
            stage.centerOnScreen();
        } catch (IOException ignored) {
            System.out.println("Could not load " + sceneName);
        }
    }

    public static void goToLoginScene() {
        StageManager.setScene("login-view.fxml", "Log in");
    }
}
