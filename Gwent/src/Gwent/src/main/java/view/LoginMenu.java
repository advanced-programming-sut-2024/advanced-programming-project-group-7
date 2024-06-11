package view;

//import controller.LoginMenuController;
import controller.LoginMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class LoginMenu extends Application {

    public static Stage stage;
    public static MediaPlayer mediaPlayer;
    public TextField nameField;
    public boolean hasMusic = false;
    public TextField password;
    public Label nicknameLable;
    public TextField nicknameText;
    public Label emailLabel;
    public TextField emailText;
    public Button hybridButt;
    public boolean isLoggingIN = true;
    public TextField confirmPWD;
    public Button forgotPWD;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        if (!hasMusic) {
            Media music = new Media(LoginMenu.class.getResource("/Sounds/01 No Escape.mp3").toExternalForm());
            mediaPlayer = new MediaPlayer(music);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        }

        LoginMenu.stage = stage;
        URL url = LoginMenu.class.getResource("/FXML/LoginMenu.fxml");
        Image icon = new Image(LoginMenu.class.getResource("/Images/icons/gwent.jpg").toExternalForm());
        stage.getIcons().add(icon);
        BorderPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void signUp(MouseEvent mouseEvent) {
        Alert alert = null;
        if (!isLoggingIN) {
        alert = LoginMenuController.userRegister(nameField.getText()
                , password.getText(),confirmPWD.getText(), nicknameText.getText(), emailText.getText());
            if (alert == null) {
                try {
                    Stage recoveryStage = new Stage();
                    recoveryStage.setTitle("Password Recovery");

                    Label usernameLabel = new Label("Answer one the questions below");

                    Label[] securityQuestionLabels = new Label[3];
                    TextField[] securityAnswerFields = new TextField[3];
                    String[] securityQuestions = {
                            "What is your favorite color?",
                            "What is your pet's name?",
                            "Where were you born?"
                    };

                    for (int i = 0; i < 3; i++) {
                        securityQuestionLabels[i] = new Label(securityQuestions[i]);
                        securityAnswerFields[i] = new TextField();
                        securityAnswerFields[i].setMaxWidth(300);
                    }

                    Button confirmButton = new Button("Confirm");
                    Button backButton = new Button("Back");
                    confirmButton.setOnMouseClicked(event -> {
                        LoginMenuController.setSecutrityQ(nameField.getText(), //todo complete the method  todo ok but it's empty now
                                securityAnswerFields[0].getText(), securityAnswerFields[1].getText(), securityAnswerFields[2].getText());
                        goToMainMenu();
                    });
                    backButton.setOnMouseClicked(event -> {
                        try {
                            hasMusic = true;
                            start(LoginMenu.stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });


                    // Align components in center
                    VBox vbox = new VBox(10);
                    vbox.setAlignment(Pos.CENTER);
                    vbox.setMaxWidth(300);
                    vbox.getChildren().addAll(
                            usernameLabel,
                            securityQuestionLabels[0], securityAnswerFields[0],
                            securityQuestionLabels[1], securityAnswerFields[1],
                            securityQuestionLabels[2], securityAnswerFields[2],
                            confirmButton, backButton
                    );

                    Scene scene = new Scene(vbox, 600, 400);
                    LoginMenu.stage.setScene(scene);
                    LoginMenu.stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert.show();
            }
        } else {

        alert = LoginMenuController.userLogin(nameField.getText(), password.getText());
            if (alert == null) {
            goToMainMenu();
            } else {
                alert.show();
            }
        }
    }

    private void goToMainMenu() {
        MainMenu mainMenu = new MainMenu();
        try {
            mainMenu.start(LoginMenu.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void recoverPSWD(MouseEvent mouseEvent) {

        Stage recoveryStage = new Stage();
        recoveryStage.setTitle("Password Recovery");

        Label usernameLabel = new Label("Username:");
        TextField usernameTextField = new TextField();
        usernameTextField.setMaxWidth(300);

        Label securityQuestion = new Label();
        TextField securityAnswerField = new TextField();
        securityQuestion.setText("what?"); //User.getUserByUsername(nameField.getText()).getSecurityQuestion() //todo after method is completed
        securityAnswerField.setMaxWidth(300);

        Button confirmButton = new Button("Confirm");
        Button backButton = new Button("Back");
        confirmButton.setOnMouseClicked(event -> {
            LoginMenuController.handleForgottenPassword(usernameTextField.getText(), //todo complete the method and change the name
                    securityAnswerField.getText());
        });
        backButton.setOnMouseClicked(event -> {
            try {
                hasMusic = true;
                start(LoginMenu.stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        // Align components in center
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setMaxWidth(300);
        vbox.getChildren().addAll(
                usernameLabel, usernameTextField,
                securityQuestion, securityAnswerField,
                confirmButton, backButton
        );

        Scene scene = new Scene(vbox, 600, 400);
        LoginMenu.stage.setScene(scene);
        LoginMenu.stage.show();
    }

    public void Switch(MouseEvent mouseEvent) {
        isLoggingIN = !isLoggingIN;
        if (isLoggingIN) {
            confirmPWD.setVisible(false);
            nicknameLable.setVisible(false);
            nicknameText.setVisible(false);
            emailLabel.setVisible(false);
            emailText.setVisible(false);
            forgotPWD.setVisible(true);
            hybridButt.setText("login");
        } else {
            confirmPWD.setVisible(true);
            forgotPWD.setVisible(false);
            nicknameLable.setVisible(true);
            nicknameText.setVisible(true);
            emailLabel.setVisible(true);
            emailText.setVisible(true);
            hybridButt.setText("signup");
        }
    }
}


