package view;

//import controller.LoginMenuController;
import controller.LoginMenuController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Game;
import model.User;

import java.net.URL;

public class LoginMenu extends Application {
    public static Stage stage;
    public static MediaPlayer mediaPlayer;
    public TextField nameField;
    public boolean hasMusic = false;
    public TextField password;
    public Label nicknameLabel;
    public TextField nicknameText;
    public Label emailLabel;
    public TextField emailText;
    public boolean isLoggingIN = true;
    public TextField confirmPWD;
    public Label switchButton;
    public Label hybridButton;
    public Label forgotPasswordButton;
    public Button PassGen;
    public Pane switchPane;
    public Pane forgotPasswordPane;
    public Pane hybridButtPane;
    public Rectangle switchRectangle = new Rectangle(100,50);
    public Rectangle hybridRectangle = new Rectangle(100,50);
    public Rectangle forgotPasswordRectangle=new Rectangle(300,50);
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
//            GameLauncher gameLauncher = new GameLauncher();
//            Game game = new Game(gameLauncher);
////            Client client = new Client(game, User.getLoggedInUser());
////            client.start();
        }
        LoginMenu.stage = stage;
        URL url = LoginMenu.class.getResource("/FXML/LoginMenu.fxml");
        Image icon = new Image(LoginMenu.class.getResource("/Images/icons/gwent.jpg").toExternalForm());
        stage.getIcons().add(icon);
        BorderPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Image image=new Image(LoginMenu.class.getResource("/Images/icons/CursorDefault.png").toExternalForm());
        stage.setScene(scene);
        scene.setCursor(new ImageCursor(image));
        root.setBackground(new Background(createBackgroundImage()));
        stage.show();
    }
    @FXML
    public void initialize(){
        Pane pane=new Pane();
        pane.setMinWidth(500);
        pane.setMinHeight(500);
        pane.setLayoutX(100);
        pane.setLayoutY(100);
        Rectangle rectangle1=new Rectangle(200,30);
        Rectangle rectangle2=new Rectangle(200,30);
        Rectangle rectangle3=new Rectangle(200,30);
        Rectangle rectangle4=new Rectangle(200,30);
        Circle circle=new Circle(20);
        circle.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/buttonimage.jpg")))));
        rectangle1.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/buttonimage.jpg")))));
        rectangle2.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/buttonimage.jpg")))));
        rectangle3.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/buttonimage.jpg")))));
        rectangle4.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/buttonimage.jpg")))));
        pane.getChildren().addAll(circle,rectangle1,rectangle2,rectangle3,rectangle4);

















        hybridButton = new Label("Login");
        hybridButton.setFont(new Font("Tiro Gurmukhi",30));
        hybridButton.setTextFill(Color.BLACK);
        hybridButton.setLayoutX(10);
        hybridButton.setLayoutY(7);
        hybridRectangle.setVisible(false);
        hybridButton.setOnMouseClicked(this::signUp);
        hybridButton.setOnMouseEntered(event -> {
            hybridRectangle.setVisible(true);
        });
        hybridButton.setOnMouseExited(event -> {
            hybridRectangle.setVisible(false);
        });
        hybridRectangle.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/buttonimage.jpg")))));
        hybridButtPane.getChildren().addAll(hybridRectangle, hybridButton);
        switchButton = new Label("Switch");
        switchButton.setFont(new Font("Tiro Gurmukhi",30));
        switchButton.setTextFill(Color.BLACK);
        switchButton.setLayoutX(10);
        switchButton.setLayoutY(7);
        switchRectangle.setVisible(false);
        switchButton.setOnMouseClicked(this::Switch);
        switchButton.setOnMouseEntered(event -> {
            switchRectangle.setVisible(true);
        });
        switchButton.setOnMouseExited(event -> {
            switchRectangle.setVisible(false);
        });
        switchRectangle.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/buttonimage.jpg")))));
        switchPane.getChildren().addAll(switchRectangle, switchButton);
        forgotPasswordButton = new Label("Forgot password");
        forgotPasswordButton.setFont(new Font("Tiro Gurmukhi",30));
        forgotPasswordButton.setTextFill(Color.BLACK);
        forgotPasswordButton.setLayoutX(38);
        forgotPasswordButton.setLayoutY(8);
        forgotPasswordRectangle.setVisible(false);
        forgotPasswordButton.setOnMouseClicked(this::recoverPSWD);
        forgotPasswordButton.setOnMouseEntered(event -> {
            forgotPasswordRectangle.setVisible(true);
        });
        forgotPasswordButton.setOnMouseExited(event -> {
            forgotPasswordRectangle.setVisible(false);
        });
        forgotPasswordRectangle.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/buttonimage.jpg")))));
        forgotPasswordPane.getChildren().addAll(forgotPasswordRectangle, forgotPasswordButton);
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
                       Alert alert1= LoginMenuController.setSecurityQ(nameField.getText(),
                                securityAnswerFields[0].getText(), securityAnswerFields[1].getText(), securityAnswerFields[2].getText());
                       if (alert1==null){
                           goToMainMenu();
                       }
                       else {
                           alert1.show();
                       }

                    });
                    backButton.setOnMouseClicked(event -> {
                        try {
                            hasMusic = true;
                            start(LoginMenu.stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
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
        User user = User.getUserByUsername(nameField.getText());
        if (user == null) {
            Alert alert  = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("enter your username first");
            alert.show();
        } else {
            Stage recoveryStage = new Stage();
//            VBox vbox1=new VBox(10);
//            vbox1.setAlignment(Pos.CENTER);
            recoveryStage.setTitle("Password Recovery");
            Label usernameLabel = new Label("Username");
            usernameLabel.setFont(new Font("Baloo Bhaina 2 Bold",20));
            TextField usernameTextField = new TextField();
            usernameTextField.setMaxWidth(300);
            Label securityQuestion = new Label();
            TextField securityAnswerField = new TextField();
            securityQuestion.setFont(new Font("Baloo Bhaina 2 Bold",20));
            securityQuestion.setText(user.getSecurityQuestion());
            securityAnswerField.setMaxWidth(300);
            Button confirmButton = new Button("Confirm");
            Button backButton = new Button("Back");
//            vbox1.getChildren().addAll(usernameLabel,usernameTextField,securityQuestion,securityAnswerField,confirmButton,backButton);
//            Scene scene1=new Scene(vbox1,600,400);
//            stage.setScene(scene1);
//            scene1.setFill(Color.BLACK);
            confirmButton.setOnMouseClicked(event ->{
                    Alert alert = LoginMenuController.hasAnsweredCorrectly(usernameTextField.getText(), securityAnswerField.getText());
                    if (alert == null) {
                        User.setLoggedInUser(user);
                        Stage newPass = new Stage();
                        newPass.setTitle("Reset Password");
                        TextField newPassTextField = new TextField();
                        newPassTextField.setMaxWidth(300);
                        Label newPassLabel = new Label("Enter new password");
                        TextField confirmNewPassTextField = new TextField();
                        confirmNewPassTextField.setMaxWidth(300);
                        Button randomPass = new Button("Generate Pass");
                        randomPass.setOnMouseClicked(event1 -> {
                            String autoPass = LoginMenuController.generatePassword();
                            newPassTextField.setText(autoPass);
                            confirmNewPassTextField.setText(autoPass);
                        });
                        Button confirmNewPass = new Button("Confirm");
                        confirmNewPass.setOnMouseClicked(event1 -> {
                           Alert alert1= LoginMenuController.setNewPassword(newPassTextField.getText(), confirmNewPassTextField.getText());
                            if(alert1==null){
                                User.setLoggedInUser(null);
                                goToMainMenu();
                            }else alert1.show();
                        });
                        VBox vbox = new VBox(10);
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setMaxWidth(300);
                        vbox.getChildren().addAll(newPassLabel, newPassTextField, confirmNewPassTextField, randomPass);
                        Scene scene = new Scene(vbox, 600, 400);
                        LoginMenu.stage.setScene(scene);
                        LoginMenu.stage.show();
                    } else
                        alert.show();
            });
            backButton.setOnMouseClicked(event -> {
                try {
                    hasMusic = true;
                    start(LoginMenu.stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });


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
    }

    public void Switch(MouseEvent mouseEvent) {
        isLoggingIN = !isLoggingIN;
        if (isLoggingIN) {
            confirmPWD.setVisible(false);
            nicknameLabel.setVisible(false);
            nicknameText.setVisible(false);
            emailLabel.setVisible(false);
            emailText.setVisible(false);
            PassGen.setVisible(false);
            forgotPasswordButton.setVisible(true);
            hybridButton.setText("login");
        } else {
            confirmPWD.setVisible(true);
            forgotPasswordButton.setVisible(false);
            nicknameLabel.setVisible(true);
            PassGen.setVisible(true);
            nicknameText.setVisible(true);
            emailLabel.setVisible(true);
            emailText.setVisible(true);
            hybridButton.setText("signup");
        }
    }

    public void genratePassforSignup(MouseEvent mouseEvent) {
        String autoPass = LoginMenuController.generatePassword();
        password.setText(autoPass);
        confirmPWD.setText(autoPass);
    }
    private BackgroundImage createBackgroundImage () {
        Image image = new Image(Game.class.getResource("/Images/background.jpg").toExternalForm(), 1280 ,768, false, false);
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



