package view.animations;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Card;
import model.Game;

public class AnnihilationAnimation extends Transition {
    private final Card card;
    private final HBox row;
    private final Game game;

    public AnnihilationAnimation(HBox location, Card card, Game game) {
        this.card = card;
        this.game = game;
        this.row = location;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(1000));
    }

    @Override
    protected void interpolate(double v) {

        card.rectangle.setFill(new ImagePattern(new Image(AnnihilationAnimation
                .class.getResource("/Images/icons/anim_scorch.png").toExternalForm())));
        card.rectangle.toFront();
        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                row.getChildren().remove(card);
                card.rectangle.setFill(new ImagePattern(new Image(AnnihilationAnimation
                        .class.getResource(card.smPathCreator(card.getCardName(), card.getFactionName())).toExternalForm())));
                game.graveyard.getChildren().add(card);
                card.rectangle.toBack();
            }
        });
    }
}
