package controller;

import model.Card;
import model.Game;
import model.factions.Monsters;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer extends Thread {

    private Socket socket1;
    public static Card lastCard;
    private Game game;
    private int connectedPlayers =0;
    private Socket socket2;

    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        gameServer.start();
    }

    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(9200)) {
            System.out.println("Server is listening on port 9200");
            while (true) {
                socket1 = serverSocket.accept();
                if (socket1 !=null) {
                    System.out.println(1);
                    while (true) {
                        socket2 = serverSocket.accept();
                        System.out.println("player1 connected");
                        if (socket2 != null) {
                            System.out.println(2);
                            GameThread gameThread = new GameThread(socket1, socket2);
                            gameThread.run(); // may here
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
