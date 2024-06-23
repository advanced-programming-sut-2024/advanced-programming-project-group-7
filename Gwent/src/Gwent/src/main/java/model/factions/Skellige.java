package model.factions;

import model.*;
import model.cards.*;

import java.util.ArrayList;

public class Skellige extends Faction {
    public Skellige(String descriptions) {
        super(descriptions);
        this.factionName="skellige";
    }
    public static ArrayList<Card> skelligeCards = new ArrayList<>();
    static {
        skelligeCards.add(new Decoy("horn", 3, true, 0, "special"));
        skelligeCards.add(new Horn("decoy", 3, true, 0, "special"));
        skelligeCards.add(new Mardroeme("mardroeme", 3, true, 0, "special"));
        skelligeCards.add(new Scorch("scorch", 3, true, 0, "special"));
        skelligeCards.add(new Card("frost", 3, true, 0, "weather"));
        skelligeCards.add(new Card("clear", 2, true, 0, "weather"));
        skelligeCards.add(new Card("fog", 3, true, 0, "weather"));
        skelligeCards.add(new Card("storm", 3, true, 0, "weather"));
        skelligeCards.add(new Card("rain", 2, true, 0, "weather"));
        skelligeCards.add(new Card("ciri", 1, false, 15, "neutral"));
        skelligeCards.add(new Card("geralt", 1, false, 15, "neutral"));
        skelligeCards.add(new MoralBoost("olaf", 1, false, 12, "skellige"));
        skelligeCards.add(new Muster("cerys", 1, false, 10, "skellige"));
        skelligeCards.add(new Card("hjalmar", 1, false, 10, "skellige"));
        skelligeCards.add(new Mardroeme("ermion", 1, false, 8, "skellige"));
        skelligeCards.add(new Card("triss", 1, false, 7, "neutral"));
        skelligeCards.add(new Scorch("villen", 1, false, 7, "neutral"));
        skelligeCards.add(new Medic("yennefer", 1, false, 7, "neutral"));
        skelligeCards.add(new Card("blueboy", 1, false, 6, "skellige"));
        skelligeCards.add(new TightBond("craite warrior", 3, false, 6, "skellige"));
        skelligeCards.add(new Card("brokva archer", 2, false, 6, "skellige"));
        skelligeCards.add(new Scorch("dimun pirate", 1, false, 6, "skellige"));
        skelligeCards.add(new Card("madmad lugos", 1, false, 6, "skellige"));
        skelligeCards.add(new MoralBoost("olgierd", 1, false, 6, "neutral"));
        skelligeCards.add(new Card("vesemir", 1, false, 6, "neutral"));
        skelligeCards.add(new TightBond("war longship", 2, false, 6, "skellige"));
        skelligeCards.add(new Card("emiel", 1, false, 5, "neutral"));
        skelligeCards.add(new Card("zoltan", 1, false, 5, "neutral"));
        skelligeCards.add(new Berserker("berserker", 1, false, 4, "skellige"));
        skelligeCards.add(new TightBond("shield maiden", 1, false, 4, "skellige"));
        skelligeCards.add(new TightBond("shield maiden 1", 1, false, 4, "skellige"));
        skelligeCards.add(new TightBond("shield maiden 2", 1, false, 4, "skellige"));
        skelligeCards.add(new Card("heymaey", 1, false, 4, "skellige"));
        skelligeCards.add(new Card("tordarroch", 1, false, 4, "skellige"));
        skelligeCards.add(new Card("donar", 1, false, 4, "skellige"));
        skelligeCards.add(new Muster("gaunter odimm darkness", 3, false, 4, "neutral"));
        skelligeCards.add(new Card("holger", 1, false, 4, "skellige"));
        skelligeCards.add(new Muster("light longship", 3, false, 4, "skellige"));
        skelligeCards.add(new Card("svanrige", 1, false, 4, "skellige"));
        skelligeCards.add(new Card("udalryk", 1, false, 4, "skellige"));
        skelligeCards.add(new Medic("birna", 1, false, 2, "skellige"));
        skelligeCards.add(new Horn("dandelion", 1, false, 2, "neutral"));
        skelligeCards.add(new Horn("draig", 1, false, 2, "skellige"));
        skelligeCards.add(new Muster("gaunter odimm", 1, false, 2, "neutral"));
        skelligeCards.add(new Berserker("young berserker", 3, false, 2, "skellige"));
        skelligeCards.add(new Cow("cow", 1, false, 0, "neutral"));
        skelligeCards.add(new Cow("kambi", 1, false, 0, "skellige"));
        skelligeCards.add(new Spy("mysterious elf", 1, false, 0, "neutral"));
    }
    public static ArrayList<Card> getSkelligeCards() {
        return skelligeCards;
    }
}
