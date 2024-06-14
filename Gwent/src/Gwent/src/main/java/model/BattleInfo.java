package model;

import java.time.LocalDate;

public class BattleInfo {
    private LocalDate date= LocalDate.now();
   // private String[] players={User.getLoggedInUser().getUsername(),"amir"};
    private int[][] roundsPoints=new int[3][2];//{{20,10},{10,20},{20,10}};
    private int[] finalPoints=new int[2];//{50,40};
    private User winner=User.getLoggedInUser();
    String opponent="amir";

    public BattleInfo(String opponent, LocalDate date, int[][]roundsPoints, int[] finalPoints, User winner) {
        this.opponent = opponent;
        this.date = date;
        this.roundsPoints = roundsPoints;
        this.finalPoints = finalPoints;
        this.winner = winner;
    }
}
