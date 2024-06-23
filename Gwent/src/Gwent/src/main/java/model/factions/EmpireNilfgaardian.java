package model.factions;

import model.*;
import model.cards.*;
import model.cards.Horn;
import model.leaders.EmpireNilfgaardiansLeaders;

import java.util.ArrayList;

public class EmpireNilfgaardian extends Faction {
    public EmpireNilfgaardian(String descriptions) {
        super(descriptions);
        this.factionName="nilfgaard";
    }
    public static ArrayList<Card> empireNilfgaardian =new ArrayList<>();
    static {
        empireNilfgaardian.add(new Card("horn",3,true,0,"special"));
        empireNilfgaardian.add(new Horn("decoy", 3 , true, 0, "special"));
        empireNilfgaardian.add(new Mardroeme("mardroeme", 3 , true, 0, "special"));
        empireNilfgaardian.add(new Scorch("scorch", 3 , true, 0, "special"));
        empireNilfgaardian.add(new Card("frost", 3 , true, 0, "weather"));
        empireNilfgaardian.add(new Card("clear", 2 , true, 0, "weather"));
        empireNilfgaardian.add(new Card("fog", 3 , true, 0, "weather"));
        empireNilfgaardian.add(new Card("storm", 3 , true, 0, "weather"));
        empireNilfgaardian.add(new Card("rain", 2 , true, 0, "weather"));
        empireNilfgaardian.add(new Card("ciri", 1 , false, 15, "neutral"));
        empireNilfgaardian.add(new Card("geralt", 1 , false, 15, "neutral"));
        empireNilfgaardian.add(new Card("black archer",1,false,10,"nilfgaard"));
        empireNilfgaardian.add(new Card("black archer 1",1,false,10,"nilfgaard"));
        empireNilfgaardian.add(new Card("heavy zerri",1,false,10,"nilfgaard"));
        empireNilfgaardian.add(new Card("letho",1,false,10,"nilfgaard"));
        empireNilfgaardian.add(new Medic("menno",1,false,10,"nilfgaard"));
        empireNilfgaardian.add(new Card("moorvran",1,false,10,"nilfgaard"));
        empireNilfgaardian.add(new Card("tibor",1,false,10,"nilfgaard"));
        empireNilfgaardian.add(new Spy("stefan",1,false,9,"nilfgaard"));
        empireNilfgaardian.add(new Spy("shilard",1,false,7,"nilfgaard"));
        empireNilfgaardian.add(new Card("triss",1,false,7,"neutral"));
        empireNilfgaardian.add(new Scorch("villen",1,false,7,"neutral"));
        empireNilfgaardian.add(new Medic("yennefer",1,false,7,"neutral"));
        empireNilfgaardian.add(new Card("assire",1,false,6,"nilfgaard"));
        empireNilfgaardian.add(new Card("cahir",1,false,6,"nilfgaard"));
        empireNilfgaardian.add(new Card("fringilla",1,false,6,"nilfgaard"));
        empireNilfgaardian.add(new MoralBoost("olgierd",1,false,6,"neutral"));
        empireNilfgaardian.add(new Card("siege engineer",1,false,6,"nilfgaard"));
        empireNilfgaardian.add(new Card("vesemir",1,false,6,"neutral"));
        empireNilfgaardian.add(new Card("emiel",1,false,5,"neutral"));
        empireNilfgaardian.add(new Card("renuald",1,false,5,"nilfgaard"));
        empireNilfgaardian.add(new TightBond("young emissary",1,false,5,"nilfgaard"));
        empireNilfgaardian.add(new TightBond("young emissary 1",1,false,5,"nilfgaard"));
        empireNilfgaardian.add(new Card("zerri",1,false,5,"nilfgaard"));
        empireNilfgaardian.add(new Card("zoltan",1,false,5,"neutral"));
        empireNilfgaardian.add(new Card("cynthia",1,false,4,"nilfgaard"));
        empireNilfgaardian.add(new Muster("gaunter odimm darkness",3,false,4,"neutral"));
        empireNilfgaardian.add(new Card("rainfarn",1,false,4,"nilfgaard"));
        empireNilfgaardian.add(new Card("vanhemar",1,false,4,"nilfgaard"));
        empireNilfgaardian.add(new Spy("vattier",1,false,4,"nilfgaard"));
        empireNilfgaardian.add(new TightBond("imperal brigade",4,false,3,"nilfgaard"));
        empireNilfgaardian.add(new Card("morteisen",1,false,3,"nilfgaard"));
        empireNilfgaardian.add(new Card("puttkammer",1,false,3,"nilfgaard"));
        empireNilfgaardian.add(new Card("rotten",1,false,3,"nilfgaard"));
        empireNilfgaardian.add(new Card("albrich",1,false,2,"nilfgaard"));
        empireNilfgaardian.add(new Horn("dandelion",1,false,2,"neutral"));
        empireNilfgaardian.add(new Muster("gaunter odimm",1,false,2,"neutral"));
        empireNilfgaardian.add(new TightBond("nauzicaa 2",3,false,2,"nilfgaard"));
        empireNilfgaardian.add(new Card("sweers",1,false,2,"nilfgaard"));
        empireNilfgaardian.add(new Card("vreemde",1,false,2,"nilfgaard"));
        empireNilfgaardian.add(new Medic("archer support",1,false,1,"nilfgaard"));
        empireNilfgaardian.add(new Medic("archer support 1",1,false,1,"nilfgaard"));
        empireNilfgaardian.add(new Cow("cow",1,false,0,"neutral"));
        empireNilfgaardian.add(new Spy("mysterious elf",1,false,0,"neutral"));
        empireNilfgaardian.add(new Medic("siege support",1,false,0,"nilfgaard"));
    }
    private static ArrayList<Leader> empireNilfgaardiansLeader=new ArrayList<>();
    static {
        empireNilfgaardiansLeader.add(new EmpireNilfgaardiansLeaders("emhyr silver","pick a torrential rain card from your deck and play it instantly"));
        empireNilfgaardiansLeader.add(new EmpireNilfgaardiansLeaders("emhyr copper","look at 3 random cards from your opponent's hand"));
        empireNilfgaardiansLeader.add(new EmpireNilfgaardiansLeaders("emhyr bronze","cansel your opponent's Leader ability"));
        empireNilfgaardiansLeader.add(new EmpireNilfgaardiansLeaders("emhyr gold","draw a card from your opponent's discard pile"));
        empireNilfgaardiansLeader.add(new EmpireNilfgaardiansLeaders("emhyr invader of the north","abilities that restore a unit to the battlefield restore a randomly-chosen unit.affects both players."));
    }

    public ArrayList<Leader> getLeaders() {
        return empireNilfgaardiansLeader;
    }

    public static ArrayList<Card> getEmpireNilfgaardian() {
        return empireNilfgaardian;
    }
}
