package view;

import controller.Client;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Game;
import model.OngoingGame;
import model.User;

import java.util.ArrayList;

public class CupMenu extends Application {

    public static OngoingGame ongoingGames;
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
    private Pane root;




    public void setReady(String player, String nodeName) {
        System.out.println("we doin this "+ player +"  "+ nodeName );

        switch (nodeName) {
            case "m0" -> M0.setReady(player, false);
            case "m1" -> M1.setReady(player, false);
            case "m2" -> M2.setReady(player, false);
            case "m3" -> M3.setReady(player, false);
            case "m4" -> M4.setReady(player, false);
            case "m5" -> M5.setReady(player, false);
            case "m6" -> M6.setReady(player, false);
            case "m7" -> M7.setReady(player, false);
            case "m8" -> M8.setReady(player, false);
            case "m9" -> M9.setReady(player, false);
            case "m10" -> M10.setReady(player, false);
            case "m11" -> M11.setReady(player, false);
            case "m12" -> M12.setReady(player, false);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        root = new Pane();
        LoginMenu.stage.close();

        stage.setTitle(you);
        root.setMinHeight(900);
        root.setMinWidth(1280);
        root.setBackground(new Background(createBackgroundImage("/Images/bracket.png")));

        P0 = new Label("Cup Menu");
        P0.setLayoutX(1160);
        P0.setLayoutY(55);
        P0.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P0);

        P1 = new Label("Cup Menu");
        P1.setLayoutX(1160);
        P1.setLayoutY(130);
        P1.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P1);

