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
import javafx.scene.shape.Rectangle;
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

    @Override
    public void start(Stage stage) throws Exception {
        GameLauncher gameLauncher = new GameLauncher();
        Game game = new Game(gameLauncher);
        Client client = new Client(game, User.getLoggedInUser());
        client.start();

        URL url = LoginMenu.class.getResource("/FXML/MainMenu.fxml");
        BorderPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        root.setBackground(new Background(createBackgroundImage()));
        stage.show();
        User.getLoggedInUser().client.sendMessage(User.getLoggedInUser().getNickname());
    }

    @FXML
    public void initialize() {
        setLabelText();
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
            hBox.getChildren().add(accept);
            Button reject = new Button("Accept");
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
            User.getLoggedInUser().client.sendMessage("req:" + textField.getText());
        });
        pane.getChildren().add(reqs);
        reqMenu.setScene(scene);
        reqMenu.show();
    }
    private BackgroundImage createBackgroundImage () {
        Image image = new Image(Game.class.getResource("/Images/mainmenubackground.jpg").toExternalForm(), 1280 ,768, false, false);
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