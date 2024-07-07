package model.cards;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Card;

public class Decoy extends Card {

    public Decoy(String cardName, int countOfCard, boolean isSpecial, int power, String factionName, int rows, boolean isHero) {
        super(cardName, countOfCard, isSpecial, power, factionName, rows, isHero);
    }
}