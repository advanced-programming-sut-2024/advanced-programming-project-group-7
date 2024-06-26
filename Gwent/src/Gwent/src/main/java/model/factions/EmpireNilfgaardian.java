package model.factions;

import model.*;
import model.cards.*;
import model.cards.Horn;
import model.leaders.EmpireNilfgaardiansLeaders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class EmpireNilfgaardian extends Faction {
    public EmpireNilfgaardian(String descriptions) {
        super(descriptions);
        this.factionName="nilfgaard";
    }
    private final static ArrayList<Card> empireNilfgaardianCards =new ArrayList<>();
    static {
        empireNilfgaardianCards.add(new Card("horn",3,true,0,"special",123,false));
        empireNilfgaardianCards.add(new Horn("decoy", 3 , true, 0, "special",123,false));
        empireNilfgaardianCards.add(new Mardroeme("mardroeme", 3 , true, 0, "special",123,false));
        empireNilfgaardianCards.add(new Scorch("scorch", 3 , true, 0, "special",123456,false));
        empireNilfgaardianCards.add(new Card("frost", 3 , true, 0, "weather",7,false));
        empireNilfgaardianCards.add(new Card("clear", 2 , true, 0, "weather",7,false));
        empireNilfgaardianCards.add(new Card("fog", 3 , true, 0, "weather",7,false));
        empireNilfgaardianCards.add(new Card("storm", 3 , true, 0, "weather",7,false));
        empireNilfgaardianCards.add(new Card("rain", 2 , true, 0, "weather",7,false));
        empireNilfgaardianCards.add(new Card("ciri", 1 , false, 15, "neutral",3,true));
        empireNilfgaardianCards.add(new Card("geralt", 1 , false, 15, "neutral",3,true));
        empireNilfgaardianCards.add(new Card("black archer",1,false,10,"nilfgaard",2,false));
        empireNilfgaardianCards.add(new Card("black archer 1",1,false,10,"nilfgaard",2,false));
        empireNilfgaardianCards.add(new Card("heavy zerri",1,false,10,"nilfgaard",3,false));
        empireNilfgaardianCards.add(new Card("letho",1,false,10,"nilfgaard",3,true));
        empireNilfgaardianCards.add(new Medic("menno",1,false,10,"nilfgaard",3,true));
        empireNilfgaardianCards.add(new Card("moorvran",1,false,10,"nilfgaard",1,true));
        empireNilfgaardianCards.add(new Card("tibor",1,false,10,"nilfgaard",2,true));
        empireNilfgaardianCards.add(new Spy("stefan",1,false,9,"nilfgaard",3,false));
        empireNilfgaardianCards.add(new Spy("shilard",1,false,7,"nilfgaard",3,false));
        empireNilfgaardianCards.add(new Card("triss",1,false,7,"neutral",3,true));
        empireNilfgaardianCards.add(new Scorch("villen",1,false,7,"neutral",3,false));
        empireNilfgaardianCards.add(new Medic("yennefer",1,false,7,"neutral",2,true));
        empireNilfgaardianCards.add(new Card("assire",1,false,6,"nilfgaard",2,false));
        empireNilfgaardianCards.add(new Card("cahir",1,false,6,"nilfgaard",3,false));
        empireNilfgaardianCards.add(new Card("fringilla",1,false,6,"nilfgaard",2,false));
        empireNilfgaardianCards.add(new MoralBoost("olgierd",1,false,6,"neutral",23,false));
        empireNilfgaardianCards.add(new Card("siege engineer",1,false,6,"nilfgaard",1,false));
        empireNilfgaardianCards.add(new Card("vesemir",1,false,6,"neutral",3,false));
        empireNilfgaardianCards.add(new Card("emiel",1,false,5,"neutral",3,false));
        empireNilfgaardianCards.add(new Card("renuald",1,false,5,"nilfgaard",2,false));
        empireNilfgaardianCards.add(new TightBond("young emissary",1,false,5,"nilfgaard",3,false));
        empireNilfgaardianCards.add(new TightBond("young emissary 1",1,false,5,"nilfgaard",3,false));
        empireNilfgaardianCards.add(new Card("zerri",1,false,5,"nilfgaard",1,false));
        empireNilfgaardianCards.add(new Card("zoltan",1,false,5,"neutral",3,false));
        empireNilfgaardianCards.add(new Card("cynthia",1,false,4,"nilfgaard",2,false));
        empireNilfgaardianCards.add(new Muster("gaunter odimm darkness",3,false,4,"neutral",2,false));
        empireNilfgaardianCards.add(new Card("rainfarn",1,false,4,"nilfgaard",3,false));
        empireNilfgaardianCards.add(new Card("vanhemar",1,false,4,"nilfgaard",2,false));
        empireNilfgaardianCards.add(new Spy("vattier",1,false,4,"nilfgaard",3,false));
        empireNilfgaardianCards.add(new TightBond("imperal brigade",4,false,3,"nilfgaard",3,false));
        empireNilfgaardianCards.add(new Card("morteisen",1,false,3,"nilfgaard",3,false));
        empireNilfgaardianCards.add(new Card("puttkammer",1,false,3,"nilfgaard",2,false));
        empireNilfgaardianCards.add(new Card("rotten",1,false,3,"nilfgaard",1,false));
        empireNilfgaardianCards.add(new Card("albrich",1,false,2,"nilfgaard",2,false));
        empireNilfgaardianCards.add(new Horn("dandelion",1,false,2,"neutral",3,false));
        empireNilfgaardianCards.add(new Muster("gaunter odimm",1,false,2,"neutral",1,false));
        empireNilfgaardianCards.add(new TightBond("nauzicaa 2",3,false,2,"nilfgaard",3,false));
        empireNilfgaardianCards.add(new Card("sweers",1,false,2,"nilfgaard",2,false));
        empireNilfgaardianCards.add(new Card("vreemde",1,false,2,"nilfgaard",3,false));
        empireNilfgaardianCards.add(new Medic("archer support",1,false,1,"nilfgaard",2,false));
        empireNilfgaardianCards.add(new Medic("archer support 1",1,false,1,"nilfgaard",2,false));
        empireNilfgaardianCards.add(new Cow("cow",1,false,0,"neutral",2,false));
        empireNilfgaardianCards.add(new Spy("mysterious elf",1,false,0,"neutral",3,true));
        empireNilfgaardianCards.add(new Medic("siege support",1,false,0,"nilfgaard",1,false));
    }
    private final static LinkedHashMap<Card,Integer> empireNilfgaardianDefaultDeck=new LinkedHashMap<>();
    static {
        empireNilfgaardianDefaultDeck.put(empireNilfgaardianCards.get(0), 2);
        empireNilfgaardianDefaultDeck.put(empireNilfgaardianCards.get(1), 3);
        empireNilfgaardianDefaultDeck.put(empireNilfgaardianCards.get(3), 2);
        empireNilfgaardianDefaultDeck.put(empireNilfgaardianCards.get(4), 1);
        empireNilfgaardianDefaultDeck.put(empireNilfgaardianCards.get(8), 1);
        empireNilfgaardianDefaultDeck.put(empireNilfgaardianCards.get(9), 1);
        empireNilfgaardianDefaultDeck.put(empireNilfgaardianCards.get(10), 1);
        empireNilfgaardianDefaultDeck.put(empireNilfgaardianCards.get(12), 1);
        empireNilfgaardianDefaultDeck.put(empireNilfgaardianCards.get(14), 1);
        empireNilfgaardianDefaultDeck.put(empireNilfgaardianCards.get(16), 1);
        empireNilfgaardianDefaultDeck.put(empireNilfgaardianCards.get(17), 1);
        empireNilfgaardianDefaultDeck.put(empireNilfgaardianCards.get(21), 1);
        empireNilfgaardianDefaultDeck.put(empireNilfgaardianCards.get(22), 1);
        empireNilfgaardianDefaultDeck.put(empireNilfgaardianCards.get(26), 1);
        empireNilfgaardianDefaultDeck.put(empireNilfgaardianCards.get(27), 1);
        empireNilfgaardianDefaultDeck.put(empireNilfgaardianCards.get(36), 3);
        empireNilfgaardianDefaultDeck.put(empireNilfgaardianCards.get(44), 1);
        empireNilfgaardianDefaultDeck.put(empireNilfgaardianCards.get(45), 1);
        empireNilfgaardianDefaultDeck.put(empireNilfgaardianCards.get(47), 3);
        empireNilfgaardianDefaultDeck.put(empireNilfgaardianCards.get(53), 1);
    }
    private final static ArrayList<Leader> empireNilfgaardiansLeader=new ArrayList<>();
    static {
        empireNilfgaardiansLeader.add(new EmpireNilfgaardiansLeaders("emhyr silver","pick a torrential rain card from your deck and play it instantly","nilfgaard"));
        empireNilfgaardiansLeader.add(new EmpireNilfgaardiansLeaders("emhyr copper","look at 3 random cards from your opponent's hand","nilfgaard"));
        empireNilfgaardiansLeader.add(new EmpireNilfgaardiansLeaders("emhyr bronze","cansel your opponent's Leader ability","nilfgaard"));
        empireNilfgaardiansLeader.add(new EmpireNilfgaardiansLeaders("emhyr gold","draw a card from your opponent's discard pile","nilfgaard"));
        empireNilfgaardiansLeader.add(new EmpireNilfgaardiansLeaders("emhyr invader of the north","abilities that restore a unit to the battlefield restore a randomly-chosen unit.affects both players.","nilfgaard"));
    }
    public ArrayList<Leader> getLeaders() {
        return empireNilfgaardiansLeader;
    }
    public static ArrayList<Card> getEmpireNilfgaardianCards() {
        return empireNilfgaardianCards;
    }
    public static LinkedHashMap<Card,Integer> getEmpireNilfgaardianDefaultDeck(){
        return empireNilfgaardianDefaultDeck;
    }
}
