package model;

import java.util.ArrayList;

public class Faction {
    private String descriptions;
    private String lgPath = "/Images/lg/faction_monsters.jpg";
    private static ArrayList<Faction> factions = new ArrayList<>();
    static {
        factions.add(new Monsters("hi there"));
    }

    public Faction(String descriptions) {
        this.descriptions = descriptions;
    }

    public static ArrayList<Faction> getFactions() {
        return factions;
    }

    public String getDescription() {
        return descriptions;
    }

    public String getLgPath() {
        return  lgPath;
    }
}
