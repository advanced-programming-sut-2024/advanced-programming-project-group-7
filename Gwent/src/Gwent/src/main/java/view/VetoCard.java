package view;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Card;
import model.Deck;
import model.cards.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class VetoCard  extends Application {
    Pane pane;
    static Stage stage;
    private static final double HEIGHT = 900;
    private static final double WIDTH = 1600;
    private int totalClick=0;

    private static ArrayList <Card>hand=new ArrayList<>();
    private static ArrayList<Card> currentDeck=new ArrayList<>();
    private static ArrayList<Card> tempDeck=new ArrayList<>();


    public void setRectanglesProperty(int number, ArrayList<Card> cards,Pane pane){
//        ArrayList<Rectangle> rectangles=new ArrayList<>();
        for(int i=0;i<number;i++){
            int j=i;
            Rectangle rectangle=new Rectangle();
            rectangle.setFill(new ImagePattern(new Image(String.valueOf(PreGameMenu.class.getResource(hand.get(i).getLgPath()).toExternalForm()))));
            rectangle.setHeight(170);
            rectangle.setWidth(128);
            rectangle.setLayoutY(400);
            rectangle.setLayoutX(160*i-(80*number)+816);
            pane.getChildren().add(rectangle);
//            rectangles.add(rectangle);
            rectangle.setOnMouseClicked(mouseEvent -> {
                totalClick++;
                if(totalClick>=3) {
                Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("only 2!");
                alert.show();}
                else {
                    hand.remove(j);
                    pane.getChildren().clear();
                    Button okButton=new Button();
                    okButton.setText("ok");
                    okButton.setLayoutX(730);
                    okButton.setLayoutY(790);
                    okButton.setOnMouseClicked(mouseEvent1 -> {
                        addSomeCardsToHand();
                        try {
                            gotoGameLauncher();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                    pane.getChildren().add(okButton);
//                List<Node> rectanglesToRemove = new ArrayList<>();
//                for (Node node : pane.getChildren()) {
//                    if (node instanceof Rectangle) {
//                        rectanglesToRemove.add(node);
//                    }
//                    pane.getChildren().removeAll(rectanglesToRemove);

//                }
                    setRectanglesProperty(hand.size(), hand, pane);
                }

            });
        }

    }

    private void gotoGameLauncher() throws Exception {
        GameLauncher gameLauncher = new GameLauncher();
        gameLauncher.setDeck(currentDeck);
        gameLauncher.setHand(hand);
        gameLauncher.start(stage);
    }
    public void addSomeCardsToHand(){
        int first;
        int second;
        Random random=new Random();

        if(totalClick==2){
            first= random.nextInt(0,tempDeck.size()-1);
            while (true){
                second= random.nextInt(0,tempDeck.size()-1);
                if(second!=first)break;
            }
            hand.add(tempDeck.get(first));
            hand.add(tempDeck.get(second));
        }else if(totalClick==1){
            first= random.nextInt(0,tempDeck.size()-1);
            hand.add(tempDeck.get(first));
        }//formerge
    }





    @Override
    public void start(Stage stage) throws Exception {

        hand.add(new Card("horn",3,true,0,"special",123,false));
        hand.add(new Card("horn",3,true,0,"special",123,false));
        hand.add(new Mardroeme("mardroeme", 3 , true, 0, "special",123,false));
        hand.add(new Scorch("scorch", 3 , true, 0, "special",123456,false));
        hand.add(new Card("frost", 3 , true, 0, "weather",7,false));
        hand.add(new Card("clear", 2 , true, 0, "weather",7,false));
        hand.add(new Card("fog", 3 , true, 0, "weather",7,false));
        hand.add(new Card("storm", 3 , true, 0, "weather",7,false));
        hand.add(new Card("rain", 2 , true, 0, "weather",7,false));
        hand.add(new Card("ciri", 1 , false, 15, "neutral",3,true));


        currentDeck.add(new Card("horn",3,true,0,"special",123,false));
        currentDeck.add(new Card("horn",3,true,0,"special",123,false));
        currentDeck.add(new Mardroeme("mardroeme", 3 , true, 0, "special",123,false));
        currentDeck.add(new Scorch("scorch", 3 , true, 0, "special",123456,false));
        currentDeck.add(new Card("frost", 3 , true, 0, "weather",7,false));
        currentDeck.add(new Card("clear", 2 , true, 0, "weather",7,false));
        currentDeck.add(new Card("fog", 3 , true, 0, "weather",7,false));
        currentDeck.add(new Card("storm", 3 , true, 0, "weather",7,false));
        currentDeck.add(new Card("rain", 2 , true, 0, "weather",7,false));
        currentDeck.add(new Card("ciri", 1 , false, 15, "neutral",3,true));
        currentDeck.add(new Muster("arachas behemoth", 1, false, 6, "monsters",1,false));
        currentDeck.add(new Muster("witch velen", 1, false, 6, "monsters",3,false));
        currentDeck.add(new Muster("witch velen 1", 1, false, 6, "monsters",3,false));
        currentDeck.add(new Muster("witch velen 2", 1, false, 6, "monsters",3,false));
        currentDeck.add(new Card("earth elemental", 1, false, 6, "monsters",1,false));
        currentDeck.add(new Card("fiend", 1, false, 6, "monsters",3,false));


        for(Card card:currentDeck){
            tempDeck.add(card);
        }
        for(Card card:hand){
            tempDeck.remove(hand);
        }


            pane = new Pane();
        setSize(pane);
        pane.setBackground(Background.EMPTY);




        Button okButton=new Button();
        okButton.setText("ok");
        okButton.setLayoutX(730);
        okButton.setLayoutY(790);
        okButton.setOnMouseClicked(mouseEvent1 -> {
            try {
                gotoGameLauncher();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        pane.getChildren().add(okButton);


        setRectanglesProperty(10,hand,pane);




        Scene scene = new Scene(pane);
        VetoCard.stage=stage;
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        stage.setFullScreen(true);
    }

    private void setSize(Pane pane) {
        pane.setMinHeight(HEIGHT);
        pane.setMaxHeight(HEIGHT);
        pane.setMinWidth(WIDTH);
        pane.setMaxWidth(WIDTH);
    }

    public void setDeck(ArrayList<Card> currentDeck) {
        VetoCard.currentDeck=currentDeck;
    }
}
