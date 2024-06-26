package model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public abstract class Leader extends Pane {

    private String leaderName;
    private String description;
    private String lgPath;
    private  String factionName;
    public Rectangle rectangle = new Rectangle();


    public Leader(String leaderName, String description,String factionName) {
        this.leaderName = leaderName;
        this.description = description;
        this.lgPath = lgPathMaker(leaderName, factionName);
        this.factionName=factionName;
        this.setHeight(98);
        this.setWidth(70);
        rectangle.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource(this.getLgPath()).toExternalForm()))));
        rectangle.setHeight(98);
        rectangle.setWidth(70);
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
