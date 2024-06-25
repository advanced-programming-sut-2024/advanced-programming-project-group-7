package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Card;
import model.Game;
import model.cards.*;

public class GameLauncher extends Application {

    private static final double HEIGHT = 900;
    private static final double WIDTH = 1600;
    public HBox playerHand = new HBox();
    public HBox playerFirstRowHorn = new HBox();
    public HBox playerFirstRow = new HBox();
    public HBox playerThirdRowHorn = new HBox();
    public HBox playerThirdRow = new HBox();
    public HBox playerSecondRowHorn = new HBox();
    public HBox playerSecondRow = new HBox();

    public Game game;
    public HBox playerFourthRow = new HBox();
    public HBox playerFourthRowHorn = new HBox();
    public HBox playerSixthRowHorn = new HBox();
    public HBox playerSixthRow = new HBox();
    public HBox playerFifthRowHorn = new HBox();
    public HBox playerFifthRow = new HBox();
    private Pane pane = new Pane();
    private Card selected;
    private double sceneX;
    private double sceneY;

    @Override
    public void start(Stage stage) throws Exception {
        pane = new Pane();
        game = new Game();
        setSize(pane);
        pane.setBackground(new Background(createBackgroundImage()));
        pane.getChildren().add(createHbox());

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        stage.setFullScreen(true);
        playerFirstRowHorn.getChildren().add(new Decoy("horn", 3, true, 0, "special",12,false));
//        playerFirstRow.getChildren().add(new Decoy("horn", 3, true, 0, "special"));
//        playerSecondRowHorn.getChildren().add(new Decoy("horn", 3, true, 0, "special"));
//        playerSecondRow.getChildren().add(new Decoy("horn", 3, true, 0, "special"));
//        playerThirdRowHorn.getChildren().add(new Decoy("horn", 3, true, 0, "special"));
//        playerThirdRow.getChildren().add(new Decoy("horn", 3, true, 0, "special"));
//        playerFourthRow.getChildren().add(new Agile("harpy", 1, false, 2, "monsters"));
//        playerFourthRowHorn.getChildren().add(new Decoy("horn", 3, true, 0, "special"));
//        playerFifthRow.getChildren().add(new Agile("harpy", 1, false, 2, "monsters"));
//        playerFifthRowHorn.getChildren().add(new Decoy("horn", 3, true, 0, "special"));
//        playerSixthRow.getChildren().add(new Agile("harpy", 1, false, 2, "monsters"));
//        playerSixthRowHorn.getChildren().add(new Decoy("horn", 3, true, 0, "special"));
//        playerHand.getChildren().add(new Decoy("horn", 3, true, 0, "special"));
//        playerHand.getChildren().add(new Agile("harpy", 1, false, 2, "monsters"));
//        playerHand.getChildren().add(new Agile("harpy", 1, false, 2, "monsters"));
        for (Node card : playerHand.getChildren()) {
            card.setOnMouseClicked(event -> {
                sceneX = event.getSceneX();
                sceneY = event.getSceneY();
                selected = (Card) card;
            });
        }
    }
    public void addCardToPane(Card card, double endY, double endX, HBox playerRow) {
        pane.getChildren().add(card);
        card.setLayoutY(this.sceneY);
        card.setLayoutX(this.sceneX);
        double dx = endX - sceneX;
        double dy = endY - sceneY;
        double theta = Math.atan2(dy, dx);
        double sinTheta = Math.sin(theta);
        double cosTheta = Math.cos(theta);
        double vy = sinTheta * 8;
        double vx = cosTheta * 8; //I'm doing math here
        CardPlacementAnimation cardPlacementAnimation =new CardPlacementAnimation(pane, game, card, playerRow, vx, vy, playerRow, endY, endX);
        cardPlacementAnimation.play();
    }

    private HBox createHbox() {
        HBox rootHBox = new HBox();
        rootHBox.setAlignment(Pos.BOTTOM_CENTER);

        // Create the left VBox (equivalent to the first <VBox> in FXML)
        VBox leftVBox = new VBox();
        leftVBox.setAlignment(Pos.BOTTOM_CENTER);
        leftVBox.setPrefHeight(600.0);
        leftVBox.setMinWidth(220);
        leftVBox.setSpacing(5.0);

        // Create the right VBox (equivalent to the second <VBox> in FXML)
        VBox rightVBox = new VBox();
        rightVBox.setAlignment(Pos.BOTTOM_CENTER);
        rightVBox.setPrefWidth(750.0);
        rightVBox.setSpacing(10.0);

        // Add HBoxes to the right VBox (equivalent to the nested <HBox> elements in FXML)
        addRow(rightVBox, playerSixthRowHorn, playerSixthRow, 98.0, 80.0);
        addRow(rightVBox, playerFifthRowHorn, playerFifthRow, 98.0, 670.0);
        addRow(rightVBox, playerFourthRowHorn, playerFourthRow, 98.0, 670.0);
        addRow(rightVBox, null ,null, 0 , 670);
        addRow(rightVBox, playerThirdRowHorn, playerThirdRow, 98, 670.0);
        addRow(rightVBox, playerSecondRowHorn, playerSecondRow, 98.0, 670.0);
        addRow(rightVBox, playerFirstRowHorn, playerFirstRow, 98.0, 670.0);

        // Add player hand
        playerHand.setAlignment(Pos.CENTER);
        playerHand.setMinHeight(98.0);
        playerHand.setMinWidth(770.0);
        playerHand.setSpacing(10);
        rightVBox.getChildren().add(playerHand);


        // Add spacing at the bottom
        HBox bottomSpacing = new HBox();
        bottomSpacing.setMinHeight(40.0);
        bottomSpacing.setPrefWidth(200.0);

        // Add all components to the root HBox
        rootHBox.getChildren().addAll(leftVBox, rightVBox);
        rootHBox.setLayoutX(263);
        rootHBox.setLayoutY(20);
        return rootHBox;
    }
    private void addRow(VBox parent, HBox horn, HBox playerRow, double minHeight, double minWidth) {
        HBox row = new HBox();
        row.setAlignment(Pos.CENTER);
        row.setMinHeight(minHeight);
        row.setMinWidth(minWidth);
        row.setOnMouseClicked(event -> {
            if (selected != null) {
                game.selectedBox = playerRow;
                playerHand.getChildren().remove(selected);
                addCardToPane(selected, event.getSceneY(), event.getSceneX(), playerRow);
                selected = null;
            }
        });

        if (horn != null) {
            horn.setAlignment(Pos.CENTER);
            horn.setMinHeight(minHeight);
            horn.setMinWidth(80.0);
            row.getChildren().add(horn);

        }

        if (playerRow != null) {
            playerRow.setAlignment(Pos.CENTER);
            playerRow.setMinHeight(minHeight);
            playerRow.setMinWidth(670.0);
            row.getChildren().add(playerRow);

        }

        parent.getChildren().add(row);
    }

    private void setSize (Pane pane) {
        pane.setMinHeight(HEIGHT);
        pane.setMaxHeight(HEIGHT);
        pane.setMinWidth(WIDTH);
        pane.setMaxWidth(WIDTH);
    }
    private BackgroundImage createBackgroundImage () {
        Image image = new Image(Game.class.getResource("/Images/board.jpg").toExternalForm(), WIDTH ,HEIGHT, false, false);
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
