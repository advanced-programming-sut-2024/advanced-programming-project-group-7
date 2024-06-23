package model.factions;

import model.*;
import model.cards.*;
import model.leaders.MonstersLeaders;

import java.util.ArrayList;

public class Monsters extends Faction {
    public Monsters(String descriptions) {
        super(descriptions);
        this.factionName="monsters";
    }
    public static ArrayList<Card> monsterCards = new ArrayList<>();
    static {
        monsterCards.add(new Decoy("horn", 3, true, 0, "special"));
        monsterCards.add(new Horn("decoy", 3, true, 0, "special"));
        monsterCards.add(new Mardroeme("mardroeme", 3, true, 0, "special"));
        monsterCards.add(new Scorch("scorch", 3, true, 0, "special"));
        monsterCards.add(new Card("frost", 3, true, 0, "weather"));
        monsterCards.add(new Card("clear", 2, true, 0, "weather"));
        monsterCards.add(new Card("fog", 3, true, 0, "weather"));
        monsterCards.add(new Card("storm", 3, true, 0, "weather"));
        monsterCards.add(new Card("rain", 2, true, 0, "weather"));
        monsterCards.add(new Card("ciri", 1, false, 15, "neutral"));
        monsterCards.add(new Card("geralt", 1, false, 15, "neutral"));
        monsterCards.add(new Card("draug", 1, false, 10, "monsters"));
        monsterCards.add(new Card("imlerith", 1, false, 10, "monsters"));
        monsterCards.add(new Card("leshen", 1, false, 10, "monsters"));
        monsterCards.add(new Card("kayran", 1, false, 8, "monsters"));
        monsterCards.add(new Scorch("toad", 1, false, 7, "monsters"));
        monsterCards.add(new Card("triss", 1, false, 7, "neutral"));
        monsterCards.add(new Scorch("villen", 1, false, 7, "neutral"));
        monsterCards.add(new Medic("yennefer", 1, false, 7, "neutral"));
        monsterCards.add(new Muster("arachas behemoth", 1, false, 6, "monsters"));
        monsterCards.add(new Muster("witch velen", 1, false, 6, "monsters"));
        monsterCards.add(new Muster("witch velen 1", 1, false, 6, "monsters"));
        monsterCards.add(new Muster("witch velen 2", 1, false, 6, "monsters"));
        monsterCards.add(new Card("earth elemental", 1, false, 6, "monsters"));
        monsterCards.add(new Card("fiend", 1, false, 6, "monsters"));
        monsterCards.add(new Card("fire elemental", 1, false, 6, "monsters"));
        monsterCards.add(new MoralBoost("olgierd", 1, false, 6, "neutral"));
        monsterCards.add(new Card("vesemir", 1, false, 6, "neutral"));
        monsterCards.add(new Card("emiel", 1, false, 5, "neutral"));
        monsterCards.add(new Card("forktail", 1, false, 5, "monsters"));
        monsterCards.add(new Card("frightener", 1, false, 5, "monsters"));
        monsterCards.add(new Card("gravehag", 1, false, 5, "monsters"));
        monsterCards.add(new Card("gryffin", 1, false, 5, "monsters"));
        monsterCards.add(new Card("frost giant", 1, false, 5, "monsters"));
        monsterCards.add(new Card("mighty maiden", 1, false, 5, "monsters"));
        monsterCards.add(new Muster("katakan", 1, false, 5, "monsters"));
        monsterCards.add(new Card("werewolf", 1, false, 5, "monsters"));
        monsterCards.add(new Card("zoltan", 1, false, 5, "neutral"));
        monsterCards.add(new Muster("arachas", 1, false, 4, "monsters"));
        monsterCards.add(new Muster("arachas 1", 1, false, 4, "monsters"));
        monsterCards.add(new Muster("arachas 2", 1, false, 4, "monsters"));
        monsterCards.add(new Card("poroniec", 1, false, 4, "monsters"));
        monsterCards.add(new Muster("gaunter odimm darkness", 3, false, 4, "neutral"));
        monsterCards.add(new Muster("bruxa", 1, false, 4, "monsters"));
        monsterCards.add(new Muster("ekkima", 1, false, 4, "monsters"));
        monsterCards.add(new Muster("fleder", 1, false, 4, "monsters"));
        monsterCards.add(new Muster("garkain", 1, false, 4, "monsters"));
        monsterCards.add(new Agile("celaeno harpy", 1, false, 2, "monsters"));
        monsterCards.add(new Card("cockatrice", 1, false, 2, "monsters"));
        monsterCards.add(new Horn("dandelion", 1, false, 2, "neutral"));
        monsterCards.add(new Card("endrega", 1, false, 2, "monsters"));
        monsterCards.add(new Card("fogling", 1, false, 2, "monsters"));
        monsterCards.add(new Card("gargoyle", 1, false, 2, "monsters"));
        monsterCards.add(new Muster("gaunter odimm", 1, false, 2, "neutral"));
        monsterCards.add(new Agile("harpy", 1, false, 2, "monsters"));
        monsterCards.add(new Muster("nekker", 1, false, 2, "monsters"));
        monsterCards.add(new Muster("nekker 1", 1, false, 2, "monsters"));
        monsterCards.add(new Muster("nekker 2", 1, false, 2, "monsters"));
        monsterCards.add(new Card("wyvern", 1, false, 2, "monsters"));
        monsterCards.add(new Muster("ghoul", 1, false, 1, "monsters"));
        monsterCards.add(new Muster("ghoul 1", 1, false, 1, "monsters"));
        monsterCards.add(new Muster("ghoul 2", 1, false, 1, "monsters"));
        monsterCards.add(new Cow("cow", 1, false, 0, "neutral"));
        monsterCards.add(new Spy("mysterious elf", 1, false, 0, "neutral"));
    }
    private static  ArrayList<Leader> monsterLeaders=new ArrayList<>();
    static {
        monsterLeaders.add(new MonstersLeaders("eredin silver","double the strength of all your ","monsters"));
        monsterLeaders.add(new MonstersLeaders("eredin bronze","restore a card from your discard pile to your hand","monsters"));
        monsterLeaders.add(new MonstersLeaders("eredin gold","discard 2 card amd draw 1 card of your choise from your deck","monsters"));
        monsterLeaders.add(new MonstersLeaders("eredin copper","pick any weather card from your deck and play it instantly","monsters"));
        monsterLeaders.add(new MonstersLeaders("eredin the treacherous","doubles the strength of all spy cards(affects both players)","monsters"));
    }

    public static ArrayList<Leader> getMonsterLeaders() {
        return monsterLeaders;
    }

    public static ArrayList<Card> getMonsterCards() {
        return monsterCards;
    }
}
