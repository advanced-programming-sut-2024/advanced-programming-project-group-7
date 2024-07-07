package model.factions;

import model.*;
import model.cards.*;
import model.leaders.MonstersLeaders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Monsters extends Faction {
    public Monsters(String descriptions) {
        super(descriptions);
        this.factionName="monsters";
    }
    private final static ArrayList<Card> monsterCards = new ArrayList<>();
    static {
        monsterCards.add(new Horn("horn", 3, true, 0, "special",123,false));
        monsterCards.add(new Card("decoy", 3, true, 0, "special",123,false));
        monsterCards.add(new Mardroeme("mardroeme", 3, true, 0, "special",123,false));
        monsterCards.add(new Scorch("scorch", 3, true, 0, "special",123456,false));
        monsterCards.add(new Card("frost", 3, true, 0, "weather",7,false));
        monsterCards.add(new Card("clear", 2, true, 0, "weather",7,false));
        monsterCards.add(new Card("fog", 3, true, 0, "weather",7,false));
        monsterCards.add(new Card("storm", 3, true, 0, "weather",7,false));
        monsterCards.add(new Card("rain", 2, true, 0, "weather",7,false));
        monsterCards.add(new Card("ciri", 1, false, 15, "neutral",3,true));
        monsterCards.add(new Card("geralt", 1, false, 15, "neutral",3,true));
        monsterCards.add(new Card("draug", 1, false, 10, "monsters",3,true));
        monsterCards.add(new Card("imlerith", 1, false, 10, "monsters",3,true));
        monsterCards.add(new Card("leshen", 1, false, 10, "monsters",2,true));
        monsterCards.add(new Card("kayran", 1, false, 8, "monsters",23,true));
        monsterCards.add(new Scorch("toad", 1, false, 7, "monsters",2,false));
        monsterCards.add(new Card("triss", 1, false, 7, "neutral",3,true));
        monsterCards.add(new Scorch("villen", 1, false, 7, "neutral",3,false));
        monsterCards.add(new Medic("yennefer", 1, false, 7, "neutral",2,true));
        monsterCards.add(new Muster("arachas behemoth", 1, false, 6, "monsters",1,false));
        monsterCards.add(new Muster("witch velen", 1, false, 6, "monsters",3,false));
        monsterCards.add(new Muster("witch velen 1", 1, false, 6, "monsters",3,false));
        monsterCards.add(new Muster("witch velen 2", 1, false, 6, "monsters",3,false));
        monsterCards.add(new Card("earth elemental", 1, false, 6, "monsters",1,false));
        monsterCards.add(new Card("fiend", 1, false, 6, "monsters",3,false));
        monsterCards.add(new Card("fire elemental", 1, false, 6, "monsters",1,false));
        monsterCards.add(new MoralBoost("olgierd", 1, false, 6, "neutral",23,false));
        monsterCards.add(new Card("vesemir", 1, false, 6, "neutral",3,false));
        monsterCards.add(new Card("emiel", 1, false, 5, "neutral",3,false));
        monsterCards.add(new Card("forktail", 1, false, 5, "monsters",3,false));
        monsterCards.add(new Card("frightener", 1, false, 5, "monsters",3,false));
        monsterCards.add(new Card("gravehag", 1, false, 5, "monsters",2,false));
        monsterCards.add(new Card("gryffin", 1, false, 5, "monsters",3,false));
        monsterCards.add(new Card("frost giant", 1, false, 5, "monsters",1,false));
        monsterCards.add(new Card("mighty maiden", 1, false, 5, "monsters",3,false));
        monsterCards.add(new Muster("katakan", 1, false, 5, "monsters",3,false));
        monsterCards.add(new Card("werewolf", 1, false, 5, "monsters",3,false));
        monsterCards.add(new Card("zoltan", 1, false, 5, "neutral",3,false));
        monsterCards.add(new Muster("arachas", 1, false, 4, "monsters",3,false));
        monsterCards.add(new Muster("arachas 1", 1, false, 4, "monsters",3,false));
        monsterCards.add(new Muster("arachas 2", 1, false, 4, "monsters",3,false));
        monsterCards.add(new Card("poroniec", 1, false, 4, "monsters",3,false));
        monsterCards.add(new Muster("gaunter odimm darkness", 3, false, 4, "neutral",2,false));
        monsterCards.add(new Muster("bruxa", 1, false, 4, "monsters",3,false));
        monsterCards.add(new Muster("ekkima", 1, false, 4, "monsters",3,false));
        monsterCards.add(new Muster("fleder", 1, false, 4, "monsters",3,false));
        monsterCards.add(new Muster("garkain", 1, false, 4, "monsters",3,false));
        monsterCards.add(new Agile("celaeno harpy", 1, false, 2, "monsters",23,false));
        monsterCards.add(new Card("cockatrice", 1, false, 2, "monsters",1,false));
        monsterCards.add(new Horn("dandelion", 1, false, 2, "neutral",3,false));
        monsterCards.add(new Card("endrega", 1, false, 2, "monsters",2,false));
        monsterCards.add(new Card("fogling", 1, false, 2, "monsters",3,false));
        monsterCards.add(new Card("gargoyle", 1, false, 2, "monsters",2,false));
        monsterCards.add(new Muster("gaunter odimm", 1, false, 2, "neutral",1,false));
        monsterCards.add(new Agile("harpy", 1, false, 2, "monsters",23,false));
        monsterCards.add(new Muster("nekker", 1, false, 2, "monsters",3,false));
        monsterCards.add(new Muster("nekker 1", 1, false, 2, "monsters",3,false));
        monsterCards.add(new Muster("nekker 2", 1, false, 2, "monsters",3,false));
        monsterCards.add(new Card("wyvern", 1, false, 2, "monsters",2,false));
        monsterCards.add(new Muster("ghoul", 1, false, 1, "monsters",3,false));
        monsterCards.add(new Muster("ghoul 1", 1, false, 1, "monsters",3,false));
        monsterCards.add(new Muster("ghoul 2", 1, false, 1, "monsters",2,false));
        monsterCards.add(new Cow("cow", 1, false, 0, "neutral",2,false));
        monsterCards.add(new Spy("mysterious elf", 1, false, 0, "neutral",4,true));
    }
    private final static LinkedHashMap<Card,Integer> monsterDefaultDeck=new LinkedHashMap<>();
    static{
        monsterDefaultDeck.put(monsterCards.get(0),1);
        monsterDefaultDeck.put(monsterCards.get(1),3);
        monsterDefaultDeck.put(monsterCards.get(3),1);
        monsterDefaultDeck.put(monsterCards.get(4),2);
        monsterDefaultDeck.put(monsterCards.get(8),1);
        monsterDefaultDeck.put(monsterCards.get(9),1);
        monsterDefaultDeck.put(monsterCards.get(11),1);
        monsterDefaultDeck.put(monsterCards.get(14),1);
        monsterDefaultDeck.put(monsterCards.get(15),1);
        monsterDefaultDeck.put(monsterCards.get(17),1);
        monsterDefaultDeck.put(monsterCards.get(19),1);
        monsterDefaultDeck.put(monsterCards.get(20),1);
        monsterDefaultDeck.put(monsterCards.get(21),1);
        monsterDefaultDeck.put(monsterCards.get(23),1);
        monsterDefaultDeck.put(monsterCards.get(25),1);
        monsterDefaultDeck.put(monsterCards.get(30),1);
        monsterDefaultDeck.put(monsterCards.get(35),1);
        monsterDefaultDeck.put(monsterCards.get(37),1);
        monsterDefaultDeck.put(monsterCards.get(38),1);
        monsterDefaultDeck.put(monsterCards.get(42),3);
        monsterDefaultDeck.put(monsterCards.get(49),1);
        monsterDefaultDeck.put(monsterCards.get(53),1);
        monsterDefaultDeck.put(monsterCards.get(59),1);
        monsterDefaultDeck.put(monsterCards.get(60),1);
        monsterDefaultDeck.put(monsterCards.get(63),1);
    }
    private  final static  ArrayList<Leader> monsterLeaders=new ArrayList<>();
    static {
        monsterLeaders.add(new MonstersLeaders("eredin silver","double the strength of all your ","monsters"));
        monsterLeaders.add(new MonstersLeaders("eredin bronze","restore a card from your discard pile to your hand","monsters"));
        monsterLeaders.add(new MonstersLeaders("eredin gold","discard 2 card amd draw 1 card of your choise from your deck","monsters"));
        monsterLeaders.add(new MonstersLeaders("eredin copper","pick any weather card from your deck and play it instantly","monsters"));
        monsterLeaders.add(new MonstersLeaders("eredin the treacherous","doubles the strength of all spy cards(affects both players)","monsters"));
    }

    public ArrayList<Leader> getLeaders() {
        return monsterLeaders;
    }
    public static ArrayList<Card> getMonsterCards() {
        return monsterCards;
    }
    public static LinkedHashMap<Card,Integer> getMonsterDefaultDeck(){
        return monsterDefaultDeck;
    }
}
