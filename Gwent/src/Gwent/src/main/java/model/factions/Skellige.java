package model.factions;

import model.*;
import model.cards.*;
import model.leaders.SkelligeLeaders;

import java.util.ArrayList;
import java.util.HashMap;

public class Skellige extends Faction {
    public Skellige(String descriptions) {
        super(descriptions);
        this.factionName="skellige";
    }
    private final static ArrayList<Card> skelligeCards = new ArrayList<>();
    static {
        skelligeCards.add(new Decoy("horn", 3, true, 0, "special",123,false));
        skelligeCards.add(new Horn("decoy", 3, true, 0, "special",123,false));
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
        skelligeCards.add(new Spy("mysterious elf", 1, false, 0, "neutral",3,true));
    }
//    private final static HashMap<Card,Integer> skelligeDefaultDeck=new HashMap<>();
//    static {
//        skelligeDefaultDeck.put(new Decoy("horn", 3, true, 0, "special"),1);
//        skelligeDefaultDeck.put(new Horn("decoy", 3, true, 0, "special"),3);
//        skelligeDefaultDeck.put(new Scorch("scorch", 3, true, 0, "special"),1);
//        skelligeDefaultDeck.put(new Card("frost", 3, true, 0, "weather"),1);
//        skelligeDefaultDeck.put(new Card("ciri", 1, false, 15, "neutral"),1);
//        skelligeDefaultDeck.put(new Card("geralt", 1, false, 15, "neutral"),1);
//        skelligeDefaultDeck.put(new Card("triss", 1, false, 7, "neutral"),1);
//        skelligeDefaultDeck.put(new Scorch("villen", 1, false, 7, "neutral"),1);
//        skelligeDefaultDeck.put(new Medic("yennefer", 1, false, 7, "neutral"),1);
//        skelligeDefaultDeck.put(new Card("brokva archer", 2, false, 6, "skellige"),2);
//        skelligeDefaultDeck.put(new Scorch("dimun pirate", 1, false, 6, "skellige"),1);
//        skelligeDefaultDeck.put(new TightBond("war longship", 2, false, 6, "skellige"),2);
//        skelligeDefaultDeck.put(new TightBond("shield maiden", 1, false, 4, "skellige"),1);
//        skelligeDefaultDeck.put(new TightBond("shield maiden 1", 1, false, 4, "skellige"),1);
//        skelligeDefaultDeck.put(new Muster("gaunter odimm darkness", 3, false, 4, "neutral"),1);
//        skelligeDefaultDeck.put(new Medic("birna", 1, false, 2, "skellige"),1);
//        skelligeDefaultDeck.put(new Horn("dandelion", 1, false, 2, "neutral"),1);
//        skelligeDefaultDeck.put(new Muster("gaunter odimm", 1, false, 2, "neutral"),1);
//        skelligeDefaultDeck.put(new Spy("mysterious elf", 1, false, 0, "neutral"),1);
//    }
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
//    public static HashMap<Card,Integer> getSkelligeDefaultDeck(){
//        return skelligeDefaultDeck;
//    }
}
