package model;

import model.cards.*;

import java.util.*;

public class Deck {
    public static Deck currentDeck;
    private Faction deckFaction;
    private Leader deckLeader;
    private LinkedHashMap<Card, Integer> cardsInDeck=new LinkedHashMap<>();
    public int totalCardsInDeck;
    public int totalSpecialCardInDeck;
    public int totalHeroCard;
    public int totalUnitCard;
    public int totalUnitCardStrength;
    public ArrayList<Card> hand=new ArrayList<>();
    public  ArrayList<Card> deckAsArrayList=new ArrayList<>();
    public List<Card> reservedCards=new ArrayList<>();

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
    public void shuffleDeck(){
        ArrayList<Card> shuffledDeck=new ArrayList<>();
        for (Card card:cardsInDeck.keySet()){
            for(int i=0;i<cardsInDeck.get(card);i++) {
                if (card.getClass().getSimpleName().equals("Agile")) shuffledDeck.add(new Agile(card.getCardName(), card.getCountOfCard(), card.isSpecial(), card.getPower(), card.getFactionName(), card.rows, card.isHero()));
                else if (card.getClass().getSimpleName().equals("Berserker")) shuffledDeck.add(new Berserker(card.getCardName(), card.getCountOfCard(), card.isSpecial(), card.getPower(), card.getFactionName(), card.rows, card.isHero()));
                else if (card.getClass().getSimpleName().equals("Cow")) shuffledDeck.add(new Cow(card.getCardName(), card.getCountOfCard(), card.isSpecial(), card.getPower(), card.getFactionName(), card.rows, card.isHero()));
                else if (card.getClass().getSimpleName().equals("Decoy")) shuffledDeck.add(new Decoy(card.getCardName(), card.getCountOfCard(), card.isSpecial(), card.getPower(), card.getFactionName(), card.rows, card.isHero()));
                else if (card.getClass().getSimpleName().equals("Horn")) shuffledDeck.add(new Horn(card.getCardName(), card.getCountOfCard(), card.isSpecial(), card.getPower(), card.getFactionName(), card.rows, card.isHero()));
                else if (card.getClass().getSimpleName().equals("Mardroeme")) shuffledDeck.add(new Mardroeme(card.getCardName(), card.getCountOfCard(), card.isSpecial(), card.getPower(), card.getFactionName(), card.rows, card.isHero()));
                else if (card.getClass().getSimpleName().equals("Medic")) shuffledDeck.add(new Medic(card.getCardName(), card.getCountOfCard(), card.isSpecial(), card.getPower(), card.getFactionName(), card.rows, card.isHero()));
                else if (card.getClass().getSimpleName().equals("MoralBoost")) shuffledDeck.add(new MoralBoost(card.getCardName(), card.getCountOfCard(), card.isSpecial(), card.getPower(), card.getFactionName(), card.rows, card.isHero()));
                else if (card.getClass().getSimpleName().equals("Muster")) shuffledDeck.add(new Muster(card.getCardName(), card.getCountOfCard(), card.isSpecial(), card.getPower(), card.getFactionName(), card.rows, card.isHero()));
                else if (card.getClass().getSimpleName().equals("Scorch")) shuffledDeck.add(new Scorch(card.getCardName(), card.getCountOfCard(), card.isSpecial(), card.getPower(), card.getFactionName(), card.rows, card.isHero()));
                else if (card.getClass().getSimpleName().equals("Spy")) shuffledDeck.add(new Spy(card.getCardName(), card.getCountOfCard(), card.isSpecial(), card.getPower(), card.getFactionName(), card.rows, card.isHero()));
                else if (card.getClass().getSimpleName().equals("TightBond")) shuffledDeck.add(new TightBond(card.getCardName(), card.getCountOfCard(), card.isSpecial(), card.getPower(), card.getFactionName(), card.rows, card.isHero()));
                else if (card.getClass().getSimpleName().equals("Card")) shuffledDeck.add(new Card(card.getCardName(), card.getCountOfCard(), card.isSpecial(), card.getPower(), card.getFactionName(), card.rows, card.isHero()));
                else System.out.println("khak bar saret");
            }


        }
        Collections.shuffle(shuffledDeck);
        deckAsArrayList=shuffledDeck;
        hand.addAll(shuffledDeck.subList(0,10));
        reservedCards.addAll(shuffledDeck.subList(10, shuffledDeck.size()));
        for(Card card:hand) System.out.println(card+card.getCardName());
        System.out.println("---------------------");
        for(Card card:reservedCards) System.out.println(card+card.getCardName());
        System.out.println("0000000000000000000000");
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
