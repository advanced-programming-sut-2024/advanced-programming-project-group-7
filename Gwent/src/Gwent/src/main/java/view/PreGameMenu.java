package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import model.factions.Monsters;

import java.io.IOException;
import java.net.URL;

public class PreGameMenu extends Application {

    public Rectangle LeaderImage;
    public Leader leader;
    public int leaderIndex;
    public GridPane leftGrid;
    public Stage leaderMenu;
    public Stage factionMenu;
    public Faction currentFaction = new Monsters("hello there");
    private int factionIndex;

    @Override
    public void start(Stage stage) throws IOException {
        URL url = LoginMenu.class.getResource("/FXML/PreGameMenu.fxml");
        BorderPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("pregame menu");
        stage.show();
        stage.setFullScreen(true);
    }

    private void handleLeaderMenu(Rectangle rectangle, Label label) {
        leaderIndex %= 1; // todo adapt it
        Leader MonsterLeader = (Leader) Leader.MonsterLeader.getLeaders().get(leaderIndex);
        label.setText(MonsterLeader.getDescription());
        rectangle.setFill(new ImagePattern(new Image(
                String.valueOf(PreGameMenu.class.getResource(MonsterLeader.getLgPath()).toExternalForm()))));
    }


    @FXML
    public void initialize() {
        LeaderImage.setFill(new ImagePattern(new Image(PreGameMenu.class.getResource("/Images/lg/monsters_eredin_gold.jpg").toString())));
        setCardsAndCommander();//this is the real deal
    }

    private void setCardsAndCommander() {
        if(currentFaction instanceof Monsters){
            //this is how it is done
        }
        int count = 0;
        for (Card card: Monsters.getMonsterCards()) {
            Pane pane = new Pane();
            Rectangle rectangle = new Rectangle();
            rectangle.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource(card.getLgPath()).toExternalForm()))));
            rectangle.setHeight(300);
            rectangle.setWidth(150);
            rectangle.setArcHeight(20);
            rectangle.setArcWidth(20);
            Label label = new Label(String.valueOf(card.getCountOfCard()));
            label.setLayoutY(240);
            label.setLayoutX(120);
            label.setTextFill(Color.GOLD);
            label.setFont(new Font(20));
            pane.getChildren().addAll(rectangle, label);
            leftGrid.add(pane,count % 3,count / 3);
            pane.setOnMouseClicked(event -> {
                System.out.println(card.getCardName());

            });
            System.out.println("hi");
            count++;
        }
    }

    public void add00(MouseEvent mouseEvent) {
    }

    public void add01(MouseEvent mouseEvent) {
    }

    public void add02(MouseEvent mouseEvent) {
    }

    public void showLeaderMenu(MouseEvent mouseEvent) {
        leaderMenu = new Stage();
        Pane root = new Pane();
        Scene scene = new Scene(root);
        // Remove window decoration
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
        Rectangle rectangle = new Rectangle(400, 300, 200, 320);
        rectangle.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/lg/monsters_eredin_silver.jpg")))));
        Button confirm = new Button("confirm");
        confirm.setOnMouseClicked(event -> {
            LeaderImage.setFill(rectangle.getFill());
            leaderMenu.close();
            leaderIndex = 0;
        });
        Label label = new Label("leader description here");

        Button toRight = new Button("to right");
        Button toLeft = new Button("to left");
        toRight.setOnMouseClicked(event -> {
            leaderIndex++;
            handleLeaderMenu(rectangle, label);
        });
        toLeft.setOnMouseClicked(event -> {
            leaderIndex--;
            handleLeaderMenu(rectangle, label);
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
        Rectangle rectangle = new Rectangle(400, 300, 200, 320);
        rectangle.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource("/Images/lg/faction_nilfgaard.jpg")))));
        Button confirm = new Button("confirm");
        confirm.setOnMouseClicked(event -> {
            factionMenu.close();
            factionIndex = 0;
        });
        Label label = new Label("faction description here");//todo why is it here?

        Button toRight = new Button("to right");
        Button toLeft = new Button("to left");
        toRight.setOnMouseClicked(event -> {
            factionIndex++;
            handleFactionMenu(rectangle, label);
        });
        toLeft.setOnMouseClicked(event -> {
            factionIndex--;
            handleFactionMenu(rectangle, label);
        });
        HBox hBox = new HBox(10, toLeft, toRight);
        hBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(rectangle, hBox, confirm, label);
        root.getChildren().add(vBox);
        factionMenu.setScene(scene);
        factionMenu.showAndWait();
    }

    private void handleFactionMenu(Rectangle rectangle, Label label) {
        factionIndex %= 5; //todo set to 5 when all factions are added
        Faction faction = Faction.getFactions().get(factionIndex);
        label.setText(faction.getDescription());
        rectangle.setFill(new ImagePattern(new Image(
                String.valueOf(PreGameMenu.class.getResource(faction.getLgPath()).toExternalForm()))));
    }

}
