package model;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Card extends Pane {
    private String cardName;
    private int countOfCard;
    private boolean isSpecial;
    private String lgPath;
    private String factionName;
    public Rectangle rectangle = new Rectangle();
    private Circle powerIcon = new Circle();
    private Label powerLabel;
    private int power;
    private boolean isHero;
    private int rows;

    public Card(String cardName, int countOfCard, boolean isSpecial, int power, String factionName,int rows,boolean isHero) {
//        System.out.println("3");
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
        powerLabel = new Label(String.valueOf(power));
        this.getChildren().addAll(rectangle, powerIcon, powerLabel);
        rectangle.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource(smPathCreator(cardName, factionName)).toExternalForm()))));
        rectangle.setHeight(98);
        rectangle.setWidth(70);
        powerIcon.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource("/Images/icons/power_normal.png").toExternalForm()))));
        powerIcon.setRadius(25);
        powerIcon.setCenterX(22);
        powerIcon.setCenterY(22);
        powerLabel.setLayoutX(8);
        powerLabel.setLayoutY(-2);
        powerLabel.setTextFill(Color.BLACK);
        powerLabel.setFont(new Font(20));
    }

    public String getCardName() {
        return cardName;
    }

    public int getCountOfCard() {
        return countOfCard;
    }

    public int getPower() {
        return power;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public boolean isHero(){
        return isHero;
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

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setHero(boolean hero) {
        isHero = hero;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
