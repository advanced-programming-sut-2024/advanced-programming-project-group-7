package view;

import controller.Client;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import model.cards.*;
import model.leaders.*;
import view.animations.CardPlacementAnimation;

import java.util.ArrayList;
import java.util.Iterator;

public class GameLauncher extends Application {

    public EnhancedHBox playerHand = new EnhancedHBox();
    public ArrayList<Card> reservedCards = new ArrayList<>();
    public ArrayList<Card> graveyardCard=new ArrayList<>();

    public ArrayList<Card> enemyGraveyardCard=new ArrayList<>();
    public ArrayList<Card> enemyHand=new ArrayList<>();

    public  ArrayList<EnhancedHBox> hBoxes=new ArrayList<EnhancedHBox>();
    private static final double HEIGHT = 900;
    private static final double WIDTH = 1600;
    public EnhancedHBox playerFirstRowHorn = new EnhancedHBox();
    public EnhancedHBox playerFirstRow = new EnhancedHBox();
    public EnhancedHBox playerThirdRowHorn = new EnhancedHBox();
    public EnhancedHBox playerThirdRow = new EnhancedHBox();
    public EnhancedHBox playerSecondRowHorn = new EnhancedHBox();
    public EnhancedHBox playerSecondRow = new EnhancedHBox();

    public Game game;
    public EnhancedHBox playerFourthRow = new EnhancedHBox();
    public EnhancedHBox playerFourthRowHorn = new EnhancedHBox();
    public EnhancedHBox playerSixthRowHorn = new EnhancedHBox();
    public EnhancedHBox playerSixthRow = new EnhancedHBox();
    public EnhancedHBox playerFifthRowHorn = new EnhancedHBox();
    public EnhancedHBox playerFifthRow = new EnhancedHBox();
    public Pane pane = new Pane();
    private Card selected;
    public double sceneX;
    public double sceneY;
    public Rectangle showCardRectangle = new Rectangle();
    private boolean yourTurn = true;
    private Deck deck;
    private  Stage stage;
    private Client client;
    public boolean isLeaderDisabled=false;
    public EnhancedHBox weatherBox = new EnhancedHBox();
    public Card lastCardPlayed;
    public Label labelForNumberOfCards = new Label();
    public Label numberOfRemainingCardsInDeck=new Label();
    public Label labelForNumberOfCardsOpponent = new Label(String.valueOf(10));
    public Card handLastCard;
    public Pane endRoundPane=new Pane();
    public Rectangle endRoundRec=new Rectangle(300,200);





