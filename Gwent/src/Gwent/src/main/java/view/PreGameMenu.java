package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
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
import model.factions.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class PreGameMenu extends Application {

    public Rectangle LeaderImage;
    public ArrayList<Leader> leaders;
    public ArrayList<Integer> leftCardLabels=new ArrayList<>();
    public int leaderIndex;
    public GridPane leftGrid;
    public Stage leaderMenu;
    public Stage factionMenu;
    public Faction currentFaction = Faction.getFactions().get(0);
    public Leader currentLeader = (Leader) currentFaction.getLeaders().get(0);
    public Deck currentDeck=new Deck(currentFaction,currentLeader);
    public GridPane rightGrid;
    public Rectangle factionLogo;
    public Label factionName;
    public ArrayList<Card> factionCards = new ArrayList<>();
    public Label totalCardInDeck;
    public Label numberOfUnitCard;
    public Label specialCards;
    public Label totalUnitCardStrength;
    public Label heroCards;


    private int factionIndex;

    @Override
    public void start(Stage stage) throws IOException {
        URL url = LoginMenu.class.getResource("/FXML/PreGameMenu.fxml");
//        System.out.println("1");
        BorderPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("pregame menu");
        stage.show();
        stage.setFullScreen(true);
    }

    @FXML
    public void initialize() {
        LeaderImage.setFill(new ImagePattern(new Image(PreGameMenu.class.getResource(currentLeader.getLgPath()).toString())));
        setCurrentFactionInfo();
        setCardsAndCommander();
        setRightGrid();
        calculateLabels();
    }

    private void setCurrentFactionInfo() {
        factionLogo.setFill(new ImagePattern(new Image(PreGameMenu.class.getResource(currentFaction.getShieldPic()).toString())));
        factionName.setText(currentFaction.getFactionName());
    }

    private void setCardsAndCommander() {
        LeaderImage.setFill(new ImagePattern(new Image(PreGameMenu.class.getResource(currentLeader.getLgPath()).toString())));
//        LinkedHashMap<Card,Integer> cardsInDeck=currentDeck.getCardsInDeck();
        if(currentFaction instanceof Monsters){
            factionCards = Monsters.getMonsterCards();
//            currentDeck.setCardsInDeck(Monsters.getMonsterDefaultDeck());
        } else if (currentFaction instanceof Skellige) {
            factionCards = Skellige.getSkelligeCards();
//            currentDeck.setCardsInDeck(Skellige.getSkelligeDefaultDeck());
        } else if (currentFaction instanceof NorthernRealms) {
            factionCards = NorthernRealms.getNorthernRealmsCards();
            currentDeck.setCardsInDeck(NorthernRealms.getNorthernRealmsDefaultDeck());
            System.out.println("norhen");
        } else if (currentFaction instanceof Scoiatael) {
            factionCards = Scoiatael.getScoiataelCards();
//            currentDeck.setCardsInDeck(Scoiatael.getScoiataelDefaultDeck());
        } else if (currentFaction instanceof EmpireNilfgaardian) {
            factionCards = EmpireNilfgaardian.getEmpireNilfgaardianCards();
//            currentDeck.setCardsInDeck(EmpireNilfgaardian.getEmpireNilfgaardianDefaultDeck());
        }
        int count = 0;
        leftGrid.getChildren().clear();
        for (Card card: factionCards) {
            Pane pane = new Pane();
            Pane pane1 = new Pane();
            Rectangle rectangle = new Rectangle();
            rectangle.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource(card.getLgPath()).toExternalForm()))));
            rectangle.setHeight(300);
            rectangle.setWidth(150);
            rectangle.setArcHeight(20);
            rectangle.setArcWidth(20);
            Label label;
            if(currentDeck.getCardsInDeck().containsKey(card)){
                 label=new Label(String.valueOf(card.getCountOfCard()-currentDeck.getCardsInDeck().get(card)));
                 leftCardLabels.add(card.getCountOfCard()-currentDeck.getCardsInDeck().get(card));
//                System.out.println("jj");
            }
            else{
                label=new Label(String.valueOf(card.getCountOfCard()));
                leftCardLabels.add(card.getCountOfCard());
//                System.out.println("kk ");
            }
