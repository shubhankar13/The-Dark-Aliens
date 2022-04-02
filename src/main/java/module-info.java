module com.darkaliens.darkaliens {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.auth.oauth2;
    requires com.google.auth;
    requires com.google.api.apicommon;
    requires google.cloud.storage;
    requires google.cloud.firestore;
    requires firebase.admin;
    requires google.api.client;
    requires google.cloud.core;
    requires com.google.api.client.auth;

    opens com.darkaliens.darkaliens to javafx.fxml;
    exports com.darkaliens.darkaliens;
}