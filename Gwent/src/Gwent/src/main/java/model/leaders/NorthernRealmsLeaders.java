package model.leaders;

import model.Leader;
import model.User;

import java.util.ArrayList;

public class NorthernRealmsLeaders extends Leader{
    public NorthernRealmsLeaders(String leaderName, String description) {
        super(leaderName, description);
    }
    private final static ArrayList<Leader> northernRealmsLeader=new ArrayList<>();
    static {
        northernRealmsLeader.add(new NorthernRealmsLeaders("foltest silver","pick an impenetrable fog card from your deck and play it instantly"));
        northernRealmsLeader.add(new NorthernRealmsLeaders("foltest gold","clear any weather effects(resulting from biting frost, torrential rain or impenetrable fog cards) in play"));
        northernRealmsLeader.add(new NorthernRealmsLeaders("foltest copper","doubles the strength of all your siege units (unless a commander's horn is also present on that row)."));
        northernRealmsLeader.add(new NorthernRealmsLeaders("foltest bronze","destroy your enemy's strongest siege unit(s) if the combined strength of all his or her siege units is 10 or more."));
        northernRealmsLeader.add(new NorthernRealmsLeaders("foltest son of medell","distroy your enemy's strongest ranged combat unit(s) if the combined strength of all his or her ranged combat units is 10 or more."));
    }
    @Override
    public void generalAbility(){
    }
    @Override
    public void specialAbility(User user){
    }
}

