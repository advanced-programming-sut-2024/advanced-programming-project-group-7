package model;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import view.PreGameMenu;

import java.awt.*;

public class Card extends Pane {
    private String cardName;
    private int countOfCard;
    private boolean isSpecial;
    private int power;
    private String lgPath;
    private String factionName;
    private Rectangle cardImage=new Rectangle();

    public Card(String cardName, int countOfCard, boolean isSpecial, int power, String factionName, Node... children) {
        super(children);
        this.cardName = cardName;
        this.countOfCard = countOfCard;
        this.isSpecial = isSpecial;
        this.power = power;
        this.lgPath = lgPathCreator(cardName);
        this.factionName = factionName;
        fillRectangle(factionName,cardName);
    }
    public void fillRectangle(String factionName,String cardName){
        StringBuilder path=new StringBuilder();
        String newCardName=cardName.replaceAll(" ","_");
        path.append("/Images/sm/");
        path.append(Card.getCardByName(cardName).getFactionName());
        path.append("_");
        path.append(newCardName);
        path.append(".jpg");
        this.cardImage.setFill(new ImagePattern(new Image(PreGameMenu.class.getResource(path.toString()).toExternalForm())))
    }

    public static Card getCardByName(String cardName){return null;}

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

    public String lgPathCreator(String cardName){
        StringBuilder path=new StringBuilder();
        String newCardName=cardName.replaceAll(" ","_");
        path.append("/Images/lg/");
        path.append(Card.getCardByName(cardName).getFactionName());
        path.append("_");
        path.append(newCardName);
        path.append(".jpg");
        return path.toString();
    }



}
