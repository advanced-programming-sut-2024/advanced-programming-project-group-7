module Gwent {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires jakarta.mail;
    requires javax.servlet.api;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    exports view;
    opens view to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;

    opens model to javafx.base;
    opens model.factions to javafx.base;
    opens model.cards to javafx.base;
}