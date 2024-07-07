package controller;


import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Card;
import model.Deck;
import model.Game;
import model.User;
import view.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Client extends Thread {


    public Game game;
    public User user;
    public PreGameMenu pregameMenu;
    Socket socket;
    DataOutputStream sendBuffer;
    DataInputStream receiveBuffer;

    public Client(Game game, User loggedInUser) {
        this.game = game;
        this.user = loggedInUser;
    }

    @Override
    public void run(){
        try {
            socket = new Socket("127.0.0.1", 34800);
            sendBuffer = new DataOutputStream(socket.getOutputStream());
            receiveBuffer = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.err.println("unable to initialize socket");
            throw new RuntimeException(e);
        }
        System.out.println("new Client " + this.toString() + "socket be " + socket.toString());
        getMessageFromOtherClient();
    }


    public void sendMessage(String command) {
        try {
            System.out.println(command);
            sendBuffer.writeUTF(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void getMessageFromOtherClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String message;
                while (true) {
                    try {
                        message = receiveBuffer.readUTF();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(message);
                   serverResponseToAction(message);
                }
            }
        }).start();
    }

    private void serverResponseToAction(String response) {
        Card card = null;
        AtomicReference<HBox> target = new AtomicReference<>();
        String[] components = response.split("\\.");
        System.out.println(Arrays.toString(components) + "reached response");
        if (components.length == 7) {
            card = new Card(components[0], Integer.parseInt(components[1]), Boolean.parseBoolean(components[2]), Integer.parseInt(components[3]), components[4], Integer.parseInt(components[5]), Boolean.parseBoolean(components[6]));
            Card finalCard = card;
            Platform.runLater(() -> {
                if ( Integer.parseInt(components[5]) != 7)
                    game.enemyPlaceCard(finalCard, game.hBoxes.get(Integer.parseInt(components[5])));
                else
                    game.enemyPlaceCard(finalCard, game.hBoxes.get(0));
            });
        } else if (components[0].equals("registered")) {
            Platform.runLater(()-> {
                User newUser = new User(components[1], components[2], components[3], components[4]);
                User.setLoggedInUser(newUser);
                User.getLoggedInUser().client= this;
                MainMenu mainMenu = new MainMenu();
                try {
                    mainMenu.start(LoginMenu.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } else if (components[0].equals("logged")) {
            Platform.runLater(()-> {
                User newUser = new User(components[1], components[2], components[3], components[4]);
                User.setLoggedInUser(newUser);
                User.getLoggedInUser().client= this;
                MainMenu mainMenu = new MainMenu();
                try {
                    mainMenu.start(LoginMenu.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } else if (components.length == 2 && components[1].equals("accepted")) {
            Platform.runLater(()-> {
                User.getLoggedInUser().addFriend(components[0]);
                System.out.println("friend list of "+User.getLoggedInUser().getUsername()+" is: "+User.getLoggedInUser().getFriends().toString());
            });
        } else if (components[0].equals("searchResult")) {
            Platform.runLater(()-> {
                User user1 = new User(components[1], "1234", "a" , "a");
                user1.setRank(Integer.parseInt(components[2]));
                user1.setWonGame(Integer.parseInt(components[3]));
                ProfileMenu.searchResult = user1;
            });
        } else if (components[0].equals("unsuccessful")) {
            Platform.runLater(()-> {
                new Alert(Alert.AlertType.WARNING).show();
            });
        } else if (components.length == 2 && components[1].equals("reaction")) {
            Platform.runLater(()-> {
                game.gameLauncher.getReaction(Integer.parseInt(components[0]));
            });
        } else if (components.length == 2 && components[1].equals("passed")) {
                game.gameLauncher.hasPlayed = false;
                game.gameLauncher.hasPassed = false;
        } else if (components.length == 2 && components[1].equals("newRound")) {
                game.newRound(game);
        } else if (components.length == 2 && components[1].equals("isDone")) {
                game.gameLauncher.enemyIsDone = true;
        } else if (components.length == 2 && components[1].equals("startGame")) {
            Platform.runLater(()-> {
                User.getLoggedInUser().currentOponentName = components[0];
                pregameMenu.goToVetoMenu();
            });
        } else if (components.length == 1) {
            User.getLoggedInUser().addReq(components[0]);
        } else if (components.length == 3 && components[1].equals("invite")) {
            System.out.println("reached stage");
            Platform.runLater(()-> {
                MainMenu.menuTest(components[0], this);
            });
        } else if (components.length == 4) {
            Platform.runLater(()-> {
                game.gameLauncher.addMessageToGlobalChat(components[0], components[1], components[2], components[3]);
            });
        }
    }
}


