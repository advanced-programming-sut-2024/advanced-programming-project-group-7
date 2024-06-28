package model.factions;

import model.*;
import model.cards.*;
import model.leaders.SkelligeLeaders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Skellige extends Faction {
    public Skellige(String descriptions) {
        super(descriptions);
        this.factionName="skellige";
    }
    private final static ArrayList<Card> skelligeCards = new ArrayList<>();
    static {
        skelligeCards.add(new Horn("horn", 3, true, 0, "special",123,false));
        skelligeCards.add(new Card("decoy", 3, true, 0, "special",123,false));
        skelligeCards.add(new Mardroeme("mardroeme", 3, true, 0, "special",123,false));
        skelligeCards.add(new Scorch("scorch", 3, true, 0, "special",123456,false));
        skelligeCards.add(new Card("frost", 3, true, 0, "weather",7,false));
        skelligeCards.add(new Card("clear", 2, true, 0, "weather",7,false));
        skelligeCards.add(new Card("fog", 3, true, 0, "weather",7,false));
        skelligeCards.add(new Card("storm", 3, true, 0, "weather",7,false));
        skelligeCards.add(new Card("rain", 2, true, 0, "weather",7,false));
        skelligeCards.add(new Card("ciri", 1, false, 15, "neutral",3,true));
        skelligeCards.add(new Card("geralt", 1, false, 15, "neutral",3,true));
        skelligeCards.add(new MoralBoost("olaf", 1, false, 12, "skellige",23,false));
        skelligeCards.add(new Muster("cerys", 1, false, 10, "skellige",3,true));
        skelligeCards.add(new Card("hjalmar", 1, false, 10, "skellige",2,true));
        skelligeCards.add(new Mardroeme("ermion", 1, false, 8, "skellige",2,true));
        skelligeCards.add(new Card("triss", 1, false, 7, "neutral",3,true));
        skelligeCards.add(new Scorch("villen", 1, false, 7, "neutral",3,false));
        skelligeCards.add(new Medic("yennefer", 1, false, 7, "neutral",2,true));
        skelligeCards.add(new Card("blueboy", 1, false, 6, "skellige",3,false));
        skelligeCards.add(new TightBond("craite warrior", 3, false, 6, "skellige",3,false));
        skelligeCards.add(new Card("brokva archer", 2, false, 6, "skellige",2,false));
        skelligeCards.add(new Scorch("dimun pirate", 1, false, 6, "skellige",2,false));
        skelligeCards.add(new Card("madmad lugos", 1, false, 6, "skellige",3,false));
        skelligeCards.add(new MoralBoost("olgierd", 1, false, 6, "neutral",23,false));
        skelligeCards.add(new Card("vesemir", 1, false, 6, "neutral",3,false));
        skelligeCards.add(new TightBond("war longship", 2, false, 6, "skellige",2,false));
        skelligeCards.add(new Card("emiel", 1, false, 5, "neutral",3,false));
        skelligeCards.add(new Card("zoltan", 1, false, 5, "neutral",3,false));
        skelligeCards.add(new Berserker("berserker", 1, false, 4, "skellige",3,false));
        skelligeCards.add(new TightBond("shield maiden", 1, false, 4, "skellige",3,false));
        skelligeCards.add(new TightBond("shield maiden 1", 1, false, 4, "skellige",3,false));
        skelligeCards.add(new TightBond("shield maiden 2", 1, false, 4, "skellige",3,false));
        skelligeCards.add(new Card("heymaey", 1, false, 4, "skellige",3,false));
        skelligeCards.add(new Card("tordarroch", 1, false, 4, "skellige",3,false));
        skelligeCards.add(new Card("donar", 1, false, 4, "skellige",3,false));
        skelligeCards.add(new Muster("gaunter odimm darkness", 3, false, 4, "neutral",2,false));
        skelligeCards.add(new Card("holger", 1, false, 4, "skellige",1,false));
        skelligeCards.add(new Muster("light longship", 3, false, 4, "skellige",2,false));
        skelligeCards.add(new Card("svanrige", 1, false, 4, "skellige",3,false));
        skelligeCards.add(new Card("udalryk", 1, false, 4, "skellige",3,false));
        skelligeCards.add(new Medic("birna", 1, false, 2, "skellige",3,false));
        skelligeCards.add(new Horn("dandelion", 1, false, 2, "neutral",3,false));
        skelligeCards.add(new Horn("draig", 1, false, 2, "skellige",1,false));
        skelligeCards.add(new Muster("gaunter odimm", 1, false, 2, "neutral",1,false));
        skelligeCards.add(new Berserker("young berserker", 3, false, 2, "skellige",2,false));
        skelligeCards.add(new Cow("cow", 1, false, 0, "neutral",2,false));
        skelligeCards.add(new Cow("kambi", 1, false, 0, "skellige",3,false));
        skelligeCards.add(new Spy("mysterious elf", 1, false, 0, "neutral",4,true));
    }
    private final static LinkedHashMap<Card,Integer> skelligeDefaultDeck=new LinkedHashMap<>();
    static {
        skelligeDefaultDeck.put(skelligeCards.get(0),1);
        skelligeDefaultDeck.put(skelligeCards.get(1),2);
        skelligeDefaultDeck.put(skelligeCards.get(2),2);
        skelligeDefaultDeck.put(skelligeCards.get(3),1);
        skelligeDefaultDeck.put(skelligeCards.get(4),2);
        skelligeDefaultDeck.put(skelligeCards.get(8),1);
        skelligeDefaultDeck.put(skelligeCards.get(9),1);
        skelligeDefaultDeck.put(skelligeCards.get(10),1);
        skelligeDefaultDeck.put(skelligeCards.get(15),1);
        skelligeDefaultDeck.put(skelligeCards.get(16),1);
        skelligeDefaultDeck.put(skelligeCards.get(17),1);
        skelligeDefaultDeck.put(skelligeCards.get(24),1);
        skelligeDefaultDeck.put(skelligeCards.get(25),1);
        skelligeDefaultDeck.put(skelligeCards.get(26),1);
        skelligeDefaultDeck.put(skelligeCards.get(37),3);
        skelligeDefaultDeck.put(skelligeCards.get(38),1);
        skelligeDefaultDeck.put(skelligeCards.get(42),1);
        skelligeDefaultDeck.put(skelligeCards.get(44),1);
        skelligeDefaultDeck.put(skelligeCards.get(47),1);
    }
    private final static  ArrayList<Leader> skelligeLeaders=new ArrayList<>();
    static {
        skelligeLeaders.add(new SkelligeLeaders("crach an craite","shuffle all cards from each player's graveyard back into their decks","skellige"));
        skelligeLeaders.add(new SkelligeLeaders("king bran","units only lose half their strength in bad weather conditions","skellige"));
    }
    public ArrayList<Leader> getLeaders() {
        return skelligeLeaders;
    }
    public static ArrayList<Card> getSkelligeCards() {
        return skelligeCards;
    }
    public static LinkedHashMap<Card,Integer> getSkelligeDefaultDeck(){
        return skelligeDefaultDeck;
    }
}
