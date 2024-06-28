
package view;

import controller.MainMenuController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.User;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;

public class IPMenu extends Application {

    public static Stage stage;
    @FXML
    public TextField IPtext;
    public TextField clientMassage;

    @Override
    public void start(Stage stage) throws Exception {
        URL url = LoginMenu.class.getResource("/FXML/clientIP.fxml");
        Pane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        IPMenu.stage=stage;
        stage.show();
    }

    public void makeClient(ActionEvent actionEvent) throws Exception {

        try {

            gotoLoginMenu();
        } catch (Exception e) {
            {Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("client had problem and we didn't make it ");alert.show();}
        }
    }

    private void gotoLoginMenu() throws Exception {
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.start(stage);
    }

}
