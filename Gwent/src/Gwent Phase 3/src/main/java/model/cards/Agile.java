package model.cards;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Card;

public class Agile extends Card {
    public Agile(String cardName, int countOfCard, boolean isSpecial, int power, String factionName, int row,boolean isHero) {
        super(cardName, countOfCard, isSpecial, power, factionName,row,isHero);
        Circle circle = new Circle(12.5);
        circle.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource("/Images/icons/card_ability_agile.png").toExternalForm()))));
        circle.setCenterX(35);
        circle.setCenterY(85);
        this.getChildren().add(circle);
    }
}
