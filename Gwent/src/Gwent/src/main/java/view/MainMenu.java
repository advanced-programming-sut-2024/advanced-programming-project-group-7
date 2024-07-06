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
    public Pane televisionPane;

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
        profileMenuLabel.setTextFill(Color.WHITE);
        profileMenuLabel.setLayoutX(20);
        profileMenuLabel.setOnMouseClicked(this::goToProfileMenu);
        profileMenuLabel.setOnMouseEntered(event -> {
            profileMenuLabel.setTextFill(new Color(0.538,0.51,0.002,1));
            profileMenuRec.setVisible(true);
            indicator1rec.setVisible(true);
        });
        profileMenuLabel.setOnMouseExited(event -> {
            profileMenuLabel.setTextFill(Color.WHITE);
            profileMenuRec.setVisible(false);
            indicator1rec.setVisible(false);
        });
        startNewGameLabel=new Label("Start new game");
        startNewGameLabel.setFont(new Font("Tiro Gurmukhi",30));
        startNewGameLabel.setTextFill(Color.WHITE);
        startNewGameLabel.setOnMouseClicked(this::startNewGame);
        startNewGameLabel.setOnMouseEntered(event -> {
            startNewGameLabel.setTextFill(new Color(0.538,0.51,0.002,1));
            startNewGameRec.setVisible(true);
            indicator2rec.setVisible(true);
        });
        startNewGameLabel.setOnMouseExited(event -> {
            startNewGameLabel.setTextFill(Color.WHITE);
            startNewGameRec.setVisible(false);
            indicator2rec.setVisible(false);
        });
        logOutRLabel=new Label("Log out");
        logOutRLabel.setFont(new Font("Tiro Gurmukhi",30));
        logOutRLabel.setTextFill(Color.WHITE);
        logOutRLabel.setLayoutX(50);
        logOutRLabel.setOnMouseClicked(this::Logout);
        logOutRLabel.setOnMouseEntered(event -> {
            logOutRLabel.setTextFill(new Color(0.538,0.51,0.002,1));
            logOutRec.setVisible(true);
            indicator3rec.setVisible(true);
        });
        logOutRLabel.setOnMouseExited(event -> {
            logOutRLabel.setTextFill(Color.WHITE);
            logOutRec.setVisible(false);
            indicator3rec.setVisible(false);
        });
        exitGameLabel=new Label("Exit game");
        exitGameLabel.setFont(new Font("Tiro Gurmukhi",30));
        exitGameLabel.setTextFill(Color.WHITE);
        exitGameLabel.setLayoutX(40);
        exitGameLabel.setOnMouseClicked(this::exitGame);
        exitGameLabel.setOnMouseEntered(event -> {
            exitGameLabel.setTextFill(new Color(0.538,0.51,0.002,1));
            exitGameRec.setVisible(true);
            indicator4rec.setVisible(true);
        });
        exitGameLabel.setOnMouseExited(event -> {
            exitGameLabel.setTextFill(Color.WHITE);
            exitGameRec.setVisible(false);
            indicator4rec.setVisible(false);
        });
        profileMenu.getChildren().addAll(profileMenuRec,profileMenuLabel);
        startNewGame.getChildren().addAll(startNewGameRec,startNewGameLabel);
        logOut.getChildren().addAll(logOutRec,logOutRLabel);
        exitGame.getChildren().addAll(exitGameRec,exitGameLabel);
        Rectangle televisionRec=new Rectangle(80,80);
        televisionRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/icons/television1.png")))));
        televisionRec.setLayoutY(20);
        televisionRec.setLayoutX(10);
        televisionPane.getChildren().add(televisionRec);
        televisionPane.setOnMouseClicked(this::showTelevisionMenu);
        televisionRec.setOnMouseEntered(event -> {
            televisionRec.setWidth(100);
            televisionRec.setHeight(100);
        });
        televisionRec.setOnMouseExited(event -> {
            televisionRec.setWidth(80);
            televisionRec.setHeight(80);
        });
    }


    private void showTelevisionMenu(MouseEvent mouseEvent) {
        Stage televisionMenu=new Stage();
        televisionMenu.setTitle("Television");
        Pane pane=new Pane();
        setSize(pane,400,600);
        Scene scene=new Scene(pane);
        VBox vBox=new VBox(10);
        Pane buttonPane=new Pane();
        Pane televisionPane=new Pane();
        Pane rankTelevisionPane=new Pane();
        Rectangle rankTelevisionRec=new Rectangle(600,400);
        rankTelevisionRec.setFill(new ImagePattern(new Image(String.valueOf(MainMenu.class.getResource("/Images/buttonimage1.png")))));
        rankTelevisionPane.getChildren().addAll(rankTelevisionRec);
        televisionPane.getChildren().addAll(rankTelevisionPane);
        Pane myPreviousGameTelevisionPane=new Pane();
        Rectangle myPreviousGameTelevisionRec=new Rectangle(600,400);
        myPreviousGameTelevisionRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/indicator.png")))));
        myPreviousGameTelevisionPane.getChildren().addAll(myPreviousGameTelevisionRec);
        Button rankPlayerGames=new Button("rank");
        rankPlayerGames.setOnMouseClicked(event -> {
            televisionPane.getChildren().clear();
            televisionPane.getChildren().addAll(rankTelevisionPane);
        });
        Button myPreviousGame=new Button("my games");
        myPreviousGame.setOnMouseClicked(event -> {
            televisionPane.getChildren().clear();
            televisionPane.getChildren().addAll(myPreviousGameTelevisionPane);
        });
        buttonPane.getChildren().addAll(rankPlayerGames,myPreviousGame);
        buttonPane.setMaxHeight(30);
        rankPlayerGames.setLayoutX(10);
        myPreviousGame.setLayoutX(100);
        vBox.getChildren().addAll(buttonPane,televisionPane);
        vBox.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(vBox);
        televisionMenu.setScene(scene);
        televisionMenu.show();
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


    private void setSize (Pane pane,double height,double width) {
        pane.setMinHeight(height);
        pane.setMaxHeight(height);
        pane.setMinWidth(width);
        pane.setMaxWidth(width);
    }
}