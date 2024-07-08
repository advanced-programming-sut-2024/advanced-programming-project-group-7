package model;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Card extends Pane {
    private String cardName;
    private int countOfCard;
    private boolean isSpecial;
    private String lgPath;
    private String factionName;
    public Rectangle rectangle = new Rectangle();
    private Circle powerIcon = new Circle();
    public Label powerLabel = new Label();
    private int power;
    private static ArrayList<Card> cards=new ArrayList<>();
    public int rows;
    public HBox rowInGame;
    private boolean isHero;
    public boolean isWeak = false;
    public boolean isDoubeld = false;
    public int bondLevel = 1;
    public int boostLevel = 0;

    public Card(String cardName, int countOfCard, boolean isSpecial, int power, String factionName,int rows,boolean isHero) {
        this.cardName = cardName;
        this.countOfCard = countOfCard;
        this.isSpecial = isSpecial;
        this.power = power;
        this.rows=rows;
        this.isHero=isHero;
        this.factionName = factionName;
        this.lgPath = lgPathCreator(cardName, factionName);
        this.setHeight(98);
        this.setWidth(70);
        rectangle.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource(smPathCreator(cardName, factionName)).toExternalForm()))));
        rectangle.setHeight(98);
        rectangle.setWidth(70);
        if(this.isSpecial) {
            setupForSpecial(this);
        }else {
            powerLabel = new Label(String.valueOf(power));
            this.getChildren().addAll(rectangle, powerIcon, powerLabel);

            if (this.isHero) {
                powerIcon.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource("/Images/icons/power_hero.png").toExternalForm()))));
            } else
                powerIcon.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource("/Images/icons/power_normal.png").toExternalForm()))));
            powerIcon.setRadius(25);
            powerIcon.setCenterX(22);
            powerIcon.setCenterY(22);
            powerLabel.setLayoutX(8);
            powerLabel.setLayoutY(-2);
            if (this.isHero) {
                powerLabel.setTextFill(Color.WHITE);
            } else powerLabel.setTextFill(Color.BLACK);
            powerLabel.setFont(new Font(20));

            Circle circle = new Circle(12.5);
            if ((this.rows == 3)||(this.rows == 4)) {
                circle.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource("/Images/icons/card_row_close.png").toExternalForm()))));
            } else if (this.rows == 2) {
                circle.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource("/Images/icons/card_row_ranged.png").toExternalForm()))));
            } else if( (this.rows == 1)||(this.rows == 6) ){
                circle.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource("/Images/icons/card_row_siege.png").toExternalForm()))));
            } else if (this.rows == 23) {
                circle.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource("/Images/icons/card_row_agile.png").toExternalForm()))));
            }
            circle.setCenterX(60);
            circle.setCenterY(85);
            this.getChildren().add(circle);
        }
        cards.add(this);
    }

    public static Card getCardByName(Card card) {
        for(Card card1:cards){
           if(card1.cardName.equals(card.cardName))return  card1;
        }
        return null;
    }

    public String getCardName() {
        return cardName;
    }

    public int getCountOfCard() {
        return countOfCard;
    }

   public int getPower() {
        return power * bondLevel;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public void setCardLabel(int currentPower) {
        this.powerLabel.setText(String.valueOf(currentPower));
    }

    public String getFactionName() {
        return factionName;
    }

    public String getLgPath() {
        return lgPath;
    }

    public String lgPathCreator(String cardName, String factionName){
        return "/Images/lg/"+factionName+"_"+cardName.replaceAll(" ","_")+".jpg";
    }
    public String smPathCreator(String cardName, String factionName){
        StringBuilder path=new StringBuilder();
        String newCardName=cardName.replaceAll(" ","_");
        path.append("/Images/sm/");
        path.append(factionName);
        path.append("_");
        path.append(newCardName);
        path.append(".jpg");
        return path.toString();
    }
    private void setupForSpecial(Card card) {
        if(card.cardName.equals("decoy")){
            powerIcon.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource("/Images/icons/card_special_decoy.png").toExternalForm()))));
        }
        else if (card.cardName.equals("horn")){
            powerIcon.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource("/Images/icons/card_special_horn.png").toExternalForm()))));
        }
        else if (card.cardName.equals("mardroeme")){
            powerIcon.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource("/Images/icons/card_special_mardroeme.png").toExternalForm()))));
        }
        else if (card.cardName.equals("scorch")){
            powerIcon.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource("/Images/icons/card_special_scorch.png").toExternalForm()))));
        }
        else if (card.cardName.equals("frost")){
            powerIcon.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource("/Images/icons/card_weather_frost.png").toExternalForm()))));
        }
        else if (card.cardName.equals("clear")){
            powerIcon.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource("/Images/icons/card_weather_clearpng.png").toExternalForm()))));
        }
        else if (card.cardName.equals("fog")){
            powerIcon.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource("/Images/icons/card_weather_fog.png").toExternalForm()))));
        }
        else if (card.cardName.equals("storm")){
            powerIcon.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource("/Images/icons/card_weather_storm.png").toExternalForm()))));
        }
        else if (card.cardName.equals("rain")){
            powerIcon.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource("/Images/icons/card_weather_rain.png").toExternalForm()))));
        }
        powerIcon.setRadius(16);
        powerIcon.setCenterX(14);
        powerIcon.setCenterY(14);
        this.getChildren().addAll(rectangle,powerIcon);
    }

    public ArrayList<Integer> getRows() {
        int rowInt = rows;
        ArrayList<Integer> row = new ArrayList<>();
        while(rowInt > 0) {
            row.add(rowInt % 10);
            rowInt /= 10;
        }
        return row;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getrows(){
        return this.rows;
    }

    public boolean isHero() {
        return isHero;
    }

    public void setHero(boolean hero) {
        isHero = hero;
    }

    public void setRowInGame(HBox rowInGame) {
        this.rowInGame = rowInGame;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCountOfCard(int countOfCard) {
        this.countOfCard = countOfCard;
    }

    public void setSpecial(boolean special) {
        isSpecial = special;
    }

    public void setLgPath(String lgPath) {
        this.lgPath = lgPath;
    }

    public void setFactionName(String factionName) {
        this.factionName = factionName;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Circle getPowerIcon() {
        return powerIcon;
    }

    public void setPowerIcon(Circle powerIcon) {
        this.powerIcon = powerIcon;
    }

    public Label getPowerLabel() {
        return powerLabel;
    }

    public void setPowerLabel(Label powerLabel) {
        this.powerLabel = powerLabel;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public HBox getRowInGame() {
        return rowInGame;
    }

    public boolean isWeak() {
        return isWeak;
    }

    public void setWeak(boolean weak) {
        isWeak = weak;
    }

    public boolean isDoubeld() {
        return isDoubeld;
    }

    public void setDoubeld(boolean doubeld) {
        isDoubeld = doubeld;
    }

    public int getBondLevel() {
        return bondLevel;
    }

    public void setBondLevel(int bondLevel) {
        this.bondLevel = bondLevel;
    }

    public int getBoostLevel() {
        return boostLevel;
    }

    public void setBoostLevel(int boostLevel) {
        this.boostLevel = boostLevel;
    }

    public Card(String cardName, int countOfCard, boolean isSpecial, String lgPath, String factionName, Rectangle rectangle, Circle powerIcon, Label powerLabel, int power, int rows, HBox rowInGame, boolean isHero, boolean isWeak, boolean isDoubeld, int bondLevel, int boostLevel) {
        this.cardName = cardName;
        this.countOfCard = countOfCard;
        this.isSpecial = isSpecial;
        this.lgPath = lgPath;
        this.factionName = factionName;
        this.rectangle = rectangle;
        this.powerIcon = powerIcon;
        this.powerLabel = powerLabel;
        this.power = power;
        this.rows = rows;
        this.rowInGame = rowInGame;
        this.isHero = isHero;
        this.isWeak = isWeak;
        this.isDoubeld = isDoubeld;
        this.bondLevel = bondLevel;
        this.boostLevel = boostLevel;
    }

    public Card(String cardName, int countOfCard, boolean isSpecial, String lgPath, String factionName, Rectangle rectangle, Circle powerIcon, Label powerLabel, int power, int rows, HBox rowInGame, boolean isHero, boolean isWeak, boolean isDoubeld, int bondLevel, int boostLevel, Node... nodes) {
        super(nodes);
        this.cardName = cardName;
        this.countOfCard = countOfCard;
        this.isSpecial = isSpecial;
        this.lgPath = lgPath;
        this.factionName = factionName;
        this.rectangle = rectangle;
        this.powerIcon = powerIcon;
        this.powerLabel = powerLabel;
        this.power = power;
        this.rows = rows;
        this.rowInGame = rowInGame;
        this.isHero = isHero;
        this.isWeak = isWeak;
        this.isDoubeld = isDoubeld;
        this.bondLevel = bondLevel;
        this.boostLevel = boostLevel;
    }

}
