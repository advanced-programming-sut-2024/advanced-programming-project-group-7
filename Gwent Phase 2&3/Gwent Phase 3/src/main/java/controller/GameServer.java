package controller;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javafx.application.Platform;
import model.Card;
import model.Game;
import model.OngoingGame;
import model.User;
import model.factions.Monsters;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.JAXBException;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class GameServer extends Thread {

    public static String randomChallenger;
    public static ArrayList<String> cupPlayers = new ArrayList<>();
    private Socket newSoc;
    public static HashMap<String, Socket> onlineUsers = new HashMap<>();
    public static ArrayList<User> allUsers = new ArrayList<>();
    public static HashMap<String, OngoingGame> ongoingGames = new HashMap<>();

    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        gameServer.start();
    }

    @Override
    public void start() {
//        loadUsersFromXml();
        try (ServerSocket serverSocket = new ServerSocket(34800)) {
            System.out.println("Server is listening on port 34800");
            while (true) {
                newSoc = serverSocket.accept();
                UserThread userThread = new UserThread(newSoc);
                userThread.start();
//                saveUsersToXml();
            }
        } catch (IOException e) {
        }
    }
    private void saveUsersToXml() {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.writeValue(new File("users.xml"), allUsers);
            System.out.println("Users saved to users.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadUsersFromXml() {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            allUsers = xmlMapper.readValue(new File("users.xml"), ArrayList.class);
            System.out.println("Users loaded from users.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}