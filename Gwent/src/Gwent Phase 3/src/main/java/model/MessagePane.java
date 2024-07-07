package model;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class MessagePane extends Pane {
    public final String sender;
    public final String text;
    public Line line = new Line(10,40,250,40);
    public Rectangle like = new Rectangle(15,15);
    public Rectangle dislike = new Rectangle(15,15);
    public Rectangle reply = new Rectangle(15,15);
    public Label newMassage = new Label();
    public Label repliedMessage = new Label();
    public MessagePane(String text, String repliedText, String sender, String  repliedGuy) {
        this.sender = sender;
        this.text = text;
        like.setFill(new ImagePattern(new Image(MessagePane.class.getResource("/Images/icons/anim_bond.png").toExternalForm())));
        dislike.setFill(new ImagePattern(new Image(MessagePane.class.getResource("/Images/icons/anim_bond.png").toExternalForm())));
        reply.setFill(new ImagePattern(new Image(MessagePane.class.getResource("/Images/icons/anim_bond.png").toExternalForm())));
        like. setLayoutY(25);
        dislike. setLayoutY(25);
        reply. setLayoutY(25);
        like.setLayoutX(240);
        dislike.setLayoutX(230);
        reply.setLayoutX(190);
        line.setStroke(Color.WHITE);
        if (!repliedGuy.equals("null")) {
            repliedMessage.setText("replied to " + repliedGuy + ": " + repliedText);
        }
        newMassage.setLayoutY(15);
        newMassage.setLayoutX(30);
        newMassage.setStyle("-fx-font-weight: bold; -fx-font-size: 13px;");
        if (sender.equals(User.getLoggedInUser().getUsername())) {
            newMassage.setText("You: " + text);
        } else {
            newMassage.setText(sender + ": " + text);
        }
        newMassage.setTextFill(Color.WHITE);
        repliedMessage.setLayoutX(10);
        this.getChildren().addAll(like, dislike, reply, newMassage, repliedMessage, line);
        this.setMinHeight(40);
        this.setMinWidth(260);
    }
}
