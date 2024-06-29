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
import model.Card;
import model.Deck;
import model.Game;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class VetoCard  extends Application {
    private Pane pane;
    public Deck deck;
    private static final double HEIGHT = 900;
    private static final double WIDTH = 1600;
    private int totalClick=0;

    private Stage vetoMenu;
    private GridPane gridpane = new GridPane();
    private int count =0;
    private Stage stage;
    private ArrayList<Card> update = new ArrayList<>();


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
            vetoMenu.close();
            stage.close();
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
        vetoMenu.showAndWait();
    }

    private void setCards() {
        gridpane.getChildren().clear();
         deck.hand = update;
        int bound = deck.reservedCards.size() - 1;
        for (Iterator<Card> iterator = deck.hand.iterator(); iterator.hasNext(); ) {
            Card card = iterator.next();
            Pane pane = new Pane();
            Rectangle rectangle = new Rectangle();
            rectangle.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource(card.getLgPath()).toExternalForm()))));
            rectangle.setHeight(300);
            rectangle.setWidth(150);
            rectangle.setArcHeight(20);
            rectangle.setArcWidth(20);
            pane.getChildren().add(rectangle);
            pane.setOnMouseClicked(event -> {
                update.remove(card);// Use iterator to remove the card
                Random random = new Random();
                Card card1 = deck.reservedCards.get(random.nextInt(0, bound));
                update.add(card1);
                deck.reservedCards.add(card);
                setCards();
                for(Card tmpCard:deck.hand){
                    for(Card tmpcard1: deck.hand)
                        if(tmpCard==tmpcard1) System.out.println(tmpCard+":"+tmpCard.getCardName()+"\n"+tmpcard1+":"+tmpcard1.getCardName());
                }
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

    @Override
    public void start(Stage stage) throws Exception {
        pane = new Pane();
        this.stage = new Stage();
        setSize(pane);
        pane.setBackground(new Background(createBackgroundImage()));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        deck = Deck.currentDeck;
        deck.shuffleDeck();
        update = deck.hand;
        stage.centerOnScreen();
        stage.show();
        stage.setFullScreen(true);
        showVetoMenu();
    }
}
