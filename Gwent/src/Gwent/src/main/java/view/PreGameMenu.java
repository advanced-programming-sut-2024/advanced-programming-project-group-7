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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Card;
import model.Faction;
import model.Medic;
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
    public GridPane leftGrid;

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
        setCardsAndCommander();//this is the real deal
    }

    private void setCardsAndCommander() {
        for (Card card: Faction.Monsters.getMonsterCards()) {
            Pane pane = new Pane();
            Rectangle rectangle = new Rectangle();
            rectangle.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource(card.getLgPath()).toExternalForm()))));
            rectangle.setHeight(300);
            rectangle.setWidth(150);
            pane.getChildren().add(rectangle);
            leftGrid.add(pane, 1, 1);
            System.out.println("hi");
        }
    }

    public void add00(MouseEvent mouseEvent) {
    }

    public void add01(MouseEvent mouseEvent) {
    }

    public void add02(MouseEvent mouseEvent) {
    }
}