    @Override
    public void start(Stage stage1) throws Exception{
        game = new Game(this);
        stage = new Stage();
//        game.client = User.getLoggedInUser().client; todo onlination

//        client = User.getLoggedInUser().client; todo onlination
//        client.game = game;todo onlination
        for(Card card:Deck.currentDeck.hand){
            playerHand.getChildren().add(card);
        }
        reservedCards=Deck.getCurrentDeck().getReservedCards();
        pane = new Pane();
        setSize(pane);
        pane.setBackground(new Background(createBackgroundImage()));




        //CHAT BOX
        Pane chatBoxPane = new Pane();
        chatBoxPane.setLayoutX(1280);
        chatBoxPane.setLayoutY(200);
        chatBoxPane.setMinHeight(300);
        chatBoxPane.setMaxHeight(300);
        chatBoxPane.setMinWidth(150);
        chatBoxPane.setMaxWidth(150);
        Rectangle rectangle1 = new Rectangle(150, 30);
        rectangle1.setLayoutX(0);
        rectangle1.setLayoutY(0);
        Rectangle rectangle2 = new Rectangle(150, 30);
        rectangle2.setLayoutX(0);
        rectangle2.setLayoutY(40);
        Rectangle rectangle3 = new Rectangle(150, 30);
        rectangle3.setLayoutX(0);
        rectangle3.setLayoutY(80);
        Rectangle rectangle4 = new Rectangle(150, 30);
        rectangle4.setLayoutX(0);
        rectangle4.setLayoutY(180);
        Rectangle rectangle5 = new Rectangle(150, 30);
        rectangle5.setLayoutX(0);
        rectangle5.setLayoutY(220);
        Rectangle rectangle6 = new Rectangle(150, 30);
        rectangle6.setLayoutX(0);
        rectangle6.setLayoutY(260);
        Circle circle = new Circle(30);
        circle.setLayoutX(70);
        circle.setLayoutY(149);
        rectangle1.setVisible(false);
        rectangle2.setVisible(false);
        rectangle3.setVisible(false);
        rectangle4.setVisible(false);
        rectangle5.setVisible(false);
        rectangle6.setVisible(false);
        circle.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/anim_horn.png")))));
        rectangle1.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/goldLabel1.png")))));
        rectangle2.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/goldLabel2.png")))));
        rectangle3.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/goldLabel3.png")))));
        rectangle4.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/goldLabel4.png")))));
        rectangle5.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/goldLabel5.png")))));
        rectangle6.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/goldLabel6.png")))));
        circle.setOnMouseEntered(event -> {
            rectangle1.setVisible(true);
            rectangle2.setVisible(true);
            rectangle3.setVisible(true);
            rectangle4.setVisible(true);
            rectangle5.setVisible(true);
            rectangle6.setVisible(true);
        });
        chatBoxPane.setOnMouseExited(event -> {
            rectangle1.setVisible(false);
            rectangle2.setVisible(false);
            rectangle3.setVisible(false);
            rectangle4.setVisible(false);
            rectangle5.setVisible(false);
            rectangle6.setVisible(false);
        });
        rectangle1.setOnMouseClicked(event -> {
            sendReaction(1);
        });
        rectangle2.setOnMouseClicked(event -> {
            sendReaction(2);
        });
        rectangle3.setOnMouseClicked(event -> {
            sendReaction(3);
        });
        rectangle4.setOnMouseClicked(event -> {
            sendReaction(4);
        });
        rectangle5.setOnMouseClicked(event -> {
            sendReaction(5);
        });
        rectangle6.setOnMouseClicked(event -> {
            sendReaction(6);
        });
        chatBoxPane.getChildren().addAll(rectangle1, rectangle2, rectangle3, rectangle4, rectangle5, rectangle6, circle);
        //CHAT BOX



//        playerHand.getChildren().add(new Horn("horn", 3, true, 0, "special", 12, false));
//        playerHand.getChildren().add(new Berserker("young berserker", 3, false, 2, "skellige",2,false));
//        playerHand.getChildren().add(new Berserker("young berserker", 3, false, 2, "skellige",2,false));
//        playerHand.getChildren().add(new Mardroeme("ermion", 1, false, 8, "skellige",2,true));
//        playerHand.getChildren().add(new Berserker("young berserker", 3, false, 2, "skellige",2,false));
//        playerHand.getChildren().add(new Berserker("berserker", 1, false, 4, "skellige",3,false));
//        playerHand.getChildren().add(new Mardroeme("mardroeme", 3, true, 0, "special",123,false));
//        playerHand.getChildren().add(new Cow("cow", 1, false, 0, "neutral",2,false));
//        playerHand.getChildren().add(new Cow("kambi", 1, false, 0, "skellige",3,false));
//        playerHand.getChildren().add(new Medic("yennefer", 1, false, 7, "neutral", 2, true));
//        playerHand.getChildren().add(new Spy("stennis", 1, false, 5, "realms", 4, false));
//        playerHand.getChildren().add(new Muster("gaunter odimm darkness", 3 , false, 4, "neutral",2,false));
//        playerHand.getChildren().add(new Medic("banner nurse", 1, false, 5, "realms", 1, false));
//        playerHand.getChildren().add(new TightBond("young emissary 1", 1, false, 5, "nilfgaard", 3, false));
//        playerHand.getChildren().add(new Scorch("villen", 1, false, 7, "neutral", 3, false));
//        playerHand.getChildren().add(new Scorch("scorch", 3, true, 0, "special", 123456, false));
//        playerHand.getChildren().add(new Scorch("toad", 1, false, 7, "monsters", 2, false));
//        playerHand.getChildren().add(new Cow("cow",1,false,0,"neutral",2,false));


//        discardPile.add(new TightBond("catapult 1", 2, false, 8, "realms", 1, false));
//        enemyDiscardPile.add(new Scorch("schirru", 1, false, 8, "scoiatael", 1, false));
        reservedCards.add(new Muster("gaunter odimm darkness", 3 , false, 4, "neutral",2,false));
        reservedCards.add(new Card("ciri", 1, false, 15, "neutral", 3, true));
        reservedCards.add((new Card("rain", 2, true, 0, "weather", 7, false)));
        reservedCards.add(new Card("fog", 3, true, 0, "weather", 7, false));
        reservedCards.add(new Card("frost", 3, true, 0, "weather", 7, false));
        enemyHand.add(new Medic("yennefer", 1 , false, 7, "neutral",2,true));
        enemyHand.add(new Muster("gaunter odimm darkness", 3 , false, 4, "neutral",2,false));
        enemyHand.add(new Muster("gaunter odimm darkness", 3 , false, 4, "neutral",2,false));



        labelForNumberOfCards.setText(String.valueOf(playerHand.getChildren().size()));
        labelForNumberOfCards.setLayoutY(570);
        labelForNumberOfCards.setLayoutX(210);
        labelForNumberOfCards.setTextFill(Color.YELLOW);
        labelForNumberOfCards.setFont(new Font(20));

        Rectangle avatar = new Rectangle();
        avatar.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/profile.png").toExternalForm()))));
        avatar.setHeight(100);
        avatar.setWidth(70);
        avatar.setLayoutY(530);
        avatar.setLayoutX(120);

        Rectangle cardx = new Rectangle();
        cardx.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_card_count.png").toExternalForm()))));
        cardx.setHeight(35);
        cardx.setWidth(35);
        cardx.setLayoutY(555);
        cardx.setLayoutX(178);

        Label playerName = new Label("a"); //User.getLoggedInUser().getUsername() todo onlination
        playerName.setLayoutY(600);
        playerName.setLayoutX(200);
        playerName.setTextFill(Color.YELLOW);
        playerName.setFont(new Font(20));

        game.life1 = new Rectangle();
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

        game.totalPower = new Label("0");
        game.totalPower.setLayoutY(600);
        game.totalPower.setLayoutX(357);
        game.totalPower.setTextFill(Color.BLACK);
        game.totalPower.setFont(new Font(25));

        game.totalRow1Power = new Label("0");
        game.totalRow1Power.setLayoutY(625);
        game.totalRow1Power.setLayoutX(427);
        game.totalRow1Power.setTextFill(Color.BLACK);
        game.totalRow1Power.setFont(new Font(20));
        playerFirstRow.powerSum = game.totalRow1Power;

        game.totalRow2Power = new Label("0");
        game.totalRow2Power.setLayoutY(510);
        game.totalRow2Power.setLayoutX(427);
        game.totalRow2Power.setTextFill(Color.BLACK);
        game.totalRow2Power.setFont(new Font(20));
        playerSecondRow.powerSum = game.totalRow2Power;

        game.totalRow3Power = new Label("0");
        game.totalRow3Power.setLayoutY(395);
        game.totalRow3Power.setLayoutX(427);
        game.totalRow3Power.setTextFill(Color.BLACK);
        game.totalRow3Power.setFont(new Font(20));
        playerThirdRow.powerSum = game.totalRow3Power;

        Rectangle highScore = new Rectangle();
        highScore.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_high_score.png").toExternalForm()))));
        highScore.setHeight(63);
        highScore.setWidth(63);
        highScore.setLayoutY(590);
        highScore.setLayoutX(347);

        weatherBox = new EnhancedHBox();
        weatherBox.setLayoutY(385);
        weatherBox.setLayoutX(120);
        weatherBox.setMinHeight(98);
        weatherBox.setMinWidth(235);
        weatherBox.setOnMouseClicked(event -> {
            if (selected != null && fitsBox(selected, weatherBox)) {
                game.selectedBox = weatherBox;
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
            playerFourthRow.setStyle("");
            playerFifthRow.setStyle("");
            playerSixthRow.setStyle("");
            weatherBox.setStyle("");
            selected = null;
        });

        Rectangle realmForAvatar = new Rectangle();//todo needs 5 else if for player's faction
        realmForAvatar.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource(Deck.currentDeck.getDeckFaction().getShieldPic()).toExternalForm()))));
        realmForAvatar.setHeight(50);
        realmForAvatar.setWidth(50);
        realmForAvatar.setLayoutY(550);
        realmForAvatar.setLayoutX(75);

        labelForNumberOfCardsOpponent.setLayoutY(285);
        labelForNumberOfCardsOpponent.setLayoutX(210);
        labelForNumberOfCardsOpponent.setTextFill(Color.YELLOW);
        labelForNumberOfCardsOpponent.setFont(new Font(20));


        Rectangle avatarOpponent = new Rectangle();
        avatarOpponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/profile.png").toExternalForm()))));
        avatarOpponent.setHeight(100);
        avatarOpponent.setWidth(70);
        avatarOpponent.setLayoutY(245);
        avatarOpponent.setLayoutX(120);

        Rectangle cardxOpponent = new Rectangle();
        cardxOpponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_card_count.png").toExternalForm()))));
        cardxOpponent.setHeight(35);
        cardxOpponent.setWidth(35);
        cardxOpponent.setLayoutY(270);
        cardxOpponent.setLayoutX(178);

        Label playerNameOpponent = new Label("b");// User.getLoggedInUser().currentOponentName todo onlination
        playerNameOpponent.setLayoutY(315);
        playerNameOpponent.setLayoutX(200);
        playerNameOpponent.setTextFill(Color.YELLOW);
        playerNameOpponent.setFont(new Font(20));

        game.life1Opponent = new Rectangle();
        game.life1Opponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_gem_on.png").toExternalForm()))));
        game.life1Opponent.setHeight(40);
        game.life1Opponent.setWidth(40);
        game.life1Opponent.setLayoutY(270);
        game.life1Opponent.setLayoutX(240);

        game.life2Opponent = new Rectangle();
        game.life2Opponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_gem_on.png").toExternalForm()))));
        game.life2Opponent.setHeight(40);
        game.life2Opponent.setWidth(40);
        game.life2Opponent.setLayoutY(270);
        game.life2Opponent.setLayoutX(281);

        game.totalPowerOpponent = new Label("0");
        game.totalPowerOpponent.setLayoutY(265);
        game.totalPowerOpponent.setLayoutX(357);
        game.totalPowerOpponent.setTextFill(Color.BLACK);
        game.totalPowerOpponent.setFont(new Font(25));

        game.totalRow1PowerOpponent = new Label("0");
        game.totalRow1PowerOpponent.setLayoutY(47);
        game.totalRow1PowerOpponent.setLayoutX(427);
        game.totalRow1PowerOpponent.setTextFill(Color.BLACK);
        game.totalRow1PowerOpponent.setFont(new Font(20));
        playerSixthRow.powerSum = game.totalRow1PowerOpponent;

        game.totalRow2PowerOpponent = new Label("0");
        game.totalRow2PowerOpponent.setLayoutY(162);
        game.totalRow2PowerOpponent.setLayoutX(427);
        game.totalRow2PowerOpponent.setTextFill(Color.BLACK);
        game.totalRow2PowerOpponent.setFont(new Font(20));
        playerFifthRow.powerSum = game.totalRow2PowerOpponent;

        game.totalRow3PowerOpponent = new Label("0");
        game.totalRow3PowerOpponent.setLayoutY(277);
        game.totalRow3PowerOpponent.setLayoutX(427);
        game.totalRow3PowerOpponent.setTextFill(Color.BLACK);
        game.totalRow3PowerOpponent.setFont(new Font(20));
        playerFourthRow.powerSum = game.totalRow3PowerOpponent;

        game.highScoreOpponent = new Rectangle();
        game.highScoreOpponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_high_score.png").toExternalForm()))));
        game.highScoreOpponent.setHeight(63);
        game.highScoreOpponent.setWidth(63);
        game.highScoreOpponent.setLayoutY(252);
        game.highScoreOpponent.setLayoutX(347);

        game.highScorePlayer.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/icon_high_score.png").toExternalForm()))));
        game.highScorePlayer.setHeight(63);
        game.highScorePlayer.setWidth(63);
        game.highScorePlayer.setLayoutY(590);
        game.highScorePlayer.setLayoutX(347);


        Rectangle realmForAvatarOpponent = new Rectangle();//todo needs 5 else if for player's faction
        realmForAvatarOpponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/deck_shield_monsters.png").toExternalForm()))));
        realmForAvatarOpponent.setHeight(50);
        realmForAvatarOpponent.setWidth(50);
        realmForAvatarOpponent.setLayoutY(245);
        realmForAvatarOpponent.setLayoutX(75);


        Rectangle frostedRow = new Rectangle();
        frostedRow.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/overlay_frost.png").toExternalForm()))));
        frostedRow.setHeight(100);
        frostedRow.setWidth(680);
        frostedRow.setLayoutY(360);
        frostedRow.setLayoutX(590);
        playerThirdRow.badConditionEffect = frostedRow;

        Rectangle frostedRowOpponent = new Rectangle();
        frostedRowOpponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/overlay_frost.png").toExternalForm()))));
        frostedRowOpponent.setHeight(100);
        frostedRowOpponent.setWidth(680);
        frostedRowOpponent.setLayoutY(245);
        frostedRowOpponent.setLayoutX(590);
        playerFourthRow.badConditionEffect = frostedRowOpponent;

        Rectangle foggedRow = new Rectangle();
        foggedRow.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/overlay_fog.png").toExternalForm()))));
        foggedRow.setHeight(100);
        foggedRow.setWidth(680);
        foggedRow.setLayoutY(470);
        foggedRow.setLayoutX(590);
        playerSecondRow.badConditionEffect = foggedRow;

        Rectangle foggedRowOpponent = new Rectangle();
        foggedRowOpponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/overlay_fog.png").toExternalForm()))));
        foggedRowOpponent.setHeight(100);
        foggedRowOpponent.setWidth(680);
        foggedRowOpponent.setLayoutY(135);
        foggedRowOpponent.setLayoutX(590);
        playerFifthRow.badConditionEffect = foggedRowOpponent;

        Rectangle rainedRow = new Rectangle();
        rainedRow.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/overlay_rain.png").toExternalForm()))));
        rainedRow.setHeight(100);
        rainedRow.setWidth(680);
        rainedRow.setLayoutY(580);
        rainedRow.setLayoutX(590);
        playerFirstRow.badConditionEffect = rainedRow;


        Rectangle rainedRowOpponent = new Rectangle();
        rainedRowOpponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/overlay_rain.png").toExternalForm()))));
        rainedRowOpponent.setHeight(100);
        rainedRowOpponent.setWidth(680);
        rainedRowOpponent.setLayoutY(25);
        rainedRowOpponent.setLayoutX(590);
        playerSixthRow.badConditionEffect = rainedRowOpponent;


        Rectangle cardInDeckBack = new Rectangle();//todo load png with currentdeck
        cardInDeckBack.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource(Deck.currentDeck.getDeckFaction().getFactionCardBackPic()).toExternalForm()))));
        cardInDeckBack.setHeight(110);
        cardInDeckBack.setWidth(80);
        cardInDeckBack.setLayoutY(690);
        cardInDeckBack.setLayoutX(1440);

        Rectangle cardInDeckBackOpponent = new Rectangle();//todo load png with currentdeck
        cardInDeckBackOpponent.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/icons/deck_back_monsters.jpg").toExternalForm()))));
        cardInDeckBackOpponent.setHeight(110);
        cardInDeckBackOpponent.setWidth(80);
        cardInDeckBackOpponent.setLayoutY(60);
        cardInDeckBackOpponent.setLayoutX(1440);

        numberOfRemainingCardsInDeck.setText(String.valueOf(reservedCards.size()));
        numberOfRemainingCardsInDeck.setLayoutY(770);
        numberOfRemainingCardsInDeck.setLayoutX(1460);
        numberOfRemainingCardsInDeck.setTextFill(Color.WHITE);
        numberOfRemainingCardsInDeck.setFont(new Font(30));

        Label numberOfRemainingCardsInDeckOpponent = new Label(String.valueOf(27));
        numberOfRemainingCardsInDeckOpponent.setLayoutY(140);
        numberOfRemainingCardsInDeckOpponent.setLayoutX(1465);
        numberOfRemainingCardsInDeckOpponent.setTextFill(Color.WHITE);
        numberOfRemainingCardsInDeckOpponent.setFont(new Font(30));


        endRoundPane.setVisible(false);
        endRoundPane.setLayoutX(200);
        endRoundPane.setLayoutY(200);
