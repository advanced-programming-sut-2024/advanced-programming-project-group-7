package view;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.FinishedGame;
import model.OngoingGame;
import model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Leaderboard extends Application {
    public static ArrayList<OngoingGame> ongoingGames = new ArrayList<>();

    public static FinishedGame hisGame;
    private List<User> sortedUsers = MainMenu.users.stream()
            .sorted(Comparator.comparing(User::getWonGame).reversed())
            .limit(10)
            .collect(Collectors.toList());
    private final ObservableList<User> data =
            FXCollections.observableArrayList(sortedUsers);
    private Pane pane;


    @Override
    public void start(Stage stage) {
        pane = new Pane();
        Scene scene = new Scene(pane);
        pane.setMinHeight(768);
        pane.setMinWidth(1200);


        final Label label = new Label("Leaderboard");
        label.setFont(new Font("Arial", 40));

        final Button button = new Button("Back");
        button.setOnMouseClicked(mouseEvent -> goToMainMenu());

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().add(label);

        for (User user : sortedUsers) {
            HBox hBox = new HBox(20);
            Label name = new Label(user.getUsername());
            name.setFont(new Font("Arial", 20));
            Label wins = new Label(String.valueOf(user.getWonGame()));
            wins.setFont(new Font("Arial", 20));
            Label online = new Label(String.valueOf(user.isOnline));
            online.setFont(new Font("Arial", 20));
            Button watch = new Button("Watch");
            watch.setOnMouseClicked(mouseEvent -> {
                GameLauncher gameLauncher = new GameLauncher();
                gameLauncher.type = "replay";
                gameLauncher.fin = hisGame;
                try {
                    gameLauncher.start(LoginMenu.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            Button get = new Button("Get");
            get.setOnMouseClicked(event -> {
                User.getLoggedInUser().client.sendMessage("hisGame:" + user.getUsername()+":"+User.getLoggedInUser().getUsername());

            });
            hBox.getChildren().addAll(name, wins, online, watch, get);
            vbox.getChildren().add(hBox);
            if (sortedUsers.indexOf(user) == 0)
                hBox.setStyle("-fx-background-color: #FFD700");
            else if (sortedUsers.indexOf(user) == 1)
                hBox.setStyle("-fx-background-color: #C0C0C0");
            else if (sortedUsers.indexOf(user) == 2)
                hBox.setStyle("-fx-background-color: #CD7F32");
        }
        vbox.getChildren().add(button);
        pane.getChildren().add(vbox);
        vbox.setLayoutX(300);
        stage.setScene(scene);
        stage.show();
    }

    private void goToMainMenu() {
        MainMenu mainMenu = new MainMenu();
        try {
            mainMenu.start(LoginMenu.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
