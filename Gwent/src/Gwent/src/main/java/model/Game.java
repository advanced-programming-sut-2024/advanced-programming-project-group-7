package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Game {
    private LocalDate date= LocalDate.now();
    private String[] players={User.getLoggedInUser().getUsername(),"amir"};
    private Integer[][] roundsPoints=new Integer[][]{{20,10},{10,20},{20,10}};
    private int[] finalPoints=new int[]{50,40};
    private User winner=User.getLoggedInUser();

    public Game(String[] players) {this.players = players;
    }

    public void setPlayers(String[] players) {
        this.players = players;
    }

    public void setRoundsPoints(Integer[][] roundsPoints) {
        this.roundsPoints = roundsPoints;
    }

    public void setFinalPoints(int[] finalPoints) {
        this.finalPoints = finalPoints;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

//    public void setDate(java.util.Date date) {
//        Date = date;
//    }
//
//    public java.util.Date getDate() {
//        return Date;
//    }

    public String[] getPlayers() {
        return players;
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
