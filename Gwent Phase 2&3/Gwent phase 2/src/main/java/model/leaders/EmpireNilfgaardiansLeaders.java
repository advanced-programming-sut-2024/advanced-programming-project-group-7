package model.leaders;

import model.Leader;
import model.User;

import java.util.ArrayList;

public class EmpireNilfgaardiansLeaders extends Leader {
    public EmpireNilfgaardiansLeaders(String leaderName, String description,String factionname) {
        super(leaderName, description,factionname);
    }
    @Override
    public void generalAbility() {
    }
    @Override
    public void specialAbility(User user) {
    }
}
