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

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MessagePane extends Pane {
    public final String sender;
    public final String text;
    public int votes = 0;
    public Line line = new Line(10,40,250,40);
    public Rectangle like = new Rectangle(15,15);
    public Rectangle dislike = new Rectangle(15,15);
    public Rectangle reply = new Rectangle(15,15);
    public Label newMassage = new Label();
    public Label repliedMessage = new Label();
    public Label time = new Label();
    public Label vote = new Label("0");
    public MessagePane(String text, String repliedText, String sender, String  repliedGuy) {
        this.sender = sender;
        this.text = text;
        like.setFill(new ImagePattern(new Image(MessagePane.class.getResource("/Images/icons/like.png").toExternalForm())));
        dislike.setFill(new ImagePattern(new Image(MessagePane.class.getResource("/Images/icons/like.png").toExternalForm())));
        reply.setFill(new ImagePattern(new Image(MessagePane.class.getResource("/Images/icons/anim_bond.png").toExternalForm())));
        like. setLayoutY(25);
        dislike. setLayoutY(25);
        dislike.setRotate(180);
        reply. setLayoutY(25);
        like.setLayoutX(240);
        dislike.setLayoutX(210);
        reply.setLayoutX(190);
        line.setStroke(Color.WHITE);
        vote.setLayoutY(25);
        vote.setLayoutX(225);
        vote.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: white;");
        time.setLayoutX(0);
        time.setLayoutY(25);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        time.setText(LocalTime.now().format(formatter));
        time.setStyle("-fx-font-weight: bold; -fx-font-size: 9px;");
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
        this.getChildren().addAll(like,vote, dislike, reply, newMassage, repliedMessage, time, line);
        this.setMinHeight(40);
        this.setMinWidth(260);
    }
}
