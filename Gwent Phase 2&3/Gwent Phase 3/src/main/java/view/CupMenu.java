package view;

import controller.Client;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Game;
import model.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CupMenu extends Application {

    public Circle tournament1;
    public Circle tournament0;
    public Button watch;
    public TextField searchBar;
    public Button isPlaying;
    public Rectangle ready;
    public Label P0;
    public Label P2;
    public Label P1;
    public Label P3;
    public Label P4;
    public Label P5;
    public Label P6;
    public Label P7;
    public Label P8;
    public Label P9;
    public Label P10;
    public Label P11;
    public Label P12;
    public Label P13;
    public Label P14;
    public Label P15;
    public Label P16;
    public Label P17;
    public Label P18;
    public Label P19;
    public Label P20;
    public Label P21;
    public Label P23;
    public Label P24;
    public Label P25;
    public Label P22;
    public static MatchNode yourNode;
    public static MatchNode M0;
    public static MatchNode M1;
    public static MatchNode M3;
    public static MatchNode M2;
    public static MatchNode M4;
    public static MatchNode M5;
    public static MatchNode M6;
    public static MatchNode M7;
    public static MatchNode M8;
    public static MatchNode M9;
    public static MatchNode M10;
    public static MatchNode M11;
    public static MatchNode M12;
    private static ArrayList<MatchNode> nodes = new ArrayList<>();
    public static String  you = User.getLoggedInUser().getUsername();
    public static Client client = User.getLoggedInUser().client;

    {
        M0 = new MatchNode("m0", P0, P1);
        M1 = new MatchNode("m1", P2, P3);
        M2 = new MatchNode("m2", P4, P5);
        M3 = new MatchNode("m3", P6, P7);
        M4 = new MatchNode("m4", P8, P9);
        M5 = new MatchNode("m5", P10, P11);
        M6 = new MatchNode("m6", P12, P13);
        M7 = new MatchNode("m7", P14, P25);
        M8 = new MatchNode("m8", P15, P16);
        M9 = new MatchNode("m9", P17, P18);
        M10 = new MatchNode("m10", P19, P20);
        M11 = new MatchNode("m11", P21, P22);
        M12 = new MatchNode("m12", P23, P24);

        CupMenu.nodes.add(M0);
        CupMenu.nodes.add(M1);
        CupMenu.nodes.add(M2);
        CupMenu.nodes.add(M3);
        CupMenu.nodes.add(M11);
        CupMenu.nodes.add(M12);
        CupMenu.nodes.add(M4);
        CupMenu.nodes.add(M5);
        CupMenu.nodes.add(M9);
        CupMenu.nodes.add(M10);
        CupMenu.nodes.add(M6);
        CupMenu.nodes.add(M8);
        CupMenu.nodes.add(M7);
    }

    public CupMenu() {
    }

    public static void setReady(String player, String nodeName) {
        switch (nodeName) {
            case "m0" -> M0.setReady(player);
            case "m1" -> M1.setReady(player);
            case "m2" -> M2.setReady(player);
            case "m3" -> M3.setReady(player);
            case "m4" -> M4.setReady(player);
            case "m5" -> M5.setReady(player);
            case "m6" -> M6.setReady(player);
            case "m7" -> M7.setReady(player);
            case "m8" -> M8.setReady(player);
            case "m9" -> M9.setReady(player);
            case "m10" -> M10.setReady(player);
            case "m11" -> M11.setReady(player);
            case "m12" -> M12.setReady(player);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("reached here: " + nodes.size() );
        URL url = LoginMenu.class.getResource("/FXML/CupMenu.fxml");
        AnchorPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        root.setBackground(new Background(createBackgroundImage("/Images/bracket.png")));
        stage.show();
    }

    @FXML
    public void initialize() {
        tournament0.setFill(new ImagePattern(new Image(String.valueOf(CupMenu.class.getResource("/Images/icons/tournament.png")))));
        tournament1.setFill(new ImagePattern(new Image(String.valueOf(CupMenu.class.getResource("/Images/icons/tournament.png")))));
        watch.setVisible(false);
        ready.setFill(new ImagePattern(new Image(String.valueOf(CupMenu.class.getResource("/Images/icons/red-ready.png")))));
        ready.setOnMouseClicked(event -> {
            yourNode.setReady(you);
            RotateTransition rotate = new RotateTransition(Duration.seconds(3), ready);
            rotate.setCycleCount(1);
            rotate.setByAngle(360);
            rotate.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ready.setFill(new ImagePattern(new Image(String.valueOf(CupMenu.class.getResource("/Images/icons/green-ready.png")))));
                    yourNode.setReady(you);
                }
            });
            rotate.play();
        });
        ready.setOnMouseClicked(event -> {
            yourNode.setReady(you);
        });
    }
    private BackgroundImage createBackgroundImage (String address) {
        Image image = new Image(Game.class.getResource(address).toExternalForm(), 1280 ,900, false, false);
        ImageView imageView = new ImageView(image);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image bwImage = imageView.snapshot(params, null);
        BackgroundImage backgroundImage = new BackgroundImage(bwImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return backgroundImage;
    }
    public void init(String p0, String p1, String p2, String p3, String p4, String p5, String p6, String p7, String seed) {
        P0.setText(p0);
        P1.setText(p1);
        P2.setText(p2);
        P3.setText(p3);
        P4.setText(p4);
        P5.setText(p5);
        P6.setText(p6);
        P7.setText(p7);
        yourNode = nodes.get(Integer.parseInt(seed) / 2);
    }
    public static void result(String winner, String loser, String node) {
        switch (node) {
            case "m0" -> {
                M4.upperPlayer.setText(winner);
                M8.upperPlayer.setText(loser);
            }
            case "m1" -> {
                M4.lowerPlayer.setText(winner);
                M8.lowerPlayer.setText(loser);
            }
            case "m2" -> {
                M5.upperPlayer.setText(winner);
                M9.upperPlayer.setText(loser);
            }
            case "m3" -> {
                M5.lowerPlayer.setText(winner);
                M9.lowerPlayer.setText(loser);
            }
            case "m4" -> {
                M6.upperPlayer.setText(winner);
                M10.upperPlayer.setText(loser);
            }
            case "m5" -> {
                M6.lowerPlayer.setText(winner);
                M11.upperPlayer.setText(loser);
            }
            case "m6" -> M7.upperPlayer.setText(winner);
            case "m8" -> M10.lowerPlayer.setText(winner);
            case "m9" -> M11.lowerPlayer.setText(winner);
            case "m10" -> M12.upperPlayer.setText(winner);
            case "m11" -> M12.lowerPlayer.setText(winner);
            case "m12" -> M7.lowerPlayer.setText(winner);
        }
        for (MatchNode matchNode : nodes) {
            if (matchNode.upperPlayer.getText().equals(you) || matchNode.upperPlayer.getText().equals(you))
                yourNode = matchNode;
        }
    }
}