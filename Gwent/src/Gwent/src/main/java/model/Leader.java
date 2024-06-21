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

    public class NilfgaardianLeader extends Leader {


        public NilfgaardianLeader(String leaderName, String description) {
            super(leaderName, description);
        }

        @Override
        public void generalAbility(){

        }
        @Override
        public void specialAbility(User user){

        }
    }

    public class NilfgaardianLeaer extends Leader {


        public NilfgaardianLeaer(String leaderName, String description) {
            super(leaderName, description);
        }

        @Override

        public void generalAbility(){

        }
        @Override

        public void specialAbility(User user){

        }
    }

    public class NorthenRealmsLeader extends Leader {


        public NorthenRealmsLeader(String leaderName, String description) {
            super(leaderName, description);
        }

        @Override

        public void generalAbility(){

        }
        @Override

        public void specialAbility(User user){

        }
    }

    public class SkelligLeader extends Leader {


        public SkelligLeader(String leaderName, String description) {
            super(leaderName, description);
        }

        @Override

        public void generalAbility(){

        }
        @Override

        public void specialAbility(User user){

        }
    }
}
