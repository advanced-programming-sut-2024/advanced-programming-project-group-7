package model.leaders;

import model.Leader;
import model.User;

import java.util.ArrayList;

public class EmpireNilfgaardiansLeaders extends Leader {
    public EmpireNilfgaardiansLeaders(String leaderName, String description) {
        super(leaderName, description);
    }
    private static final ArrayList<Leader> empireNilfgaardiansLeader=new ArrayList<>();
    static {
        empireNilfgaardiansLeader.add(new EmpireNilfgaardiansLeaders("emhyr silver","pick a torrential rain card from your deck and play it instantly"));
        empireNilfgaardiansLeader.add(new EmpireNilfgaardiansLeaders("emhyr copper","look at 3 random cards from your opponent's hand"));
        empireNilfgaardiansLeader.add(new EmpireNilfgaardiansLeaders("emhyr bronze","cansel your opponent's Leader ability"));
        empireNilfgaardiansLeader.add(new EmpireNilfgaardiansLeaders("emhyr gold","draw a card from your opponent's discard pile"));
        empireNilfgaardiansLeader.add(new EmpireNilfgaardiansLeaders("emhyr invader of the north","abilities that restore a unit to the battlefield restore a randomly-chosen unit.affects both players."));
    }
    @Override
    public void generalAbility() {
    }
    @Override
    public void specialAbility(User user) {
    }
}
