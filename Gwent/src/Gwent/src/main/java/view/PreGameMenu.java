package view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
import controller.Client;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;
import model.cards.Medic;
import model.factions.*;
import model.leaders.*;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class PreGameMenu extends Application {

    public Label factionName;
    public ScrollPane leftGridScrollPane;
    public ScrollPane rightGridScrollPane;
    public Label lobyMenuPlayerInfo;
    public TextField fileName;
    private int factionIndex;
    public Rectangle LeaderImage;
    public ArrayList<Leader> leaders;
    public int leaderIndex;
    public GridPane leftGrid;
    public GridPane rightGrid;
    public Stage leaderMenu;
    public Stage factionMenu;
    public Faction currentFaction = Faction.getFactions().get(0);
    public Leader currentLeader = (Leader) currentFaction.getLeaders().get(0);
    public Deck currentDeck=new Deck(currentFaction,currentLeader);
    public Rectangle factionLogo;
    public ArrayList<Card> factionCards = new ArrayList<>();
    public Label totalCardInDeck;
    public Label numberOfUnitCard;
    public Label specialCards;
    public Label totalUnitCardStrength;
    public Label heroCards;
    public Stage stage;
    private Client client;
    private Label challengeLabel = new Label("VS");
    private Button accept = new Button("accept");
    private Button reject = new Button("reject");
    private Stage inviteMenu;


    @Override
    public void start(Stage stage) throws IOException {
        URL url = LoginMenu.class.getResource("/FXML/PreGameMenu.fxml");
        BorderPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("pregame menu");
        stage.show();
        stage.setFullScreen(true);
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        LeaderImage.setFill(new ImagePattern(new Image(PreGameMenu.class.getResource(currentLeader.getLgPath()).toString())));
        leftGridScrollPane.setFitToWidth(true);
        rightGridScrollPane.setFitToWidth(true);
        lobyMenuPlayerInfo.setText("Welcome "+User.getLoggedInUser().getUsername());
        setCurrentFactionInfo();
        setCardsAndCommander();
        setCardsInDeck();
        calculateLabels();
//        client = User.getLoggedInUser().client;
//        client.pregameMenu = this;
    }

    private void setCurrentFactionInfo() {
        factionLogo.setFill(new ImagePattern(new Image(PreGameMenu.class.getResource(currentFaction.getShieldPic()).toString())));
        factionName.setText(currentFaction.getFactionName());
    }

    private void setCardsAndCommander() {
        LeaderImage.setFill(new ImagePattern(new Image(PreGameMenu.class.getResource(currentLeader.getLgPath()).toString())));
        if(currentFaction instanceof Monsters){
            factionCards = Monsters.getMonsterCards();
            currentDeck.setCardsInDeck(Monsters.getMonsterDefaultDeck());
        } else if (currentFaction instanceof Skellige) {
            factionCards = Skellige.getSkelligeCards();
            currentDeck.setCardsInDeck(Skellige.getSkelligeDefaultDeck());
        } else if (currentFaction instanceof NorthernRealms) {
            factionCards = NorthernRealms.getNorthernRealmsCards();
            currentDeck.setCardsInDeck(NorthernRealms.getNorthernRealmsDefaultDeck());
        } else if (currentFaction instanceof Scoiatael) {
            factionCards = Scoiatael.getScoiataelCards();
            currentDeck.setCardsInDeck(Scoiatael.getScoiataelDefaultDeck());
        } else if (currentFaction instanceof EmpireNilfgaardian) {
            factionCards = EmpireNilfgaardian.getEmpireNilfgaardianCards();
            currentDeck.setCardsInDeck(EmpireNilfgaardian.getEmpireNilfgaardianDefaultDeck());
        }
        int count = 0;
        leftGrid.getChildren().clear();
        for (Card card: factionCards) {
            Pane pane = new Pane();
            Rectangle rectangle = new Rectangle();
            rectangle.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource(card.getLgPath()).toExternalForm()))));
            rectangle.setHeight(300);
            rectangle.setWidth(150);
            rectangle.setArcHeight(20);
            rectangle.setArcWidth(20);
            Label label;
            if(currentDeck.getCardsInDeck().containsKey(card)){
                 label=new Label(String.valueOf(card.getCountOfCard()-currentDeck.getCardsInDeck().get(card)));
            }
            else{
                label=new Label(String.valueOf(card.getCountOfCard()));
            }
            label.setLayoutY(240);
            label.setLayoutX(120);
            label.setTextFill(Color.BLACK);
            label.setFont(new Font(20));
            pane.getChildren().addAll(rectangle, label);
            leftGrid.add(pane,count % 3,count / 3);
            pane.setOnMouseClicked(event -> {
//                ObjectMapper mapper=new ObjectMapper();
//                try {
//                    String cardName= mapper.writeValueAsString(card);
//                    System.out.println(cardName);
//                } catch (JsonProcessingException e) {
//                    throw new RuntimeException(e);
//                }
//                Gson gson = new Gson();
//                currentDeck.deckAsArrayList.add(new Medic("yennefer", 1 , false, 7, "neutral",2,true));
//                try {
//                    String jsonString = mapper.writeValueAsString(new Medic("yennefer", 1 , false, 7, "neutral",2,true));
//                    System.out.println(jsonString);
//                } catch (JsonProcessingException e) {
//                    throw new RuntimeException(e);
//                }

//                System.out.println(jsonString);
//                System.out.println(card+" : "+card.getCardName());
                int cardLeft = Integer.parseInt(label.getText());
                if (cardLeft > 0) {
                    label.setText(String.valueOf(cardLeft - 1));
                    currentDeck.addToDeck(card);
                    setCardsInDeck();
                    calculateLabels();
                }
//                for(Card card1:currentDeck.getCardsInDeck().keySet()) System.out.println(card1.getCardName()+" "+currentDeck.getCardsInDeck().get(card1));
            });
            count++;
        }
    }

    public void setCardsInDeck(){
        rightGrid.getChildren().clear();
        int count=0;
        for (Card factionCard : factionCards) {
            for (Card card : currentDeck.getCardsInDeck().keySet()) {
                if ((factionCard.getCardName().equals(card.getCardName()))) {
                    Pane pane = new Pane();
                    Rectangle rectangle = new Rectangle();
                    rectangle.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource(card.getLgPath()).toExternalForm()))));
                    rectangle.setHeight(300);
                    rectangle.setWidth(150);
                    rectangle.setArcWidth(20);
                    rectangle.setArcHeight(20);
                    Label label = new Label(String.valueOf(currentDeck.getCardsInDeck().get(card)));
                    label.setLayoutY(240);
                    label.setLayoutX(120);
                    label.setTextFill(Color.BLACK);
                    label.setFont(new Font(20));
                    pane.getChildren().addAll(rectangle, label);
                    pane.setOnMouseClicked(event -> {
                        int countOfCardInDeck = Integer.parseInt(label.getText());
                        if (countOfCardInDeck > 0) {
                            label.setText(String.valueOf(countOfCardInDeck - 1));
                            currentDeck.deleteCardFromDeck(card);
                            setCardsAndCommander();
                            setCardsInDeck();
                            calculateLabels();
//                            for(Card card1:currentDeck.getCardsInDeck().keySet()) System.out.println(card1+" : "+card1.getCardName());
                        }
                    });
                    rightGrid.add(pane, count % 3, count / 3);
                    count++;
                }
            }
        }
    }

    public void showLeaderMenu(MouseEvent mouseEvent) {
        leaders = new ArrayList<>();
        leaders = currentFaction.getLeaders();
        leaderMenu = new Stage();
        Pane root = new Pane();
        Scene scene = new Scene(root);
        Leader leader = null;
        int leaderCount = leaders.size();
        leaderMenu.initStyle(StageStyle.UNDECORATED);
        leaderMenu.initStyle(StageStyle.TRANSPARENT);
        root.setBackground(Background.EMPTY);
        scene.setFill(Color.rgb(1,1,1, 0.7));
        root.setMinHeight(500);
        root.setMinWidth(800);
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setLayoutX(300);
        vBox.setLayoutY(10);
        vBox.setMaxWidth(200);
        Rectangle rectangle = new Rectangle(400, 300, 200, 320);
        rectangle.setArcWidth(25);
        rectangle.setArcHeight(25);
        rectangle.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource(currentLeader.getLgPath())))));
        Button confirm = new Button("confirm");
        confirm.setOnMouseClicked(event -> {
            currentDeck.setDeckLeader(currentLeader);
            LeaderImage.setFill(rectangle.getFill());
            leaderMenu.close();
        });
        Label label = new Label(currentLeader.getDescription());

        Button toRight = new Button("to right");
        Button toLeft = new Button("to left");
        toRight.setOnMouseClicked(event -> {
            leaderIndex++;
            leaderIndex %= leaderCount;
            Leader leader1 = leaders.get(leaderIndex);
            label.setText(leader1.getDescription());
            rectangle.setFill(new ImagePattern(new Image(
                    String.valueOf(PreGameMenu.class.getResource(leader1.getLgPath()).toExternalForm()))));
        });
        toLeft.setOnMouseClicked(event -> {
            leaderIndex--;
            if (leaderIndex < 0)
                leaderIndex = leaderCount -1;
            leaderIndex %= leaderCount;
            Leader leader1 = leaders.get(leaderIndex);
            label.setText(leader1.getDescription());
            rectangle.setFill(new ImagePattern(new Image(
                    String.valueOf(PreGameMenu.class.getResource(leader1.getLgPath()).toExternalForm()))));
        });
        HBox hBox = new HBox(10, toLeft, toRight);
        hBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(rectangle, hBox, confirm, label);
        root.getChildren().add(vBox);
        leaderMenu.setScene(scene);
        leaderMenu.showAndWait();
    }

    public void showFactionMenu(MouseEvent mouseEvent) {
        factionMenu = new Stage();
        Pane root = new Pane();
        Scene scene = new Scene(root);
        // Remove window decoration
        factionMenu.initStyle(StageStyle.UNDECORATED);
        factionMenu.initStyle(StageStyle.TRANSPARENT);
        root.setBackground(Background.EMPTY);
        scene.setFill(Color.rgb(1,1,1, 0.7));
        root.setMinHeight(500);
        root.setMinWidth(800);
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setLayoutX(300);
        vBox.setLayoutY(10);
        vBox.setMaxWidth(200);
        Rectangle rectangle = new Rectangle(400, 300, 200, 320);
        rectangle.setArcWidth(25);
        rectangle.setArcHeight(25);
        rectangle.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource(currentFaction.getLgPath())))));
        Button confirm = new Button("confirm");
        confirm.setOnMouseClicked(event -> {
            factionMenu.close();
            currentFaction = Faction.getFactions().get(factionIndex);
            currentDeck.setDeckFaction(currentFaction);
            currentLeader = (Leader) currentFaction.getLeaders().get(0);
            setCurrentFactionInfo();
            setCardsAndCommander();
            setCardsInDeck();
            leaderIndex = 0;
        });
        Label label = new Label("faction description here");
        Button toRight = new Button("to right");
        Button toLeft = new Button("to left");
        toRight.setOnMouseClicked(event -> {
            factionIndex ++;
            factionIndex %= 5;
            Faction faction = Faction.getFactions().get(factionIndex);
            label.setText(faction.getDescription());
            rectangle.setFill(new ImagePattern(new Image(
                    String.valueOf(PreGameMenu.class.getResource(faction.getLgPath()).toExternalForm()))));
            rectangle.setLayoutX(400);
            rectangle.setLayoutY(300);
        });
        toLeft.setOnMouseClicked(event -> {
            factionIndex--;
            if (factionIndex < 0)
                factionIndex = 4;
            factionIndex %= 5;
            Faction faction = Faction.getFactions().get(factionIndex);
            label.setText(faction.getDescription());
            rectangle.setFill(new ImagePattern(new Image(
                    String.valueOf(PreGameMenu.class.getResource(faction.getLgPath()).toExternalForm()))));
            rectangle.setLayoutX(400);
            rectangle.setLayoutY(300);
        });
        HBox hBox = new HBox(10, toLeft, toRight);
        hBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(rectangle, hBox, confirm, label);
        root.getChildren().add(vBox);
        factionMenu.setScene(scene);
        factionMenu.showAndWait();
    }

    public void calculateLabels(){
        currentDeck.calculateDeck();
        totalCardInDeck.setText(String.valueOf(currentDeck.totalCardsInDeck));
        if(currentDeck.totalUnitCard<22) numberOfUnitCard.setText(currentDeck.totalUnitCard +"/22");
        else  numberOfUnitCard.setText(String.valueOf(currentDeck.totalUnitCard));
        specialCards.setText(currentDeck.totalSpecialCardInDeck +"/10");
        totalUnitCardStrength.setText(String.valueOf(currentDeck.totalUnitCardStrength));
        heroCards.setText(String.valueOf(currentDeck.totalHeroCard));
        if(currentDeck.totalSpecialCardInDeck > 10) specialCards.setTextFill(Color.RED);
        else specialCards.setTextFill(Color.WHITE);
        if(currentDeck.totalUnitCard<22)numberOfUnitCard.setTextFill(Color.RED);
        else {
            numberOfUnitCard.setTextFill(Color.WHITE);
            numberOfUnitCard.setText(String.valueOf(currentDeck.totalUnitCard));
        }
        heroCards.setTextFill(Color.WHITE);
        totalCardInDeck.setTextFill(Color.WHITE);
        totalUnitCardStrength.setTextFill(Color.WHITE);
   }

    public void inviteMenu(MouseEvent mouseEvent) {
        inviteMenu = new Stage();
        Pane root = new Pane();
        Scene scene = new Scene(root);
        inviteMenu.initStyle(StageStyle.UNDECORATED);
        inviteMenu.initStyle(StageStyle.TRANSPARENT);
        root.setBackground(Background.EMPTY);
        scene.setFill(Color.rgb(1,1,1, 0.7));
        root.setMinHeight(500);
        root.setMinWidth(800);
        TextField opponentName = new TextField();
        Button invite = new Button("invite");
        invite.setOnMouseClicked(event -> {
//            if (User.getLoggedInUser().getFriends().contains(opponentName.getText())) {
//                client.sendMessage("invite:" + opponentName.getText() + ":" + User.getLoggedInUser().getUsername());
//            }
            client.sendMessage("invite:" + opponentName.getText() + ":" + User.getLoggedInUser().getUsername());
        });
        challengeLabel.setVisible(false);
        challengeLabel.setTextFill(Color.WHITE);
        accept.setVisible(false);
        reject.setVisible(false);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(opponentName, invite, challengeLabel, accept, reject);
        root.getChildren().add(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setLayoutX(250);
        vBox.setLayoutY(300);
        inviteMenu.setScene(scene);
        inviteMenu.show();
    }

    public void showInvitation(String opponent) {
        challengeLabel.setVisible(true);
        challengeLabel.setText(opponent + " challenged you");
        accept.setVisible(true);
        reject.setVisible(true);
        accept.setOnMouseClicked(event ->  {
            VetoCard vetoCard = new VetoCard();
            try {
                User.getLoggedInUser().currentOponentName = opponent;
                client.sendMessage("accept:" + opponent + ":" + User.getLoggedInUser().getUsername() );
                inviteMenu.close();
                Deck.currentDeck = currentDeck;
//                Deck.currentDeck.shuffleDeck();
                vetoCard.start(LoginMenu.stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void goToVetoMenu() {
        Alert alert = null;
        if(currentDeck.totalUnitCard<22){
            alert=new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("please choose 22 unit card at least");
            alert.show();
        }
        else if(currentDeck.totalSpecialCardInDeck>10){
            alert=new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("please choose 10 special card at most");
            alert.show();
        }
        if(alert==null) {
            VetoCard vetoCard = new VetoCard();
            try {
                Deck.currentDeck = currentDeck;
                Deck.currentDeck.shuffleDeck();
//            inviteMenu.close();
                vetoCard.start(LoginMenu.stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void saveDeck(Deck deck, String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(deck);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public Deck loadDeck(String filename) {
        Deck deck = null;
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            deck = (Deck) in.readObject();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
        return deck;
    }

    public void saveDeckFunc(MouseEvent mouseEvent) throws IOException {
        StringBuilder deckAsString=new StringBuilder();
        deckAsString.append(currentDeck.getDeckFaction().getFactionName()).append("-").append(currentDeck.getDeckLeader().getLeaderName()).append("-");
        for(Card card:currentDeck.getCardsInDeck().keySet())
            deckAsString.append(card.getCardName()).append(":").append(currentDeck.getCardsInDeck().get(card)).append("-");
//        PrintWriter out = new PrintWriter("filename.txt");
//        out.println(deckAsString.toString());
//        out.close();
        File file=new File("/users/wishuwerehere/Desktop/"+fileName.getText()+".json");
        Files.writeString(file.toPath(),deckAsString.toString());
//        System.out.println(deckAsString.toString());
    }

    public void loadDeckFunc() throws IOException {
        Path path=Path.of("/users/wishuwerehere/Desktop/"+fileName.getText()+".json");
        String deckAsString=Files.readString(path);
        String[] splitDec=deckAsString.split("-");
        switch (splitDec[0]) {
            case "Northern Realms":
                NorthernRealms northernRealms=new NorthernRealms("draw a card from your deck whenever you win a round.");
                currentFaction=northernRealms;
                currentDeck.setDeckFaction(northernRealms);
                if(splitDec[1].equals("foltest silver"))currentDeck.setDeckLeader(new NorthernRealmsLeaders("foltest silver","pick an impenetrable fog card from your deck and play it instantly","realms"));
                else if(splitDec[1].equals("foltest gold"))currentDeck.setDeckLeader(new NorthernRealmsLeaders("foltest gold","clear any weather effects(resulting from biting frost, torrential rain or impenetrable fog cards) in play","realms"));
                else if(splitDec[1].equals("foltest copper"))currentDeck.setDeckLeader(new NorthernRealmsLeaders("foltest copper","doubles the strength of all your siege units (unless a commander's horn is also present on that row).","realms"));
                else if(splitDec[1].equals("foltest bronze"))currentDeck.setDeckLeader(new NorthernRealmsLeaders("foltest bronze","destroy your enemy's strongest siege unit(s) if the combined strength of all his or her siege units is 10 or more.","realms"));
                else if(splitDec[1].equals("foltest son of medell"))currentDeck.setDeckLeader(new NorthernRealmsLeaders("foltest son of medell","distroy your enemy's strongest ranged combat unit(s) if the combined strength of all his or her ranged combat units is 10 or more.","realms"));
                currentDeck.getCardsInDeck().clear();
                for(Card card:NorthernRealms.getNorthernRealmsCards())
                    for(int i=0;i< Arrays.stream(splitDec).count()-2;i++){
                        String[] cardInfo=splitDec[i].split(":");
                        if(cardInfo[0].equals(card.getCardName())){
                            currentDeck.getCardsInDeck().put(card,Integer.parseInt(cardInfo[1]));
                        }
                    }
                break;
            case "Skellige":
                Skellige skellige=new Skellige("two random cards from the graveyard are placed on the battlefield at the start of the third round");
                currentFaction=skellige;
                currentDeck.setDeckFaction(skellige);
                if(splitDec[1].equals("crach an craite"))currentDeck.setDeckLeader(new SkelligeLeaders("crach an craite","shuffle all cards from each player's graveyard back into their decks","skellige"));
                else if(splitDec[1].equals("king bran"))currentDeck.setDeckLeader(new SkelligeLeaders("king bran","units only lose half their strength in bad weather conditions","skellige"));
                currentDeck.getCardsInDeck().clear();
                for(Card card:Skellige.getSkelligeCards())
                    for(int i=0;i< Arrays.stream(splitDec).count()-2;i++) {
                        String[] cardInfo = splitDec[i].split(":");
                        if (cardInfo[0].equals(card.getCardName())) {
                            currentDeck.getCardsInDeck().put(card, Integer.parseInt(cardInfo[1]));
                        }
                    }
                break;
            case "Scoiatael":
                Scoiatael scoiatael=new Scoiatael("decides who takes first turn");
                currentFaction=scoiatael;
                currentDeck.setDeckFaction(scoiatael);
                if(splitDec[1].equals("francesca silver"))currentDeck.setDeckLeader(new ScoiataelLeaders("francesca silver","destroy your enemy's strongest close combat unit(s) if the combined strength of all his or her close combat units is 10 or more.","scoiatael"));
                else if(splitDec[1].equals("francesca gold"))currentDeck.setDeckLeader(new ScoiataelLeaders("francesca gold","doubles the strength of all your ranged combat units (unless a commander's horn is also present on that row).","scoiatael"));
                else if(splitDec[1].equals("francesca copper"))currentDeck.setDeckLeader(new ScoiataelLeaders("francesca copper","draw an extra card at the beginning of the battle.","scoiatael"));
                else if(splitDec[1].equals("francesca bronze"))currentDeck.setDeckLeader(new ScoiataelLeaders("francesca bronze","pick a biting frost card from your deck and play it instantly.","scoiatael"));
                else if(splitDec[1].equals("francesca hope of the aen seidhe"))currentDeck.setDeckLeader(new ScoiataelLeaders("francesca hope of the aen seidhe","move agile units to whichever valid row maximizes their strength(don't move units in optimal row).","scoiatael"));
                currentDeck.getCardsInDeck().clear();
                for(Card card:Scoiatael.getScoiataelCards())
                    for(int i=0;i< Arrays.stream(splitDec).count()-2;i++) {
                        String[] cardInfo = splitDec[i].split(":");
                        if (cardInfo[0].equals(card.getCardName())) {
                            currentDeck.getCardsInDeck().put(card, Integer.parseInt(cardInfo[1]));
                        }
                    }
                break;
            case "Monsters":
                Monsters monsters=new Monsters("keeps a random unit card after each round");
                currentFaction=monsters;
                currentDeck.setDeckFaction(monsters);
                if(splitDec[1].equals("eredin silver"))currentDeck.setDeckLeader(new MonstersLeaders("eredin silver","double the strength of all your ","monsters"));
                else if(splitDec[1].equals("eredin bronze"))currentDeck.setDeckLeader(new MonstersLeaders("eredin bronze","restore a card from your discard pile to your hand","monsters"));
                else if(splitDec[1].equals("eredin gold"))currentDeck.setDeckLeader(new MonstersLeaders("eredin gold","discard 2 card amd draw 1 card of your choise from your deck","monsters"));
                else if(splitDec[1].equals("eredin copper"))currentDeck.setDeckLeader(new MonstersLeaders("eredin copper","pick any weather card from your deck and play it instantly","monsters"));
                else if(splitDec[1].equals("eredin the treacherous"))currentDeck.setDeckLeader(new MonstersLeaders("eredin the treacherous","doubles the strength of all spy cards(affects both players)","monsters"));
                currentDeck.getCardsInDeck().clear();
                for(Card card:Monsters.getMonsterCards())
                    for(int i=0;i< Arrays.stream(splitDec).count()-2;i++) {
                        String[] cardInfo = splitDec[i].split(":");
                        if (cardInfo[0].equals(card.getCardName())) {
                            currentDeck.getCardsInDeck().put(card, Integer.parseInt(cardInfo[1]));
                        }
                    }
                break;
            case "EmpireNilfgaardian":
                EmpireNilfgaardian empireNilfgaardian=new EmpireNilfgaardian("wins any round that ends in draw");
                currentFaction=empireNilfgaardian;
                currentDeck.setDeckFaction(empireNilfgaardian);
                if(splitDec[1].equals("emhyr silver"))currentDeck.setDeckLeader(new EmpireNilfgaardiansLeaders("emhyr silver","pick a torrential rain card from your deck and play it instantly","nilfgaard"));
                else if(splitDec[1].equals("emhyr copper"))currentDeck.setDeckLeader(new EmpireNilfgaardiansLeaders("emhyr copper","look at 3 random cards from your opponent's hand","nilfgaard"));
                else if(splitDec[1].equals("emhyr bronze"))currentDeck.setDeckLeader(new EmpireNilfgaardiansLeaders("emhyr bronze","cansel your opponent's Leader ability","nilfgaard"));
                else if(splitDec[1].equals("emhyr gold"))currentDeck.setDeckLeader(new EmpireNilfgaardiansLeaders("emhyr gold","draw a card from your opponent's discard pile","nilfgaard"));
                else if(splitDec[1].equals("emhyr invader of the north"))currentDeck.setDeckLeader(new EmpireNilfgaardiansLeaders("emhyr invader of the north","abilities that restore a unit to the battlefield restore a randomly-chosen unit.affects both players.","nilfgaard"));
                currentDeck.getCardsInDeck().clear();
                for(Card card:EmpireNilfgaardian.getEmpireNilfgaardianCards())
                    for(int i=0;i< Arrays.stream(splitDec).count()-2;i++) {
                        String[] cardInfo = splitDec[i].split(":");
                        if (cardInfo[0].equals(card.getCardName())) {
                            currentDeck.getCardsInDeck().put(card, Integer.parseInt(cardInfo[1]));
                        }
                    }
                break;
        }
        setCardsAndCommander();
        setCardsInDeck();
        calculateLabels();
        LeaderImage.setFill(new ImagePattern(new Image(PreGameMenu.class.getResource(currentDeck.getDeckLeader().getLgPath()).toString())));
        factionLogo.setFill(new ImagePattern(new Image(PreGameMenu.class.getResource(currentDeck.getDeckFaction().getShieldPic()).toString())));
        factionName.setText(currentDeck.getDeckFaction().getFactionName());
    }
}
//try {
//        Deck.currentDeck = currentDeck;// todo 1
//        Deck.currentDeck.shuffleDeck();
//        VetoCard vetoCard = new VetoCard();
//        vetoCard.start(LoginMenu.stage);
//        } catch (Exception e) {
//        e.printStackTrace();
//        }
