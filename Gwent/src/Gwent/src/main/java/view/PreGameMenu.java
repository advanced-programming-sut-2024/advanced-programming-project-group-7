package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    public static Stage stage;
    public Label factionName;
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

    @Override
    public void start(Stage stage) throws IOException {
        URL url = LoginMenu.class.getResource("/FXML/PreGameMenu.fxml");
        BorderPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("pregame menu");
        PreGameMenu.stage=stage;
        stage.show();
        stage.setFullScreen(true);
    }

    @FXML
    public void initialize() {
        LeaderImage.setFill(new ImagePattern(new Image(PreGameMenu.class.getResource(currentLeader.getLgPath()).toString())));
        setCurrentFactionInfo();
        setCardsAndCommander();
        setCardsInDeck();
        calculateLabels();
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
                int cardLeft = Integer.parseInt(label.getText());
                if (cardLeft > 0) {
                    label.setText(String.valueOf(cardLeft - 1));
                    currentDeck.addToDeck(card);
                    setCardsInDeck();
                    calculateLabels();
                }
                for(Card card1:currentDeck.getCardsInDeck().keySet()) System.out.println(card1.getCardName()+" "+currentDeck.getCardsInDeck().get(card1));
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

    @FXML
    private void gotoVeto() throws Exception {
        if(currentDeck.totalUnitCard>=22){
            if(currentDeck.totalSpecialCardInDeck<=10) {
                VetoCard vetoCard = new VetoCard();
                vetoCard.setDeck(currentDeck.shuffleDeck());
                vetoCard.start(stage);
            }
            Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("!");
            alert.show();
        }
        Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("!");
        alert.show();
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
        else specialCards.setTextFill(Color.BLACK);
        if(currentDeck.totalUnitCard<22)numberOfUnitCard.setTextFill(Color.RED);
        else {
            numberOfUnitCard.setTextFill(Color.BLACK);
            numberOfUnitCard.setText(String.valueOf(currentDeck.totalUnitCard));
        }
        heroCards.setTextFill(Color.BLACK);
        totalCardInDeck.setTextFill(Color.BLACK);
        totalUnitCardStrength.setTextFill(Color.BLACK);
   }
}
