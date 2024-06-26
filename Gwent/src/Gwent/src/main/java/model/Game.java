package model;

import javafx.scene.Group;
import javafx.scene.layout.HBox;
import view.GameLauncher;

import java.time.LocalDate;

public class Game {
    public HBox selectedBox;
    public HBox playerHand;

    public Game(GameLauncher gameLauncher) {
        this.playerHand = gameLauncher.playerHand;
    }
    public Group cardGroup = new Group();
    private LocalDate date= LocalDate.now();
    private Integer[][] roundsPoints=new Integer[][]{{20,10},{10,20},{20,10}};
    private int[] finalPoints=new int[]{50,40};
    private User winner=User.getLoggedInUser();

    public void setRoundsPoints(Integer[][] roundsPoints) {
        this.roundsPoints = roundsPoints;
    }

    public void setFinalPoints(int[] finalPoints) {
        this.finalPoints = finalPoints;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }


    public Integer[][] getRoundsPoints() {
        return roundsPoints;
    }

    public User getWinner() {
        return winner;
    }

    public int[] getFinalPoints() {
        return finalPoints;
    }
}
