package model;

import controller.Client;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.cards.Horn;
import model.cards.MoralBoost;
import model.cards.TightBond;
import view.GameLauncher;
import view.LoginMenu;
import view.MainMenu;
import view.PreGameMenu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Game {
    private int[][] roundsPoints={{0,0}, {0,0}, {0,0}};
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
    public ArrayList<Card> graveyard = new ArrayList<>(); //don't add hero
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
    private int yourLives = 2;
    private int opponentLives = 2;
    private int roundCount = 0;
    public Card lastPlacedCard;
    public Card yourLastCard;

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


    public void scorch(int i){
        ArrayList<Card> dominant = new ArrayList<>();
        int maxPower = 0;
        if (i == 1) {
            for (Node card : playerFirstRow.getChildren()) {
                if (Integer.parseInt(((Card) card).powerLabel.getText()) == maxPower) {
                    dominant.add((Card) card);
                } else if (Integer.parseInt(((Card) card).powerLabel.getText()) > maxPower) {
                    dominant.clear();
                    dominant.add((Card) card);
                }
            } for (Node card : playerSixthRow.getChildren()) {
                if (Integer.parseInt(((Card) card).powerLabel.getText()) == maxPower) {
                    dominant.add((Card) card);
                } else if (Integer.parseInt(((Card) card).powerLabel.getText()) > maxPower) {
                    dominant.clear();
                    dominant.add((Card) card);
                }
            }
        } else if (i == 2) {
            for (Node card : playerSecondRow.getChildren()) {
                if (Integer.parseInt(((Card) card).powerLabel.getText()) == maxPower) {
                    dominant.add((Card) card);
                } else if (Integer.parseInt(((Card) card).powerLabel.getText()) > maxPower) {
                    dominant.clear();
                    dominant.add((Card) card);
                }
            } for (Node card : playerFifthRow.getChildren()) {
                if (Integer.parseInt(((Card) card).powerLabel.getText()) == maxPower) {
                    dominant.add((Card) card);
                } else if (Integer.parseInt(((Card) card).powerLabel.getText()) > maxPower) {
                    dominant.clear();
                    dominant.add((Card) card);
                }
            }
        } else if (i == 3) {
            for (Node card : playerThirdRow.getChildren()) {
                if (Integer.parseInt(((Card) card).powerLabel.getText()) == maxPower) {
                    dominant.add((Card) card);
                } else if (Integer.parseInt(((Card) card).powerLabel.getText()) > maxPower) {
                    dominant.clear();
                    dominant.add((Card) card);
                }
            } for (Node card : playerFourthRow.getChildren()) {
                if (Integer.parseInt(((Card) card).powerLabel.getText()) == maxPower) {
                    dominant.add((Card) card);
                } else if (Integer.parseInt(((Card) card).powerLabel.getText()) > maxPower) {
                    dominant.clear();
                    dominant.add((Card) card);
                }
            }
        } else{
            for (Node card : playerFirstRow.getChildren()) {
                if (Integer.parseInt(((Card) card).powerLabel.getText()) == maxPower) {
                    dominant.add((Card) card);
                } else if (Integer.parseInt(((Card) card).powerLabel.getText()) > maxPower) {
                    dominant.clear();
                    dominant.add((Card) card);
                }
            }
            for (Node card : playerSecondRow.getChildren()) {
                if (Integer.parseInt(((Card) card).powerLabel.getText()) == maxPower) {
                    dominant.add((Card) card);
                } else if (Integer.parseInt(((Card) card).powerLabel.getText()) > maxPower) {
                    dominant.clear();
                    dominant.add((Card) card);
                }
            }
            for (Node card : playerThirdRow.getChildren()) {
                if (Integer.parseInt(((Card) card).powerLabel.getText()) == maxPower) {
                    dominant.add((Card) card);
                } else if (Integer.parseInt(((Card) card).powerLabel.getText()) > maxPower) {
                    dominant.clear();
                    dominant.add((Card) card);
                }
            }
            for (Node card : playerFourthRow.getChildren()) {
                if (Integer.parseInt(((Card) card).powerLabel.getText()) == maxPower) {
                    dominant.add((Card) card);
                } else if (Integer.parseInt(((Card) card).powerLabel.getText()) > maxPower) {
                    dominant.clear();
                    dominant.add((Card) card);
                }
            } for (Node card : playerFifthRow.getChildren()) {
                if (Integer.parseInt(((Card) card).powerLabel.getText()) == maxPower) {
                    dominant.add((Card) card);
                } else if (Integer.parseInt(((Card) card).powerLabel.getText()) > maxPower) {
                    dominant.clear();
                    dominant.add((Card) card);
                }
            } for (Node card : playerSixthRow.getChildren()) {
                if (Integer.parseInt(((Card) card).powerLabel.getText()) == maxPower) {
                    dominant.add((Card) card);
                } else if (Integer.parseInt(((Card) card).powerLabel.getText()) > maxPower) {
                    dominant.clear();
                    dominant.add((Card) card);
                }
            }
        }
        for (Card card : dominant) {
            EnhancedHBox place = (EnhancedHBox) card.getParent();
            System.out.println(card.getCardName());
            place.getChildren().remove(card);
        }
    }
    public void showMedicMenu(Game game) {
        Stage medicMenu = new Stage();
        Pane root = new Pane();
        Scene scene = new Scene(root);
        medicMenu.initStyle(StageStyle.UNDECORATED);
        medicMenu.initStyle(StageStyle.TRANSPARENT);
        root.setBackground(Background.EMPTY);
        scene.setFill(Color.rgb(1,1,1, 0.7));
        root.setMinHeight(500);
        root.setMinWidth(800);
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(100);
        gridPane.setLayoutY(45);
        gridPane.snapSpaceX(12.5);
        gridPane.snapSpaceY(10);
        int count = 0;
        for (Card deadCard : game.graveyard) {
            Pane pane = new Pane();
            Rectangle rectangle = new Rectangle();
            rectangle.setFill(new ImagePattern(new Image(
                    String.valueOf(PreGameMenu.class.getResource(deadCard.getLgPath()).toExternalForm()))));
            rectangle.setHeight(200);
            rectangle.setWidth(100);
            rectangle.setArcWidth(20);
            rectangle.setArcHeight(20);
            pane.getChildren().addAll(rectangle);
            pane.setOnMouseClicked(event -> {
                graveyard.remove(deadCard);
                Card temp = deadCard;
                if (deadCard.getRows().contains(1)) {
                    game.placeCard(temp, playerFirstRow);
                    medicMenu.close();
                } else if (deadCard.getRows().contains(2)) {
                    game.placeCard(temp, playerSecondRow);
                    medicMenu.close();
                } else if (deadCard.getRows().contains(3)) {
                    game.playerThirdRow.getChildren().add(temp);
                    medicMenu.close();
                }
            });
            gridPane.add(pane, count % 4, count / 4);
            count++;
        }
        root.getChildren().add(gridPane);
        medicMenu.setScene(scene);
        medicMenu.show();
    }

    public void spyACard() {
        Card card = Deck.currentDeck.reservedCards.get(0);
        playerHand.getChildren().add(card);
        gameLauncher.reservedCards.remove(card);
    }

    public void handleBond(EnhancedHBox selectedBox, Card card, Game game) {
        for (Node card1 : selectedBox.getChildren()){
            if ( card1 instanceof TightBond && !card1.equals(card)) {
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
                    hBox.moralBoostLevel = 1;
            }
        }
        int totalSumPlayer = 0;
        int totalSumOpponent = 0;
        for (int i = 0; i < 7; i++) {
            EnhancedHBox hBox = hBoxes.get(i);
            int sum = 0;
            for (Node card : hBox.getChildren()) {
                int currentPower = 0;
                if (!((Card) card).isHero()) {
                    currentPower = hBox.badCondition ? 1 : ((Card) card).getPower();
                    currentPower += hBox.moralBoostLevel;
                    if (hBox.isDoubled)
                        currentPower *= 2;
                } else
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
            }  else {
                yourLastCard = card;
                hBox.getChildren().add(card);
                calculateLabels();
                gameLauncher.playerHandMouseSetter();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String st = "card:" + User.getLoggedInUser().currentOponentName + ":" + card.getCardName() + "."
                + card.getCountOfCard() + "." + card.isSpecial() + "." + card.getPower() + "." + card.getFactionName()
                + "." + card.rows + "." + card.isHero();
        System.out.println(st);
        client.sendMessage(st);
    }
    public void enemyPlaceCard(Card finalCard, EnhancedHBox hBox) {
        hBox.getChildren().add(finalCard);
        if (lastPlacedCard != null){
            lastPlacedCard.setOnMouseEntered(event -> {

            });
        }
        finalCard.setOnMouseEntered(event -> {
            finalCard.fireEmoji.setVisible(true);
            finalCard.fireEmoji.setOnMouseClicked(event1 -> {
                client.sendMessage("emoji:" + User.getLoggedInUser().currentOponentName+":fire");
            });
            finalCard.sleepyEmoji.setVisible(true);
            finalCard.sleepyEmoji.setOnMouseClicked(event1 -> {
                client.sendMessage("emoji:" + User.getLoggedInUser().currentOponentName+":sleepy");
            });
        });
        finalCard.setOnMouseExited(event -> {
            finalCard.fireEmoji.setVisible(false);
            finalCard.sleepyEmoji.setVisible(false);
        });
        lastPlacedCard = finalCard;
        calculateLabels();
    }
    public void newRound(Game game) {
        roundsPoints[roundCount][0] = Integer.parseInt(totalPower.getText());
        roundsPoints[roundCount][1] = Integer.parseInt(totalPowerOpponent.getText());
        roundCount++;
        if (Integer.parseInt(totalPower.getText()) < Integer.parseInt(totalPowerOpponent.getText())) {
            life2.setVisible(false);
            yourLives--;
        } else if (Integer.parseInt(totalPower.getText()) > Integer.parseInt(totalPowerOpponent.getText())) {
            life2Opponent.setVisible(false);
            opponentLives--;
        } else {
            life2Opponent.setVisible(false);
            life2.setVisible(false);
            yourLives--;
            opponentLives--;
        }

        ArrayList<Node> remove = new ArrayList<>();
        for (int i = 1; i < hBoxes.size(); i++) {
            EnhancedHBox box = hBoxes.get(1);
            remove.addAll(box.getChildren());
            for (Node card: remove) {
                box.getChildren().remove(card);
                if (!((Card) card).isHero() || !((Card) card).isSpecial()) {
                    graveyard.add(((Card) card));
                }
            }
        }
        playerFirstRowHorn.getChildren().clear();
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
        int[] finalPoints = {yourLives, opponentLives};
        if (yourLives==0 && opponentLives == 0) {
            showResult("draw");
            User.getLoggedInUser().getBattleLog().add(new BattleInfo(User.getLoggedInUser().currentOponentName,
                    LocalDate.now(), roundsPoints, finalPoints, "draw"));
        }
        else if (yourLives == 0) {
            showResult("lose");
            User.getLoggedInUser().getBattleLog().add(new BattleInfo(User.getLoggedInUser().currentOponentName,
                    LocalDate.now(), roundsPoints, finalPoints, User.getLoggedInUser().currentOponentName));
        } else if (opponentLives == 0) {
            showResult("win");
            User.getLoggedInUser().getBattleLog().add(new BattleInfo(User.getLoggedInUser().currentOponentName,
                    LocalDate.now(), roundsPoints, finalPoints, User.getLoggedInUser().getUsername()));
        }
    }

    private void showResult(String result) {
        Stage resultPop = new Stage();
        Pane root = new Pane();
        Scene scene = new Scene(root);
        resultPop.initStyle(StageStyle.UNDECORATED);
        resultPop.initStyle(StageStyle.TRANSPARENT);
        root.setBackground(Background.EMPTY);
        scene.setFill(Color.rgb(1,1,1, 0.7));
        root.setMinHeight(500);
        root.setMinWidth(400);
        Button invite = new Button("continue");
        invite.setOnMouseClicked(event -> {
            MainMenu mainMenu = new MainMenu();
            try {
                mainMenu.start(LoginMenu.stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        VBox vBox = new VBox();
        Rectangle rectangle = new Rectangle(300,200);
        rectangle.setFill(new ImagePattern(new Image(String.valueOf(Game.class.getResource("/Images/icons/end_"+result+".png")))));
        vBox.getChildren().addAll(rectangle, invite);
        root.getChildren().add(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setLayoutX(100);
        vBox.setLayoutY(100);
        resultPop.setScene(scene);
        resultPop.show();
    }
}
