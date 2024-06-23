package model;

import java.util.ArrayList;

public class EmpireNilfgaardian extends Faction{
    public EmpireNilfgaardian(String descriptions) {
        super(descriptions);
        this.factionName="nilfgaard";
    }
    public static ArrayList<Card> emoireNilfgaardian=new ArrayList<>();
    static {
        emoireNilfgaardian.add(new Card("horn",3,true,0,"special"));
        emoireNilfgaardian.add(new Horn("decoy", 3 , true, 0, "special"));
        emoireNilfgaardian.add(new Mardroeme("mardroeme", 3 , true, 0, "special"));
        emoireNilfgaardian.add(new Scorch("scorch", 3 , true, 0, "special"));
        emoireNilfgaardian.add(new Card("frost", 3 , true, 0, "weather"));
        emoireNilfgaardian.add(new Card("clear", 2 , true, 0, "weather"));
        emoireNilfgaardian.add(new Card("fog", 3 , true, 0, "weather"));
        emoireNilfgaardian.add(new Card("storm", 3 , true, 0, "weather"));
        emoireNilfgaardian.add(new Card("rain", 2 , true, 0, "weather"));
        emoireNilfgaardian.add(new Card("ciri", 1 , false, 15, "neutral"));
        emoireNilfgaardian.add(new Card("geralt", 1 , false, 15, "neutral"));
        emoireNilfgaardian.add(new Card("black archer",1,false,10,"nilfgaard"));
        emoireNilfgaardian.add(new Card("black archer 1",1,false,10,"nilfgaard"));
        emoireNilfgaardian.add(new Card("heavy zerri",1,false,10,"nilfgaard"));
        emoireNilfgaardian.add(new Card("letho",1,false,10,"nilfgaard"));
        emoireNilfgaardian.add(new Medic("menno",1,false,10,"nilfgaard"));
        emoireNilfgaardian.add(new Card("moorvran",1,false,10,"nilfgaard"));
        emoireNilfgaardian.add(new Card("tibor",1,false,10,"nilfgaard"));
        emoireNilfgaardian.add(new Spy("stefan",1,false,9,"nilfgaard"));
        emoireNilfgaardian.add(new Spy("shilard",1,false,7,"nilfgaard"));
        emoireNilfgaardian.add(new Card("triss",1,false,7,"neutral"));
        emoireNilfgaardian.add(new Scorch("villen",1,false,7,"neutral"));
        emoireNilfgaardian.add(new Medic("yennefer",1,false,7,"neutral"));
        emoireNilfgaardian.add(new Card("assire",1,false,6,"nilfgaard"));
        emoireNilfgaardian.add(new Card("cahir",1,false,6,"nilfgaard"));
        emoireNilfgaardian.add(new Card("fringilla",1,false,6,"nilfgaard"));
        emoireNilfgaardian.add(new MoralBoost("olgierd",1,false,6,"neutral"));
        emoireNilfgaardian.add(new Card("siege engineer",1,false,6,"nilfgaard"));
        emoireNilfgaardian.add(new Card("vesemir",1,false,6,"neutral"));
        emoireNilfgaardian.add(new Card("emiel",1,false,5,"neutral"));
        emoireNilfgaardian.add(new Card("renuald",1,false,5,"nilfgaard"));
        emoireNilfgaardian.add(new TightBond("young emissary",1,false,5,"nilfgaard"));
        emoireNilfgaardian.add(new TightBond("young emissary 1",1,false,5,"nilfgaard"));
        emoireNilfgaardian.add(new Card("zerri",1,false,5,"nilfgaard"));
        emoireNilfgaardian.add(new Card("zoltan",1,false,5,"neutral"));
        emoireNilfgaardian.add(new Card("cynthia",1,false,4,"nilfgaard"));
        emoireNilfgaardian.add(new Muster("gaunter odimm darkness",3,false,4,"neutral"));
        emoireNilfgaardian.add(new Card("rainfarn",1,false,4,"nilfgaard"));
        emoireNilfgaardian.add(new Card("vanhemar",1,false,4,"nilfgaard"));
        emoireNilfgaardian.add(new Spy("vattier",1,false,4,"nilfgaard"));
        emoireNilfgaardian.add(new TightBond("imperal brigade",4,false,3,"nilfgaard"));
        emoireNilfgaardian.add(new Card("morteisen",1,false,3,"nilfgaard"));
        emoireNilfgaardian.add(new Card("puttkammer",1,false,3,"nilfgaard"));
        emoireNilfgaardian.add(new Card("rotten",1,false,3,"nilfgaard"));
        emoireNilfgaardian.add(new Card("albrich",1,false,2,"nilfgaard"));
        emoireNilfgaardian.add(new Horn("dandelion",1,false,2,"neutral"));
        emoireNilfgaardian.add(new Muster("gaunter odimm",1,false,2,"neutral"));
        emoireNilfgaardian.add(new TightBond("nauzicaa 2",3,false,2,"nilfgaard"));
        emoireNilfgaardian.add(new Card("sweers",1,false,2,"nilfgaard"));
        emoireNilfgaardian.add(new Card("vreemde",1,false,2,"nilfgaard"));
        emoireNilfgaardian.add(new Medic("archer support",1,false,1,"nilfgaard"));
        emoireNilfgaardian.add(new Medic("archer support 1",1,false,1,"nilfgaard"));
        emoireNilfgaardian.add(new Cow("cow",1,false,0,"neutral"));
        emoireNilfgaardian.add(new Spy("mysterious elf",1,false,0,"neutral"));
        emoireNilfgaardian.add(new Medic("siege support",1,false,0,"nilfgaard"));
    }
    public static ArrayList<Card> getEmoireNilfgaardian() {
        return emoireNilfgaardian;
    }
}