        P2 = new Label("Cup Menu");
        P2.setLayoutX(1160);
        P2.setLayoutY(205);
        P2.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P2);

        P3 = new Label("Cup Menu");
        P3.setLayoutX(1160);
        P3.setLayoutY(280);
        P3.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P3);

        P4 = new Label("Cup Menu");
        P4.setLayoutX(1160);
        P4.setLayoutY(355);
        P4.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P4);

        P5 = new Label("Cup Menu");
        P5.setLayoutX(1160);
        P5.setLayoutY(430);
        P5.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P5);

        P6 = new Label("Cup Menu");
        P6.setLayoutX(1160);
        P6.setLayoutY(505);
        P6.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P6);

        P7 = new Label("Cup Menu");
        P7.setLayoutX(1160);
        P7.setLayoutY(580);
        P7.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P7);

        P8 = new Label("Cup Menu");
        P8.setLayoutX(970);
        P8.setLayoutY(95);
        P8.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P8);

        P9 = new Label("Cup Menu");
        P9.setLayoutX(970);
        P9.setLayoutY(245);
        P9.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P9);

        P10 = new Label("Cup Menu");
        P10.setLayoutX(970);
        P10.setLayoutY(385);
        P10.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P10);

        P11 = new Label("Cup Menu");
        P11.setLayoutX(970);
        P11.setLayoutY(535);
        P11.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P11);

        P12 = new Label("Cup Menu");
        P12.setLayoutX(780);
        P12.setLayoutY(165);
        P12.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P12);

        P13 = new Label("Cup Menu");
        P13.setLayoutX(780);
        P13.setLayoutY(465);
        P13.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P13);

        P14 = new Label("Cup Menu");
        P14.setLayoutX(589);
        P14.setLayoutY(309);
        P14.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P14);

        P15 = new Label("Cup Menu");
        P15.setLayoutX(50);
        P15.setLayoutY(245);
        P15.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P15);

        P16 = new Label("Cup Menu");
        P16.setLayoutX(50);
        P16.setLayoutY(395);
        P16.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P16);

        P17 = new Label("Cup Menu");
        P17.setLayoutX(50);
        P17.setLayoutY(545);
        P17.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P17);

        P18 = new Label("Cup Menu");
        P18.setLayoutX(50);
        P18.setLayoutY(685);
        P18.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P18);

        P19 = new Label("Cup Menu");
        P19.setLayoutX(240);
        P19.setLayoutY(320);
        P19.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P19);

        P20 = new Label("Cup Menu");
        P20.setLayoutX(240);
        P20.setLayoutY(470);
        P20.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P20);

        P21 = new Label("Cup Menu");
        P21.setLayoutX(240);
        P21.setLayoutY(620);
        P21.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P21);

        P22 = new Label("Cup Menu");
        P22.setLayoutX(240);
        P22.setLayoutY(760);
        P22.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P22);

        P23 = new Label("Cup Menu");
        P23.setLayoutX(410);
        P23.setLayoutY(395);
        P23.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P23);

        P24 = new Label("Cup Menu");
        P24.setLayoutX(410);
        P24.setLayoutY(695);
        P24.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P24);

        P25 = new Label("Cup Menu");
        P25.setLayoutX(590);
        P25.setLayoutY(545);
        P25.setStyle("-fx-font-size: 20px");
        root.getChildren().add(P25);

        watch = new Button();
        watch.setText("watch");
        watch.setLayoutX(615);
        watch.setLayoutY(731);
        watch.setOnMouseClicked(event -> {
            GameLauncher gameLauncher = new GameLauncher();
            User.getLoggedInUser().client.sendMessage("viewer:" + User.getLoggedInUser().getUsername()
                    + ":" + ongoingGames.player1);
            try {
                gameLauncher.type = "live";
                gameLauncher.ongoingGame = ongoingGames;
                gameLauncher.start(LoginMenu.stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        searchBar = new TextField();
        searchBar.setLayoutX(589);
        searchBar.setLayoutY(695);
        searchBar.maxWidth(86);

        ready = new Rectangle(130,130);
        ready.setLayoutX(567);
        ready.setLayoutY(139);

        isPlaying = new Button("?");
        isPlaying.setLayoutX(675);
        isPlaying.setLayoutY(695);
        isPlaying.setOnMouseClicked(event -> {
            User.getLoggedInUser().client.sendMessage("cupTV:"+User.getLoggedInUser().getUsername()+":"+searchBar.getText());
        });

        root.getChildren().addAll(watch, searchBar, isPlaying, ready);
        ready.setFill(new ImagePattern(new Image(String.valueOf(CupMenu.class.getResource("/Images/icons/red-ready.png")))));
        ready.setOnMouseClicked(event -> {

            RotateTransition rotate = new RotateTransition(Duration.seconds(3), ready);
            rotate.setCycleCount(1);
            rotate.setByAngle(360);
            rotate.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ready.setFill(new ImagePattern(new Image(String.valueOf(CupMenu.class.getResource("/Images/icons/green-ready.png")))));
                    yourNode.setReady(you, yourNode.ready == null);
                    Timeline t = new Timeline(new KeyFrame(Duration.seconds(10)));
                    t.setCycleCount(1);
                    t.setOnFinished(event1 -> {
                        ready.setFill(new ImagePattern(new Image(String.valueOf(CupMenu.class.getResource("/Images/icons/red-ready.png")))));
                    });
                    t.play();
                }
            });
            rotate.play();
        });

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

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
    public void result(String winner, String loser, String node) {
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
        if (M4.upperPlayer.getText().equals(you) || M4.lowerPlayer.getText().equals(you)){
            yourNode = M4;
        }
        else if (M5.upperPlayer.getText().equals(you) || M5.lowerPlayer.getText().equals(you)){
            yourNode = M5;
        }
        else if (M6.upperPlayer.getText().equals(you) || M6.lowerPlayer.getText().equals(you)){
            yourNode = M6;
        }
        else if (M7.upperPlayer.getText().equals(you) || M7.lowerPlayer.getText().equals(you)){
            yourNode = M7;
        }
        else if (M8.upperPlayer.getText().equals(you) || M8.lowerPlayer.getText().equals(you)){
            yourNode = M8;
        }
        else if (M9.upperPlayer.getText().equals(you) || M9.lowerPlayer.getText().equals(you)){
            yourNode = M9;
        }
        else if (M10.upperPlayer.getText().equals(you) || M10.lowerPlayer.getText().equals(you)){
            yourNode = M10;
        }
        else if (M11.upperPlayer.getText().equals(you) || M11.lowerPlayer.getText().equals(you)){
            yourNode = M11;
        }
        else if (M12.upperPlayer.getText().equals(you) || M12.lowerPlayer.getText().equals(you)){
            yourNode = M12;
        }

    }
}