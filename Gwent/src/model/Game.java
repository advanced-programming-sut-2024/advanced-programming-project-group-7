package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Game {
    private Date Date;
    private String[] players;
    private Integer[][] roundsPoints=new Integer[3][2];
    private int[] finalPoints=new int[2];
    private User winner;

    public Game(String[] players) {
        this.players = players;
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

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public java.util.Date getDate() {
        return Date;
    }

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
