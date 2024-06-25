package view.animations;

import javafx.animation.Transition;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Card;
import model.Game;
import view.GameLauncher;

import java.security.cert.PolicyNode;

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

    public CardPlacementAnimation(Pane pane, Game game, Card card, HBox row, double vx, double vy, HBox playerRow, double endY, double endX) {
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
        if (Math.abs(x - endX) <= 10  && Math.abs(y - endY) <= 10){
            pane.getChildren().remove(card);
            game.selectedBox.getChildren().add(card);
            this.stop();
        }
        card.setLayoutX(x);
        card.setLayoutY(y);
    }
}
