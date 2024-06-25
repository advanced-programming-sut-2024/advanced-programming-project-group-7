package model;

import java.util.HashMap;

public class Deck {
    private Faction deckFaction;
    private Leader deckLeader;
    private HashMap<Card, Integer> cardsInDeck=new HashMap<>();

    public Deck(Faction deckFaction, Leader deckLeader) {
        this.deckFaction = deckFaction;
        this.deckLeader = deckLeader;
    }
    public HashMap<Card, Integer> getCardsInDeck() {
        return cardsInDeck;
    }
    public void setCardsInDeck(HashMap<Card, Integer> cardsInDeck) {
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
