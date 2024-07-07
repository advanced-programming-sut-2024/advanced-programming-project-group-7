package model.leaders;

import model.Leader;
import model.User;

import java.util.ArrayList;

public class NorthernRealmsLeaders extends Leader{
    public NorthernRealmsLeaders(String leaderName, String description,String faction) {
        super(leaderName, description,faction);

    }

    @Override
    public void generalAbility(){
    }
    @Override
    public void specialAbility(User user){
    }
}

