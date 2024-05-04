package controller;

import model.Faction;
import model.GameTable;

public class GameMenuController {
    public void createGame(String username){}
    public void showFactions(){}
    public void passTurn(GameTable gameTable) {
        gameTable.haveBothPlayersPassed(gameTable);
        gameTable.checkPlayersLives(gameTable);
    }
    public void showCards(Faction userFaction){}
}
