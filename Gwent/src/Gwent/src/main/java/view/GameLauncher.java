package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import model.Card;
import model.Game;
import model.cards.*;

import java.net.URL;

import static view.LoginMenu.stage;

public class GameLauncher extends Application {

    private static final double HEIGHT = 900;
    private static final double WIDTH = 1600;
    public HBox playerHand;
    public HBox playerFirstRowHorn;
    public HBox playerFirstRow;
    public HBox playerThirdRowHorn;
    public HBox playerThirdRow;
    public HBox playerSecondRowHorn;
    public HBox playerSecondRow;

    @Override
    public void start(Stage stage) throws Exception {
        URL url = LoginMenu.class.getResource("/FXML/GameLauncher.fxml");
        BorderPane root = FXMLLoader.load(url);
        Image image = new Image(GameLauncher.class.getResource("/Images/board.jpg").toExternalForm(), WIDTH ,HEIGHT, false, false);
        ImageView imageView = new ImageView(image);

        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image bwImage = imageView.snapshot(params, null);

        BackgroundImage backgroundImage = new BackgroundImage(bwImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        root.setBackground(new Background(backgroundImage));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gwent");
        stage.show();
        stage.setFullScreen(true);
    }

    @FXML
    public void initialize() {

        playerFirstRowHorn.getChildren().add(new Decoy("horn", 3, true, 0, "special"));
        playerFirstRow.getChildren().add(new Decoy("horn", 3, true, 0, "special"));
        playerSecondRowHorn.getChildren().add(new Decoy("horn", 3, true, 0, "special"));
        playerSecondRow.getChildren().add(new Decoy("horn", 3, true, 0, "special"));
        playerThirdRowHorn.getChildren().add(new Decoy("horn", 3, true, 0, "special"));
        playerThirdRow.getChildren().add(new Decoy("horn", 3, true, 0, "special"));
        playerHand.getChildren().add(new Decoy("horn", 3, true, 0, "special"));
        playerHand.getChildren().add(new Agile("harpy", 1, false, 2, "monsters"));
        playerHand.getChildren().add(new Agile("harpy", 1, false, 2, "monsters"));

//        playerHand.getChildren().add(new Scorch("scorch", 3, true, 0, "special"));
//        playerHand.getChildren().add(new Card("frost", 3, true, 0, "weather"));
//        playerHand.getChildren().add(new Card("clear", 2, true, 0, "weather"));
    }
}
