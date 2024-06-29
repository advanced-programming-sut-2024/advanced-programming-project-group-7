package controller;

import javafx.application.Platform;
import javafx.scene.layout.HBox;
import model.Card;
import model.Game;

import java.io.*;
import java.lang.annotation.Target;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

public class Client extends Thread {
    private final String serverName = "localhost";
    private final int serverPort = 9200;
    private Game game;

    public Client(Game game) {
        this.game = game;
    }

    @Override
    public void run(){

    }
    public void start() {
        try (Socket socket = new Socket(serverName, serverPort);
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {

            while (true) {
                System.out.print("Enter command: ");
                String commandToSend = consoleReader.readLine(); //here
                if (commandToSend.equalsIgnoreCase("exit")) {
                    break;
                }
                dataOutputStream.writeUTF(commandToSend);
                dataOutputStream.flush();

                String response = dataInputStream.readUTF();
                responseToCard(response);
                System.out.println("Server response: " + response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void responseToCard(String response) {
        HBox target = game.playerFourthRow;
        Card card = null;
        String[] components = response.split("\\s");
        if (components[0].equals("card"))
            card = new Card(components[1], Integer.parseInt(components[1]), Boolean.parseBoolean(components[2]), Integer.parseInt(components[3]), components[4], Integer.parseInt(components[5]), Boolean.parseBoolean(components[6]));
        Card finalCard = card;
        Platform.runLater(() -> {
            System.out.println("got the card");
            System.out.println(finalCard.getCardName());
            target.getChildren().add(finalCard);
            game.calculateLabels(game.playerFourthRow);
        });
    }
}


