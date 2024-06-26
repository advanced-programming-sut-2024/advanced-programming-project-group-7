package model;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Deck {
    private Faction deckFaction;
    private Leader deckLeader;
    private LinkedHashMap<Card, Integer> cardsInDeck=new LinkedHashMap<>();

    public Deck(Faction deckFaction, Leader deckLeader) {
        this.deckFaction = deckFaction;
        this.deckLeader = deckLeader;
    }
    public LinkedHashMap<Card, Integer> getCardsInDeck() {
        return cardsInDeck;
    }

    public void setCardsInDeck(LinkedHashMap<Card, Integer> cardsInDeck) {
        this.cardsInDeck = cardsInDeck;
    }
    public void addToDeck(Card card){
        if(cardsInDeck.containsKey(card))
            cardsInDeck.put(card,cardsInDeck.get(card)+1);
        else
            cardsInDeck.put(card,1);
    }
    public void deleteCardFromDeck(Card card){
        if(cardsInDeck.get(card)==0)
            cardsInDeck.remove(card);
        else
            cardsInDeck.put(card,cardsInDeck.get(card)-1);
    }
}
