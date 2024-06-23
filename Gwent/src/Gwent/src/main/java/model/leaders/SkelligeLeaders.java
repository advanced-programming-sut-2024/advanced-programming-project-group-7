package model.leaders;

import model.Leader;
import model.User;

import java.util.ArrayList;

public class SkelligeLeaders extends Leader {
    public SkelligeLeaders(String leaderName, String description) {
        super(leaderName, description);
    }
    private static final ArrayList<Leader> skelligeLeaders=new ArrayList<>();
    static {
        skelligeLeaders.add(new SkelligeLeaders("crach an craite","shuffle all cards from each player's graveyard back into their decks"));
        skelligeLeaders.add(new SkelligeLeaders("king bran","units only lose half their strength in bad weather conditions"));
    }

    @Override
    public void generalAbility(){

    }

    @Override
    public void specialAbility(User user){

    }
}