package model;

import java.util.ArrayList;

public abstract class Leader {

    private String leaderName;
    private String description;
    private String lgPath;
    private  String factionName;

    public Leader(String leaderName, String description,String factionName) {
        this.leaderName = leaderName;
        this.description = description;
        this.lgPath = lgPathMaker(leaderName, factionName);
        this.factionName=factionName;
    }

    private static String lgPathMaker(String leaderName, String factionName) {
        return "/Images/lg/" + factionName+"_" + leaderName.replaceAll(" ", "_") + ".jpg";
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
            monsterLeaders.add(new MonsterLeader("ALI","hi bitches",""));
            System.out.println(monsterLeaders.size());
        }

        public MonsterLeader(String leaderName, String description, String faction) {
            super(leaderName, description,faction);
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
