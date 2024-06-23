package model.leaders;

import model.Leader;
import model.User;

import java.util.ArrayList;

public class MonstersLeaders extends Leader {
    public MonstersLeaders(String leaderName, String description) {
        super(leaderName, description);
    }
    private static final ArrayList<Leader> monsterLeaders=new ArrayList<>();
    static {
        monsterLeaders.add(new MonstersLeaders("eredin silver","double the strength of all your "));
        monsterLeaders.add(new MonstersLeaders("eredin",""));
        monsterLeaders.add(new MonstersLeaders("eredin",""));
        monsterLeaders.add(new MonstersLeaders("eredin",""));
        monsterLeaders.add(new MonstersLeaders("eredin",""));
    }


    @Override
    public void generalAbility() {

    }

    @Override
    public void specialAbility(User user) {

    }
}
