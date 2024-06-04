package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        String musicPath = "/Sounds/Opeth-Bleak.mp3";
        URL resource = getClass().getResource(musicPath);
        Media music = new Media(resource.toExternalForm());
        mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        LoginMenu.stage = stage;
        URL url = LoginMenu.class.getResource("/FXML/LoginMenu.fxml");
//        Image icon = new Image(LoginMenu.class.getResource("/Images/icon.png").toExternalForm());
//        stage.getIcons().add(icon);
        BorderPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
//    public void signUp(MouseEvent mouseEvent) {
//        Alert alert;
//        alert = AllMenusController.signUpControl(nameField.getText(), password.getText());
//        if (alert == null) {
//            MainMenu mainMenu = new MainMenu();
//            try {
//                mainMenu.start(LoginMenu.stage);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            alert.show();
//        }
//    }
//
//    public void startGameAsGuest(MouseEvent mouseEvent) {
//        try {
//            new GameLauncher(true).start(LoginMenu.stage);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
