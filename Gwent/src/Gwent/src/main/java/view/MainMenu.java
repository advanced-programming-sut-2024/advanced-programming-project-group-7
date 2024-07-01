package view;

import controller.Client;
import controller.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Game;
import model.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;

public class MainMenu extends Application {

    public static Stage stage;
    public Label Username;
    public Rectangle mail;
    public Pane indicator1;
    public Pane indicator2;
    public Pane indicator3;
    public Pane indicator4;
    public Pane profileMenu;
    public Pane startNewGame;
    public Pane logOut;
    public Pane exitGame;
    public Rectangle indicator1rec=new Rectangle(50,50);
    public Rectangle indicator2rec=new Rectangle(50,50);
    public Rectangle indicator3rec=new Rectangle(50,50);
    public Rectangle indicator4rec=new Rectangle(50,50);
    public Rectangle profileMenuRec=new Rectangle(220,50);
    public Rectangle startNewGameRec=new Rectangle(220,50);
    public Rectangle logOutRec=new Rectangle(220,50);
    public Rectangle exitGameRec=new Rectangle(220,50);
    public Label profileMenuLabel;
    public Label startNewGameLabel;
    public Label logOutRLabel;
    public Label exitGameLabel;

    @Override
    public void start(Stage stage) throws Exception {
        GameLauncher gameLauncher = new GameLauncher();
        Game game = new Game(gameLauncher);
        Client client = new Client(game, User.getLoggedInUser());
        User.getLoggedInUser().client=client;
        client.start();
        URL url = LoginMenu.class.getResource("/FXML/MainMenu.fxml");
        BorderPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        root.setBackground(new Background(createBackgroundImage("/Images/mainmenubackground.jpg")));
        stage.show();
//        User.getLoggedInUser().client.sendMessage(User.getLoggedInUser().getNickname());
    }

    @FXML
    public void initialize() {
        setLabelText();
        indicator1rec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/indicator.png")))));
        indicator2rec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/indicator.png")))));
        indicator3rec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/indicator.png")))));
        indicator4rec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/indicator.png")))));
        indicator1rec.setVisible(false);
        indicator2rec.setVisible(false);
        indicator3rec.setVisible(false);
        indicator4rec.setVisible(false);
        indicator1.getChildren().addAll(indicator1rec);
        indicator2.getChildren().addAll(indicator2rec);
        indicator3.getChildren().addAll(indicator3rec);
        indicator4.getChildren().addAll(indicator4rec);
        profileMenuRec.setFill(new ImagePattern(new Image(String.valueOf(MainMenu.class.getResource("/Images/buttonimage1.png")))));
        startNewGameRec.setFill(new ImagePattern(new Image(String.valueOf(MainMenu.class.getResource("/Images/buttonimage1.png")))));
        logOutRec.setFill(new ImagePattern(new Image(String.valueOf(MainMenu.class.getResource("/Images/buttonimage1.png")))));
        exitGameRec.setFill(new ImagePattern(new Image(String.valueOf(MainMenu.class.getResource("/Images/buttonimage1.png")))));
        profileMenuRec.setVisible(false);
        startNewGameRec.setVisible(false);
        logOutRec.setVisible(false);
        exitGameRec.setVisible(false);
        profileMenuLabel=new Label("Profile menu");
        profileMenuLabel.setFont(new Font("Tiro Gurmukhi",30));
        profileMenuLabel.setTextFill(Color.BLACK);
        profileMenuLabel.setLayoutX(20);
        profileMenuLabel.setOnMouseClicked(this::goToProfileMenu);
        profileMenuLabel.setOnMouseEntered(event -> {
            profileMenuLabel.setTextFill(new Color(0.538,0.51,0.002,1));
            profileMenuRec.setVisible(true);
            indicator1rec.setVisible(true);
        });
        profileMenuLabel.setOnMouseExited(event -> {
            profileMenuLabel.setTextFill(Color.BLACK);
            profileMenuRec.setVisible(false);
            indicator1rec.setVisible(false);
        });
        startNewGameLabel=new Label("Start new game");
        startNewGameLabel.setFont(new Font("Tiro Gurmukhi",30));
        startNewGameLabel.setTextFill(Color.BLACK);
        startNewGameLabel.setOnMouseClicked(this::startNewGame);
        startNewGameLabel.setOnMouseEntered(event -> {
            startNewGameLabel.setTextFill(new Color(0.538,0.51,0.002,1));
            startNewGameRec.setVisible(true);
            indicator2rec.setVisible(true);
        });
        startNewGameLabel.setOnMouseExited(event -> {
            startNewGameLabel.setTextFill(Color.BLACK);
            startNewGameRec.setVisible(false);
            indicator2rec.setVisible(false);
        });
        logOutRLabel=new Label("Log out");
        logOutRLabel.setFont(new Font("Tiro Gurmukhi",30));
        logOutRLabel.setTextFill(Color.BLACK);
        logOutRLabel.setLayoutX(50);
        logOutRLabel.setOnMouseClicked(this::Logout);
        logOutRLabel.setOnMouseEntered(event -> {
            logOutRLabel.setTextFill(new Color(0.538,0.51,0.002,1));
            logOutRec.setVisible(true);
            indicator3rec.setVisible(true);
        });
        logOutRLabel.setOnMouseExited(event -> {
            logOutRLabel.setTextFill(Color.BLACK);
            logOutRec.setVisible(false);
            indicator3rec.setVisible(false);
        });
        exitGameLabel=new Label("Exit game");
        exitGameLabel.setFont(new Font("Tiro Gurmukhi",30));
        exitGameLabel.setTextFill(Color.BLACK);
        exitGameLabel.setLayoutX(40);
        exitGameLabel.setOnMouseClicked(this::exitGame);
        exitGameLabel.setOnMouseEntered(event -> {
            exitGameLabel.setTextFill(new Color(0.538,0.51,0.002,1));
            exitGameRec.setVisible(true);
            indicator4rec.setVisible(true);
        });
        exitGameLabel.setOnMouseExited(event -> {
            exitGameLabel.setTextFill(Color.BLACK);
            exitGameRec.setVisible(false);
            indicator4rec.setVisible(false);
        });
        profileMenu.getChildren().addAll(profileMenuRec,profileMenuLabel);
        startNewGame.getChildren().addAll(startNewGameRec,startNewGameLabel);
        logOut.getChildren().addAll(logOutRec,logOutRLabel);
        exitGame.getChildren().addAll(exitGameRec,exitGameLabel);
    }