//        Leader leader=new MonstersLeaders("eredin silver","double the strength of all your ","monsters");
//        Leader leader=(new NorthernRealmsLeaders("foltest gold","clear any weather effects(resulting from biting frost, torrential rain or impenetrable fog cards) in play","realms"));
//        Leader leader=(new NorthernRealmsLeaders("foltest copper","doubles the strength of all your siege units (unless a commander's horn is also present on that row).","realms"));
//        Leader leader=(new NorthernRealmsLeaders("foltest bronze","destroy your enemy's strongest siege unit(s) if the combined strength of all his or her siege units is 10 or more.","realms"));
//        Leader leader=(new NorthernRealmsLeaders("foltest son of medell","distroy your enemy's strongest ranged combat unit(s) if the combined strength of all his or her ranged combat units is 10 or more.","realms"));
//        Leader leader=new NorthernRealmsLeaders("foltest silver","pick an impenetrable fog card from your deck and play it instantly","realms");
//        Leader leader=(new EmpireNilfgaardiansLeaders("emhyr silver","pick a torrential rain card from your deck and play it instantly","nilfgaard"));
//        Leader leader=(new EmpireNilfgaardiansLeaders("emhyr copper","look at 3 random cards from your opponent's hand","nilfgaard"));
//        Leader leader=(new EmpireNilfgaardiansLeaders("emhyr bronze","cansel your opponent's Leader ability","nilfgaard"));
//        Leader leader=(new EmpireNilfgaardiansLeaders("emhyr gold","draw a card from your opponent's discard pile","nilfgaard"));
//        Leader leader=(new EmpireNilfgaardiansLeaders("emhyr invader of the north","abilities that restore a unit to the battlefield restore a randomly-chosen unit.affects both players.","nilfgaard"));
//        Leader leader=(new MonstersLeaders("eredin silver","double the strength of all your ","monsters"));
//        Leader leader=(new MonstersLeaders("eredin bronze","restore a card from your discard pile to your hand","monsters"));
        Leader leader = Deck.currentDeck.getDeckLeader();
