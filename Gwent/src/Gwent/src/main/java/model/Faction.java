package model;

import model.factions.*;

import java.util.ArrayList;

public class Faction {
    private final String description;
    protected String factionName;
    private static ArrayList<Faction>factions=new ArrayList<>();
    static {
        factions.add(new NorthernRealms("draw a card from your deck whenever you win a round"));
        factions.add(new EmpireNilfgaardian("wins any round that ends in draw"));
        factions.add(new Monsters("keeps a random unit card after each round"));
        factions.add(new Scoiatael("decides who takes first turn"));
        factions.add(new Skellige("two random cards from the graveyard are placed on the battlefield at the start of the third round"));
    }
    public Faction(String descriptions) {
        this.description=descriptions;
    }
    public boolean isFactionNameValid(String factionName){
        return false;
    }
    public static ArrayList<Faction> getFactions() {
        return factions;
    }
    public String getDescription() {
        return description;
    }
    public String getLgPath(){
        return "/Images/lg/" +"faction_" + this.factionName + ".jpg";
    }

    public String getFactionName() {
        return factionName;
    }
}
