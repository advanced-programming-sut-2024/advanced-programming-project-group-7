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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.User;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Leaderboard extends Application {
    private TableView<User> table = new TableView<User>();

    private List<User> sortedUsers = MainMenu.users.stream()
            .sorted(Comparator.comparing(User::getWonGame).reversed())
            .limit(10)
            .collect(Collectors.toList());
    private final ObservableList<User> data =
            FXCollections.observableArrayList(sortedUsers);


    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setMinWidth(1280);
        stage.setMinHeight(768);
        table.setRowFactory(tv -> new TableRow<User>() {
            @Override
            public void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                if (getIndex() == 0 && item != null) {
                    setStyle("-fx-background-color: #FFD700;");
                } else if (getIndex() == 1 && item != null) {
                    setStyle("-fx-background-color: #C0C0C0;");
                } else if (getIndex() == 2 && item != null) {
                    setStyle("-fx-background-color: #CD7F32;");
                } else {
                    setStyle("");
                }
            }
        });

        final Label label = new Label("Leaderboard");
        label.setFont(new Font("Arial", 20));

        final Button button = new Button("Back");
        button.setOnMouseClicked(mouseEvent -> goToMainMenu());

        table.setEditable(true);

        TableColumn name = new TableColumn("name");
        name.setMinWidth(150);
        name.setCellValueFactory(
                new PropertyValueFactory<User, String>("username"));

        TableColumn score = new TableColumn("wins");
        score.setMinWidth(60);
        score.setCellValueFactory(
                new PropertyValueFactory<User, Integer>("wonGame"));

        TableColumn online = new TableColumn<>("is online ?");
        online.setMinWidth(60);
        online.setCellValueFactory(
                new PropertyValueFactory<User, Boolean>("isOnline"));

//        TableColumn lastGame = new TableColumn("lastGame");
//        lastGame.setMinWidth(60);
//        lastGame.setCellValueFactory(
//                (Callback<TableColumn.CellDataFeatures, ObservableValue>) new Button("hi"));


        table.setItems(data);
        table.getColumns().addAll(name, score, online);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, button);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

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
