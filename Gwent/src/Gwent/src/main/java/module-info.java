module Gwent {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires jakarta.mail;
    requires javax.servlet.api;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.google.gson;


    exports view;
    opens view to javafx.fxml;
    exports controller;
    exports model;
    opens controller to javafx.fxml;
    opens model to javafx.base, com.fasterxml.jackson.databind,com.google.gson;
    opens model.factions to javafx.base;
    opens model.cards to javafx.base;

    exports model.factions to com.fasterxml.jackson.databind;

}