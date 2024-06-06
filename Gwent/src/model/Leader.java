package model;

public class Leader {

    private String leaderName;

    public Leader(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public class MonsterLeader extends Leader {


        public MonsterLeader(String leaderName) {
            super(leaderName);
        }

        public void generalAbility(){

        }
        public void specialAbility(){

        }
    }

    public class NilfgaardianLeader extends Leader {


        public NilfgaardianLeader(String leaderName) {
            super(leaderName);
        }
        public void generalAbility(){

        }
        public void specialAbility(){

        }
    }

    public class NilfgaardianLeaer extends Leader {


        public NilfgaardianLeaer(String leaderName) {
            super(leaderName);
        }
        public void generalAbility(){

        }
        public void specialAbility(){

        }
    }

    public class NorthenRealmsLeader extends Leader {


        public NorthenRealmsLeader(String leaderName) {
            super(leaderName);
        }
        public void generalAbility(){

        }
        public void specialAbility(){

        }
    }

    public class SkelligLeader extends Leader {


        public SkelligLeader(String leaderName) {
            super(leaderName);
        }
        public void generalAbility(){

        }
        public void specialAbility(){

        }
    }
}
