package view;

import controller.ProfileMenuController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.BattleInfo;
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

    @Override
    public void start(Stage stage) throws Exception {
        URL url = LoginMenu.class.getResource("/FXML/ProfileMenu.fxml");
        BorderPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML

    public void initialize() {
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
}
