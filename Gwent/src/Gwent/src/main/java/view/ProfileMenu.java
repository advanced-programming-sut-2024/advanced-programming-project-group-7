package view;

import controller.ProfileMenuController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class ProfileMenu extends Application {

    public TextField newName;
    public TextField newPassword;
    public TextField oldPassword;
    public TextField newNickname;
    public TextField newEmail;
    public ComboBox numberOfBattles;
    public TextField searchBar;
    public Label username;
    public Label nickname;
    public Label email;

    @Override
    public void start(Stage stage) throws Exception {
        URL url = LoginMenu.class.getResource("/FXML/ProfileMenu.fxml");
        BorderPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML

    public void initialize() {
        numberOfBattles.setValue(5);
        username.setText("username");
        nickname.setText("nickname");//todo changed from tofill.thought it's gonna be filled by myself.
        email.setText("email");
    }

    public void changeUsername(MouseEvent mouseEvent) {
          Alert alert = ProfileMenuController.changeUsername(newName.getText());
        System.out.println(User.getLoggedInUser().getUsername());
        if (alert != null) {
            alert.show();
        }
    }

    public void changePassword(MouseEvent mouseEvent) {
        Alert alert= ProfileMenuController.changePassword(oldPassword.getText(),newPassword.getText());
        if (alert != null) {
            alert.show();
        }
    }

    public void backToMainMenu(MouseEvent mouseEvent) {
        MainMenu mainMenu = new MainMenu();
        try {
            mainMenu.start(LoginMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void changeNickname(MouseEvent mouseEvent) {
        Alert alert = ProfileMenuController.changeNickname(newNickname.getText());
        if (alert != null) {
            alert.show();
        }
    }

    public void changeEmail(MouseEvent mouseEvent) {
        Alert alert = ProfileMenuController.changeEmail(email.getText());
        if (alert != null) {
            alert.show();
        }
    }

    public void showPlayerInfo(MouseEvent mouseEvent) {
        String name = searchBar.getText();
        //todo
    }
}
