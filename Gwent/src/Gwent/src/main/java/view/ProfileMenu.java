package view;

import com.sun.jdi.IntegerValue;
import controller.ProfileMenuController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.BattleInfo;
import model.Game;
import model.User;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class ProfileMenu extends Application {

    public Stage friendsStage=new Stage();
    public TextField newName;
    public TextField newPassword;
    public TextField oldPassword;
    public TextField newNickname;
    public TextField newEmail;
    public ComboBox numberOfBattles;
//    public TextField searchBar;
    public Label username;
    public Label nickname;
    public Label email;
    public Button searchButton;
    public Button battleLog;
    public Pane indicator1;
    public Pane indicator2;
    public Pane indicator3;
    public Pane indicator4;
    public Pane changeNamePane;
    public Pane changePasswordPane;
    public Pane changeNickNamePane;
    public Pane changeEmailPane;
    public Rectangle indicator1rec=new Rectangle(44,40);
    public Rectangle indicator2rec=new Rectangle(44,40);
    public Rectangle indicator3rec=new Rectangle(44,40);
    public Rectangle indicator4rec=new Rectangle(44,40);
    public Rectangle changeNameRec=new Rectangle(200,40);
    public Rectangle changePasswordRec=new Rectangle(200,40);
    public Rectangle changeNickNameRec=new Rectangle(200,40);
    public Rectangle changeEmailRec=new Rectangle(200,40);
    public Label changeNameLabel;
    public Label changePasswordLabel;
    public Label changeNickNameLabel;
    public Label changeEmailLabel;
    public Pane backButtonPane;
    public Pane friendRequestPane;
    public static MediaPlayer mediaPlayer;
    public boolean hasMusic=false;

    @Override
    public void start(Stage stage) throws Exception {
        URL url = LoginMenu.class.getResource("/FXML/ProfileMenu.fxml");
        BorderPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        root.setBackground(new Background(createBackgroundImage()));
        if(!hasMusic){
            Media music = new Media(String.valueOf(LoginMenu.class.getResource("/Sounds/geralt of ciria.mp3")));
            mediaPlayer = new MediaPlayer(music);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(0.05);
            mediaPlayer.play();
        }
        stage.show();
    }


    @FXML
    public void initialize() {
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
        changeNameRec.setFill(new ImagePattern(new Image(String.valueOf(MainMenu.class.getResource("/Images/buttonimage1.png")))));
        changePasswordRec.setFill(new ImagePattern(new Image(String.valueOf(MainMenu.class.getResource("/Images/buttonimage1.png")))));
        changeNickNameRec.setFill(new ImagePattern(new Image(String.valueOf(MainMenu.class.getResource("/Images/buttonimage1.png")))));
        changeEmailRec.setFill(new ImagePattern(new Image(String.valueOf(MainMenu.class.getResource("/Images/buttonimage1.png")))));
        changeNameRec.setVisible(false);
        changePasswordRec.setVisible(false);
        changeNickNameRec.setVisible(false);
        changeEmailRec.setVisible(false);
        changeNameLabel=new Label("Change name");
        changeNameLabel.setFont(new Font("Tiro Gurmukhi",25));
        changeNameLabel.setTextFill(Color.WHITE);
        changeNameLabel.setLayoutX(20);
        changeNameLabel.setOnMouseClicked(this::changeUsername);
        changeNameLabel.setOnMouseEntered(event -> {
            changeNameLabel.setTextFill(new Color(0.538,0.51,0.002,1));
            changeNameRec.setVisible(true);
            indicator1rec.setVisible(true);
        });
        changeNameLabel.setOnMouseExited(event -> {
            changeNameLabel.setTextFill(Color.WHITE);
            changeNameRec.setVisible(false);
            indicator1rec.setVisible(false);
        });
        changePasswordLabel=new Label("Change password");
        changePasswordLabel.setFont(new Font("Tiro Gurmukhi",25));
        changePasswordLabel.setTextFill(Color.WHITE);
        changePasswordLabel.setOnMouseClicked(this::changePassword);
        changePasswordLabel.setOnMouseEntered(event -> {
            changePasswordLabel.setTextFill(new Color(0.538,0.51,0.002,1));
            changePasswordRec.setVisible(true);
            indicator2rec.setVisible(true);
        });
        changePasswordLabel.setOnMouseExited(event -> {
            changePasswordLabel.setTextFill(Color.WHITE);
            changePasswordRec.setVisible(false);
            indicator2rec.setVisible(false);
        });
        changeNickNameLabel=new Label("Change nickname");
        changeNickNameLabel.setFont(new Font("Tiro Gurmukhi",25));
        changeNickNameLabel.setTextFill(Color.WHITE);
        changeNickNameLabel.setOnMouseClicked(this::changeNickname);
        changeNickNameLabel.setOnMouseEntered(event -> {
            changeNickNameLabel.setTextFill(new Color(0.538,0.51,0.002,1));
            changeNickNameRec.setVisible(true);
            indicator3rec.setVisible(true);
        });
        changeNickNameLabel.setOnMouseExited(event -> {
            changeNickNameLabel.setTextFill(Color.WHITE);
            changeNickNameRec.setVisible(false);
            indicator3rec.setVisible(false);
        });
        changeEmailLabel=new Label("Change email");
        changeEmailLabel.setFont(new Font("Tiro Gurmukhi",25));
        changeEmailLabel.setTextFill(Color.WHITE);
        changeEmailLabel.setLayoutX(15);
        changeEmailLabel.setOnMouseClicked(this::changeEmail);
        changeEmailLabel.setOnMouseEntered(event -> {
            changeEmailLabel.setTextFill(new Color(0.538,0.51,0.002,1));
            changeEmailRec.setVisible(true);
            indicator4rec.setVisible(true);
        });
        changeEmailLabel.setOnMouseExited(event -> {
            changeEmailLabel.setTextFill(Color.WHITE);
            changeEmailRec.setVisible(false);
            indicator4rec.setVisible(false);
        });
        changeNamePane.getChildren().addAll(changeNameRec,changeNameLabel);
        changePasswordPane.getChildren().addAll(changePasswordRec,changePasswordLabel);
        changeNickNamePane.getChildren().addAll(changeNickNameRec,changeNickNameLabel);
        changeEmailPane.getChildren().addAll(changeEmailRec,changeEmailLabel);
        numberOfBattles.setValue(5);
        username.setText("username");
        nickname.setText("nickname");
        email.setText("email");


        Rectangle backButtonRec=new Rectangle(60,60);
        backButtonRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/backbuttonimage.png")))));
        backButtonPane.getChildren().addAll(backButtonRec);
        backButtonPane.setLayoutX(20);
        backButtonPane.setLayoutY(20);
        backButtonRec.setOnMouseClicked(this::backToMainMenu);
        backButtonRec.setOnMouseEntered(event -> {
            backButtonRec.setWidth(80);
            backButtonRec.setHeight(80);
        });
        backButtonRec.setOnMouseExited(event -> {
            backButtonRec.setWidth(60);
            backButtonRec.setHeight(60);
        });


        Rectangle friendsManagerRec=new Rectangle(80,80);
        friendsManagerRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/icons/friendrequestaccept.png")))));
        friendRequestPane.getChildren().add(friendsManagerRec);
        friendsManagerRec.setLayoutY(200);
        friendsManagerRec.setLayoutX(10);
        friendsManagerRec.setOnMouseClicked(this::friendsManager);
        friendsManagerRec.setOnMouseEntered(event -> {
            friendsManagerRec.setWidth(100);
            friendsManagerRec.setHeight(100);
        });
        friendsManagerRec.setOnMouseExited(event -> {
            friendsManagerRec.setWidth(80);
            friendsManagerRec.setHeight(80);
        });


        Rectangle friendRequestRec=new Rectangle(60,60);
        friendRequestRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/icons/community.png")))));
        friendRequestPane.getChildren().add(friendRequestRec);
        friendRequestRec.setLayoutY(300);
        friendRequestRec.setLayoutX(20);
        friendRequestRec.setOnMouseClicked(this::showPlayerInfo);
        friendRequestRec.setOnMouseEntered(event -> {
            friendRequestRec.setWidth(80);
            friendRequestRec.setHeight(80);
        });
        friendRequestRec.setOnMouseExited(event -> {
            friendRequestRec.setWidth(60);
            friendRequestRec.setHeight(60);
        });


        Rectangle battleLogRec=new Rectangle(60,60);
        battleLogRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/icons/battlelog.png")))));
        friendRequestPane.getChildren().add(battleLogRec);
        battleLogRec.setLayoutY(400);
        battleLogRec.setLayoutX(20);
        battleLogRec.setOnMouseEntered(event -> {
            battleLogRec.setWidth(80);
            battleLogRec.setHeight(80);
        });
        battleLogRec.setOnMouseExited(event -> {
            battleLogRec.setWidth(60);
            battleLogRec.setHeight(60);
        });
        battleLogRec.setOnMouseClicked(event -> {

        });
    }


    public void changeUsername(MouseEvent mouseEvent) {
          Alert alert = convertToAlertChangeUsername(ProfileMenuController.changeUsername(newName.getText()));
        System.out.println(User.getLoggedInUser().getUsername());
        if (alert != null) {
            alert.show();
        }
    }


    public void changePassword(MouseEvent mouseEvent) {
        Alert alert= convertToAlertChangePassword(ProfileMenuController.changePassword(oldPassword.getText(),newPassword.getText()));
        if (alert != null) {
            alert.show();
        }
    }


    public void backToMainMenu(MouseEvent mouseEvent) {
        MainMenu mainMenu = new MainMenu();
        try {
            mediaPlayer.stop();
            mainMenu.start(LoginMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void changeNickname(MouseEvent mouseEvent) {
        Alert alert = convertToAlertChangeNickname(ProfileMenuController.changeNickname(newNickname.getText()));
        if (alert != null) {
            alert.show();
        }
    }


    public void changeEmail(MouseEvent mouseEvent) {
        Alert alert = convertToAlertChangeEmail(ProfileMenuController.changeEmail(email.getText()));
        if (alert != null) {
            alert.show();
        }
    }


    public void showPlayerInfo(MouseEvent mouseEvent) {
        Pane showPlayerInfo=new Pane();
        showPlayerInfo.setMinWidth(300);
        showPlayerInfo.setMinHeight(400);
        VBox vbox=new VBox(15);
        Label showPlayerInfoLabel=new Label("   show player info");
        showPlayerInfoLabel.setFont(new Font("Baloo Bhai 2 Bold",20));
        HBox searchbarHbox=new HBox(10);
        TextField searchBarTextField=new TextField();
        Rectangle searchRec=new Rectangle(20,20);
        Pane backButtonPane=new Pane();
        Rectangle backButtonRec=new Rectangle(30,30);
        backButtonRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/backbuttonimage.png")))));
        backButtonPane.getChildren().addAll(backButtonRec);
        backButtonPane.setLayoutX(10);
        backButtonPane.setLayoutY(10);
        backButtonRec.setOnMouseClicked(event -> {
            friendsStage.close();
        });
        backButtonRec.setOnMouseEntered(event -> {
            backButtonRec.setWidth(40);
            backButtonRec.setHeight(40);
        });
        backButtonRec.setOnMouseExited(event -> {
            backButtonRec.setWidth(30);
            backButtonRec.setHeight(30);
        });
        backButtonRec.setOnMouseClicked(event -> {
            friendsStage.close();
        });
        searchRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/icons/magnifire.png")))));
        searchRec.setOnMouseEntered(event -> {
            searchRec.setWidth(30);
            searchRec.setHeight(30);
        });
        searchRec.setOnMouseExited(event -> {
            searchRec.setWidth(20);
            searchRec.setHeight(20);
        });
        searchbarHbox.getChildren().addAll(searchBarTextField,searchRec);
        vbox.getChildren().addAll(showPlayerInfoLabel,searchbarHbox);
        vbox.setLayoutX(70);
        showPlayerInfo.getChildren().addAll(vbox,backButtonPane);
        searchRec.setOnMouseClicked(event -> {
            String name = searchBarTextField.getText();
            User user = User.getUserByUsername(name);
            if(user!=null){
                HBox userNameHbox=new HBox(10);
                Label usernameLabel=new Label("username : "+user.getUsername());
                Rectangle friendRequestRec=new Rectangle(20,20);
                friendRequestRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/icons/add-friend.png")))));
                userNameHbox.getChildren().addAll(usernameLabel,friendRequestRec);
                Label nickNameLabel=new Label("nickname : "+user.getNickname());
                Label highestScore=new Label("highestScore : "+user.getHighestScore());
                Label rank=new Label("rank : "+user.getHighestScore());
                Label totalGame=new Label("total game : "+user.getTotalGame());
                Label drawnGame=new Label("drawn game : "+user.getDrawnGame());
                Label wonGame=new Label("won game : "+user.getWonGame());
                Label lostGame=new Label("lost game : "+user.getLostGame());
                friendRequestRec.setOnMouseClicked(event1 -> {
                    User.getLoggedInUser().addFriend(user.getUsername());
                    User.getLoggedInUser().client.sendMessage("req:" + user.getUsername() + ":" + User.getLoggedInUser().getUsername());
                });
                vbox.getChildren().clear();
                vbox.getChildren().addAll(showPlayerInfoLabel,searchbarHbox,userNameHbox,nickNameLabel,highestScore,rank,totalGame,drawnGame,wonGame,lostGame);
                showPlayerInfo.getChildren().clear();
                showPlayerInfo.getChildren().addAll(vbox,backButtonPane);
            }
        });
        Scene scene=new Scene(showPlayerInfo);
        friendsStage.setScene(scene);
        friendsStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    public void showBattleLog(MouseEvent mouseEvent) {
        Pane showBattleLogPane=new Pane();
        VBox showBattleLogVbox=new VBox(10);
        Label showBattleLogLabel=new Label("Show battle log");
        User user = User.getLoggedInUser();
        int number = Integer.parseInt(numberOfBattles.getValue().toString());
        int listSize = user.getBattleLog().size();
        ContextMenu contextMenu = new ContextMenu();
        JComboBox<Integer> comboBox=new JComboBox<>();
        comboBox.addItem(1);
        comboBox.addItem(2);
        comboBox.addItem(3);
        comboBox.addItem(4);
        comboBox.addItem(5);
        comboBox.addItem(6);
        comboBox.addItem(7);
        comboBox.addItem(8);
        comboBox.addItem(9);
        comboBox.addItem(10);
        Button confirmButton=new Button("Confirm");
//        showBattleLogVbox.getChildren().addAll((Node) showBattleLogLabel,contextMenu,confirmButton);
        int startIndex = Math.max(listSize - number, 0);
        for (int i = listSize - 1; i >= startIndex; i--) {
            BattleInfo battleInfo = user.getBattleLog().get(i);
            MenuItem menuItem = new MenuItem(
                    "Date: " + battleInfo.getDate() +
                            "        Opponent: " + battleInfo.getOpponent() +
                            "        Winner: " + battleInfo.getWinner().getUsername() +
                            "\nRounds points: " + Arrays.deepToString(battleInfo.getRoundsPoints()) +
                            ", Final Points: " + Arrays.toString(battleInfo.getFinalPoints())
            );
            menuItem.setStyle("-fx-font-weight: bold; -fx-padding: 2;");
            contextMenu.getItems().add(menuItem);
        }
        contextMenu.show((Node) mouseEvent.getSource(), mouseEvent.getScreenX(), mouseEvent.getScreenY());
    }


    public void friendsManager(MouseEvent mouseEvent) {
        System.out.println("hi");
        Pane friendsManagerPane = new Pane();
        friendsManagerPane.setMinWidth(300);
        friendsManagerPane.setMinHeight(400);
        VBox friendManagerVbox=new VBox(10);
        HBox buttonsHbox=new HBox(20);
        Rectangle friendsListRec=new Rectangle(100,60);
        friendsListRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/icons/friendsbutton.png")))));
        Rectangle inboxesRec=new Rectangle(100,60);
        inboxesRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/icons/inboxbutton.png")))));
        buttonsHbox.getChildren().addAll(friendsListRec,inboxesRec);
        Pane friendsPane=new Pane();
        Pane inboxesPane=new Pane();
        friendManagerVbox.getChildren().addAll(buttonsHbox,friendsPane);
        friendsManagerPane.getChildren().add(friendManagerVbox);
        friendManagerVbox.setLayoutX(40);
        inboxesRec.setOnMouseClicked(event -> {
            VBox requests = new VBox();
            for (String request : User.getLoggedInUser().getRequests()){
                HBox hBox = new HBox(10);
                String[] requestParts = request.split("\\.");
                Label labelName = new Label(requestParts[0]);
                hBox.getChildren().add(labelName);
                Button accept = new Button("Accept");
                accept.setOnMouseClicked(event1 -> {
                    User.getLoggedInUser().addFriend(requestParts[0]);
                    User.getLoggedInUser().getRequests().remove(request);
                });
                Button reject = new Button("Reject");
                reject.setOnMouseClicked(event2 -> {
                    User.getLoggedInUser().getRequests().remove(request);
                });
                hBox.getChildren().addAll(accept, reject);
                requests.getChildren().add(hBox);
            }
            inboxesRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/icons/inboxbuttonselected.png")))));
            friendsListRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/icons/friendsbutton.png")))));
            buttonsHbox.getChildren().clear();
            buttonsHbox.getChildren().addAll(friendsListRec,inboxesRec);
            inboxesPane.getChildren().clear();
            inboxesPane.getChildren().addAll(requests);
            friendManagerVbox.getChildren().clear();
            friendManagerVbox.getChildren().addAll(buttonsHbox,inboxesPane);
            friendsManagerPane.getChildren().clear();
            friendsManagerPane.getChildren().add(friendManagerVbox);
        });
        friendsListRec.setOnMouseClicked(event -> {
            VBox friends=new VBox(10);
            for(String friendName:User.getLoggedInUser().getFriends() ){
                System.out.println(friendName);
                Label friendNameLabel=new Label(friendName);
                friends.getChildren().add(friendNameLabel);
            }
            inboxesRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/icons/inboxbutton.png")))));
            friendsListRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/icons/friendsbuttonselected.png")))));
            buttonsHbox.getChildren().clear();
            buttonsHbox.getChildren().addAll(friendsListRec,inboxesRec);
            friendsPane.getChildren().clear();
            friendsPane.getChildren().addAll(friends);
            friendManagerVbox.getChildren().clear();
            friendManagerVbox.getChildren().addAll(buttonsHbox,friendsPane);
            friendsManagerPane.getChildren().clear();
            friendsManagerPane.getChildren().add(friendManagerVbox);
        });
        Scene scene = new Scene(friendsManagerPane);
//        requests.getChildren().add(exit);
//        exit.setOnMouseClicked(event -> {
//            friendsStage.close();
//        });
        friendsStage.setScene(scene);
        friendsStage.show();
    }


    private BackgroundImage createBackgroundImage () {
        Image image = new Image(Game.class.getResource("/Images/profilemenubackground.jpg").toExternalForm(), 1280 ,768, false, false);
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
    public Alert convertToAlertChangeUsername(int num) {
        if(num==1){
            Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("this username is not valid");return alert;
        }
        if(num==2){
            Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("this username was taken");return alert;
        }
        return null;
    }
    public Alert convertToAlertChangeNickname(int num) {
        if(num==1){
            Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("invalid nickname");return alert;
        }

        return null;
    }
    public Alert convertToAlertChangeEmail(int num) {
        if(num==1){
            Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("invalid email");return alert;
        }
        return null;
    }
    public Alert convertToAlertChangePassword(int num) {
        if(num==1){
            Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("enter previous password");return alert;
        }
        if(num==2){
            Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("previous password is wrong");return alert;
        }
        if(num==3){
            Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("this password is not valid");return alert;

        }
        if(num==4){
            Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("this password is weak");return alert;
        }
        return null;
    }

}