//        Leader leader=(new MonstersLeaders("eredin copper","pick any weather card from your deck and play it instantly","monsters"));
//        Leader leader=(new MonstersLeaders("eredin the treacherous","doubles the strength of all spy cards(affects both players)","monsters"));
//        Leader leader=(new ScoiataelLeaders("francesca silver","destroy your enemy's strongest close combat unit(s) if the combined strength of all his or her close combat units is 10 or more.","scoiatael"));
//        Leader leader=(new ScoiataelLeaders("francesca gold","doubles the strength of all your ranged combat units (unless a commander's horn is also present on that row).","scoiatael"));
//        Leader leader=(new ScoiataelLeaders("francesca copper","draw an extra card at the beginning of the battle.","scoiatael"));
//        Leader leader=(new ScoiataelLeaders("francesca bronze","pick a biting frost card from your deck and play it instantly.","scoiatael"));
//        Leader leader=(new ScoiataelLeaders("francesca hope of the aen seidhe","move agile units to whichever valid row maximizes their strength(don't move units in optimal row).","scoiatael"));
//        Leader leader=(new SkelligeLeaders("crach an craite","shuffle all cards from each player's graveyard back into their decks","skellige"));
//        Leader leader=(new SkelligeLeaders("king bran","units only lose half their strength in bad weather conditions","skellige"));
        graveyardCard.add(new Card("holger", 1, false, 4, "skellige",1,false));
        graveyardCard.add(new Card("donar", 1, false, 4, "skellige",3,false));

        leader.setLayoutX(120);
        leader.setLayoutY(700);
        Rectangle leaderRec = new Rectangle(210, 330);
        leaderRec.setVisible(false);
        leader.setOnMouseClicked(event -> {
            if (yourTurn && !isLeaderDisabled)
                leaderRec.setVisible(true);
        });
        leaderRec.setOnMouseClicked(event -> {
            game.handleLeader(leader);
            leaderRec.setVisible(false);
            leader.setVisible(false);
        });
        leaderRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource(leader.getLgPath())))));
        leaderRec.setLayoutX(700);
        leaderRec.setLayoutY(200);
        leaderRec.setArcWidth(20);
        leaderRec.setArcHeight(20);


        Leader leaderOpponent = new MonstersLeaders("eredin silver", "double the strength of all your ", "monsters");
        leaderOpponent.setLayoutX(120);
        leaderOpponent.setLayoutY(75);

        Button buttonPass = new Button();
        buttonPass.setText("Pass");
        buttonPass.setLayoutX(320);
        buttonPass.setLayoutY(750);


        Button buttonPassOpponent = new Button();
        buttonPassOpponent.setText("Pass");
        buttonPassOpponent.setLayoutX(320);
        buttonPassOpponent.setLayoutY(110);
        buttonPassOpponent.setOnMouseClicked(event -> {
            yourTurn = true;
            try {
                game.endRound(game);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        frostedRow.setVisible(false);
        frostedRowOpponent.setVisible(false);

        foggedRow.setVisible(false);
        foggedRowOpponent.setVisible(false);

        rainedRow.setVisible(false);
        rainedRowOpponent.setVisible(false);


        pane.getChildren().addAll(createHbox(), weatherBox, playerName, avatar, game.life1, game.life2, cardx, labelForNumberOfCards, game.totalPower, realmForAvatar,
                playerNameOpponent, avatarOpponent, game.life1Opponent, game.life2Opponent, cardxOpponent, labelForNumberOfCardsOpponent, game.totalPowerOpponent, game.highScoreOpponent, game.highScorePlayer, realmForAvatarOpponent,
                frostedRow, frostedRowOpponent, foggedRow, foggedRowOpponent, rainedRow, rainedRowOpponent
                , game.totalRow1Power, game.totalRow2Power, game.totalRow3Power, game.totalRow1PowerOpponent, game.totalRow2PowerOpponent, game.totalRow3PowerOpponent, cardInDeckBack, cardInDeckBackOpponent
                , numberOfRemainingCardsInDeck, numberOfRemainingCardsInDeckOpponent, leader, leaderOpponent, buttonPass, buttonPassOpponent, chatBoxPane, leaderRec,endRoundPane);


        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();

        hBoxes.add(weatherBox);
        hBoxes.add(playerSixthRow);
        playerSixthRow.hornBox = playerSixthRowHorn;
        hBoxes.add(playerFifthRow);
        playerFifthRow.hornBox = playerFifthRowHorn;
        hBoxes.add(playerFourthRow);
        playerFourthRow.hornBox = playerFourthRowHorn;
        hBoxes.add(playerThirdRow);
        playerThirdRow.hornBox = playerThirdRowHorn;
        hBoxes.add(playerFirstRow);
        playerSecondRow.hornBox = playerSecondRowHorn;
        hBoxes.add(playerSecondRow);
        playerFirstRow.hornBox = playerFirstRowHorn;

        game.hBoxes = hBoxes;
        pane.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()== KeyCode.Q)game.undoCardCheat(lastCardPlayed);
            else if(keyEvent.getCode()==KeyCode.W)game.addCardToHandcheat();
            else if(keyEvent.getCode()==KeyCode.E)game.addHerosToGraveyardCheat();
            else if(keyEvent.getCode()==KeyCode.R)game.addClearWeatherCheat();
            else if(keyEvent.getCode()==KeyCode.T)game.addHornToHandCheat();
            else if(keyEvent.getCode()==KeyCode.Y)game.removeAllCloseKombatsCheat();//todo
            else if(keyEvent.getCode()==KeyCode.U)game.addLifeCheat();
        });


        stage.show();
