package model;

import java.util.HashMap;
import java.util.HashSet;

public class Card {
    private String cardName;
    private int countOfCard;
    private boolean isSpecial;

    private int power;
    public static Card getCardByName(String cardName){}

    public Card(String cardName, int countOfCard, boolean isSpecial, int power) {
        this.cardName = cardName;
        this.countOfCard = countOfCard;
        this.isSpecial = isSpecial;
        this.power = power;
    }

    public String getCardName() {
        return cardName;
    }

    public int getCountOfCard() {
        return countOfCard;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

}
