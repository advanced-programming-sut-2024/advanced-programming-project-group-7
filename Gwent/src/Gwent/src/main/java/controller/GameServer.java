package controller;

import model.Card;
import model.Game;
import model.factions.Monsters;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer extends Thread {

    private Socket socket1;
    private Socket socket2;

    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        gameServer.start();
    }

    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(34600)) {
            System.out.println("Server is listening on port 34600");
            while (true) {
                socket1 = serverSocket.accept();
<<<<<<< Updated upstream
                if (socket1 != null) {
=======
                System.out.println("ajab");
                if (socket1 !=null) {
>>>>>>> Stashed changes
                    System.out.println(1);
                    while (true) {
                        socket2 = serverSocket.accept();
                        System.out.println("player2 connected");
                        if (socket2 != null) {
                            System.out.println(2);
                            GameThread gameThread = new GameThread(socket1, socket2);
                            System.out.println("should be only");
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
