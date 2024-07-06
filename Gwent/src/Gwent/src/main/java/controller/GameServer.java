package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import javafx.application.Platform;
import model.Card;
import model.Game;
import model.User;
import model.factions.Monsters;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.HashMap;

public class GameServer extends Thread {

    private Socket newSoc;
    public static HashMap<String, Socket> onlineUsers = new HashMap<>();

    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        gameServer.start();
    }

    @Override
    public void start() {

        try (ServerSocket serverSocket = new ServerSocket(34800)) {
            System.out.println("Server is listening on port 34800");
            while (true) {
                newSoc = serverSocket.accept();
                UserThread userThread = new UserThread(newSoc);
                userThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
