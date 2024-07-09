module Gwent {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires java.xml.bind;
    requires com.fasterxml.jackson.dataformat.xml;
    requires java.sql;
    requires com.google.gson;

    exports view;
    opens view to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;

    opens model to javafx.base, com.google.gson;
    opens model.factions to javafx.base;
    opens model.cards to javafx.base;
}