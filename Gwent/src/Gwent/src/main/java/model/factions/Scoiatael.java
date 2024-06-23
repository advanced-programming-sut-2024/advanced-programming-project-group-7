package model.factions;

import model.*;
import model.cards.*;
import model.leaders.ScoiataelLeaders;

import java.util.ArrayList;

public class Scoiatael extends Faction {
    public Scoiatael(String descriptions) {
        super(descriptions);
        this.factionName="scoiatael";
    }
    public static ArrayList<Card> scoiataelCards = new ArrayList<>();
    static {
        scoiataelCards.add(new Decoy("horn", 3, true, 0, "special"));
        scoiataelCards.add(new Horn("decoy", 3, true, 0, "special"));
        scoiataelCards.add(new Mardroeme("mardroeme", 3, true, 0, "special"));
        scoiataelCards.add(new Scorch("scorch", 3, true, 0, "special"));
        scoiataelCards.add(new Card("frost", 3, true, 0, "weather"));
        scoiataelCards.add(new Card("clear", 2, true, 0, "weather"));
        scoiataelCards.add(new Card("fog", 3, true, 0, "weather"));
        scoiataelCards.add(new Card("storm", 3, true, 0, "weather"));
        scoiataelCards.add(new Card("rain", 2, true, 0, "weather"));
        scoiataelCards.add(new Card("ciri", 1, false, 15, "neutral"));
        scoiataelCards.add(new Card("geralt", 1, false, 15, "neutral"));
        scoiataelCards.add(new Card("eithne", 1, false, 10, "scoiatael"));
        scoiataelCards.add(new Card("iorveth", 1, false, 10, "scoiatael"));
        scoiataelCards.add(new MoralBoost("isengrim", 1, false, 10, "scoiatael"));
        scoiataelCards.add(new MoralBoost("milva", 1, false, 10, "scoiatael"));
        scoiataelCards.add(new Card("saskia", 1, false, 10, "scoiatael"));
        scoiataelCards.add(new Scorch("schirru", 1, false, 8, "scoiatael"));
        scoiataelCards.add(new Card("triss", 1, false, 7, "neutral"));
        scoiataelCards.add(new Scorch("villen", 1, false, 7, "neutral"));
        scoiataelCards.add(new Medic("yennefer", 1, false, 7, "neutral"));
        scoiataelCards.add(new Agile("barclay", 1, false, 6, "scoiatael"));
        scoiataelCards.add(new Card("dennis", 1, false, 6, "scoiatael"));
        scoiataelCards.add(new Agile("dol infantry", 1, false, 6, "scoiatael"));
        scoiataelCards.add(new Agile("dol infantry 1", 1, false, 6, "scoiatael"));
        scoiataelCards.add(new Agile("dol infantry 2", 1, false, 6, "scoiatael"));
        scoiataelCards.add(new Agile("filavandrel", 1, false, 6, "scoiatael"));
        scoiataelCards.add(new Card("ida", 1, false, 6, "scoiatael"));
        scoiataelCards.add(new MoralBoost("olgierd", 1, false, 6, "neutral"));
        scoiataelCards.add(new Card("vesemir", 1, false, 6, "neutral"));
        scoiataelCards.add(new Agile("yaevinn", 1, false, 6, "scoiatael"));
        scoiataelCards.add(new Card("emiel", 1, false, 5, "neutral"));
        scoiataelCards.add(new Muster("havekar support", 1, false, 5, "scoiatael"));
        scoiataelCards.add(new Muster("havekar support 1", 1, false, 5, "scoiatael"));
        scoiataelCards.add(new Muster("havekar support 2", 1, false, 5, "scoiatael"));
        scoiataelCards.add(new Card("mahakam", 1, false, 5, "scoiatael"));
        scoiataelCards.add(new Card("mahakam 1", 1, false, 5, "scoiatael"));
        scoiataelCards.add(new Card("mahakam 2", 1, false, 5, "scoiatael"));
        scoiataelCards.add(new Card("mahakam 3", 1, false, 5, "scoiatael"));
        scoiataelCards.add(new Card("mahakam 4", 1, false, 5, "scoiatael"));
        scoiataelCards.add(new Agile("vrihedd brigade", 1, false, 5, "scoiatael"));
        scoiataelCards.add(new Agile("vrihedd brigade 1", 1, false, 5, "scoiatael"));
        scoiataelCards.add(new Card("zoltan", 1, false, 5, "neutral"));
        scoiataelCards.add(new Card("dol archer", 1, false, 4, "scoiatael"));
        scoiataelCards.add(new Muster("gaunter odimm darkness", 3, false, 4, "neutral"));
        scoiataelCards.add(new Card("vrihedd cadet", 1, false, 4, "scoiatael"));
        scoiataelCards.add(new Agile("ciaran", 1, false, 3, "scoiatael"));
        scoiataelCards.add(new Muster("dwarf", 1, false, 3, "scoiatael"));
        scoiataelCards.add(new Muster("dwarf 1", 1, false, 3, "scoiatael"));
        scoiataelCards.add(new Muster("dwarf 2", 1, false, 3, "scoiatael"));
        scoiataelCards.add(new Horn("dandelion", 1, false, 2, "neutral"));
        scoiataelCards.add(new Muster("elf skirmisher", 1, false, 2, "scoiatael"));
        scoiataelCards.add(new Muster("elf skirmisher 1", 1, false, 2, "scoiatael"));
        scoiataelCards.add(new Muster("elf skirmisher 2", 1, false, 2, "scoiatael"));
        scoiataelCards.add(new Muster("gaunter odimm", 1, false, 2, "neutral"));
        scoiataelCards.add(new Card("toruviel", 1, false, 2, "scoiatael"));
        scoiataelCards.add(new Card("riordain", 1, false, 1, "scoiatael"));
        scoiataelCards.add(new Cow("cow", 1, false, 0, "neutral"));
        scoiataelCards.add(new Medic("havekar nurse", 1, false, 0, "scoiatael"));
        scoiataelCards.add(new Medic("havekar nurse 1", 1, false, 0, "scoiatael"));
        scoiataelCards.add(new Medic("havekar nurse 2", 1, false, 0, "scoiatael"));
        scoiataelCards.add(new Spy("mysterious elf", 1, false, 0, "neutral"));
    }
    private static ArrayList<Leader> scoiataelLeaders=new ArrayList<>();
    static {
        scoiataelLeaders.add(new ScoiataelLeaders("francesca silver","destroy your enemy's strongest close combat unit(s) if the combined strength of all his or her close combat units is 10 or more.","scoiatael"));
        scoiataelLeaders.add(new ScoiataelLeaders("francesca gold","doubles the strength of all your ranged combat units (unless a commander's horn is also present on that row).","scoiatael"));
        scoiataelLeaders.add(new ScoiataelLeaders("francesca copper","draw an extra card at the beginning of the battle.","scoiatael"));
        scoiataelLeaders.add(new ScoiataelLeaders("francesca bronze","pick a biting frost card from your deck and play it instantly.","scoiatael"));
        scoiataelLeaders.add(new ScoiataelLeaders("francesca hope of the aen seidhe","move agile units to whichever valid row maximizes their strength(don't move units in optimal row).","scoiatael"));
    }
    public static ArrayList<Leader> getScoiataelLeaders() {
        return scoiataelLeaders;
    }
    public static ArrayList<Card> getScoiataelCards() {
        return scoiataelCards;
    }
}
