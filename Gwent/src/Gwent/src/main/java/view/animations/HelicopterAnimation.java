package view.animations;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Card;
import model.Game;

public class HelicopterAnimation extends Transition {

    public Pane pane;
    public Game game;
    public Card card;
    private double speed = 8;
    public int duration = 100;

    public HelicopterAnimation(Pane pane, Game game, Card card) {
        this.pane = pane;
        this.game = game;
        this.card = card;
        this.setCycleDuration(Duration.millis(duration));
        this.setCycleCount(400);
    }

    @Override
    protected void interpolate(double frac) {
        double y = card.getLayoutY() + speed;
        double R = card.getRotate() + frac * 10;
        card.setRotate(R);
        card.setLayoutY(y);
    }
}
