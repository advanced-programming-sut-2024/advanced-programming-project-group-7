package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;
import model.cards.Horn;


import java.util.ArrayList;

public class VetoMenu extends Application {
    private Pane pane;
    private Deck deck;
    private static final double HEIGHT = 900;
    private static final double WIDTH = 1600;
    private int totalClick=0;
    private Stage vetoMenu;
    private GridPane gridpane = new GridPane();
    private int count =0;
    private Stage stage;
    private ArrayList<Card> update = new ArrayList<>();
    private Card substitue1 = new Horn("horn", 3 , true, 0, "special",123,false);
    private Card substitue2 = new Horn("horn", 3 , true, 0, "special",123,false);
    private boolean First = true;

    @Override
    public void start(Stage stage) throws Exception {
        pane = new Pane();
        setSize(pane);
        pane.setBackground(new Background(createBackgroundImage()));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        deck = Deck.currentDeck;
        update.addAll(deck.hand);//todo update is new hand

        for(Card card: deck.hand){
            System.out.println(card+" : "+card.getCardName());
        }
        System.out.println("---------");
        for(Card card: deck.reservedCards){
            System.out.println(card+" : "+card.getCardName());
        }
//        substitue1 = deck.reservedCards.get(0);
//        substitue2 = deck.reservedCards.get(1);
        stage.centerOnScreen();
        stage.show();
        stage.setFullScreen(true);
        showVetoMenu();
//        System.out.println(substitue1+"\n"+substitue2);
        this.stage = stage;
    }
    public void showVetoMenu() {
        vetoMenu = new Stage();
        Pane root = new Pane();
        gridpane = new GridPane();
        Scene scene = new Scene(root);
        vetoMenu.initStyle(StageStyle.UNDECORATED);
        vetoMenu.initStyle(StageStyle.TRANSPARENT);
        root.setBackground(Background.EMPTY);
        scene.setFill(Color.rgb(1,1,1, 0.9));
        root.setMinHeight(800);
        root.setMinWidth(1200);
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setLayoutX(300);
        vBox.setLayoutY(10);
        vBox.setMaxWidth(200);
        Button confirm = new Button("confirm");
        confirm.setOnMouseClicked(event -> {
            stage.close();
            vetoMenu.close();
            GameLauncher gameLauncher = new GameLauncher();
            try {
                gameLauncher.start(LoginMenu.stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        setCards();
        root.getChildren().add(gridpane);
        root.getChildren().add(confirm);
        confirm.setLayoutX(550);
        confirm.setLayoutY(750);
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setLayoutX(250);
        gridpane.setLayoutY(100);
        vetoMenu.setScene(scene);
        vetoMenu.show();
    }

    private void setCards() {
        gridpane.getChildren().clear();
        for (Card card : deck.hand) {
            Pane pane = new Pane();
            Rectangle rectangle = new Rectangle();
            rectangle.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource(card.getLgPath()).toExternalForm()))));
            rectangle.setHeight(300);
            rectangle.setWidth(150);
            rectangle.setArcHeight(20);
            rectangle.setArcWidth(20);
            pane.getChildren().add(rectangle);
            pane.setOnMouseClicked(event -> {
                update.remove(card); // Use iterator to remove the card
                if (First) {
                    update.add(substitue2);
                    First = false;
                }
                else {
                    update.add(substitue1);
                }
                deck.hand.clear();
                deck.hand.addAll(update);
                setCards();
            });
            gridpane.add(pane, count % 5, count / 5);
            count++;
        }
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

    private void setSize(Pane pane) {
        pane.setMinHeight(HEIGHT);
        pane.setMaxHeight(HEIGHT);
        pane.setMinWidth(WIDTH);
        pane.setMaxWidth(WIDTH);
    }


    public int getTotalClick() {
        return totalClick;
    }

    public void setTotalClick(int totalClick) {
        this.totalClick = totalClick;
    }
}