package view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class MatchNode {
    public String nodeName;
    public Label upperPlayer;
    public Label  lowerPlayer;
    public String ready;

    public MatchNode(String nodeName, Label upperPlayer, Label lowerPlayer) {
        this.nodeName = nodeName;
        this.upperPlayer = upperPlayer;
        this.lowerPlayer = lowerPlayer;
    }
    public void setReady(String playerName) {
        if (ready == null) {
            ready = playerName;
            CupMenu.client.sendMessage("ready:"+CupMenu.you+":"+CupMenu.yourNode.nodeName);
        } else {
            if (!ready.equals(playerName)) {
                CupMenu.client.sendMessage("cupGame:"+ready+":"+playerName);
                CupMenu.ready.setFill(new ImagePattern(new Image(String.valueOf(CupMenu.class.getResource("/Images/icons/red-ready.png")))));
            }
        }
    }
}
