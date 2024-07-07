package model;

import java.time.LocalDate;

public class BattleInfo {
    private LocalDate date= LocalDate.now();
    private int[][] roundsPoints=new int[3][2];
    private int[] finalPoints=new int[2];
    private String winner;
    private String opponent="amir";

    public BattleInfo(String opponent, LocalDate date, int[][]roundsPoints, int[] finalPoints, String winner) {
        this.opponent = opponent;
        this.date = date;
        this.roundsPoints = roundsPoints;
        this.finalPoints = finalPoints;
        this.winner = winner;
    }

    public LocalDate getDate() {
        return date;
    }

    public int[][] getRoundsPoints() {
        return roundsPoints;
    }

    public int[] getFinalPoints() {
        return finalPoints;
    }

    public String getWinner() {
        return winner;
    }

    public String getOpponent() {
        return opponent;
    }
}
