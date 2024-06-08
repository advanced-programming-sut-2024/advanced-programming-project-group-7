package view;

import controller.LoginMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.URL;

public class LoginMenu extends Application {

    public static Stage stage;
    public static MediaPlayer mediaPlayer;
    public TextField nameField;
    public TextField password;
    public Label nicknameLable;
    public TextField nicknameText;
    public Label emailLabel;
    public TextField emailText;
    public Button hybridButt;
    public boolean isLoggingIN = true;
    public TextField confirmPWD;
    public Button forgotPWD;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Media music = new Media(MainMenu.class.getResource("/Sounds/01 No Escape.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        LoginMenu.stage = stage;
        URL url = LoginMenu.class.getResource("/FXML/LoginMenu.fxml");
        Image icon = new Image(LoginMenu.class.getResource("/Images/icons/gwent.jpg").toExternalForm());
        stage.getIcons().add(icon);
        BorderPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void signUp(MouseEvent mouseEvent) {
        Alert alert = null;
//        alert = LoginMenuController.userRegister(nameField.getText()
//                , password.getText(),confirmPWD.getText(), nicknameText.getText(), emailText.getText());
        if (alert == null) {
            MainMenu mainMenu = new MainMenu();
            try {
//                mainMenu.start(LoginMenu.stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            alert.show();
        }
    }

    public void login(MouseEvent mouseEvent) {
        Alert alert = null;
//        alert = LoginMenuController.userLogin(nameField.getText(), password.getText());
        if (alert == null) {
            MainMenu mainMenu = new MainMenu();
            try {
//                mainMenu.start(LoginMenu.stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            alert.show();
        }
    }

    public void recoverPSWD(MouseEvent mouseEvent) {
    }

    public void Switch(MouseEvent mouseEvent) {
        isLoggingIN = !isLoggingIN;
        if (isLoggingIN) {
            confirmPWD.setVisible(false);
            nicknameLable.setVisible(false);
            nicknameText.setVisible(false);
            emailLabel.setVisible(false);
            emailText.setVisible(false);
            forgotPWD.setVisible(true);
            hybridButt.setText("login");
        } else {
            confirmPWD.setVisible(true);
            forgotPWD.setVisible(false);
            nicknameLable.setVisible(true);
            nicknameText.setVisible(true);
            emailLabel.setVisible(true);
            emailText.setVisible(true);
            hybridButt.setText("signup");
        }
    }
}

