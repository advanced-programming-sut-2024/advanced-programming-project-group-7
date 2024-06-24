package model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Card extends Pane {
    private String cardName;
    private int countOfCard;
    private boolean isSpecial;
    private String lgPath;
    private String factionName;
    private Rectangle rectangle = new Rectangle();
    private int power;

    public Card(String cardName, int countOfCard, boolean isSpecial, int power, String factionName) {
        this.cardName = cardName;
        this.countOfCard = countOfCard;
        this.isSpecial = isSpecial;
        this.power = power;
        this.factionName = factionName;
        this.lgPath = lgPathCreator(cardName, factionName);
        this.setHeight(98);
        this.setWidth(70);
        this.getChildren().add(rectangle);
        rectangle.setFill(new ImagePattern(new Image(String.valueOf(Card.class.getResource(smPathCreator(cardName, factionName)).toExternalForm()))));
        rectangle.setHeight(98);
        rectangle.setWidth(70);
    }

    public String getCardName() {
        return cardName;
    }

    public int getCountOfCard() {
        return countOfCard;
    }

    public boolean isSpecial() {
        return isSpecial;
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
}
