package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Card;
import model.Game;
import model.cards.*;
import view.animations.CardPlacementAnimation;

import java.awt.*;
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

        Label labelForNumberOfCards=new Label(String.valueOf(10));
        labelForNumberOfCards.setLayoutY(570);
        labelForNumberOfCards.setLayoutX(210);
        labelForNumberOfCards.setTextFill(Color.YELLOW);
        labelForNumberOfCards.setFont(new Font(20));

        Rectangle avatar=new Rectangle();
        avatar.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/profile.png").toExternalForm()))));
        avatar.setHeight(100);
        avatar.setWidth(70);
        avatar.setLayoutY(530);
        avatar.setLayoutX(120);

        Rectangle cardx=new Rectangle();
        cardx.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_card_count.png").toExternalForm()))));
        cardx.setHeight(35);
        cardx.setWidth(35);
        cardx.setLayoutY(555);
        cardx.setLayoutX(178);

        Label playerName=new Label("player1");
        playerName.setLayoutY(600);
        playerName.setLayoutX(200);
        playerName.setTextFill(Color.YELLOW);
        playerName.setFont(new Font(20));

        Rectangle life1=new Rectangle();
        life1.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_gem_on.png").toExternalForm()))));
        life1.setHeight(40);
        life1.setWidth(40);
        life1.setLayoutY(555);
        life1.setLayoutX(240);

        Rectangle life2=new Rectangle();
        life2.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_gem_on.png").toExternalForm()))));
        life2.setHeight(40);
        life2.setWidth(40);
        life2.setLayoutY(555);
        life2.setLayoutX(281);

        Label totalPower=new Label(String.valueOf(100));
        totalPower.setLayoutY(600);
        totalPower.setLayoutX(357);
        totalPower.setTextFill(Color.BLACK);
        totalPower.setFont(new Font(25));

        Rectangle highScore=new Rectangle();
        highScore.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_high_score.png").toExternalForm()))));
        highScore.setHeight(63);
        highScore.setWidth(63);
        highScore.setLayoutY(590);
        highScore.setLayoutX(347);




        Rectangle realmForAvatar=new Rectangle();//todo needs 5 else if for player's faction
        realmForAvatar.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/deck_shield_realms.png").toExternalForm()))));
        realmForAvatar.setHeight(50);
        realmForAvatar.setWidth(50);
        realmForAvatar.setLayoutY(530);
        realmForAvatar.setLayoutX(75);


        Label labelForNumberOfCardsOpponent=new Label(String.valueOf(10));
        labelForNumberOfCardsOpponent.setLayoutY(285);
        labelForNumberOfCardsOpponent.setLayoutX(210);
        labelForNumberOfCardsOpponent.setTextFill(Color.YELLOW);
        labelForNumberOfCardsOpponent.setFont(new Font(20));


        Rectangle avatarOpponent=new Rectangle();
        avatarOpponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/profile.png").toExternalForm()))));
        avatarOpponent.setHeight(100);
        avatarOpponent.setWidth(70);
        avatarOpponent.setLayoutY(245);
        avatarOpponent.setLayoutX(120);

        Rectangle cardxOpponent=new Rectangle();
        cardxOpponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_card_count.png").toExternalForm()))));
        cardxOpponent.setHeight(35);
        cardxOpponent.setWidth(35);
        cardxOpponent.setLayoutY(270);
        cardxOpponent.setLayoutX(178);

        Label playerNameOpponent=new Label("player2");
        playerNameOpponent.setLayoutY(315);
        playerNameOpponent.setLayoutX(200);
        playerNameOpponent.setTextFill(Color.YELLOW);
        playerNameOpponent.setFont(new Font(20));

        Rectangle life1Opponent=new Rectangle();
        life1Opponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_gem_on.png").toExternalForm()))));
        life1Opponent.setHeight(40);
        life1Opponent.setWidth(40);
        life1Opponent.setLayoutY(270);
        life1Opponent.setLayoutX(240);

        Rectangle life2Opponent=new Rectangle();
        life2Opponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_gem_on.png").toExternalForm()))));
        life2Opponent.setHeight(40);
        life2Opponent.setWidth(40);
        life2Opponent.setLayoutY(270);
        life2Opponent.setLayoutX(281);

        Label totalPowerOpponent=new Label(String.valueOf(50));
        totalPowerOpponent.setLayoutY(265);
        totalPowerOpponent.setLayoutX(357);
        totalPowerOpponent.setTextFill(Color.BLACK);
        totalPowerOpponent.setFont(new Font(25));

        Rectangle highScoreOpponent=new Rectangle();
        highScoreOpponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_high_score.png").toExternalForm()))));
        highScoreOpponent.setHeight(63);
        highScoreOpponent.setWidth(63);
        highScoreOpponent.setLayoutY(252);
        highScoreOpponent.setLayoutX(347);



        Rectangle realmForAvatarOpponent=new Rectangle();//todo needs 5 else if for player's faction
        realmForAvatarOpponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/deck_shield_monsters.png").toExternalForm()))));
        realmForAvatarOpponent.setHeight(50);
        realmForAvatarOpponent.setWidth(50);
        realmForAvatarOpponent.setLayoutY(245);
        realmForAvatarOpponent.setLayoutX(75);




        Rectangle frostedRow=new Rectangle();
        frostedRow.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/overlay_frost.png").toExternalForm()))));
        frostedRow.setHeight(100);
        frostedRow.setWidth(680);
        frostedRow.setLayoutY(360);
        frostedRow.setLayoutX(590);

        Rectangle frostedRowOpponent=new Rectangle();
        frostedRowOpponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/overlay_frost.png").toExternalForm()))));
        frostedRowOpponent.setHeight(100);
        frostedRowOpponent.setWidth(680);
        frostedRowOpponent.setLayoutY(245);
        frostedRowOpponent.setLayoutX(590);

        Rectangle foggedRow=new Rectangle();
        foggedRow.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/overlay_fog.png").toExternalForm()))));
        foggedRow.setHeight(100);
        foggedRow.setWidth(680);
        foggedRow.setLayoutY(470);
        foggedRow.setLayoutX(590);

        Rectangle foggedRowOpponent=new Rectangle();
        foggedRowOpponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/overlay_fog.png").toExternalForm()))));
        foggedRowOpponent.setHeight(100);
        foggedRowOpponent.setWidth(680);
        foggedRowOpponent.setLayoutY(135);
        foggedRowOpponent.setLayoutX(590);

        Rectangle rainedRow=new Rectangle();
        rainedRow.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/overlay_rain.png").toExternalForm()))));
        rainedRow.setHeight(100);
        rainedRow.setWidth(680);
        rainedRow.setLayoutY(580);
        rainedRow.setLayoutX(590);


        Rectangle rainedRowOpponent=new Rectangle();
        rainedRowOpponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/overlay_rain.png").toExternalForm()))));
        rainedRowOpponent.setHeight(100);
        rainedRowOpponent.setWidth(680);
        rainedRowOpponent.setLayoutY(25);
        rainedRowOpponent.setLayoutX(590);



        highScoreOpponent.setVisible(false);

        frostedRow.setVisible(false);
        frostedRowOpponent.setVisible(false);

        foggedRow.setVisible(false);
        foggedRowOpponent.setVisible(false);

        rainedRow.setVisible(false);
        rainedRowOpponent.setVisible(false);






        pane.getChildren().addAll(createHbox(),playerName,avatar,life1,life2, cardx,labelForNumberOfCards,totalPower,highScore,realmForAvatar,
                playerNameOpponent,avatarOpponent,life1Opponent,life2Opponent,cardxOpponent,labelForNumberOfCardsOpponent,totalPowerOpponent,highScoreOpponent,realmForAvatarOpponent,
                frostedRow,frostedRowOpponent,foggedRow,foggedRowOpponent,rainedRow,rainedRowOpponent);


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
