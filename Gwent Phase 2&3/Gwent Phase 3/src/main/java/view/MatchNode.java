package view;

import javafx.scene.control.Label;

public class MatchNode {
    public String nodeName;
    public Label upperPlayer;
    public Label  lowerPlayer;
    public String ready;
    public boolean done = false;

    public MatchNode(String nodeName, Label upperPlayer, Label lowerPlayer) {
        this.nodeName = nodeName;
        this.upperPlayer = upperPlayer;
        this.lowerPlayer = lowerPlayer;
    }
    public void setReady(String playerName, boolean b) {
        if (ready == null) {
            ready = playerName;
            System.out.println(upperPlayer.getText() + "+++++" + lowerPlayer.getText() + " " + ready + "+++" + nodeName);
            String dude;
            if (b) {
                if (ready != null && playerName.equals(upperPlayer.getText())) {

                    dude = lowerPlayer.getText();
                } else {
                    dude = upperPlayer.getText();
                }
                CupMenu.client.sendMessage("ready:" + CupMenu.you + ":" + CupMenu.yourNode.nodeName +
                        ":" + dude);
                done = true;
            }
        } else {
            if (!ready.equals(playerName)) {
                CupMenu.client.sendMessage("cupGame:"+ready+":"+playerName);
            }
        }
    }
}
