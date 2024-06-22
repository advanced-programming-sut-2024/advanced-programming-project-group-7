package model;

import java.util.ArrayList;

public class Faction {
    private static String[] factionsName={"",""};
    public boolean isFactionNameValid(String factionName){
        return false;
    }
    public static class NorthernRealms{
        public static ArrayList<Card> northernRealmsCards=new ArrayList<>();
        static {
            northernRealmsCards.add(new Decoy("special horn", 3 , true, 0, "special"));
            northernRealmsCards.add(new Horn("special decoy", 3 , true, 0, "special"));
            northernRealmsCards.add(new Mardroeme("special mardroeme", 3 , true, 0, "special"));
            northernRealmsCards.add(new Scorch("scorch", 3 , true, 0, "special"));
            northernRealmsCards.add(new Card("frost", 3 , true, 0, "weather"));
            northernRealmsCards.add(new Card("clear", 2 , true, 0, "weather"));
            northernRealmsCards.add(new Card("fog", 3 , true, 0, "weather"));
            northernRealmsCards.add(new Card("storm", 3 , true, 0, "weather"));
            northernRealmsCards.add(new Card("rain", 2 , true, 0, "weather"));
            northernRealmsCards.add(new Card("ciri", 1 , false, 15, "neutral"));
            northernRealmsCards.add(new Card("geralt", 1 , false, 15, "neutral"));
            northernRealmsCards.add(new Card("esterad", 1 , false, 10, "neutral"));
            northernRealmsCards.add(new Card("natalis", 1 , false, 10, "realms"));
            northernRealmsCards.add(new Card("philippa", 1 , false, 10, "realms"));
            northernRealmsCards.add(new Card("vernon", 1 , false, 10, "realms"));
            northernRealmsCards.add(new TightBond("catapult", 2 , false, 8, "realms"));
            northernRealmsCards.add(new Card("triss", 1 , false, 7, "neutral"));
            northernRealmsCards.add(new Scorch("villentretenmerth", 1 , false, 7, "neutral"));
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
            northernRealmsCards.add(new Card("tansarville", 1 , false, 5, "realms"));
            northernRealmsCards.add(new Card("sheala", 1 , false, 5, "realms"));
            northernRealmsCards.add(new Card("ves", 1 , false, 5, "neutral"));
            northernRealmsCards.add(new Card("zoltan", 1 , false, 5, "neutral"));
            northernRealmsCards.add(new TightBond("blue stripes", 3 , false, 4, "realms"));
            northernRealmsCards.add(new Muster("gaunter odimm darkness", 3 , false, 4, "neutral"));
            northernRealmsCards.add(new Card("sabrina", 1 , false, 4, "realms"));
            northernRealmsCards.add(new Card("sheldon", 1 , false, 4, "realms"));
            northernRealmsCards.add(new Spy("dijkstra", 1 , false, 4, "realms"));
            northernRealmsCards.add(new Horn("dendelion", 1 , false, 2, "neutral"));
            northernRealmsCards.add(new Muster("gaunter odimm", 1 , false, 2, "neutral"));
            northernRealmsCards.add(new Horn("yarpen", 1 , false, 2, "realms"));
            northernRealmsCards.add(new MoralBoost("kaedwen siege", 1 , false, 1, "realms"));
            northernRealmsCards.add(new MoralBoost("kaedwen siege 1", 1 , false, 1, "realms"));
            northernRealmsCards.add(new MoralBoost("kaedwen siege 2", 1 , false, 1, "realms"));
            northernRealmsCards.add(new TightBond("poor infantry", 4 , false, 1, "realms"));
            northernRealmsCards.add(new Horn("redenia", 1 , false, 1, "realms"));
            northernRealmsCards.add(new Horn("redenia 1", 1 , false, 1, "realms"));
            northernRealmsCards.add(new Spy("thaler", 1 , false, 1, "realms"));
            northernRealmsCards.add(new Cow("cow", 1 , false, 0, "neutral"));
            northernRealmsCards.add(new Spy("mysterious elf", 1 , false, 0, "neutral"));
        }
    }
    public static class EmpireNilfgaardian{
        public static ArrayList<Card> emoireNilfgaardian=new ArrayList<>();
        static {
            emoireNilfgaardian.add(new Card("",0,false,0,""));
            emoireNilfgaardian.add(new Horn("special decoy", 3 , true, 0, "special"));
            emoireNilfgaardian.add(new Mardroeme("special mardroeme", 3 , true, 0, "special"));
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
            emoireNilfgaardian.add(new Scorch("villentretenmerth",1,false,7,"neutral"));
            emoireNilfgaardian.add(new Medic("yenefer",1,false,7,"neutral"));
            emoireNilfgaardian.add(new Card("assire",1,false,6,"nilfgaard"));
            emoireNilfgaardian.add(new Card("cahir",1,false,6,"nilfgaard"));
            emoireNilfgaardian.add(new Card("fringilla",1,false,6,"nilfgaard"));
            emoireNilfgaardian.add(new MoralBoost("olgierd",1,false,6,"neutral"));
            emoireNilfgaardian.add(new Card("seige engineer",1,false,6,"nilfgaard"));
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
            emoireNilfgaardian.add(new TightBond("nausicaa",3,false,2,"nilfgaard"));
            emoireNilfgaardian.add(new Card("sweers",1,false,2,"nilfgaard"));
            emoireNilfgaardian.add(new Card("vreemde",1,false,2,"nilfgaard"));
            emoireNilfgaardian.add(new Medic("archer support",1,false,1,"nilfgaard"));
            emoireNilfgaardian.add(new Medic("archer support 1",1,false,1,"nilfgaard"));
            emoireNilfgaardian.add(new Cow("cow",1,false,0,"neutral"));
            emoireNilfgaardian.add(new Spy("mysterious elf",1,false,0,"neutral"));
            emoireNilfgaardian.add(new Medic("siege support ",1,false,0,"nilfgaard"));
        }
    }
    public static class Monsters {
        public static ArrayList<Card> monsterCards = new ArrayList<>();
        static {
            monsterCards.add(new Decoy("special horn", 3 , true, 0, "special"));
            monsterCards.add(new Horn("special decoy", 3 , true, 0, "special"));
            monsterCards.add(new Mardroeme("special mardroeme", 3 , true, 0, "special"));
            monsterCards.add(new Scorch("scorch", 3 , true, 0, "special"));
            monsterCards.add(new Card("frost", 3 , true, 0, "weather"));
            monsterCards.add(new Card("clear", 2 , true, 0, "weather"));
            monsterCards.add(new Card("fog", 3 , true, 0, "weather"));
            monsterCards.add(new Card("storm", 3 , true, 0, "weather"));
            monsterCards.add(new Card("rain", 2 , true, 0, "weather"));
            monsterCards.add(new Card("ciri", 1 , false, 15, "neutral"));
            monsterCards.add(new Card("geralt", 1 , false, 15, "neutral"));
            monsterCards.add(new Card("draug", 1 , false, 10, "neutral"));
            monsterCards.add(new Card("imlerith", 1 , false, 10, "neutral"));
            monsterCards.add(new Card("leshen", 1 , false, 10, "neutral"));
            monsterCards.add(new Card("kayran", 1 , false, 8, "neutral"));
            monsterCards.add(new Scorch("toad", 1 , false, 7, "monsters"));
            monsterCards.add(new Card("triss", 1 , false, 7, "neutral"));
            monsterCards.add(new Scorch("villentretenmerth", 1 , false, 7, "neutral"));
            monsterCards.add(new Medic("yennefer", 1 , false, 7, "neutral"));
            monsterCards.add(new Muster("arachas behemos", 1 , false, 6, "monsters"));
            monsterCards.add(new Muster("witch velen", 1 , false, 6, "monsters"));
            monsterCards.add(new Muster("witch velen 1", 1 , false, 6, "monsters"));
            monsterCards.add(new Muster("witch velen 2", 1 , false, 6, "monsters"));
            monsterCards.add(new Card("earth elemental", 1 , false, 6, "monsters"));
            monsterCards.add(new Card("fiend", 1 , false, 6, "monsters"));
            monsterCards.add(new Card("fire elemental", 1 , false, 6, "monsters"));
            monsterCards.add(new MoralBoost("olgierd", 1 , false, 6, "neutral"));
            monsterCards.add(new Card("vesemir", 1 , false, 6, "neutral"));
            monsterCards.add(new Card("emiel", 1 , false, 5, "neutral"));
            monsterCards.add(new Card("forktail", 1 , false, 5, "monsters"));
            monsterCards.add(new Card("frightener", 1 , false, 5, "monsters"));
            monsterCards.add(new Card("gravehag", 1 , false, 5, "monsters"));
            monsterCards.add(new Card("griffin", 1 , false, 5, "monsters"));
            monsterCards.add(new Card("frost giant", 1 , false, 5, "monsters"));
            monsterCards.add(new Card("mighty maiden", 1 , false, 5, "monsters"));
            monsterCards.add(new Muster("katakan", 1 , false, 5, "monsters"));
            monsterCards.add(new Card("werewolf", 1 , false, 5, "monsters"));
            monsterCards.add(new Card("zoltan", 1 , false, 5, "neutral"));
            monsterCards.add(new Muster("arachas", 1 , false, 4, "monsters"));
            monsterCards.add(new Muster("arachas 1", 1 , false, 4, "monsters"));
            monsterCards.add(new Muster("arachas 2", 1 , false, 4, "monsters"));
            monsterCards.add(new Card("poroniec", 1 , false, 4, "monsters"));
            monsterCards.add(new Muster("gaunter odimm darkness", 3 , false, 4, "neutral"));
            monsterCards.add(new Muster("bruxa", 1 , false, 4, "monsters"));
            monsterCards.add(new Muster("ekkima", 1 , false, 4, "monsters"));
            monsterCards.add(new Muster("fleder", 1 , false, 4, "monsters"));
            monsterCards.add(new Muster("garkain", 1 , false, 4, "monsters"));
            monsterCards.add(new Agile("celaeno harpy", 1 , false, 2, "monsters"));
            monsterCards.add(new Card("cockatrice", 1 , false, 2, "monsters"));
            monsterCards.add(new Horn("dendelion", 1 , false, 2, "neutral"));
            monsterCards.add(new Card("endrega", 1 , false, 2, "monsters"));
            monsterCards.add(new Card("fogling", 1 , false, 2, "monsters"));
            monsterCards.add(new Card("gargoyle", 1 , false, 2, "monsters"));
            monsterCards.add(new Muster("gaunter odimm", 1 , false, 2, "neutral"));
            monsterCards.add(new Agile("harpy", 1 , false, 2, "monsters"));
            monsterCards.add(new Muster("nekker", 1 , false, 2, "monsters"));
            monsterCards.add(new Muster("nekker 1", 1 , false, 2, "monsters"));
            monsterCards.add(new Muster("nekker 2", 1 , false, 2, "monsters"));
            monsterCards.add(new Card("wyvern", 1 , false, 2, "monsters"));
            monsterCards.add(new Muster("ghoul", 1 , false, 1, "monsters"));
            monsterCards.add(new Muster("ghoul 1", 1 , false, 1, "monsters"));
            monsterCards.add(new Muster("ghoul 2", 1 , false, 1, "monsters"));
            monsterCards.add(new Cow("cow", 1 , false, 0, "neutral"));
            monsterCards.add(new Spy("mysterious elf", 1 , false, 0, "neutral"));
        }
        public static class Scoiatael{

        }
        public static class Skellige{

        }
        public static ArrayList<Card> getMonsterCards() {
            return monsterCards;
        }
    }
}
