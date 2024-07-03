package view;

import controller.ProfileMenuController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.BattleInfo;
import model.Game;
import model.User;

import java.net.URL;
import java.util.Arrays;

public class ProfileMenu extends Application {

    public TextField newName;
    public TextField newPassword;
    public TextField oldPassword;
    public TextField newNickname;
    public TextField newEmail;
    public ComboBox numberOfBattles;
    public TextField searchBar;
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
    public Pane backButton;
    public Rectangle backButtonRec=new Rectangle(100,100);

    @Override
    public void start(Stage stage) throws Exception {
        URL url = LoginMenu.class.getResource("/FXML/ProfileMenu.fxml");
        BorderPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        root.setBackground(new Background(createBackgroundImage()));
        stage.show();
    }
    @FXML

    public void initialize() {
        backButtonRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/indicator.png")))));
//        backButton.getChildren().addAll(backButtonRec);
//        backButton.setLayoutY(2000);
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

    }

    public void changeUsername(MouseEvent mouseEvent) {
          Alert alert = ProfileMenuController.changeUsername(newName.getText());
        System.out.println(User.getLoggedInUser().getUsername());
        if (alert != null) {
            alert.show();
        }
    }

    public void changePassword(MouseEvent mouseEvent) {
        Alert alert= ProfileMenuController.changePassword(oldPassword.getText(),newPassword.getText());
        if (alert != null) {
            alert.show();
        }
    }

    public void backToMainMenu(MouseEvent mouseEvent) {
        MainMenu mainMenu = new MainMenu();
        try {
            mainMenu.start(LoginMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void changeNickname(MouseEvent mouseEvent) {
        Alert alert = ProfileMenuController.changeNickname(newNickname.getText());
        if (alert != null) {
            alert.show();
        }
    }

    public void changeEmail(MouseEvent mouseEvent) {
        Alert alert = ProfileMenuController.changeEmail(email.getText());
        if (alert != null) {
            alert.show();
        }
    }

    public void showPlayerInfo(MouseEvent mouseEvent) {
        String name = searchBar.getText();
        User user = User.getUserByUsername(name);
        if (user != null) {
            ContextMenu contextMenu = new ContextMenu();
            Label label1 = new Label("nick name: " + user.getNickname());
            Label label2 = new Label("username: " + user.getUsername());
            Label label3 = new Label("Rank: " + user.getRank());
            label1.setStyle("-fx-font-weight: bold; -fx-padding: 5;");
            label2.setStyle("-fx-font-weight: bold; -fx-padding: 5;");
            label3.setStyle("-fx-font-weight: bold; -fx-padding: 5;");
            CustomMenuItem item1 = new CustomMenuItem(label1, false);
            CustomMenuItem item2 = new CustomMenuItem(label2, false);
            CustomMenuItem item3 = new CustomMenuItem(label3, false);
            contextMenu.getItems().addAll(item1, item2, item3);
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                contextMenu.show(searchButton, mouseEvent.getScreenX() - 40, mouseEvent.getScreenY() + 20);
            }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

    public void showBattleLog(MouseEvent mouseEvent) {
        User user = User.getLoggedInUser();
        int number = Integer.parseInt(numberOfBattles.getValue().toString());
        int listSize = user.getBattleLog().size();
        ContextMenu contextMenu = new ContextMenu();
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
}