    public void setLabelText() {
        Username.setText("welcome "+ User.getLoggedInUser().getNickname());
    }

    public void goToProfileMenu(MouseEvent mouseEvent) {
        soundPlayer("/Sounds/paper.mp3");
        ProfileMenu profileMenu = new ProfileMenu();
        try {
            profileMenu.start(LoginMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void startNewGame(MouseEvent mouseEvent) {
        soundPlayer("/Sounds/sword.mp3");

        try {
            new PreGameMenu().start(LoginMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Logout(MouseEvent mouseEvent) {
        soundPlayer("/Sounds/paper.mp3");
        MainMenuController.logout();
        LoginMenu.mediaPlayer.stop();
        LoginMenu loginMenu = new LoginMenu();
        try {
            loginMenu.start(LoginMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void exitGame(MouseEvent mouseEvent) {
        LoginMenu.stage.close();
    }

    public void mouseEnteredSound(MouseEvent mouseEvent) {
        soundPlayer("/Sounds/Tik.mp3");
    }
    private void soundPlayer(String path) {
        Media media = new Media(getClass().getResource(path).toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }

    public void showRequests(MouseEvent mouseEvent) {
        System.out.println("hi");
        Stage reqMenu = new Stage();
        Pane pane = new Pane();
        pane.setMinWidth(300);
        pane.setMinHeight(400);
        Scene scene = new Scene(pane);
        VBox reqs = new VBox();
        reqs.setAlignment(Pos.CENTER);
        for (String req : User.getLoggedInUser().getRequests()){
            HBox hBox = new HBox(5);
            String[] reqParts = req.split("\\.");
            Label labelName = new Label(reqParts[0]);
            hBox.getChildren().add(labelName);
            Button accept = new Button("Accept");
            accept.setOnMouseClicked(event -> {
                User.getLoggedInUser().addFriend(reqParts[0]);
                User.getLoggedInUser().getRequests().remove(req);
            });
            Button reject = new Button("Reject");
            accept.setOnMouseClicked(event -> {
                User.getLoggedInUser().getRequests().remove(req);
            });
            hBox.getChildren().addAll(accept, reject);
            reqs.getChildren().add(hBox);
        }
        Button exit = new Button("exit");
        reqs.getChildren().add(exit);
        exit.setOnMouseClicked(event -> {
            reqMenu.close();
        });
        TextField textField = new TextField("username");
        textField.setMaxWidth(200);
        Button sendReq = new Button("send");
        sendReq.setOnMouseClicked(event -> {
            User.getLoggedInUser().addFriend(textField.getText());
            User.getLoggedInUser().client.sendMessage("req:" + textField.getText() + ":" + User.getLoggedInUser().getUsername());
        });
        reqs.getChildren().addAll(textField, sendReq);
        pane.getChildren().add(reqs);
        reqMenu.setScene(scene);
        reqMenu.show();
    }
    private BackgroundImage createBackgroundImage (String address) {
        Image image = new Image(Game.class.getResource(address).toExternalForm(), 1280 ,768, false, false);
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
}