//        stage.setFullScreen(true);
        playerHandMouseSetter();
    }

    private void sendReaction(int i) {
        Rectangle rectangle = new Rectangle(150,40);
        rectangle.setLayoutY(640);
        rectangle.setLayoutX(100);
        rectangle.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/goldLabel"+i+".png")))));
        pane.getChildren().add(rectangle);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(8), rectangle);
        fadeTransition.setFromValue(1.0); // Fully opaque
        fadeTransition.setToValue(0.0); // Completely transparent
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);
        fadeTransition.play();
        client.sendMessage("reation:"+User.getLoggedInUser().currentOponentName+":1.reaction");
    }

    public void getReaction(int i) {
        Rectangle rectangle = new Rectangle(150,40);
        rectangle.setLayoutY(200);
        rectangle.setLayoutX(100);
        rectangle.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/goldLabel"+i+".png")))));
        pane.getChildren().add(rectangle);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(8), rectangle);
        fadeTransition.setFromValue(1.0); // Fully opaque
        fadeTransition.setToValue(0.0); // Completely transparent
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);
        fadeTransition.play();
    }

    public void playerHandMouseSetter() {
        for (Node card : playerHand.getChildren()) {
            card.setOnMouseClicked(event -> {
//                game.removeDominantCardsAllTable(0);
                if (yourTurn) {
                    if (card instanceof Decoy) {
                        game.selected = (Card) card;
                    } else {
                        pane.getChildren().remove(showCardRectangle);
                        for (EnhancedHBox hBox : hBoxes)
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
                    } else if (selected.getRows().contains(1)) {
                        playerFirstRow.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                    } else if (selected.getRows().contains(2)) {
                        playerSecondRow.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                    } else if (selected.getRows().contains(3)) {
                        playerThirdRow.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                    } else if (selected.getRows().contains(4)) {
                        playerFourthRow.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                    } else if (selected.getRows().contains(5)) {
                        playerFifthRow.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                    } else if (selected.getRows().contains(6)) {
                        playerSixthRow.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                    } else if (selected.getRows().contains(7)) {
                        weatherBox.setStyle("-fx-background-color: rgba(255, 255, 0, 0.2);");
                    }
                }
            });
        }
    }

    public void addCardToPane(Card card, double endY, double endX){
        lastCardPlayed=card;
//        System.out.println(lastCardPlayed.getCardName());
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
        updateStatus();
    }
    private EnhancedHBox createHbox() {
        EnhancedHBox rootEnhancedHBox = new EnhancedHBox();
        rootEnhancedHBox.setAlignment(Pos.BOTTOM_CENTER);

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

        // Add EnhancedHBoxes to the right VBox (equivalent to the nested <EnhancedHBox> elements in FXML)
        addRow(rightVBox, playerSixthRowHorn, playerSixthRow, 98.0, 80.0);
        addRow(rightVBox, playerFifthRowHorn, playerFifthRow, 98.0, 670.0);
        addRow(rightVBox, playerFourthRowHorn, playerFourthRow, 98.0, 670.0);
        addRow(rightVBox, new EnhancedHBox() ,null, 0 , 670);
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
        EnhancedHBox bottomSpacing = new EnhancedHBox();
        bottomSpacing.setMinHeight(40.0);
        bottomSpacing.setPrefWidth(200.0);

        // Add all components to the root EnhancedHBox
        rootEnhancedHBox.getChildren().addAll(leftVBox, rightVBox);
        rootEnhancedHBox.setLayoutX(263);
        rootEnhancedHBox.setLayoutY(20);
        return rootEnhancedHBox;
    }
    private void addRow(VBox parent, EnhancedHBox horn, EnhancedHBox playerRow, double minHeight, double minWidth) {
        EnhancedHBox row = new EnhancedHBox();
        row.setAlignment(Pos.CENTER);
        row.setMinHeight(minHeight);
        row.setMinWidth(minWidth);
        row.setOnMouseClicked(event -> {
            EnhancedHBox target;
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
            for (EnhancedHBox hBox : hBoxes)
                hBox.setStyle("");
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

    private boolean fitsBox(Card selected, EnhancedHBox playerRow) {

        int selectedRow = 0;
        if (playerRow.equals(playerFirstRow)) {
            selectedRow = 1;
        } else if (playerRow.equals(playerSecondRow)) {
            selectedRow = 2;
        } else if (playerRow.equals(playerThirdRow)) {
            selectedRow = 3;
        } else if (playerRow.equals(playerFourthRow)) {
            selectedRow = 4;
        } else if (playerRow.equals(playerFifthRow)) {
            selectedRow = 5;
        } else if (playerRow.equals(playerSixthRow)) {
            selectedRow = 6;
        } else if (selected instanceof Horn) {
            return true;
        } else if (playerRow.equals(weatherBox)) {
            selectedRow = 7;
        }
        return selected.getRows().contains(selectedRow);
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

    public static int placeTransformer(int i){
        if(i==1)return 5;
        if(i==2)return 6;
        if(i==3)return 4;
        if(i==4)return 3;
        if(i==5)return 2;
        if(i==6)return 1;
        return 0;
    }

    public void updateStatus(){
        labelForNumberOfCards.setText(String.valueOf(playerHand.getChildren().size()));
        numberOfRemainingCardsInDeck.setText(String.valueOf(reservedCards.size()));
        System.out.println("updated");;
    }

}
