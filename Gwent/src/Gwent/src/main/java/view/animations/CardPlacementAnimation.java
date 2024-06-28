package view.animations;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Card;
import model.Game;
import model.cards.*;
import view.GameLauncher;
import view.PreGameMenu;

import java.util.Iterator;

public class CardPlacementAnimation extends Transition {

    private final int duration = 100;
    private Card card;
    private double vy;
    private double vx;
    private double endX;
    private double endY;
    private Pane pane;
    private HBox playerRow;
    private Game game;

    public CardPlacementAnimation(Pane pane, Game game, Card card, double vx, double vy, double endY, double endX) {
        this.card = card;
        this.vx = vx;
        this.vy = vy;
        this.game = game;
        this.pane = pane;
        this.endX = endX;
        this.endY = endY;
        this.setCycleDuration(Duration.millis(duration));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double frac) {

        double y = card.getLayoutY() + vy;
        double x = card.getLayoutX() + vx;
        if (Math.abs(x - endX) <= 10 && Math.abs(y - endY) <= 10) {
            pane.getChildren().remove(card);
            if (!card.getCardName().equals("clear")) {
                StringBuilder st = new StringBuilder();
//                String cardName, int countOfCard, boolean isSpecial, int power, String factionName,int rows,boolean isHero
                st.append("card:").append(card.getCardName()).append(".").append(String.valueOf(card.getCountOfCard())).append(".").append(String.valueOf(card.isSpecial()))
                                .append(".").append(String.valueOf(card.getPower())).append(".").append(card.getFactionName()).append(".").append(String.valueOf(card.rows))
                        .append(".").append(String.valueOf(card.isHero()));
                st.append(".").append(String.valueOf(GameLauncher.enemyPosition(card.rows)));
                System.out.println(st.toString());

                game.client.sendMessage(st.toString());
                game.selectedBox.getChildren().add(card);
            }
            card.setOnMouseClicked(event -> {
                if (game.selected instanceof Decoy) {
                    game.playerHand.getChildren().add(card);
                    if (card.getRows().contains(3)) {
                        game.playerThirdRow.getChildren().add(game.selected);
                        game.calculateLabels(game.playerThirdRow);
                    }
                    else if (card.getRows().contains(2)) {
                        game.playerSecondRow.getChildren().add(game.selected);
                        game.calculateLabels(game.playerSecondRow);
                    }
                    else if (card.getRows().contains(1)) {
                        game.playerFirstRow.getChildren().add(game.selected);
                        game.calculateLabels(game.playerFirstRow);
                    }
                    game.selected = null;
                }
            });
            game.calculateLabels(game.selectedBox);
            this.stop();
            if (card instanceof Muster) {
                Iterator<Node> iterator = game.playerHand.getChildren().iterator();
                while (iterator.hasNext()) {
                    Node card1 = iterator.next();
                    if (card1 instanceof Muster || card.getCardName().equals(((Card) card1).getCardName())) {
                        iterator.remove();
                        game.selectedBox.getChildren().add(card1);
                    }
                }
                if (game.remainingDeck != null) {
                    Iterator<Node> iterator1 = game.remainingDeck.getChildren().iterator();
                    while (iterator.hasNext()) {
                        Node card1 = iterator.next();
                        if (card1 instanceof Muster || card.getCardName().equals(((Card) card1).getCardName())) {
                            iterator1.remove();
                            game.selectedBox.getChildren().add(card1);
                        }
                    }
                }
                game.calculateLabels(game.selectedBox);
            } else if (card instanceof Mardroeme) {
                for (Node card1 : game.selectedBox.getChildren()) {
                    if ( card1 instanceof Berserker) {
                        //todo change to bear
                    }
                }
            } else if (card instanceof Medic) {
                if (!game.graveYard.getChildren().isEmpty())
                    showMedicMenu(game);
            } else if (card instanceof Scorch) {
                game.removeDominantCard();
                game.selectedBox.getChildren().remove(card);
            } else if (card instanceof MoralBoost) {
                game.boostRow(game.selectedBox,game);
            } else if (card instanceof Spy) {
                game.spyACard();
            } else if (card instanceof TightBond) {
                game.handleBond(game.selectedBox, card, game);
            } else if (card.getRows().contains(7)) {
                for (Node card2 : game.selectedBox.getChildren()) {
                    if (!card2.equals(card) && ((Card) card2).getCardName().equals(card.getCardName()) || card.getCardName().equals("clear")){
                        game.selectedBox.getChildren().remove(card2);
                        pane.getChildren().add(card2);
                        card2.setLayoutY(385);
                        card2.setLayoutX(125);
                        HelicopterAnimation helicopterAnimation = new HelicopterAnimation(pane, game, (Card) card2);
                        helicopterAnimation.play();
                    }
                }
            }
        }
        card.setLayoutX(x);
        card.setLayoutY(y);
    }

    private void showMedicMenu(Game game) {
        Stage medicMenu = new Stage();
        Pane root = new Pane();
        Scene scene = new Scene(root);
        // Remove window decoration
        medicMenu.initStyle(StageStyle.UNDECORATED);
        medicMenu.initStyle(StageStyle.TRANSPARENT);
        root.setBackground(Background.EMPTY);
        scene.setFill(Color.rgb(1,1,1, 0.7));
        root.setMinHeight(500);
        root.setMinWidth(800);
        GridPane gridPane = new GridPane();
        int count = 0;
        for (Node deadCard : game.graveYard.getChildren()) {
            Pane pane = new Pane();
            Rectangle rectangle = new Rectangle();
            rectangle.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource(card.getLgPath()).toExternalForm()))));
            rectangle.setHeight(300);
            rectangle.setWidth(150);
            rectangle.setArcWidth(20);
            rectangle.setArcHeight(20);
            pane.getChildren().addAll(rectangle);
            pane.setOnMouseClicked(event -> {
                if (((Card)deadCard).getRows().contains(1)) {
                    game.playerFirstRow.getChildren().add(deadCard);
                    medicMenu.close();
                }
            });
            gridPane.add(pane, count % 4, count / 4);
            count++;
        }
        root.getChildren().add(gridPane);
        medicMenu.setScene(scene);
        medicMenu.showAndWait();
    }
}