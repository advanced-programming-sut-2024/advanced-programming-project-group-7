package model;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.cards.Muster;
import model.cards.Scorch;
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
    
}