//            leftCardLabels.add(card.getCountOfCard()-currentDeck.getCardsInDeck().get(card));
            label.setLayoutY(240);
            label.setLayoutX(120);
            label.setTextFill(Color.BLACK);
            label.setFont(new Font(20));
            pane.getChildren().addAll(rectangle, label);
            leftGrid.add(pane,count % 3,count / 3);
            pane.setOnMouseClicked(event -> {
                int cardLeft = Integer.parseInt(label.getText());
                if (cardLeft > 0) {
                    label.setText(String.valueOf(cardLeft - 1));
//                    leftCardLabels.set(count-1,leftCardLabels.get(count)-1);
                    addCardToDeck(card, pane1);
                    setRightGrid();
                    calculateLabels();
                }
            });
            count++;
        }
    }
    public void setRightGrid(){
        rightGrid.getChildren().clear();
        int count=0;
        for(Card card:currentDeck.getCardsInDeck().keySet()){
            Pane pane=new Pane();
            Pane pane1=new Pane();
            Rectangle rectangle=new Rectangle();
            rectangle.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource(card.getLgPath()).toExternalForm()))));
            rectangle.setHeight(300);
            rectangle.setWidth(150);
            rectangle.setArcWidth(20);
            rectangle.setArcHeight(20);
            Label label=new Label(String.valueOf(currentDeck.getCardsInDeck().get(card)));
            label.setLayoutY(240);
            label.setLayoutX(120);
            label.setTextFill(Color.BLACK);
            label.setFont(new Font(20));
            pane.getChildren().addAll(rectangle,label);
            pane.setOnMouseClicked(event -> {
                int countOfCardInDeck=Integer.parseInt(label.getText());
                if(countOfCardInDeck>0){
                    label.setText(String.valueOf(countOfCardInDeck - 1));
                    returnCardToLeft(card,pane1);
                    currentDeck.deleteCardFromDeck(card);
                }
            });
            rightGrid.add(pane,count%3,count/3);
            count++;
        }
    }
    private void addCardToDeck(Card card, Pane pane){
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource(card.getLgPath()).toExternalForm()))));
        rectangle.setHeight(300);
        rectangle.setWidth(150);
        rectangle.setArcHeight(20);
        rectangle.setArcWidth(20);
        Label label;
//        Label label = new Label(String.valueOf(card.getCountOfCard()));
        if(currentDeck.getCardsInDeck().containsKey(card)){
            label=new Label(String.valueOf(currentDeck.getCardsInDeck().get(card)+1));
            System.out.println("jj");
        }
        else{
            label=new Label(String.valueOf(1));
            System.out.println("kk ");
        }
        int index=0;
        for(Card tmpcard:factionCards){
            if(!tmpcard.getCardName().equals(card.getCardName()))
                index++;
            else break;
        }
        leftCardLabels.set(index,leftCardLabels.get(index)-1);
        currentDeck.addToDeck(card);
        label.setLayoutY(240);
        label.setLayoutX(120);
        label.setTextFill(Color.GOLD);
        label.setFont(new Font(20));
        pane.getChildren().addAll(rectangle, label);
        int size=currentDeck.getCardsInDeck().size();
        rightGrid.add(pane,(size-1)%3,(size-1)/3);
//        rightGrid.add(pane,0,0); // todo: handle the deck class asap
    }
    public void returnCardToLeft(Card card,Pane pane){
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource(card.getLgPath()).toExternalForm()))));
        rectangle.setHeight(300);
        rectangle.setWidth(150);
        rectangle.setArcHeight(20);
        rectangle.setArcWidth(20);
        int index=0;
        for(Card tmpCard:factionCards){
            if(tmpCard.getCardName().equals(card.getCardName())){
                break;
            }
            index++;
        }
        leftCardLabels.set(index,leftCardLabels.get(index)+1);
        Label label=new Label(String.valueOf(leftCardLabels.get(index)));
        label.setLayoutY(240);
        label.setLayoutX(120);
        label.setTextFill(Color.GOLD);
        label.setFont(new Font(20));
        pane.getChildren().addAll(rectangle, label);
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
            currentLeader = (Leader) currentFaction.getLeaders().get(0);
            setCurrentFactionInfo();
            setCardsAndCommander();
            setRightGrid();
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
        numberOfUnitCard.setText(currentDeck.totalUnitCard +"/22");
        specialCards.setText(currentDeck.totalSpecialCardInDeck +"/10");
        totalUnitCardStrength.setText(String.valueOf(currentDeck.totalUnitCardStrength));
        heroCards.setText(String.valueOf(currentDeck.totalHeroCard));
    }
}
