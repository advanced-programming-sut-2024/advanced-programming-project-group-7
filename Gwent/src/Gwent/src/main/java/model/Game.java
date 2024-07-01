package model;

import controller.Client;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import view.GameLauncher;

import java.time.LocalDate;
import java.util.ArrayList;

public class Game {
    private final HBox playerThirdRowHorn;
    private final HBox playerSecondRowHorn;
    private final HBox playerFirstRowHorn;
    private final HBox playerFifthRowHorn;
    private final HBox playerFourthRowHorn;
    private final HBox playerSixthRowHorn;
    public Label totalPowerOpponent;
    public Label totalRow1PowerOpponent;
    public Label totalRow2PowerOpponent;
    public Label totalRow3PowerOpponent;
    public Rectangle highScoreOpponent;
    public Rectangle life1;
    public Rectangle life2;
    public Rectangle life1Opponent;
    public Rectangle life2Opponent;
    public HBox weatherBox;
    public HBox playerFourthRow;
    public HBox selectedBox;
    public HBox playerHand;
    public HBox remainingDeck;
    public HBox graveYard = new HBox(); //don't add hero
    public HBox playerFirstRow;
    public Label totalRow3Power;
    public Label totalRow1Power;
    public HBox playerThirdRow;
    public HBox playerSecondRow;
    public Label totalRow2Power;
    public Label totalPower;
    public Card selected;
    public HBox playerFifthRow;
    public HBox playerSixthRow;
    public ArrayList<HBox> hBoxes;
    public Client client;
    public String opponentName;

    public Game(GameLauncher gameLauncher) {
        this.playerHand = gameLauncher.playerHand;
        this.playerThirdRow = gameLauncher.playerThirdRow;
        this.playerSecondRow = gameLauncher.playerSecondRow;
        this.playerFirstRow = gameLauncher.playerFirstRow;
        this.playerFourthRow =gameLauncher.playerFourthRow;
        this.playerFifthRow = gameLauncher.playerFifthRow;
        this.playerSixthRow = gameLauncher.playerSixthRow;
        this.playerThirdRowHorn = gameLauncher.playerThirdRowHorn;
        this.playerSecondRowHorn = gameLauncher.playerSecondRowHorn;
        this.playerFirstRowHorn = gameLauncher.playerFirstRowHorn;
        this.playerFourthRowHorn =gameLauncher.playerFourthRowHorn;
        this.playerFifthRowHorn = gameLauncher.playerFifthRowHorn;
        this.playerSixthRowHorn = gameLauncher.playerSixthRowHorn;

    }
    public Group cardGroup = new Group();
    private final LocalDate date= LocalDate.now();
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

    public void removeDominantCard(){
        //todo
    }

    public void spyACard() {
        //todo
    }

    public void handleBond(HBox selectedBox, Card card, Game game) {

    }

    public void calculateLabels() {
        int result = 0;
        for (HBox hBox : hBoxes) {
            for (Node card : hBox.getChildren()) {
                ((Card) card).calculatePower();
            }
        }
//        totalRow1Power.setText(String.valueOf(result));
//        totalRow2Power.setText(String.valueOf(result));
//        totalRow3Power.setText(String.valueOf(result));
//        totalRow3PowerOpponent.setText(String.valueOf(result));
//        totalRow2PowerOpponent.setText(String.valueOf(result));
//        totalRow3Power.setText(String.valueOf(result));
//        totalPowerOpponent.setText(String.valueOf(Integer.parseInt(totalRow2PowerOpponent.getText())
//                + Integer.parseInt(totalRow1PowerOpponent.getText())
//                + Integer.parseInt(totalRow3PowerOpponent.getText())));
//        totalPower.setText(String.valueOf(Integer.parseInt(totalRow1Power.getText())
//                + Integer.parseInt(totalRow2Power.getText())
//                + Integer.parseInt(totalRow3Power.getText())));
    }
    public void placeCard(Card card, HBox hBox){
        hBox.getChildren().add(card);
        String st = "card:" + User.getLoggedInUser().currentOponentName + ":" + card.getCardName() + "."
                + card.getCountOfCard() + "." + card.isSpecial() + "." + card.getPower() + "." + card.getFactionName()
                + "." + card.rows + "." + card.isHero();
        System.out.println(st);
        client.sendMessage(st);
        calculateLabels();
    }
    public void enemyPlaceCard(Card finalCard, HBox hBox) {
        hBox.getChildren().add(finalCard);
        calculateLabels();
    }
    public void newRound(Game game) {
        if (Integer.parseInt(totalPower.getText()) < Integer.parseInt(totalPowerOpponent.getText())) {
            life2.setVisible(false);
        } else if (Integer.parseInt(totalPower.getText()) > Integer.parseInt(totalPowerOpponent.getText())) {
            life2Opponent.setVisible(false);
        } else {
            life2Opponent.setVisible(false);
            life2.setVisible(false);
        }
        playerFirstRow.getChildren().clear(); // todo have to go to graveyard
        playerSecondRow.getChildren().clear();
        playerThirdRow.getChildren().clear();
        playerFourthRow.getChildren().clear();
        playerFifthRow.getChildren().clear();
        playerSixthRow.getChildren().clear();
        playerFirstRowHorn.getChildren().clear(); // todo have to go to graveyard
        playerSecondRowHorn.getChildren().clear();
        playerThirdRowHorn.getChildren().clear();
        playerFourthRowHorn.getChildren().clear();
        playerFifthRowHorn.getChildren().clear();
        playerSixthRowHorn.getChildren().clear();
        totalPower.setText("0");
        totalPowerOpponent.setText("0");
        totalRow3PowerOpponent.setText("0");
        totalRow2PowerOpponent.setText("0");
        totalRow1PowerOpponent.setText("0");
        totalRow3Power.setText("0");
        totalRow2Power.setText("0");
        totalRow1Power.setText("0");
    }

}
