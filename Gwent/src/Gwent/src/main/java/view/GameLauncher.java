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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Card;
import model.Game;
import model.cards.*;
import view.animations.CardPlacementAnimation;

import java.util.ArrayList;

public class GameLauncher extends Application {
    public  ArrayList<HBox>hBoxes=new ArrayList<HBox>();

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

    public Rectangle showCardRectangle = new Rectangle();



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

        hBoxes.add(playerFirstRow);
        hBoxes.add(playerSecondRow);
        hBoxes.add(playerThirdRow);
        hBoxes.add(playerFourthRow);
        hBoxes.add(playerFifthRow);
        hBoxes.add(playerSixthRow);

        hBoxes.add(playerFirstRowHorn);
        hBoxes.add(playerSecondRowHorn);
        hBoxes.add(playerThirdRowHorn);
        hBoxes.add(playerFourthRowHorn);
        hBoxes.add(playerFifthRowHorn);
        hBoxes.add(playerSixthRowHorn);

//        playerFirstRowHorn.getChildren().add(new Decoy("horn", 3, true, 0, "special",12,false));
//        playerSecondRow.getChildren().add(new Card("rain", 2 , true, 0, "weather",7,false));
//        playerFourthRowHorn.getChildren().add(new Decoy("horn", 3, true, 0, "special",12,false));
//        playerFifthRow.getChildren().add(new Card("geralt", 1 , false, 15, "neutral",3,true));
//        playerFifthRowHorn.getChildren().add(new Decoy("horn", 3, true, 0, "special",12,false));
//        playerSixthRow.getChildren().add(new Agile("harpy", 1, false, 2, "monsters",23,false));
        playerHand.getChildren().add(new Card("philippa", 1 , false, 10, "realms",2,true));
        playerHand.getChildren().add(new Agile("harpy", 1, false, 2, "monsters",23,false));
        playerHand.getChildren().add(new Card("ciri", 1 , false, 15, "neutral",3,true));
        playerHand.getChildren().add(new Medic("yennefer", 1 , false, 7, "neutral",2,true));
        playerHand.getChildren().add(new Spy("stennis", 1 , false, 5, "realms",3,false));
        playerHand.getChildren().add(new Horn("horn", 3 , true, 0, "special",123,false));
        playerHand.getChildren().add(new Horn("horn", 3 , true, 0, "special",123,false));
        for (Node card : playerHand.getChildren()) {
            card.setOnMouseClicked(event -> {
                if(pane.getChildren().contains(showCardRectangle)){
                    pane.getChildren().remove(showCardRectangle);
                }
                for (HBox hBox:hBoxes){
                    hBox.setStyle("");
                }
                sceneX = event.getSceneX();
                sceneY = event.getSceneY();
                selected = (Card) card;


                showCardRectangle.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource(selected.getLgPath()).toExternalForm()))));
                showCardRectangle.setHeight(470);
                showCardRectangle.setWidth(235);
                showCardRectangle.setLayoutY(220);
                showCardRectangle.setLayoutX(1280);
                pane.getChildren().add(showCardRectangle);


                if(selected.getRows()==3){
                    playerThirdRow.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                }
                else if(selected.getRows()==2){
                    playerSecondRow.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                }
                else if(selected.getRows()==1){
                    playerFirstRow.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                }
                else if(selected.getRows()==23){
                    playerSecondRow.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                    playerThirdRow.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                }
                else if(selected.getCardName().equals("horn")){
                    playerFirstRowHorn.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                    playerSecondRowHorn.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                    playerThirdRowHorn.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                }

            });
        }
    }
    public void addCardToPane(Card card, double endY, double endX, HBox playerRow){
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
        CardPlacementAnimation cardPlacementAnimation =new CardPlacementAnimation(pane, game, card, vx, vy, endY, endX);
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
                pane.getChildren().remove(showCardRectangle);
                game.selectedBox = playerRow;
                playerHand.getChildren().remove(selected);
                addCardToPane(selected, event.getSceneY(), event.getSceneX(), playerRow);
                if(selected.getRows()==3){
                    playerThirdRow.setStyle("");
                }
                else if(selected.getRows()==2){
                    playerSecondRow.setStyle("");
                }
                else if(selected.getRows()==1){
                    playerFirstRow.setStyle("");
                }
                else if(selected.getRows()==23){
                    playerSecondRow.setStyle("");
                    playerThirdRow.setStyle("");
                }
                else if(selected.getCardName().equals("horn")){
                    playerFirstRowHorn.setStyle("");
                    playerSecondRowHorn.setStyle("");
                    playerThirdRowHorn.setStyle("");
                }
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
