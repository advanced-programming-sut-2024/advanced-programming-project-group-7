package model.factions;

import model.*;
import model.cards.*;
import model.leaders.NorthernRealmsLeaders;

import java.util.ArrayList;

public class NorthernRealms extends Faction {
    private static final ArrayList<Leader> leaders=new ArrayList<>();
    static {
    }
    public NorthernRealms(String descriptions) {
        super(descriptions);
        this.factionName="realms";
    }
    public static ArrayList<Card> northernRealmsCards=new ArrayList<>();
    static {
        northernRealmsCards.add(new Horn("horn", 3 , true, 0, "special"));
        northernRealmsCards.add(new Decoy("decoy", 3 , true, 0, "special"));
        northernRealmsCards.add(new Mardroeme("mardroeme", 3 , true, 0, "special"));
        northernRealmsCards.add(new Scorch("scorch", 3 , true, 0, "special"));
        northernRealmsCards.add(new Card("frost", 3 , true, 0, "weather"));
        northernRealmsCards.add(new Card("clear", 2 , true, 0, "weather"));
        northernRealmsCards.add(new Card("fog", 3 , true, 0, "weather"));
        northernRealmsCards.add(new Card("storm", 3 , true, 0, "weather"));
        northernRealmsCards.add(new Card("rain", 2 , true, 0, "weather"));
        northernRealmsCards.add(new Card("ciri", 1 , false, 15, "neutral"));
        northernRealmsCards.add(new Card("geralt", 1 , false, 15, "neutral"));
        northernRealmsCards.add(new Card("esterad", 1 , false, 10, "realms"));
        northernRealmsCards.add(new Card("natalis", 1 , false, 10, "realms"));
        northernRealmsCards.add(new Card("philippa", 1 , false, 10, "realms"));
        northernRealmsCards.add(new Card("vernon", 1 , false, 10, "realms"));
        northernRealmsCards.add(new TightBond("catapult 1", 2 , false, 8, "realms"));
        northernRealmsCards.add(new Card("triss", 1 , false, 7, "neutral"));
        northernRealmsCards.add(new Scorch("villen", 1 , false, 7, "neutral"));
        northernRealmsCards.add(new Medic("yennefer", 1 , false, 7, "neutral"));
        northernRealmsCards.add(new Card("ballista", 1 , false, 6, "realms"));
        northernRealmsCards.add(new Card("dethmold", 1 , false, 6, "realms"));
        northernRealmsCards.add(new MoralBoost("olgierd", 1 , false, 6, "neutral"));
        northernRealmsCards.add(new Card("siege tower", 1 , false, 6, "realms"));
        northernRealmsCards.add(new Card("trebuchet", 1 , false, 6, "realms"));
        northernRealmsCards.add(new Card("trebuchet 1", 1 , false, 6, "realms"));
        northernRealmsCards.add(new Card("vesemir", 1 , false, 6, "neutral"));
        northernRealmsCards.add(new TightBond("crinfrid", 3 , false, 5, "realms"));
        northernRealmsCards.add(new Medic("banner nurse", 1 , false, 5, "realms"));
        northernRealmsCards.add(new Card("emiel", 1 , false, 5, "neutral"));
        northernRealmsCards.add(new Card("keira", 1 , false, 5, "realms"));
        northernRealmsCards.add(new Spy("stennis", 1 , false, 5, "realms"));
        northernRealmsCards.add(new Card("siegfried", 1 , false, 5, "realms"));
        northernRealmsCards.add(new Card("sheala", 1 , false, 5, "realms"));
        northernRealmsCards.add(new Card("ves", 1 , false, 5, "realms"));
        northernRealmsCards.add(new Card("zoltan", 1 , false, 5, "neutral"));
        northernRealmsCards.add(new TightBond("blue stripes", 3 , false, 4, "realms"));
        northernRealmsCards.add(new Muster("gaunter odimm darkness", 3 , false, 4, "neutral"));
        northernRealmsCards.add(new Card("sabrina", 1 , false, 4, "realms"));
        northernRealmsCards.add(new Card("sheldon", 1 , false, 4, "realms"));
        northernRealmsCards.add(new Spy("dijkstra", 1 , false, 4, "realms"));
        northernRealmsCards.add(new Horn("dandelion", 1 , false, 2, "neutral"));
        northernRealmsCards.add(new Muster("gaunter odimm", 1 , false, 2, "neutral"));
        northernRealmsCards.add(new Horn("yarpen", 1 , false, 2, "realms"));
        northernRealmsCards.add(new MoralBoost("kaedwen siege", 1 , false, 1, "realms"));
        northernRealmsCards.add(new MoralBoost("kaedwen siege 1", 1 , false, 1, "realms"));
        northernRealmsCards.add(new MoralBoost("kaedwen siege 2", 1 , false, 1, "realms"));
        northernRealmsCards.add(new TightBond("poor infantry", 4 , false, 1, "realms"));
        northernRealmsCards.add(new Horn("redania", 1 , false, 1, "realms"));
        northernRealmsCards.add(new Horn("redania 1", 1 , false, 1, "realms"));
        northernRealmsCards.add(new Spy("thaler", 1 , false, 1, "realms"));
        northernRealmsCards.add(new Cow("cow", 1 , false, 0, "neutral"));
        northernRealmsCards.add(new Spy("mysterious elf", 1 , false, 0, "neutral"));
    }
    private  static ArrayList<Leader> northernRealmsLeader=new ArrayList<>();
    static {
        northernRealmsLeader.add(new NorthernRealmsLeaders("foltest silver","pick an impenetrable fog card from your deck and play it instantly","realms"));
        northernRealmsLeader.add(new NorthernRealmsLeaders("foltest gold","clear any weather effects(resulting from biting frost, torrential rain or impenetrable fog cards) in play","realms"));
        northernRealmsLeader.add(new NorthernRealmsLeaders("foltest copper","doubles the strength of all your siege units (unless a commander's horn is also present on that row).","realms"));
        northernRealmsLeader.add(new NorthernRealmsLeaders("foltest bronze","destroy your enemy's strongest siege unit(s) if the combined strength of all his or her siege units is 10 or more.","realms"));
        northernRealmsLeader.add(new NorthernRealmsLeaders("foltest son of medell","distroy your enemy's strongest ranged combat unit(s) if the combined strength of all his or her ranged combat units is 10 or more.","realms"));
    }



    public ArrayList<Leader> getLeaders() {
        return northernRealmsLeader;
    }

    public static ArrayList<Card> getNorthernRealmsCards() {
        return northernRealmsCards;
    }
}
