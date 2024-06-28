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
                            //gotoGameLauncher();
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
        }
    }


    @Override
    public void start(Stage stage) throws Exception {


        Button okButton=new Button();
        okButton.setText("Confirm");
        okButton.setLayoutX(730);
        okButton.setLayoutY(790);
        okButton.setOnMouseClicked(mouseEvent1 -> {
            try {
                //gotoGameLauncher();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void setSize(Pane pane) {
        pane.setMinHeight(HEIGHT);
        pane.setMaxHeight(HEIGHT);
        pane.setMinWidth(WIDTH);
        pane.setMaxWidth(WIDTH);
    }
}
