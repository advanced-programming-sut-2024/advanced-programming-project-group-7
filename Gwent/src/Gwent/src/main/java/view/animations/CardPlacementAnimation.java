package view.animations;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Card;
import model.Game;
import model.cards.Muster;

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
            game.playerHand.getChildren().remove(card);
            game.selectedBox.getChildren().add(card);
            this.stop();
            if (card instanceof Muster) {
                Iterator<Node> iterator = game.playerHand.getChildren().iterator();
                while (iterator.hasNext()) {
                    Node card1 = iterator.next();
                    if (card1 instanceof Muster || card.getCardName().equals(((Card) card1).getCardName())) {
                        iterator.remove();
                        game.selectedBox.getChildren().add(card1); //tod
                    }
                }
            }
        }
        card.setLayoutX(x);
        card.setLayoutY(y);
    }
}