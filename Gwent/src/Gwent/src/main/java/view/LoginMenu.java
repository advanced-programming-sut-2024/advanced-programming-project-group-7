package view;

import controller.LoginMenuController;
import controller.GmailSender;
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
        root.setBackground(new Background(createBackgroundImage("/Images/background.jpg",1280,768)));
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
        rectangle1.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/buttonimage2.png")))));
        rectangle2.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/buttonimage2.png")))));
        rectangle3.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/buttonimage2.png")))));
        rectangle4.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/buttonimage2.png")))));
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
        hybridRectangle.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/buttonimage2.png")))));
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
        switchRectangle.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/buttonimage2.png")))));
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
        forgotPasswordRectangle.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/buttonimage2.png")))));
        forgotPasswordPane.getChildren().addAll(forgotPasswordRectangle, forgotPasswordButton);
    }


    public void signUp(MouseEvent mouseEvent) {
        Alert alert = null;
        if (!isLoggingIN) {
        alert = LoginMenuController.userRegister(nameField.getText()
                , password.getText(),confirmPWD.getText(), nicknameText.getText(), emailText.getText());
            if (alert == null) {
                try {
//                    GmailSender gmailSender=new  GmailSender(emailText.getText(),null);
//                    gmailSender.send();
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
                    vbox.setBackground(new Background(createBackgroundImage("",600,400)));
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
//                Stage twoFAStage=new Stage();
                twoFA();
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
//            Stage recoveryStage = new Stage();
//            VBox vbox1=new VBox(10);
//            vbox1.setAlignment(Pos.CENTER);
//            recoveryStage.setTitle("Password Recovery");
            Label usernameLabel = new Label("Username");
            usernameLabel.setFont(new Font("Baloo Bhaina 2 Bold",20));
            TextField usernameTextField = new TextField();
            usernameTextField.setMaxWidth(300);
            Label securityQuestion = new Label();
            TextField securityAnswerField = new TextField();
            securityQuestion.setFont(new Font("Baloo Bhaina 2 Bold",20));
            securityQuestion.setText(user.getSecurityQuestion());
            securityAnswerField.setMaxWidth(300);
//            Button confirmButton = new Button("Confirm");
            HBox confirmHbox=new HBox(10);
            Pane indicator=new Pane();
            Pane confirmPane=new Pane();
            Rectangle indicatorRec=new Rectangle(30,30);
            Rectangle confirmRec=new Rectangle(120,30);
            Label confirmLabel=new Label("Confirm");
            confirmLabel.setFont(new Font("Tiro Gurmukhi",20));
            confirmLabel.setTextFill(Color.BLACK);
            confirmLabel.setLayoutX(20);
            confirmLabel.setLayoutY(2);
            indicatorRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/indicator.png")))));
            confirmRec.setFill(new ImagePattern(new Image(String.valueOf(MainMenu.class.getResource("/Images/buttonimage1.png")))));
            confirmRec.setVisible(false);
            indicatorRec.setVisible(false);
            Rectangle helpRec=new Rectangle(30,30);
            helpRec.setVisible(false);
            indicator.getChildren().addAll(indicatorRec);
            confirmPane.getChildren().addAll(confirmRec,confirmLabel);
            confirmHbox.getChildren().addAll(indicator,confirmPane,helpRec);
            confirmHbox.setAlignment(Pos.BASELINE_CENTER);
            confirmHbox.setLayoutX(200);
            confirmHbox.setLayoutY(300);
            confirmHbox.setMaxHeight(60);
            confirmHbox.setMinHeight(60);
            Pane backButtonPane = new Pane();
            Rectangle backButtonRec=new Rectangle(40,40);
            backButtonRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/backbuttonimage.png")))));
            backButtonPane.getChildren().addAll(backButtonRec);
//            vbox1.getChildren().addAll(usernameLabel,usernameTextField,securityQuestion,securityAnswerField,confirmButton,backButton);
//            Scene scene1=new Scene(vbox1,600,400);
//            stage.setScene(scene1);
//            scene1.setFill(Color.BLACK);
            confirmLabel.setOnMouseClicked(event ->{
                    Alert alert = LoginMenuController.hasAnsweredCorrectly(usernameTextField.getText(), securityAnswerField.getText());
                    if (alert == null) {
                        User.setLoggedInUser(user);
//                        Stage newPass = new Stage();
//                        newPass.setTitle("Reset Password");
                        setNewPassword();
                    } else
                        alert.show();
            });
            confirmLabel.setOnMouseEntered(event -> {
                indicatorRec.setVisible(true);
                confirmRec.setVisible(true);
                confirmLabel.setTextFill(new Color(0.538,0.51,0.002,1));
            });
            confirmLabel.setOnMouseExited(event -> {
                indicatorRec.setVisible(false);
                confirmRec.setVisible(false);
                confirmLabel.setTextFill(Color.BLACK);
            });
            backButtonRec.setOnMouseClicked(event -> {
                try {
                    hasMusic = true;
                    start(LoginMenu.stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            backButtonRec.setOnMouseEntered(event -> {
                backButtonRec.setWidth(60);
                backButtonRec.setHeight(60);
            });
            backButtonRec.setOnMouseExited(event -> {
                backButtonRec.setWidth(40);
                backButtonRec.setHeight(40);
            });

            Pane changePasswprdPane=new Pane();
            VBox vbox = new VBox(15);
            vbox.setAlignment(Pos.CENTER);
            vbox.setMaxWidth(300);
            vbox.setLayoutX(200);
            vbox.setLayoutX(180);
            vbox.setLayoutY(50);
            backButtonPane.setLayoutX(10);
            backButtonPane.setLayoutY(10);
            vbox.getChildren().addAll(
                    usernameLabel, usernameTextField,
                    securityQuestion, securityAnswerField,
                    confirmHbox
            );
            changePasswprdPane.getChildren().addAll(backButtonPane,vbox);
            changePasswprdPane.setBackground(new Background(createBackgroundImage("/Images/forgotpasswordbackground.jpg",600,400)));
            Scene scene = new Scene(changePasswprdPane, 600, 400);
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


    public void setNewPassword(){
        Label newPassLabel = new Label("Enter new password");
        newPassLabel.setFont(new Font("Baloo Bhaina 2 Bold",20));
        newPassLabel.setTextFill(Color.BLACK);
        TextField newPassTextField = new TextField();
        newPassTextField.setMaxWidth(300);
        Label confirmNewPasswordLabel=new Label("Confirm new password");
        confirmNewPasswordLabel.setFont(new Font("Baloo Bhaina 2 Bold",20));
        confirmNewPasswordLabel.setTextFill(Color.BLACK);
        TextField confirmNewPassTextField = new TextField();
        confirmNewPassTextField.setMaxWidth(300);


        HBox generatePassHbox=new HBox(10);
        Pane indicator=new Pane();
        Rectangle indicatorRec=new Rectangle(30,30);
        indicatorRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/indicator.png")))));
        indicatorRec.setVisible(false);
        indicator.getChildren().addAll(indicatorRec);
        Pane generatePassPane=new Pane();
        Rectangle generatePassRec=new Rectangle(190,30);
        generatePassRec.setFill(new ImagePattern(new Image(String.valueOf(MainMenu.class.getResource("/Images/buttonimage1.png")))));
        Label generatePassLabel=new Label("Generate pass");
        generatePassLabel.setFont(new Font("Tiro Gurmukhi",20));
        generatePassLabel.setTextFill(Color.WHITE);
        generatePassLabel.setLayoutX(10);
        generatePassLabel.setLayoutY(2);
        generatePassRec.setVisible(false);
        Rectangle helpRec=new Rectangle(0,30);
        helpRec.setVisible(false);
        generatePassPane.getChildren().addAll(generatePassRec,generatePassLabel);
        generatePassHbox.getChildren().addAll(helpRec,indicator,generatePassPane);
        generatePassHbox.setAlignment(Pos.BASELINE_CENTER);
        generatePassHbox.setLayoutX(300);
        generatePassHbox.setLayoutY(300);
        generatePassHbox.setMaxHeight(60);
        generatePassHbox.setMinHeight(30);
        generatePassLabel.setOnMouseEntered(event1 -> {
            indicatorRec.setVisible(true);
            generatePassRec.setVisible(true);
            generatePassLabel.setTextFill(new Color(0.538,0.51,0.002,1));
        });
        generatePassLabel.setOnMouseExited(event1 -> {
            indicatorRec.setVisible(false);
            generatePassRec.setVisible(false);
            generatePassLabel.setTextFill(Color.WHITE);
        });
        generatePassLabel.setOnMouseClicked(event1 -> {
            String autoPass = LoginMenuController.generatePassword();
            newPassTextField.setText(autoPass);
            confirmNewPassTextField.setText(autoPass);
        });




        HBox confirmHbox=new HBox(10);
        Pane confirmPane=new Pane();
        Rectangle confirmRec=new Rectangle(150,30);
        confirmRec.setFill(new ImagePattern(new Image(String.valueOf(MainMenu.class.getResource("/Images/buttonimage1.png")))));
        confirmRec.setVisible(false);
        Label confirmLabel=new Label("Confirm");
        confirmLabel.setFont(new Font("Tiro Gurmukhi",20));
        confirmLabel.setTextFill(Color.WHITE);
        confirmLabel.setLayoutX(20);
        confirmLabel.setLayoutY(2);
        confirmPane.getChildren().addAll(confirmRec,confirmLabel);
        Pane indicator1=new Pane();
        Rectangle indicatorRec1=new Rectangle(30,30);
        indicatorRec1.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/indicator.png")))));
        indicatorRec1.setVisible(false);
        indicator1.getChildren().addAll(indicatorRec1);
        Rectangle helpRec1=new Rectangle(0,30);
        helpRec1.setVisible(false);
        confirmHbox.getChildren().addAll(indicator1,confirmPane,helpRec);
        confirmHbox.setAlignment(Pos.BASELINE_CENTER);
        confirmHbox.setLayoutX(200);
        confirmHbox.setLayoutY(300);
        confirmHbox.setMaxHeight(60);
        confirmHbox.setMinHeight(60);
        confirmLabel.setOnMouseEntered(event1 -> {
            indicatorRec1.setVisible(true);
            confirmRec.setVisible(true);
            confirmLabel.setTextFill(new Color(0.538,0.51,0.002,1));
        });
        confirmLabel.setOnMouseExited(event1 -> {
            indicatorRec1.setVisible(false);
            confirmRec.setVisible(false);
            confirmLabel.setTextFill(Color.WHITE);
        });
        confirmLabel.setOnMouseClicked(event1 -> {
            Alert alert1= LoginMenuController.setNewPassword(newPassTextField.getText(), confirmNewPassTextField.getText());
            if(alert1==null){
                User.setLoggedInUser(null);
                goToMainMenu();
            }else alert1.show();
        });
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setLayoutY(50);
        vbox.setLayoutX(190);
        vbox.setMaxWidth(400);
        vbox.getChildren().addAll(newPassLabel, newPassTextField,confirmNewPasswordLabel,confirmNewPassTextField, generatePassHbox,confirmHbox);




        Pane backButtonPane = new Pane();
        Rectangle backButtonRec=new Rectangle(40,40);
        backButtonRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/backbuttonimage.png")))));
        backButtonPane.getChildren().addAll(backButtonRec);
        backButtonPane.setLayoutX(10);
        backButtonPane.setLayoutY(10);
        backButtonRec.setOnMouseClicked(event -> {
            try {
                hasMusic = true;
                start(LoginMenu.stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        backButtonRec.setOnMouseEntered(event -> {
            backButtonRec.setWidth(60);
            backButtonRec.setHeight(60);
        });
        backButtonRec.setOnMouseExited(event -> {
            backButtonRec.setWidth(40);
            backButtonRec.setHeight(40);
        });


        Pane changePasswordPane=new Pane();
        changePasswordPane.getChildren().addAll(vbox,backButtonPane);
        changePasswordPane.setBackground(new Background(createBackgroundImage("/Images/setnewpasswordbackground.jpg",600,400)));
        Scene scene = new Scene(changePasswordPane, 600, 400);
        LoginMenu.stage.setScene(scene);
        LoginMenu.stage.show();

    }


    public void twoFA(){
        User user=User.getUserByUsername(nameField.getText());
        Pane twoFAPane=new Pane();
        VBox twoFAVbox=new VBox(20);
//                twoFAStage.setTitle("2FA");
        Label label=new Label("Please enter the number that we sent to your email");
        label.setFont(new Font("Baloo Bhaina 2 Bold",20));
        label.setTextFill(Color.WHITE);
        TextField randomNumber=new TextField();
        HBox confirmHbox=new HBox(10);
        Pane indicator=new Pane();
        Pane confirmPane=new Pane();
        Rectangle indicatorRec=new Rectangle(30,30);
        Rectangle confirmRec=new Rectangle(120,30);
        Label confirmLabel=new Label("Confirm");
        confirmLabel.setFont(new Font("Tiro Gurmukhi",20));
        confirmLabel.setTextFill(Color.WHITE);
        confirmLabel.setLayoutX(20);
        confirmLabel.setLayoutY(2);
        indicatorRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/indicator.png")))));
        confirmRec.setFill(new ImagePattern(new Image(String.valueOf(MainMenu.class.getResource("/Images/buttonimage1.png")))));
        confirmRec.setVisible(false);
        indicatorRec.setVisible(false);
        Rectangle helpRec=new Rectangle(30,30);
        helpRec.setVisible(false);
        indicator.getChildren().addAll(indicatorRec);
        confirmPane.getChildren().addAll(confirmRec,confirmLabel);
        confirmHbox.getChildren().addAll(indicator,confirmPane,helpRec);
        confirmHbox.setAlignment(Pos.BASELINE_CENTER);
        confirmHbox.setLayoutX(200);
        confirmHbox.setLayoutY(300);
        confirmHbox.setMaxHeight(60);
        confirmHbox.setMinHeight(60);
        twoFAVbox.setLayoutX(80);
        twoFAVbox.setLayoutY(100);
        Pane backButtonPane = new Pane();
        Rectangle backButtonRec=new Rectangle(40,40);
        backButtonRec.setFill(new ImagePattern(new Image(String.valueOf(LoginMenu.class.getResource("/Images/backbuttonimagewhite.png")))));
        backButtonPane.getChildren().addAll(backButtonRec);
        backButtonPane.setLayoutX(10);
        backButtonPane.setLayoutY(10);
        twoFAVbox.getChildren().addAll(label,randomNumber,confirmHbox);
        twoFAPane.getChildren().addAll(twoFAVbox,backButtonPane);
        confirmLabel.setOnMouseEntered(event -> {
            indicatorRec.setVisible(true);
            confirmRec.setVisible(true);
            confirmLabel.setTextFill(new Color(0.538,0.51,0.002,1));
        });
        confirmLabel.setOnMouseExited(event -> {
            indicatorRec.setVisible(false);
            confirmRec.setVisible(false);
            confirmLabel.setTextFill(Color.WHITE);
        });
//                String randomEmailedNumber=LoginMenuController.generateRandomNumber();
//                try {
//                    GmailSender gmailSender=new GmailSender(user.getEmailAddress(),randomEmailedNumber);
//                    gmailSender.send();
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
        confirmLabel.setOnMouseClicked(event -> {
//                    if(randomNumber.getText().equals(randomEmailedNumber)){
            goToMainMenu();
//                    }
//                    else{
//            Alert alert2FA=new Alert(Alert.AlertType.WARNING);
//            alert2FA.setHeaderText("this username is taken");
//            alert2FA.show();
//                    }
        });
        backButtonRec.setOnMouseClicked(event -> {
            try {
                hasMusic = true;
                start(LoginMenu.stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        backButtonRec.setOnMouseEntered(event -> {
            backButtonRec.setWidth(60);
            backButtonRec.setHeight(60);
        });
        backButtonRec.setOnMouseExited(event -> {
            backButtonRec.setWidth(40);
            backButtonRec.setHeight(40);
        });
        Scene scene=new Scene(twoFAPane,600,400);
        twoFAPane.setBackground(new Background(createBackgroundImage("/Images/2fabackgroundimage.jpg",600,400)));
        LoginMenu.stage.setScene(scene);
        LoginMenu.stage.show();
    }


    public void genratePassforSignup(MouseEvent mouseEvent) {
        String autoPass = LoginMenuController.generatePassword();
        password.setText(autoPass);
        confirmPWD.setText(autoPass);
    }


    private BackgroundImage createBackgroundImage (String address,int width,int height) {
        Image image = new Image(Game.class.getResource(address).toExternalForm(), width ,height, false, false);
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



