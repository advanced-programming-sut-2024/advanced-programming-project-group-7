package view;

import controller.Client;
import controller.GameServer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.*;
import model.cards.*;
import model.leaders.MonstersLeaders;
import model.leaders.NorthernRealmsLeaders;
import view.animations.CardPlacementAnimation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
    private boolean yourTurn = true;
    private Deck deck;
    private  Stage stage;
    private Client client;


    @Override
    public void start(Stage stage1) throws Exception {

        game = new Game(this);
        stage = new Stage();
        game.client = User.getLoggedInUser().client;

        client = User.getLoggedInUser().client;
        client.game = game;
        pane = new Pane();
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

        Label playerName=new Label(User.getLoggedInUser().getUsername());
        playerName.setLayoutY(600);
        playerName.setLayoutX(200);
        playerName.setTextFill(Color.YELLOW);
        playerName.setFont(new Font(20));

        game.life1=new Rectangle();
        game.life1.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_gem_on.png").toExternalForm()))));
        game.life1.setHeight(40);
        game.life1.setWidth(40);
        game.life1.setLayoutY(555);
        game.life1.setLayoutX(240);

        game.life2 = new Rectangle();
        game.life2.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_gem_on.png").toExternalForm()))));
        game.life2.setHeight(40);
        game.life2.setWidth(40);
        game.life2.setLayoutY(555);
        game.life2.setLayoutX(281);

        game.totalPower=new Label("0");
        game.totalPower.setLayoutY(600);
        game.totalPower.setLayoutX(357);
        game.totalPower.setTextFill(Color.BLACK);
        game.totalPower.setFont(new Font(25));

        game.totalRow1Power=new Label("0");
        game.totalRow1Power.setLayoutY(625);
        game.totalRow1Power.setLayoutX(427);
        game.totalRow1Power.setTextFill(Color.BLACK);
        game.totalRow1Power.setFont(new Font(20));

        game.totalRow2Power=new Label("0");
        game.totalRow2Power.setLayoutY(510);
        game.totalRow2Power.setLayoutX(427);
        game.totalRow2Power.setTextFill(Color.BLACK);
        game.totalRow2Power.setFont(new Font(20));

        game.totalRow3Power=new Label("0");
        game.totalRow3Power.setLayoutY(395);
        game.totalRow3Power.setLayoutX(427);
        game.totalRow3Power.setTextFill(Color.BLACK);
        game.totalRow3Power.setFont(new Font(20));

        Rectangle highScore=new Rectangle();
        highScore.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_high_score.png").toExternalForm()))));
        highScore.setHeight(63);
        highScore.setWidth(63);
        highScore.setLayoutY(590);
        highScore.setLayoutX(347);

        game.weatherBox = new HBox();
        game.weatherBox.setLayoutY(385);
        game.weatherBox.setLayoutX(120);
        game.weatherBox.setMinHeight(98);
        game.weatherBox.setMinWidth(235);
        game.weatherBox.setOnMouseClicked(event ->  {
            HBox target;
            target = game.weatherBox;
            if (selected != null && fitsBox(selected, target)) {
                game.selectedBox = target;
                game.playerHand.getChildren().remove(selected);
                addCardToPane(selected, event.getSceneY(), event.getSceneX());
            }
            pane.getChildren().remove(showCardRectangle);
            playerFirstRow.setStyle("");
            playerSecondRow.setStyle("");
            playerThirdRow.setStyle("");
            playerFirstRowHorn.setStyle("");
            playerSecondRowHorn.setStyle("");
            playerThirdRowHorn.setStyle("");
            game.weatherBox.setStyle("");
            selected = null;
        });

        Rectangle realmForAvatar=new Rectangle();//todo needs 5 else if for player's faction
        realmForAvatar.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/deck_shield_realms.png").toExternalForm()))));
        realmForAvatar.setHeight(50);
        realmForAvatar.setWidth(50);
        realmForAvatar.setLayoutY(550);
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

        Label playerNameOpponent=new Label(User.getLoggedInUser().currentOponentName);
        playerNameOpponent.setLayoutY(315);
        playerNameOpponent.setLayoutX(200);
        playerNameOpponent.setTextFill(Color.YELLOW);
        playerNameOpponent.setFont(new Font(20));

        game.life1Opponent=new Rectangle();
        game.life1Opponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_gem_on.png").toExternalForm()))));
        game.life1Opponent.setHeight(40);
        game.life1Opponent.setWidth(40);
        game.life1Opponent.setLayoutY(270);
        game.life1Opponent.setLayoutX(240);

        game.life2Opponent=new Rectangle();
        game.life2Opponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_gem_on.png").toExternalForm()))));
        game.life2Opponent.setHeight(40);
        game.life2Opponent.setWidth(40);
        game.life2Opponent.setLayoutY(270);
        game.life2Opponent.setLayoutX(281);

        game.totalPowerOpponent=new Label("0");
        game.totalPowerOpponent.setLayoutY(265);
        game.totalPowerOpponent.setLayoutX(357);
        game.totalPowerOpponent.setTextFill(Color.BLACK);
        game.totalPowerOpponent.setFont(new Font(25));

        game.totalRow1PowerOpponent=new Label("0");
        game.totalRow1PowerOpponent.setLayoutY(47);
        game.totalRow1PowerOpponent.setLayoutX(427);
        game.totalRow1PowerOpponent.setTextFill(Color.BLACK);
        game.totalRow1PowerOpponent.setFont(new Font(20));

        game.totalRow2PowerOpponent=new Label("0");
        game.totalRow2PowerOpponent.setLayoutY(162);
        game.totalRow2PowerOpponent.setLayoutX(427);
        game.totalRow2PowerOpponent.setTextFill(Color.BLACK);
        game.totalRow2PowerOpponent.setFont(new Font(20));

        game.totalRow3PowerOpponent=new Label("0");
        game.totalRow3PowerOpponent.setLayoutY(277);
        game.totalRow3PowerOpponent.setLayoutX(427);
        game.totalRow3PowerOpponent.setTextFill(Color.BLACK);
        game.totalRow3PowerOpponent.setFont(new Font(20));

        game.highScoreOpponent=new Rectangle();
        game.highScoreOpponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_high_score.png").toExternalForm()))));
        game.highScoreOpponent.setHeight(63);
        game.highScoreOpponent.setWidth(63);
        game.highScoreOpponent.setLayoutY(252);
        game.highScoreOpponent.setLayoutX(347);



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



        Rectangle cardInDeckBack=new Rectangle();//todo load png with currentdeck
        cardInDeckBack.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/deck_back_realms.jpg").toExternalForm()))));
        cardInDeckBack.setHeight(110);
        cardInDeckBack.setWidth(80);
        cardInDeckBack.setLayoutY(690);
        cardInDeckBack.setLayoutX(1440);

        Rectangle cardInDeckBackOpponent=new Rectangle();//todo load png with currentdeck
        cardInDeckBackOpponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/deck_back_monsters.jpg").toExternalForm()))));
        cardInDeckBackOpponent.setHeight(110);
        cardInDeckBackOpponent.setWidth(80);
        cardInDeckBackOpponent.setLayoutY(60);
        cardInDeckBackOpponent.setLayoutX(1440);

        Label numberOfRemainingCardsInDeck=new Label(String.valueOf(25));
        numberOfRemainingCardsInDeck.setLayoutY(770);
        numberOfRemainingCardsInDeck.setLayoutX(1460);
        numberOfRemainingCardsInDeck.setTextFill(Color.WHITE);
        numberOfRemainingCardsInDeck.setFont(new Font(30));

        Label numberOfRemainingCardsInDeckOpponent=new Label(String.valueOf(27));
        numberOfRemainingCardsInDeckOpponent.setLayoutY(140);
        numberOfRemainingCardsInDeckOpponent.setLayoutX(1465);
        numberOfRemainingCardsInDeckOpponent.setTextFill(Color.WHITE);
        numberOfRemainingCardsInDeckOpponent.setFont(new Font(30));


        Leader leader=new NorthernRealmsLeaders("foltest silver","pick an impenetrable fog card from your deck and play it instantly","realms");
        leader.setLayoutX(120);
        leader.setLayoutY(700);

        Leader leaderOpponent=new MonstersLeaders("eredin silver","double the strength of all your ","monsters");
        leaderOpponent.setLayoutX(120);
        leaderOpponent.setLayoutY(75);

        Button buttonPass=new Button();
        buttonPass.setText("Pass");
        buttonPass.setLayoutX(320);
        buttonPass.setLayoutY(750);



        Button buttonPassOpponent=new Button();
        buttonPassOpponent.setText("Pass");
        buttonPassOpponent.setLayoutX(320);
        buttonPassOpponent.setLayoutY(110);
        buttonPassOpponent.setOnMouseClicked(event -> {
            yourTurn = true;
            game.newRound(game);
        });

        game.highScoreOpponent.setVisible(false);

        frostedRow.setVisible(false);
        frostedRowOpponent.setVisible(false);

        foggedRow.setVisible(false);
        foggedRowOpponent.setVisible(false);

        rainedRow.setVisible(false);
        rainedRowOpponent.setVisible(false);






        pane.getChildren().addAll(createHbox(),game.weatherBox, playerName,avatar,game.life1,game.life2, cardx,labelForNumberOfCards,game.totalPower,highScore,realmForAvatar,
                playerNameOpponent,avatarOpponent,game.life1Opponent,game.life2Opponent,cardxOpponent,labelForNumberOfCardsOpponent,game.totalPowerOpponent,game.highScoreOpponent,realmForAvatarOpponent,
                frostedRow,frostedRowOpponent,foggedRow,foggedRowOpponent,rainedRow,rainedRowOpponent
                ,game.totalRow1Power,game.totalRow2Power,game.totalRow3Power,game.totalRow1PowerOpponent,game.totalRow2PowerOpponent,game.totalRow3PowerOpponent,cardInDeckBack,cardInDeckBackOpponent
                ,numberOfRemainingCardsInDeck,numberOfRemainingCardsInDeckOpponent,leader,leaderOpponent,buttonPass,buttonPassOpponent);


        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        stage.setFullScreen(true);

        hBoxes.add(playerFirstRowHorn);
        hBoxes.add(playerSixthRow);
        hBoxes.add(playerFifthRow);
        hBoxes.add(playerFourthRow);
        hBoxes.add(playerFirstRow);
        hBoxes.add(playerSecondRow);
        hBoxes.add(playerThirdRow);
        hBoxes.add(game.weatherBox);
        hBoxes.add(playerSecondRowHorn);
        hBoxes.add(playerThirdRowHorn);
        hBoxes.add(playerFourthRowHorn);
        hBoxes.add(playerFifthRowHorn);
        hBoxes.add(playerSixthRowHorn);

        game.hBoxes =hBoxes;
//        Deck.currentDeck.hand.clear();
        playerHand.getChildren().add(new Horn("horn", 3, true, 0, "special",12,false));
        playerHand.getChildren().add(new Card("philippa", 1 , false, 10, "realms",2,true));
        playerHand.getChildren().add(new Card("clear", 2 , true, 0, "weather",7,false));
        playerHand.getChildren().add(new Decoy("decoy", 3 , true, 0, "special",123,false));
        playerHand.getChildren().add(new Card("ciri", 1 , false, 15, "neutral",3,true));
        playerHand.getChildren().add(new Medic("yennefer", 1 , false, 7, "neutral",2,true));
        playerHand.getChildren().add(new Spy("stennis", 1 , false, 5, "realms",3,false));
        playerHand.getChildren().add(new Muster("gaunter odimm darkness", 3 , false, 4, "neutral",2,false));
        playerHand.getChildren().add(new Muster("gaunter odimm darkness", 3 , false, 4, "neutral",2,false));
        playerHand.getChildren().add(new Muster("gaunter odimm darkness", 3 , false, 4, "neutral",2,false));
        playerHand.getChildren().add(new Medic("banner nurse", 1 , false, 5, "realms",1,false));
//        Medic medic = new Medic("banner nurse", 1 , false, 5, "realms",1,false);
//        playerHand.getChildren().add(medic);
//        playerHand.getChildren().add(new Card("frost", 3 , true, 0, "weather",7,false));
//        playerHand.getChildren().add(new Card("frost", 3 , true, 0, "weather",7,false));
//        playerHand.getChildren().add(new Card("frost", 3 , true, 0, "weather",7,false));

//            for (Card card : Deck.currentDeck.hand)
//                playerHand.getChildren().add(card);

//        for (Card card : Deck.currentDeck.hand)
//            playerHand.getChildren().add(card);


        for (Node card : playerHand.getChildren()) {
            card.setOnMouseClicked(event -> {
                System.out.println("Caed has life");
                if (yourTurn) {
                    if (card instanceof Decoy) {
                        game.selected = (Card) card;
                    } else {
                        if (pane.getChildren().contains(showCardRectangle)) {
                            pane.getChildren().remove(showCardRectangle);
                        }
                        for (HBox hBox : hBoxes) {
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


                        if (selected.getCardName().equals("horn")) {
                            playerFirstRowHorn.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                            playerSecondRowHorn.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                            playerThirdRowHorn.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                        } else if (selected.getRows().contains(2) && selected.getRows().contains(3)) {
                            playerSecondRow.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                            playerThirdRow.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                        } else if (selected.getRows().contains(2)) {
                            playerSecondRow.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                        } else if (selected.getRows().contains(1)) {
                            System.out.println(selected.getCardName());
                            System.out.println(selected.getRows());
                            playerFirstRow.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                        } else if (selected.getRows().contains(3)) {
                            playerThirdRow.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                        } else if (selected.getRows().contains(7)) {
                            game.weatherBox.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                        }
                    }
                }
            });
        }
    }
    public void addCardToPane(Card card, double endY, double endX){
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
        addRow(rightVBox, new HBox() ,null, 0 , 670);
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
            HBox target;
            if (selected instanceof Horn)
                target = horn;
            else target = playerRow;
            if (selected != null && fitsBox(selected, target)) {
                pane.getChildren().remove(showCardRectangle);
                game.selectedBox = target;
                game.playerHand.getChildren().remove(selected);
                addCardToPane(selected, event.getSceneY(), event.getSceneX());
            }
            pane.getChildren().remove(showCardRectangle);
            playerFirstRow.setStyle("");
            playerSecondRow.setStyle("");
            playerThirdRow.setStyle("");
            playerFirstRowHorn.setStyle("");
            playerSecondRowHorn.setStyle("");
            playerThirdRowHorn.setStyle("");
            selected = null;
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

    private boolean fitsBox(Card selected, HBox playerRow) {
        int selectedRow = 0;
        if (playerRow.equals(playerFirstRow))
            selectedRow = 1;
        else if (playerRow.equals(playerSecondRow)) {
            selectedRow = 2;
        } else if (playerRow.equals(playerThirdRow)) {
            selectedRow = 3;
        } else if (selected instanceof Horn) {
            return true;
        } else if (playerRow.equals(game.weatherBox)) {
            selectedRow = 7;
        }
        if (selected.getRows().contains(selectedRow)) {
            return true;
        }
        return false;
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
    public static int enemyPosition(int i){
        if(i==0)return 5 ;
        if(i==1)return 4;
        if(i==2)return 3;
        if(i==3)return 2;
        if(i==4)return 1;
        if(i==5)return 0;
        if(i==6)return 11;
        if(i==7)return 10;
        if(i==8)return 9;
        if(i==9)return 8;
        if(i==10)return 7;
        if(i==11)return 6;
        if(i==12)return 12;
        return 0;
    }

}
