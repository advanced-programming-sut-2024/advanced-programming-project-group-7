package view;

import controller.MainMenuController;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;

public class PreGameMenu extends Application {

    public Rectangle LeaderImage;
    public Rectangle cell00;
    public Rectangle cell01;
    public Rectangle cell02;
    public Label L00;
    public Label L01;
    public Label L02;
    public ImageView I00;
    public ImageView I01;
    public ImageView I02;
    public Label totalCardInDeck;
    public Label numberOfUnitCard;
    public Label numberOfspecialCard;
    public Label totalUnitCardStrength;
    public Label numberOfHeroCard;

    @Override
    public void start(Stage stage) throws Exception {
        URL url = LoginMenu.class.getResource("/FXML/PreGameMenu.fxml");
        BorderPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }

    @FXML
    public void initialize() {
        // these are only here for as examples
        LeaderImage.setFill(new ImagePattern
                (new Image(PreGameMenu.class.getResource("/Images/lg/realms_foltest_bronze.jpg").toExternalForm())));
        cell00.setFill(new ImagePattern
                (new Image(PreGameMenu.class.getResource("/Images/lg/monsters_fiend.jpg").toExternalForm())));
        L00.setVisible(true);
        I00.setVisible(true); // find the difference
        cell01.setFill(new ImagePattern
                (new Image(PreGameMenu.class.getResource("/Images/lg/monsters_fire_elemental.jpg").toExternalForm())));
        cell02.setFill(new ImagePattern
                (new Image(PreGameMenu.class.getResource("/Images/lg/monsters_eredin_the_treacherous.jpg").toExternalForm())));
        //*******
        setCardsAndCommander();//this is the real deal
    }

    private void setCardsAndCommander() {
    }

    public void add00(MouseEvent mouseEvent) {
    }

    public void add01(MouseEvent mouseEvent) {
    }

    public void add02(MouseEvent mouseEvent) {
    }
}
