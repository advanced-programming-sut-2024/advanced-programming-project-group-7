package model;

import controller.Client;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.cards.*;
import model.leaders.*;
import view.GameLauncher;
import view.animations.AnnihilationAnimation;
import view.animations.CardPlacementAnimation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

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
    public EnhancedHBox weatherBox;
    public EnhancedHBox playerFourthRow;
    public EnhancedHBox selectedBox;
    public EnhancedHBox playerHand;
    public EnhancedHBox remainingDeck;
    public EnhancedHBox graveyard = new EnhancedHBox(); //don't add hero
    public EnhancedHBox playerFirstRow;
    public Label totalRow3Power;
    public Label totalRow1Power;
    public EnhancedHBox playerThirdRow;
    public EnhancedHBox playerSecondRow;
    public Label totalRow2Power;
    public Label totalPower;
    public Card selected;
    public EnhancedHBox playerFifthRow;
    public EnhancedHBox playerSixthRow;
    public ArrayList<EnhancedHBox> hBoxes;
    public Client client;
    public String opponentName;
    public GameLauncher gameLauncher;
    public Rectangle highScorePlayer = new Rectangle() ;

    public Game(GameLauncher gameLauncher) {
        this.gameLauncher = gameLauncher;
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
        this.weatherBox = gameLauncher.weatherBox;
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

    public int[] getFinalPoints() {return finalPoints;}

    public void removeDominantCardsAllTable(int j){
        ArrayList<Card> dominantCards = new ArrayList<>();
        int max = 0;
        if(j==0) {
            for (int i = 0; i <= 6; i++) {
                Iterator<Node> iterator = hBoxes.get(i).getChildren().iterator();
                while (iterator.hasNext()) {
                    Node card1 = iterator.next();
                    Card card2 = (Card) card1;
                    if (!card2.isHero()) {
                        if (getCurrentPower(card2) > max) {
                            dominantCards.clear();
                            dominantCards.add(card2);
                            max = getCurrentPower(card2);
                        } else if (getCurrentPower(card2) == max) {
                            dominantCards.add(card2);
                        }
                    }
                }
            }
        }else {
            Iterator<Node> iterator = hBoxes.get(j).getChildren().iterator();
            while (iterator.hasNext()) {
                Node card1 = iterator.next();
                Card card2 = (Card) card1;
                if (!card2.isHero()) {
                    if (getCurrentPower(card2) > max) {
                        dominantCards.clear();
                        dominantCards.add(card2);
                        max = getCurrentPower(card2);
                    } else if (getCurrentPower(card2) == max) {
                        dominantCards.add(card2);
                    }
                }
            }
        }
        for(Card card:dominantCards){
            removeCard(card);
        }
        calculateLabels();
    }

    private void removeCard(Card card) {
        EnhancedHBox box = (EnhancedHBox) card.getParent();
//        box.getChildren().remove(card);
        AnnihilationAnimation animation = new AnnihilationAnimation(box, card, this);
        animation.play();
        gameLauncher.graveyardCard.add(card);
    }
    public void spyACard() {
        if(!gameLauncher.reservedCards.isEmpty()) {
            Card card = gameLauncher.reservedCards.get(0);
            playerHand.getChildren().add(card);//todo why after adding we can't work
            gameLauncher.reservedCards.remove(card);
        }
    }
    public void handleBond(EnhancedHBox selectedBox, Card card, Game game) {
        for (Node card1 : selectedBox.getChildren()){
            if ( card1 instanceof TightBond && !card1.equals(card)) {//todo maybe wrong bond
                ((Card) card1).bondLevel++;
                card.bondLevel++;
            }
        }
        game.calculateLabels();
    }

    public void calculateLabels() {
        for (Node card : hBoxes.get(0).getChildren()) {
            System.out.println(((Card) card).getCardName());
            switch (((Card) card).getCardName()) {
                case "frost" -> {
                    playerThirdRow.badConditionEffect.setVisible(true);
                    playerThirdRow.badCondition = true;
                    playerFourthRow.badConditionEffect.setVisible(true);
                    playerFourthRow.badCondition = true;
                }
                case "fog" -> {
                    playerSecondRow.badConditionEffect.setVisible(true);
                    playerSecondRow.badCondition = true;
                    playerFifthRow.badConditionEffect.setVisible(true);
                    playerFifthRow.badCondition = true;
                }
                case "rain" -> {
                    playerFirstRow.badConditionEffect.setVisible(true);
                    playerFirstRow.badCondition = true;
                    playerSixthRow.badConditionEffect.setVisible(true);
                    playerSixthRow.badCondition = true;
                }
                case "storm" -> {
                    playerSecondRow.badConditionEffect.setVisible(true);
                    playerSecondRow.badCondition = true;
                    playerFifthRow.badConditionEffect.setVisible(true);
                    playerFifthRow.badCondition = true;
                    playerFirstRow.badConditionEffect.setVisible(true);
                    playerFirstRow.badCondition = true;
                    playerSixthRow.badConditionEffect.setVisible(true);
                    playerSixthRow.badCondition = true;
                }
            }
        }
        for (EnhancedHBox hBox : hBoxes)
            hBox.badConditionEffect.toBack();
        for (int i = 1; i < 7; i++) {
            EnhancedHBox hBox = hBoxes.get(i);
            if (hBox.hornBox.getChildren().size() == 1)
                hBox.isDoubled = true;
            for (Node card : hBox.getChildren()) {
                if (card instanceof Horn)
                    hBox.isDoubled = true;
                if (card instanceof MoralBoost)
                    hBox.moralBoostLevel++;
            }
        }
        int totalSumPlayer = 0;
        int totalSumOpponent = 0;
        for (int i = 0; i < 7; i++) {
            EnhancedHBox hBox = hBoxes.get(i);
            int sum = 0;
            for (Node card : hBox.getChildren()) {
                int currentPower = 0;
                if (!((Card) card).isHero()){
                    currentPower = hBox.badCondition ? 1 : ((Card) card).getPower();
                    currentPower += hBox.moralBoostLevel;
                    if (hBox.isDoubled|| ((Card) card).isDoubeld)
                        currentPower *= 2;
                }else
                    currentPower = ((Card) card).getPower();
                ((Card) card).setCardLabel(currentPower);
                sum += currentPower;
            }
            hBox.powerSum.setText(String.valueOf(sum));
            if (i > 3)
                totalSumPlayer += sum;
            else
                totalSumOpponent += sum;
        }
        totalPowerOpponent.setText(String.valueOf(totalSumOpponent));
        totalPower.setText(String.valueOf(totalSumPlayer));
        if (totalSumPlayer > totalSumOpponent) {
            highScoreOpponent.setVisible(false);
            highScorePlayer.setVisible(true);
        } else if (totalSumPlayer < totalSumOpponent) {
            highScoreOpponent.setVisible(true);
            highScorePlayer.setVisible(false);
        } else {
            highScoreOpponent.setVisible(true);
            highScorePlayer.setVisible(true);
        }
    }
    public void placeCard(Card card, EnhancedHBox hBox){
        try {
            if (card.getCardName().equals("clear")) {
                playerThirdRow.badConditionEffect.setVisible(false);
                playerThirdRow.badCondition = false;
                playerFourthRow.badConditionEffect.setVisible(false);
                playerFourthRow.badCondition = false;
                playerSecondRow.badConditionEffect.setVisible(false);
                playerSecondRow.badCondition = false;
                playerFifthRow.badConditionEffect.setVisible(false);
                playerFifthRow.badCondition = false;
                playerFirstRow.badConditionEffect.setVisible(false);
                playerFirstRow.badCondition = false;
                playerSixthRow.badConditionEffect.setVisible(false);
                playerSixthRow.badCondition = false;
            } else {
                hBox.getChildren().add(card);
                calculateLabels();
                gameLauncher.playerHandMouseSetter();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        String st = "card:" + User.getLoggedInUser().currentOponentName + ":" + card.getCardName() + "."
//                + card.getCountOfCard() + "." + card.isSpecial() + "." + card.getPower() + "." + card.getFactionName()
//                + "." + card.rows + "." + card.isHero();
//        System.out.println(st);
//        client.sendMessage(st); todo onlination
    }
    public void enemyPlaceCard(Card finalCard, EnhancedHBox hBox) {
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

    public void medicACard() {
        if(!gameLauncher.graveyardCard.isEmpty()) {
            Card card = gameLauncher.graveyardCard.get(0);
            playerHand.getChildren().add(card);//todo why after adding we can't work
            gameLauncher.graveyardCard.remove(card);
        }
    }
    public int getCurrentPower(Card card){
        EnhancedHBox hBox = (EnhancedHBox) card.getParent();
        for (Node card1 : hBox.getChildren()) {
            if (card1 instanceof Horn)
                hBox.isDoubled = true;
            if (card1 instanceof MoralBoost)
                hBox.moralBoostLevel++;
        }
        int currentPower = 0;
        currentPower = hBox.badCondition ? 1 : (card).getPower();
        currentPower += hBox.moralBoostLevel;
        if (hBox.isDoubled)
            currentPower *= 2;
        return currentPower;
    }

    public void handleLeader(Leader leader) {
        if(leader instanceof NorthernRealmsLeaders)handleNorthenRealmsLeader(leader);
        else if (leader instanceof MonstersLeaders) handleMonstersLeadersLeader(leader);
        else if (leader instanceof EmpireNilfgaardiansLeaders) handleEmpireNilfgaardiansLeader(leader);
        else if (leader instanceof ScoiataelLeaders) handleScoiataelLeaders(leader);
        else if (leader instanceof SkelligeLeaders) handleSkelligeLeaders(leader);

    }

    private void handleSkelligeLeaders(Leader leader) {
        System.out.println("S");
        if (leader.getLeaderName().equals("crach an craite")){
            if(!gameLauncher.graveyardCard.isEmpty()) {
                for (Card card : gameLauncher.graveyardCard) {
                    gameLauncher.reservedCards.add(card);
                }
                gameLauncher.graveyardCard.clear();
            }

        }else if(leader.getLeaderName().equals("king bran")){

        }

    }

    private void handleScoiataelLeaders(Leader leader) {
        System.out.println("Sc");
        if (leader.getLeaderName().equals("francesca silver")){
            Iterator<Node> iterator = hBoxes.get(3).getChildren().iterator();
            int max=0;
            int sum=0;
            ArrayList<Card> dominantCards = new ArrayList<>();
            while (iterator.hasNext()) {
                Node card1 = iterator.next();
                Card card2 = (Card) card1;
                if(!card2.isHero()) {
                if(getCurrentPower(card2)>max){
                    dominantCards.clear();
                    dominantCards.add(card2);
                    max=getCurrentPower(card2);
                }else if(getCurrentPower(card2)==max){
                    dominantCards.add(card2);
                }
                sum+=getCurrentPower(card2);
                }
            }
            if(sum>=10){
                for(Card card:dominantCards){
                    removeCard(card);
                }
            }
        }else if(leader.getLeaderName().equals("francesca gold")){
            hBoxes.get(6).isDoubled=true;
            calculateLabels();
        }else if(leader.getLeaderName().equals("francesca copper")){//todo changed
            spyACard();

        }else if(leader.getLeaderName().equals("francesca bronze")){
            for(Card card:gameLauncher.reservedCards){
                if(card.getCardName().equals("frost")){
                    hBoxes.get(0).getChildren().add(card);
                    gameLauncher.reservedCards.remove(card);
                    calculateLabels();
                    break;
                }
                calculateLabels();
            }


        }else if (leader.getLeaderName().equals("francesca hope of the aen seidhe")){//todo changed
            for(int i=0;i<=6;i++) {
                Iterator<Node> iterator = hBoxes.get(i).getChildren().iterator();
                while (iterator.hasNext()) {
                    Node card1 = iterator.next();
                    Card card2 = (Card) card1;
                    if (card2 instanceof Agile) {
                        card2.isDoubeld = true;
                    }
                }
            }
            calculateLabels();
        }
    }


    private void handleMonstersLeadersLeader(Leader leader) {
        System.out.println("M");
        if (leader.getLeaderName().equals("eredin silver")){
            hBoxes.get(4).isDoubled=true;
            calculateLabels();
        }else if(leader.getLeaderName().equals("eredin gold")){


        }else if(leader.getLeaderName().equals("eredin copper")){

        }else if(leader.getLeaderName().equals("eredin bronze")){
            Card card = gameLauncher.discardPile.get(0);
            playerHand.getChildren().add(card);
            gameLauncher.discardPile.remove(card);

        }else if (leader.getLeaderName().equals("eredin the treacherous")){//todo not working!
            for(int i=0;i<=6;i++) {
                Iterator<Node> iterator = hBoxes.get(i).getChildren().iterator();
                while (iterator.hasNext()) {
                    Node card1 = iterator.next();
                    Card card2 = (Card) card1;
                    if (card2 instanceof Spy) {
                        card2.isDoubeld = true;
                    }
                }
            }
            calculateLabels();
        }
    }
    private void handleNorthenRealmsLeader(Leader leader) {
        System.out.println("R");
      if (leader.getLeaderName().equals("foltest silver")){
          for(Card card:gameLauncher.reservedCards){
              if(card.getCardName().equals("fog")){
//                 Card card1=new Card("fog", 3 , true, 0, "weather",7,false);
//                 gameLauncher.addCardToPane(card,10,10);
//                  CardPlacementAnimation cardPlacementAnimation=new CardPlacementAnimation(gameLauncher.pane,gameLauncher.game,card1,10,10,200,520);
//                  Pane pane=gameLauncher.pane;
//                  pane.getChildren().add(card);
//                  card.setLayoutY(gameLauncher.sceneY);
//                  card.setLayoutX(gameLauncher.sceneX);
//                  double dx = 200 - gameLauncher.sceneX;
//                  double dy = 300 - gameLauncher.sceneY;
//                  double theta = Math.atan2(dy, dx);
//                  double sinTheta = Math.sin(theta);
//                  double cosTheta = Math.cos(theta);
//                  double vy = sinTheta * 8;
//                  double vx = cosTheta * 8;
//                  CardPlacementAnimation cardPlacementAnimation =new CardPlacementAnimation(pane, this, card, vx, vy, 10,10);
//                  cardPlacementAnimation.play();
//                  cardPlacementAnimation.play();
//                  gameLauncher.pane.getChildren().add(card1);
                  hBoxes.get(0).getChildren().add(card);
                  gameLauncher.reservedCards.remove(card);
                  calculateLabels();
                  break;
              }
          }
      }else if(leader.getLeaderName().equals("foltest gold")){//todo fucked
          Card card=new Card("clear", 2 , true, 0, "weather",7,false);
//          Card card=new Card("frost", 3 , true, 0, "weather",7,false);
//          int endY=200;
//          int endX=100;
////          gameLauncher.addCardToPane(card,endY,endX);
//                  Pane pane=gameLauncher.pane;
//                  pane.getChildren().add(card);
//                  card.setLayoutY(gameLauncher.sceneY);
//                  card.setLayoutX(gameLauncher.sceneX);
//                  double dx = 200 - gameLauncher.sceneX;
//                  double dy = 100 - gameLauncher.sceneY;
//                  double theta = Math.atan2(dy, dx);
//                  double sinTheta = Math.sin(theta);
//                  double cosTheta = Math.cos(theta);
//                  double vy = sinTheta * 8;
//                  double vx = cosTheta * 8;
//                  CardPlacementAnimation cardPlacementAnimation =new CardPlacementAnimation(pane, this, card, vx, vy, 200,100);
//                  cardPlacementAnimation.play();
                  hBoxes.get(0).getChildren().add(card);
          playerThirdRow.badConditionEffect.setVisible(false);
          playerThirdRow.badCondition = false;
          playerFourthRow.badConditionEffect.setVisible(false);
          playerFourthRow.badCondition = false;
          playerSecondRow.badConditionEffect.setVisible(false);
          playerSecondRow.badCondition = false;
          playerFifthRow.badConditionEffect.setVisible(false);
          playerFifthRow.badCondition = false;
          playerFirstRow.badConditionEffect.setVisible(false);
          playerFirstRow.badCondition = false;
          playerSixthRow.badConditionEffect.setVisible(false);
          playerSixthRow.badCondition = false;
//          calculateLabels();
//          placeCard(card,hBoxes.get(0));
          calculateLabels();

      }else if(leader.getLeaderName().equals("foltest copper")){
          hBoxes.get(5).isDoubled=true;
          calculateLabels();

      }else if(leader.getLeaderName().equals("foltest bronze")){//todo better test
          Iterator<Node> iterator = hBoxes.get(1).getChildren().iterator();
          int max=0;
          int sum=0;
          ArrayList<Card> dominantCards = new ArrayList<>();
          while (iterator.hasNext()) {
              Node card1 = iterator.next();
              Card card2 = (Card) card1;
              if(!card2.isHero()) {
                  if (getCurrentPower(card2) > max) {
                      dominantCards.clear();
                      dominantCards.add(card2);
                      max = getCurrentPower(card2);
                  } else if (getCurrentPower(card2) == max) {
                      dominantCards.add(card2);
                  }
                  sum += getCurrentPower(card2);
              }
          }
          if(sum>=10){
              for(Card card:dominantCards){
                  removeCard(card);
              }
          }

      }else if (leader.getLeaderName().equals("foltest son of medell")){//todo test+
          Iterator<Node> iterator = hBoxes.get(2).getChildren().iterator();
          int max=0;
          int sum=0;
          ArrayList<Card> dominantCards = new ArrayList<>();
          while (iterator.hasNext()) {
              Node card1 = iterator.next();
              Card card2 = (Card) card1;
              if(!card2.isHero()) {
                  if (getCurrentPower(card2) > max) {
                      dominantCards.clear();
                      dominantCards.add(card2);
                      max = getCurrentPower(card2);
                  } else if (getCurrentPower(card2) == max) {
                      dominantCards.add(card2);
                  }
                  sum += getCurrentPower(card2);
              }
          }
          if(sum>=10){
              for(Card card:dominantCards){
                  removeCard(card);
              }
          }
      }
    }
    private void handleEmpireNilfgaardiansLeader(Leader leader) {
        System.out.println("Ni");
        if (leader.getLeaderName().equals("emhyr silver")){
            if(!gameLauncher.reservedCards.isEmpty()) {
                for (Card card : gameLauncher.reservedCards) {
                    if (card.getCardName().equals("rain")) {
                        hBoxes.get(0).getChildren().add(card);
                        gameLauncher.reservedCards.remove(card);
                        calculateLabels();
                        break;
                    }
                }
            }
        }else if(leader.getLeaderName().equals("emhyr gold")){


        }else if(leader.getLeaderName().equals("emhyr copper")){

        }else if(leader.getLeaderName().equals("emhyr bronze")){

        }else if (leader.getLeaderName().equals("emhyr invader of the north")){

        }
    }
}
