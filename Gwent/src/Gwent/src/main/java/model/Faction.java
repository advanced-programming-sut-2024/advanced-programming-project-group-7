package model;

import java.util.ArrayList;

public class Faction {
    private static String[] factionsName={"",""};
    public boolean isFactionNameValid(String factionName){
        return false;
    }
    public class Monsters {
        public static ArrayList<Card> monsterCards = new ArrayList<>();

        static {
            monsterCards.add(new Card("fiend", 5 , false, 5, "monsters"));
        }
        public static ArrayList<Card> getMonsterCards() {
            return monsterCards;
        }
    }
}
