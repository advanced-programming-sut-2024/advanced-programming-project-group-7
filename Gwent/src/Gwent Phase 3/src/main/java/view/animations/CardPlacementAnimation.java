package view.animations;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.util.Duration;
import model.Card;
import model.Game;
import model.cards.*;

import java.util.Iterator;

public class CardPlacementAnimation extends Transition {

    private final int duration = 100;
    private final Card card;
    private final double vy;
    private final double vx;
    private final double endX;
    private final double endY;
    private final Pane pane;
    private final Game game;

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
            this.stop();
            pane.getChildren().remove(card);
                try {
                    game.placeCard(card, game.selectedBox);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            if (game.selected instanceof Decoy) {
                card.setOnMouseClicked(event -> {
                    game.playerHand.getChildren().add(card);
                    if (card.getRows().contains(3)) {
                        game.placeCard(game.selected, game.playerThirdRow);
                    } else if (card.getRows().contains(2)) {
                        game.placeCard(game.selected, game.playerSecondRow);
                    } else if (card.getRows().contains(1)) {
                        game.placeCard(game.selected, game.playerFirstRow);
                    }
                    game.selected = null;
                });
            }
            else if (card instanceof Muster) {
                Iterator<Node> iterator = game.playerHand.getChildren().iterator();
                while (iterator.hasNext()) {
                    Node card1 = iterator.next();
                    if (card1 instanceof Muster || card.getCardName().equals(((Card) card1).getCardName())) {
                        iterator.remove();
                        game.placeCard((Card) card1, game.selectedBox);
                    }
                }if (game.remainingDeck != null) {
                    Iterator<Node> iterator1 = game.remainingDeck.getChildren().iterator();
                    while (iterator.hasNext()) {
                        Node card1 = iterator.next();
                        if (card1 instanceof Muster || card.getCardName().equals(((Card) card1).getCardName())) {
                            iterator1.remove();
                            game.placeCard((Card) card1, game.selectedBox);
                        }
                    }
                }
            } else if (card instanceof Mardroeme) {
                for (Node card1 : game.selectedBox.getChildren()) {
                    if ( card1 instanceof Berserker) {
                        //todo change to bear
                    }
                }
            } else if (card instanceof Medic) {
                if (!game.graveyard.isEmpty())
                    game.showMedicMenu(game);
            } else if (card instanceof Scorch) {
                if (card.getCardName().equals("scorch"))
                    game.scorch(4);
                else if (card.getCardName().equals("villen")) {
                    game.scorch(3);
                }
                else if (card.getCardName().equals("toad")) {
                    game.scorch(2);
                }
                else if (card.getCardName().equals("shirru")) {
                    game.scorch(1);
                } else {
                    game.scorch(2);
                }
                game.selectedBox.getChildren().remove(card); //todo prob scorch bug
            } else if (card instanceof Spy) {
                game.spyACard();
            } else if (card instanceof TightBond) {
                game.handleBond(game.selectedBox, card, game);
            } else if (card.getRows().contains(7)) {
                Iterator<Node> iterator = game.selectedBox.getChildren().iterator();
                while (iterator.hasNext()) {
                    Node card2 = iterator.next();
                    if ((!card2.equals(card) && ((Card) card2).getCardName().equals(card.getCardName()))
                            || card.getCardName().equals("clear")) {
                        iterator.remove(); // Safely remove the element
                        pane.getChildren().add(card2);
                        card2.setLayoutY(385);
                        card2.setLayoutX(125);
                        HelicopterAnimation helicopterAnimation = new HelicopterAnimation(pane, game, (Card) card2);
                        helicopterAnimation.play();
                    }
                }
                game.calculateLabels();
            }
        }
        card.setLayoutX(x);
        card.setLayoutY(y);
    }
}