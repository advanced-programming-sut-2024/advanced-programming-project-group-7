package model;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.cards.*;
import model.leaders.NorthernRealmsLeaders;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import view.GameLauncher;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    @BeforeAll
    public static void start() {

        Platform.startup(() -> {
            final CountDownLatch latch = new CountDownLatch(1);
            final GameLauncher[] gameLauncherArray = new GameLauncher[10];
            GameLauncher gameLauncher = new GameLauncher();
            gameLauncherArray[0] = gameLauncher; // store the gameLauncher instance in the array
            try {
                gameLauncher.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Card card = new Scorch("villen", 1, false, 7, "neutral", 3, false);
            Card card1 = new Scorch("villen", 1, false, 7, "neutral", 3, false);
            gameLauncher.hBoxes.get(4).getChildren().addAll(card, card1);
            gameLauncher.hBoxes.get(3).getChildren().add(card1);
            gameLauncher.hBoxes.get(4).getChildren().add(new Scorch("toad", 1, false, 7, "monsters", 2, false));
            gameLauncher.hBoxes.get(4).getChildren().add(new Muster("witch velen", 1, false, 6, "monsters", 3, false));
            gameLauncher.hBoxes.get(2).getChildren().add(new Card("gravehag", 1, false, 5, "monsters", 2, false));
            gameLauncher.hBoxes.get(5).getChildren().add(new Card("gravehag", 1, false, 5, "monsters", 2, false));
            gameLauncher.hBoxes.get(6).getChildren().add(new Card("frost giant", 1, false, 5, "monsters", 1, false));
            Game game = gameLauncher.game;
            game.removeCardForEnemy(card1);
            game.removeDominantCardsAllTable(0);
            game.removeDominantCardsAllTable(1);
            game.removeDominantCardsAllTable(2);
            latch.countDown(); // signal that the Runnable has completed
        });
    }
//
//        Platform.startup(() -> {
//            GameLauncher gameLauncher=new GameLauncher();
//            try {
//                gameLauncher.start(new Stage());
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//            Game game=new Game(gameLauncher);
//            game.gameLauncher=gameLauncher;
//            gameLauncher.game=game;
//        });
//    }

//    @Test
//    public void testDominantRemove() throws Exception {
//        Platform.startup(() -> {
//        GameLauncher gameLauncher=new GameLauncher();
//            try {
//                gameLauncher.start(new Stage());
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//            Game game=new Game(gameLauncher);
//        game.gameLauncher=gameLauncher;
//        gameLauncher.game=game;
//        gameLauncher.hBoxes.get(4).getChildren().add(new Scorch("villen", 1, false, 7, "neutral",3,false));
//        gameLauncher.hBoxes.get(4).getChildren().add(new Card("draug", 1, false, 10, "monsters",3,true));
//        gameLauncher.hBoxes.get(5).getChildren().add(new Card("gravehag", 1, false, 5, "monsters",2,false));
//        gameLauncher.hBoxes.get(6).getChildren().add(new Card("frost giant", 1, false, 5, "monsters",1,false));
//        game.removeDominantCardsAllTable(0);
//        assertTrue(gameLauncher.hBoxes.get(5).getChildren().isEmpty());
//        });
//    }
    @Test
    public void testDominantRemove() throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);
        final GameLauncher[] gameLauncherArray = new GameLauncher[10]; // declare an array to hold the gameLauncher instance
        Platform.startup(() -> {
            GameLauncher gameLauncher = new GameLauncher();
            gameLauncherArray[0]=gameLauncher; // store the gameLauncher instance in the array
            try {
                gameLauncher.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Card card=new Scorch("villen", 1, false, 7, "neutral", 3, false);
            Card card1=new Scorch("villen", 1, false, 7, "neutral", 3, false);
            gameLauncher.hBoxes.get(4).getChildren().addAll(card,card1);
            gameLauncher.hBoxes.get(3).getChildren().add(card1);
            gameLauncher.hBoxes.get(4).getChildren().add(new Scorch("toad", 1, false, 7, "monsters",2,false));
            gameLauncher.hBoxes.get(4).getChildren().add(new Muster("witch velen", 1, false, 6, "monsters",3,false));
            gameLauncher.hBoxes.get(2).getChildren().add(new Card("gravehag", 1, false, 5, "monsters", 2, false));
            gameLauncher.hBoxes.get(5).getChildren().add(new Card("gravehag", 1, false, 5, "monsters", 2, false));
            gameLauncher.hBoxes.get(6).getChildren().add(new Card("frost giant", 1, false, 5, "monsters", 1, false));
            Game game =gameLauncher.game;
            game.removeCardForEnemy(card1);
            game.removeDominantCardsAllTable(0);
            game.removeDominantCardsAllTable(1);
            game.removeDominantCardsAllTable(2);
            latch.countDown(); // signal that the Runnable has completed
        });
        latch.await(); // wait for the Runnable to complete
        assertFalse(gameLauncherArray[0].hBoxes.get(5).getChildren().isEmpty());
        assertTrue(gameLauncherArray[0].game.hBoxes.get(4).getChildren().size()==1);
        assertTrue(gameLauncherArray[0].game.hBoxes.get(2).getChildren().isEmpty());
    }
    @Test
    public void testSpyACard() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final GameLauncher[] gameLauncherArray = new GameLauncher[10]; // declare an array to hold the gameLauncher instance
        Platform.startup(() -> {
            GameLauncher gameLauncher = new GameLauncher();
            gameLauncherArray[0]=gameLauncher; // store the gameLauncher instance in the array
            try {
                gameLauncher.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            gameLauncher.reservedCards.add(new Scorch("toad", 1, false, 7, "monsters",2,false));
            Game game=gameLauncher.game;
            int oldSize=gameLauncher.playerHand.getChildren().size();
            game.spyACard();
            int lastSize=game.playerHand.getChildren().size();
            assertTrue((lastSize-oldSize)==1);
            game.spyACard();
            int secondNew=gameLauncher.playerHand.getChildren().size();
            assertTrue(lastSize==secondNew);

            latch.countDown(); // signal that the Runnable has completed
        });
        latch.await(); // wait for the Runnable to complete
    }
    @Test
    public void testMedicACard() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final GameLauncher[] gameLauncherArray = new GameLauncher[10]; // declare an array to hold the gameLauncher instance
        Platform.startup(() -> {
            GameLauncher gameLauncher = new GameLauncher();
            gameLauncherArray[0]=gameLauncher; // store the gameLauncher instance in the array
            try {
                gameLauncher.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            gameLauncher.graveyardCard.add(new Scorch("toad", 1, false, 7, "monsters",2,false));
            int a=gameLauncher.playerHand.getChildren().size();
            Game game =gameLauncher.game;
            game.medicACard();
            int b=gameLauncher.playerHand.getChildren().size();
            assertTrue(b-a==1);
            game.medicACard();
            int c=gameLauncher.playerHand.getChildren().size();
            assertTrue(c==b);
//            System.out.println(gameLauncher.hBoxes.get(4).getChildren().size());

            latch.countDown(); // signal that the Runnable has completed
        });
        latch.await(); // wait for the Runnable to complete
    }
    @Test
    public void handleBond() throws InterruptedException {//todo problem
        final CountDownLatch latch = new CountDownLatch(1);
        final GameLauncher[] gameLauncherArray = new GameLauncher[10]; // declare an array to hold the gameLauncher instance
        Platform.startup(() -> {
            GameLauncher gameLauncher = new GameLauncher();
            gameLauncherArray[0]=gameLauncher; // store the gameLauncher instance in the array
            try {
                gameLauncher.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Card card=(new TightBond("imperal brigade",4,false,3,"nilfgaard",3,false));
            Card card2=(new TightBond("imperal brigade",4,false,3,"nilfgaard",3,false));
            gameLauncher.hBoxes.get(4).getChildren().add(card);
            gameLauncher.hBoxes.get(4).getChildren().add(card2);
            Game game =gameLauncher.game;
            int p1=game.getCurrentPower(card);
            game.handleBond(gameLauncher.hBoxes.get(4),card,game);
            game.handleBond(gameLauncher.hBoxes.get(4),card2,game);
            int p2=game.getCurrentPower(card2);
            int p12=game.getCurrentPower(card);
            assertTrue(game.getCurrentPower(card)==game.getCurrentPower(card2));
            assertFalse(p2==p1);
//            game.calculateLabels();
//            assertTrue(p2==(p1*2));
//            assertTrue(p2==p12);


            latch.countDown(); // signal that the Runnable has completed
        });
        latch.await(); // wait for the Runnable to complete
//        assertTrue(game.getCurrentPower(card)==game.getCurrentPower(card2));
//        assertFalse(p2==p1);
    }
    @Test
    public void calculateLables() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final GameLauncher[] gameLauncherArray = new GameLauncher[10]; // declare an array to hold the gameLauncher instance
        Platform.startup(() -> {
            GameLauncher gameLauncher = new GameLauncher();
            gameLauncherArray[0]=gameLauncher; // store the gameLauncher instance in the array
            try {
                gameLauncher.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Game game =gameLauncher.game;
//            gameLauncher.hBoxes.get(4).getChildren().add(new TightBond("imperal brigade",4,false,3,"nilfgaard",3,false));
            gameLauncher.hBoxes.get(4).getChildren().add(new Card("morteisen",1,false,3,"nilfgaard",3,false));
//            game.playerThirdRow.getChildren().add(new TightBond("imperal brigade",4,false,3,"nilfgaard",3,false));

//            gameLauncher.hBoxes.get(3).getChildren().add(new TightBond("imperal brigade",4,false,3,"nilfgaard",3,false));
            game.calculateLabels();
             int a=Integer.parseInt(game.totalPower.getText());
            assertEquals(3, Integer.parseInt(game.totalPower.getText()));

//             game.weatherBox.getChildren().add(new Card("fog", 3 , true, 0, "weather",7,false));
//            game.weatherBox.getChildren().add(new Card("rain", 3 , true, 0, "weather",7,false));
//            game.weatherBox.getChildren().add(new Card("frost", 3 , true, 0, "weather",7,false));
            gameLauncher.hBoxes.get(0).getChildren().add(new Card("fog", 3 , true, 0, "weather",7,false));
            gameLauncher.hBoxes.get(0).getChildren().add(new Card("rain", 3 , true, 0, "weather",7,false));
            gameLauncher.hBoxes.get(0).getChildren().add(new Card("frost", 3 , true, 0, "weather",7,false));
            gameLauncher.hBoxes.get(0).getChildren().add(new Card("storm", 3 , true, 0, "weather",7,false));
            game.calculateLabels();
            assertEquals(1, Integer.parseInt(game.totalPower.getText()));
//
//            game.hBoxes.get(5).getChildren().add(new Horn("horn",3,true,0,"special",123,false));
//            game.calculateLabels();


//            assertEquals(3, Integer.parseInt(game.totalPower.getText()));
            gameLauncher.hBoxes.get(3).getChildren().add(new Card("morteisen",1,false,3,"nilfgaard",3,false));
            game.calculateLabels();
//            System.out.println( Integer.parseInt(game.totalPower.getText()));
//            System.out.println( Integer.parseInt(game.totalPowerOpponent.getText()));
            assertEquals(Integer.parseInt(game.totalPower.getText()),Integer.parseInt(game.totalPowerOpponent.getText()));
            gameLauncher.hBoxes.get(3).getChildren().add(new Card("morteisen",1,false,3,"nilfgaard",3,false));
            game.calculateLabels();
            assertTrue(Integer.parseInt(game.totalPower.getText())<Integer.parseInt(game.totalPowerOpponent.getText()));
            game.hBoxes.get(1).getChildren().add(new Horn("horn",3,true,0,"special",123,false));
            game.hBoxes.get(1).getChildren().add(new MoralBoost("olgierd",1,false,6,"neutral",23,false));
            game.calculateLabels();
            assertTrue(Integer.parseInt(game.totalPower.getText())==1);



            latch.countDown(); // signal that the Runnable has completed
        });
        latch.await(); // wait for the Runnable to complete

    }
    @Test
    public void testPlaceCard() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final GameLauncher[] gameLauncherArray = new GameLauncher[10]; // declare an array to hold the gameLauncher instance
        Platform.startup(() -> {
            GameLauncher gameLauncher = new GameLauncher();
            gameLauncherArray[0]=gameLauncher; // store the gameLauncher instance in the array
            try {
                gameLauncher.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Game game =gameLauncher.game;
            Card card=new Card("assire",1,false,6,"nilfgaard",2,false);
            game.placeCard(card,gameLauncher.hBoxes.get(5));
            game.calculateLabels();
            assertEquals(card.getPower(),Integer.parseInt(game.totalPower.getText()));
            game.placeCard(new Card("fog", 3 , true, 0, "weather",7,false),gameLauncher.hBoxes.get(0));
            game.placeCard(new Card("rain", 3 , true, 0, "weather",7,false),gameLauncher.hBoxes.get(0));
            game.placeCard(new Card("storm", 3 , true, 0, "weather",7,false),gameLauncher.hBoxes.get(0));
            game.calculateLabels();
            assertEquals(1,Integer.parseInt(game.totalPower.getText()));
            game.placeCard((new Card("clear", 2 , true, 0, "weather",7,false)),gameLauncher.hBoxes.get(0));
            game.addClearWeatherCheat();
            game.calculateLabels();
            assertEquals(6,Integer.parseInt(game.totalPower.getText()));
            latch.countDown(); // signal that the Runnable has completed
        });
        latch.await(); // wait for the Runnable to complete
    }
    @Test
    public void testEnemyPlaceCard() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final GameLauncher[] gameLauncherArray = new GameLauncher[10]; // declare an array to hold the gameLauncher instance
        Platform.startup(() -> {
            GameLauncher gameLauncher = new GameLauncher();
            gameLauncherArray[0]=gameLauncher; // store the gameLauncher instance in the array
            try {
                gameLauncher.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Game game =gameLauncher.game;
            Card card=new Card("assire",1,false,6,"nilfgaard",2,false);
            game.enemyPlaceCard(card,gameLauncher.hBoxes.get(2));
            game.calculateLabels();
            assertEquals(0,Integer.parseInt(game.totalPower.getText()));
            assertEquals(card.getPower(),Integer.parseInt(game.totalPowerOpponent.getText()));


            latch.countDown(); // signal that the Runnable has completed
        });
        latch.await(); // wait for the Runnable to complete
    }
    @Test
    public void testGetCurrentPower() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final GameLauncher[] gameLauncherArray = new GameLauncher[10]; // declare an array to hold the gameLauncher instance
        Platform.startup(() -> {
            GameLauncher gameLauncher = new GameLauncher();
            gameLauncherArray[0]=gameLauncher; // store the gameLauncher instance in the array
            try {
                gameLauncher.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Game game =gameLauncher.game;
            Card card=new Card("assire",1,false,6,"nilfgaard",2,false);
            game.placeCard(card,gameLauncher.hBoxes.get(6));
            assertEquals(card.getPower(),game.getCurrentPower(card));
            game.placeCard(new Card("storm", 3 , true, 0, "weather",7,false),gameLauncher.hBoxes.get(0));
//            game.placeCard(new Card("rain", 3 , true, 0, "weather",7,false),gameLauncher.hBoxes.get(0));
            assertEquals(1,game.getCurrentPower(card));
            Card hero=(new Card("geralt", 1 , false, 15, "neutral",3,true));
            game.placeCard(hero,gameLauncher.hBoxes.get(4));
            gameLauncher.hBoxes.get(3).getChildren().add(new Horn("horn",3,true,0,"special",123,false));
            assertEquals(game.getCurrentPower(hero),hero.getPower());
            Card card1=new Card("heavy zerri",1,false,10,"nilfgaard",3,false);
            game.placeCard(card1,gameLauncher.hBoxes.get(3));
            assertEquals(game.getCurrentPower(card1),card1.getPower()*2);
            Card card2=new MoralBoost("olgierd",1,false,6,"neutral",23,false);
            game.placeCard(card2,gameLauncher.hBoxes.get(3));
            assertEquals(game.getCurrentPower(card1),(card1.getPower()+2)*2);
            game.kingBran=true;
            assertEquals(card.getPower()/2,game.getCurrentPower(card));
            latch.countDown(); // signal that the Runnable has completed
        });
        latch.await(); // wait for the Runnable to complete
    }
    @Test
    public void testNorthenRealmsLeaders() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final GameLauncher[] gameLauncherArray = new GameLauncher[10]; // declare an array to hold the gameLauncher instance
        Platform.startup(() -> {
            GameLauncher gameLauncher = new GameLauncher();
            gameLauncherArray[0]=gameLauncher; // store the gameLauncher instance in the array
            try {
                gameLauncher.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Game game =gameLauncher.game;
            Leader leaderGold=new NorthernRealmsLeaders("foltest gold","clear any weather effects(resulting from biting frost, torrential rain or impenetrable fog cards) in play","realms");
            Leader leaderCopper=(new NorthernRealmsLeaders("foltest copper","doubles the strength of all your siege units (unless a commander's horn is also present on that row).","realms"));
            Leader leaderBronze=(new NorthernRealmsLeaders("foltest bronze","destroy your enemy's strongest siege unit(s) if the combined strength of all his or her siege units is 10 or more.","realms"));
            Leader leaderSonOfMeddel=(new NorthernRealmsLeaders("foltest son of medell","distroy your enemy's strongest ranged combat unit(s) if the combined strength of all his or her ranged combat units is 10 or more.","realms"));
            Leader leaderSilver=new NorthernRealmsLeaders("foltest silver","pick an impenetrable fog card from your deck and play it instantly","realms");

            Card card=new Card("assire",1,false,6,"nilfgaard",2,false);
            game.placeCard(card,gameLauncher.hBoxes.get(6));
            game.placeCard(new Card("storm", 3 , true, 0, "weather",7,false),gameLauncher.hBoxes.get(0));
            assertEquals(1,game.getCurrentPower(card));
            game.handleLeader(leaderGold);
            assertEquals(card.getPower(),game.getCurrentPower(card));

            Card card1=new Card("werewolf", 1, false, 5, "monsters",3,false);
            gameLauncher.hBoxes.get(6).getChildren().add(card1);
            assertEquals(card1.getPower(),game.getCurrentPower(card1));
            game.handleLeader(leaderCopper);
            assertEquals(card1.getPower()*2,game.getCurrentPower(card1));
            Card card2=new Card("werewolf", 1, false, 5, "monsters",3,false);
            Card card3=new Card("fiend", 1, false, 6, "monsters",3,false);
            game.handleLeader(leaderBronze);
            gameLauncher.hBoxes.get(1).getChildren().addAll(card1);
            assertFalse(gameLauncher.hBoxes.get(1).getChildren().isEmpty());
            gameLauncher.hBoxes.get(1).getChildren().addAll(card2,card3);
            assertEquals(6,game.getCurrentPower(card3));
            game.handleLeader(leaderBronze);
            assertTrue(gameLauncher.hBoxes.get(1).getChildren().isEmpty());


        });
    }

}
