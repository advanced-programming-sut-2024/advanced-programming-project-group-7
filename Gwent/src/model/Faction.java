package model;

import java.util.HashMap;

public class Faction {

    private String factionName;

    private HashMap<Card, Integer> cardsOfFaction = new HashMap<>();

    private String[] leaders = new String[4];

    public HashMap<Card, Integer> getCardsOfFaction() {
        return cardsOfFaction;
    }
}
