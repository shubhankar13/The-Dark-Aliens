package com.darkaliens.darkaliens;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {

    public static Stage stage;

    @Override
    public void start(Stage stage) {
        MainApplication.stage = stage;
        SceneUtils.goToLogin();
    }

    public static void main(String[] args) {
        launch();
    }
}