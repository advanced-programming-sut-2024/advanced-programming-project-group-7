package model;

import java.util.ArrayList;

public class GameTable {

    private Card[] weather = new Card[3];
    private Card[][] GraveYards= new Card[60][2];
    private Card[][][] gameRows = new Card[3][20][2];
    private Integer[] playerLife= {2,2};
    private Integer[] remainingCards=new Integer[2];
    private Leader [] Leaders=new Leader[2];
    private boolean[] passState={false,false};
    private Integer[][] totalPoints={{0,0},{0,0},{0,0},{0,0}};
    private String[]
            players;


//todo constructor
    public void clearWeather(){}
    public int calculator(Card card){//todo
        return 0;
    }
    public void setWeather(Card card){}

    public Card[] getWeather() {
        return weather;
    }

    public Card[][] getGraveYards() {
        return GraveYards;
    }

    public Card[][][] getGameRows() {
        return gameRows;
    }

    public Integer[] getPlayerLife() {
        return playerLife;
    }

    public Integer[] getRemainingCards() {
        return remainingCards;
    }

    public Leader[] getLeaders() {
        return Leaders;
    }

    public void setPlayerLife(Integer[] playerLife) {
        this.playerLife = playerLife;
    }

    public void setRemainingCards(Integer[] remainingCards) {
        this.remainingCards = remainingCards;
    }
    public boolean checkPlayersLives(GameTable gameTable){return false;}

    public Boolean haveBothPlayersPassed(GameTable gameTable) {return false;
    }
}
