package controller;
//
//import javafx.application.Platform;
//import javafx.scene.layout.HBox;
//import model.Card;
//import model.Game;
//
//import java.io.*;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.Scanner;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicReference;
//
//public class Client extends Thread {
//    private final String serverName = "127.0.0.1";
//    private final int serverPort = 34600;
//    private Game game;
//    private int turn = 0;
//
//
//    public Client(Game game) {this.game = game;
//    }
//
//    @Override
//    public void run(){
//        this.dir();
//    }
//    private void establishConnection(String address, int port) {
//        try {
//            socket = new Socket(address, port);
//            sendBuffer = new DataOutputStream(socket.getOutputStream());
//            receiveBuffer = new DataInputStream(socket.getInputStream());
//        } catch (IOException e) {
//            System.err.println("unable to initialize socket");
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void dir() {
//
//
////        this.establishConnection("127.0.0.1", 36000);
////        Scanner scanner = new Scanner(System.in);
////        String input;
////        this.getMessageFromOtherClient();
////        while (true) {
////            TimeUnit.SECONDS.sleep(1);
////            this.sendMessage(scanner.nextLine());
//
//
////        System.out.println("yo");
////        try (Socket socket = new Socket(serverName, serverPort);
////             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
////             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
////             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
////
////            while (true) {
////                System.out.print("Enter command: ");
////                String commandToSend = consoleReader.readLine(); //here
////                if (commandToSend.equalsIgnoreCase("exit")) {
////                    break;
////                }
////
////                dataOutputStream.writeUTF(commandToSend);
////                dataOutputStream.flush();//todo what?
////
////                String response = dataInputStream.readUTF();
////                responseToCard(response);
////                System.out.println("Server response: " + response);
////            }
////
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//    }
//
//    private void responseToCard(String response) {
//        Card card = null;
//        AtomicReference<HBox> target = new AtomicReference<>();
//            String[] components = response.split("\\.");
//            card = new Card(components[0], Integer.parseInt(components[1]), Boolean.parseBoolean(components[2]), Integer.parseInt(components[3]), components[4], Integer.parseInt(components[5]), Boolean.parseBoolean(components[6]));
//        Card finalCard = card;
//        Platform.runLater(() -> {
//            System.out.println("got the card");
//
//            System.out.println(finalCard.getCardName());
//            game.hBoxes.get(7).getChildren().add(finalCard);
////            for (HBox hBox : game.hBoxes) {
////                if (components[8].equals(hBox.getClass().getSimpleName()))
////                    target.set(hBox);
////            }
////            target.get().getChildren().add(finalCard);
//            game.calculateLabels(game.playerFourthRow);
//        });
//    }
////    public void getMessageFromOtherClient() {
////        new Thread(new Runnable() {
////            @Override
////            public void run() {
////                String message;
////                while (true) {
//////                    TimeUnit.SECONDS.sleep(1);
////                    try {
////                        message =dataInputStream.readUTF();
////                    } catch (IOException e) {
////                        throw new RuntimeException(e);
////                    }
////                    System.out.println(message);
////                }
////            }
////        }).start();
////    }
//}

import javafx.application.Platform;
import javafx.scene.layout.HBox;
import model.Card;
import model.Game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class Client extends Thread {


    public Game game;
    Socket socket;
    DataOutputStream sendBuffer;
    DataInputStream receiveBuffer;

    public Client(Game game) {this.game = game;
    }

    @Override
    public void run(){
        try {
            this.dir();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void establishConnection(String address, int port) {
        try {
            socket = new Socket(address, port);
            sendBuffer = new DataOutputStream(socket.getOutputStream());
            receiveBuffer = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.err.println("unable to initialize socket");
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String command) {
        try {
            sendBuffer.writeUTF(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void dir() throws IOException, InterruptedException {
        this.establishConnection("127.0.0.1", 34600);
        Scanner scanner = new Scanner(System.in);
        String input;
        this.getMessageFromOtherClient();
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            this.sendMessage(scanner.nextLine());
        }
    }


    public void getMessageFromOtherClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String message;
                while (true) {
//                    TimeUnit.SECONDS.sleep(1);
                    try {
                        message = receiveBuffer.readUTF();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    responseToCard(message);
                }
            }
        }).start();
    }

    private void responseToCard(String response) {
        Card card = null;
        AtomicReference<HBox> target = new AtomicReference<>();
        String[] components = response.split("\\.");
        card = new Card(components[0], Integer.parseInt(components[1]), Boolean.parseBoolean(components[2]), Integer.parseInt(components[3]), components[4], Integer.parseInt(components[5]), Boolean.parseBoolean(components[6]));
        Card finalCard = card;
        Platform.runLater(() -> {
            System.out.println(finalCard.getCardName());
//            game.hBoxes.get(7).getChildren().add(finalCard);
                    target.set(game.hBoxes.get(Integer.parseInt(components[7])));

            target.get().getChildren().add(finalCard);
            game.calculateLabels(game.playerFourthRow);
        });
    }
}


