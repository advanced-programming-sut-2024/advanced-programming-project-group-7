package controller;

import javafx.application.Platform;
import model.Card;
import model.Game;
import model.User;
import model.factions.Monsters;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class GameServer extends Thread {

    private Socket oldSoc;
    private Socket newSoc;
    public static HashMap<String, Socket> onlineUsers=new HashMap<>();

    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        gameServer.start();
    }

    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(34600)) {
            System.out.println("Server is listening on port 34600");
            while (true) {
                newSoc = serverSocket.accept();
                if (newSoc != null) {
//                    DataInputStream dataInputStream1 = new DataInputStream(newSoc.getInputStream());
//                    String initialConnection = dataInputStream1.readUTF();
//                    onlineUsers.put(initialConnection, newSoc);
//                    System.out.println(initialConnection);
                    UserThread userThread = new UserThread(newSoc);
                    userThread.run();
                    newSoc = null;
                }
//                            GameThread gameThread = new GameThread(socket1, socket2);
//                            System.out.println("should be only");
//                            gameThread.run(); // may here
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
