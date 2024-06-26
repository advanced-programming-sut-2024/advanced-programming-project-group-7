package model;

import java.util.*;

public class Deck {
    private Faction deckFaction;
    private Leader deckLeader;
    private LinkedHashMap<Card, Integer> cardsInDeck=new LinkedHashMap<>();
    public int totalCardsInDeck;
    public int totalSpecialCardInDeck;
    public int totalHeroCard;
    public int totalUnitCard;
    public int totalUnitCardStrength;


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
        if(cardsInDeck.get(card)==1)
            cardsInDeck.remove(card);
        else
            cardsInDeck.put(card,cardsInDeck.get(card)-1);
    }
    public ArrayList<Card> shuffleDeck(){
        ArrayList<Card> shuffledDeck=new ArrayList<>();
        for (Card card:cardsInDeck.keySet()){
            for(int i=0;i<cardsInDeck.get(card);i++)
                shuffledDeck.add(card);
        }
        Collections.shuffle(shuffledDeck);
        return shuffledDeck;
    }
    public void calculateDeck(){
         int totalCardsInDeck=0;
         int totalSpecialCardInDeck=0;
         int totalHeroCard=0;
         int totalUnitCard=0;
         int totalUnitCardStrength=0;
         for(Card card: cardsInDeck.keySet()){
             totalCardsInDeck+=cardsInDeck.get(card);
             totalUnitCardStrength+=card.getPower();
             if(card.isSpecial())totalSpecialCardInDeck+=cardsInDeck.get(card);
             if(card.isHero())totalHeroCard+=cardsInDeck.get(card);
             if(!card.isSpecial())totalUnitCard+=cardsInDeck.get(card);
         }
         this.totalCardsInDeck=totalCardsInDeck;
         this.totalHeroCard=totalHeroCard;
         this.totalUnitCard=totalUnitCard;
         this.totalSpecialCardInDeck=totalSpecialCardInDeck;
         this.totalUnitCardStrength=totalUnitCardStrength;
    }
}
