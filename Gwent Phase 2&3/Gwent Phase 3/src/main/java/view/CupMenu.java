package view;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Game;

import java.net.URL;

public class CupMenu extends Application {

    public Circle tournament1;
    public Circle tournament0;
    public Button watch;
    public TextField searchBar;
    public Button isPlaying;
    public Rectangle ready;
    public Label P0;
    public Label P2;
    public Label P1;
    public Label P3;
    public Label P4 ;
    public Label P5 ;
    public Label P6;
    public Label P7 ;
    public Label P8;
    public Label P9;
    public Label P10;
    public Label P11;
    public Label P12;
    public Label P13;
    public Label P14;
    public Label P15;
    public Label P16;
    public Label P17;
    public Label P18;
    public Label P19;
    public Label P20;
    public Label P21;
    public Label P23;
    public Label P24;
    public Label P25;
    public Label P26;

    @Override
    public void start(Stage stage) throws Exception {
        URL url = LoginMenu.class.getResource("/FXML/CupMenu.fxml");
        AnchorPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        root.setBackground(new Background(createBackgroundImage("/Images/bracket.png")));
        stage.show();

    }

    @FXML
    public void initialize() {
        tournament0.setFill(new ImagePattern(new Image(String.valueOf(CupMenu.class.getResource("/Images/icons/tournament.png")))));
        tournament1.setFill(new ImagePattern(new Image(String.valueOf(CupMenu.class.getResource("/Images/icons/tournament.png")))));
        watch.setVisible(false);
        ready.setFill(new ImagePattern(new Image(String.valueOf(CupMenu.class.getResource("/Images/icons/red-ready.png")))));
        RotateTransition rotate = new RotateTransition(Duration.seconds(5), ready);
        rotate.setCycleCount(50);
        rotate.setByAngle(360);
        rotate.play();
    }
    private BackgroundImage createBackgroundImage (String address) {
        Image image = new Image(Game.class.getResource(address).toExternalForm(), 1280 ,900, false, false);
        ImageView imageView = new ImageView(image);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image bwImage = imageView.snapshot(params, null);
        BackgroundImage backgroundImage = new BackgroundImage(bwImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return backgroundImage;
    }
}

class MatchNode {

}
