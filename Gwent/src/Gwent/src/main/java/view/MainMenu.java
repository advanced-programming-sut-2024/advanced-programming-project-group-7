package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;

public class MainMenu extends Application {

    public Label Username;

    @Override
    public void start(Stage stage) throws Exception {
        URL url = LoginMenu.class.getResource("/FXML/MainMenu.fxml");
        BorderPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        setLabelText();
    }

    public void setLabelText() {
        //todo get the name and handle
        Username.setText("name");
    }


    public void goToProfileMenu(MouseEvent mouseEvent) {
        soundPlayer("/Sounds/paper.mp3");// todo find sound
        ProfileMenu profileMenu = new ProfileMenu();
        try {
            profileMenu.start(LoginMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void startNewGame(MouseEvent mouseEvent) {
        soundPlayer("/Sounds/sword.mp3");//todo find sound
//        try {
//            new GameLauncher().start(LoginMenu.stage);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    public void Logout(MouseEvent mouseEvent) {
        // todo MainMenuController.logout();
        soundPlayer("/Sounds/paper.mp3");
        LoginMenu loginMenu = new LoginMenu();
        try {
            loginMenu.start(LoginMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void exitGame(MouseEvent mouseEvent) {
        LoginMenu.stage.close();
    }

    public void mouseEnteredSound(MouseEvent mouseEvent) {
        soundPlayer("/Sounds/Tik.mp3"); // todo find sound
    }
    private void soundPlayer(String path) {
        Media media = new Media(getClass().getResource(path).toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }
}