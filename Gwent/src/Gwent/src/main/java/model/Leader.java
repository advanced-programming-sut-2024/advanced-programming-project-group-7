package model;

import java.util.ArrayList;
import java.util.Map;

public abstract class Leader {

    private String leaderName;
    private String description;
    private String lgPath;

    public Leader(String leaderName, String description) {
        this.leaderName = leaderName;
        this.description = description;
        this.lgPath = lgPathMaker();
    }

    private String lgPathMaker() {
        return "/Images/lg/monsters_eredin_bronze.jpg"; // todo fill it
    }

    public String getLeaderName() {
        return leaderName;
    }

    public String getDescription() {
        return description;
    }

    public String getLgPath() {
        return lgPath;
    }

    public abstract void generalAbility();

    public abstract void specialAbility(User user);

    public static class MonsterLeader extends Leader {

        private static ArrayList<Leader> monsterLeaders = new ArrayList<>();
        static {
            monsterLeaders.add(new MonsterLeader("ALI","hi bitches"));
            System.out.println(monsterLeaders.size());
        }

        public MonsterLeader(String leaderName, String description) {
            super(leaderName, description);
        }

        public static ArrayList getLeaders() {
            return monsterLeaders;
        }


        @Override
        public void generalAbility(){

        }

        @Override
        public void specialAbility(User user){

        }

        public static ArrayList<Leader> getMonsterLeaders() {
            return monsterLeaders;
        }
    }
}
