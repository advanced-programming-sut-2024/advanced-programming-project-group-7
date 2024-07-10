package controller;

import model.OngoingGame;
import model.User;
import java.sql.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GameServer extends Thread {

    public static String randomChallenger;
    public static ArrayList<String> cupPlayers = new ArrayList<>();
    private Socket newSoc;
    public static HashMap<String, Socket> onlineUsers = new HashMap<>();
    public static ArrayList<User> allUsers = new ArrayList<>();
    public static HashMap<String, OngoingGame> ongoingGames = new HashMap<>();
    public static ArrayList<String> sample = new ArrayList<>();

    public static void main(String[] args) {
//        loadUsers();
        GameServer gameServer = new GameServer();
        gameServer.start();
    }

    public static User getUserByName(String s) {
        for (User user : allUsers) {
            if (user.getUsername().equals(s)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(34800)) {
            System.out.println("Server is listening on port 34800");
            while (true) {
                newSoc = serverSocket.accept();
                UserThread userThread = new UserThread(newSoc);
                userThread.start();
                System.out.println("hi");
                for (User user : allUsers) {
                    if (onlineUsers.get(user.getUsername()) != null && !onlineUsers.get(user.getUsername()).isConnected()) {
                        onlineUsers.get(user.getUsername()).close();
                        user.isOnline = false;
                    }
                }
            }
        } catch (IOException e) {
        }
    }
    public static OngoingGame getGameByName(String player) {
        for (String gamers :ongoingGames.keySet()){
            String [] players = gamers.split("-");
            if (players[0].equals(player) || players[1].equals(player)){
                return ongoingGames.get(gamers);
            }
        }
        return null;
    }
    public static void saveUsers(ArrayList<User> users) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/T.moobile/Desktop/GwentDataBase.db")) {
            // Drop the table if it exists
            String dropSql = "DROP TABLE IF EXISTS users";
            try (PreparedStatement pstmt = conn.prepareStatement(dropSql)) {
                pstmt.executeUpdate();
            }
            // Create the table
            String createSql = "CREATE TABLE IF NOT EXISTS users (\n"
                    + " username text NOT NULL,\n"
                    + " password text NOT NULL,\n"
                    + " nickname text NOT NULL,\n"
                    + " emailAddress text NOT NULL,\n"
                    + " wonGame integer NOT NULL,\n"
                    + " friends text NOT NULL\n"
                    + ");";
            try (PreparedStatement pstmt = conn.prepareStatement(createSql)) {
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/T.moobile/Desktop/GwentDataBase.db")) {
            String sql = "INSERT INTO users(username, password, nickname, emailAddress, wonGame, friends) VALUES(?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (User user : users) {
                    pstmt.setString(1, user.getUsername());
                    pstmt.setString(2, user.getPassword());
                    pstmt.setString(3, user.getNickname());
                    pstmt.setString(4, user.getEmailAddress());
                    pstmt.setInt(5, user.getWonGame());
                    // Convert the friends list to a comma-separated string
                    String friends = String.join(",", user.getFriends());
                    pstmt.setString(6, friends);
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void loadUsers() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/T.moobile/Desktop/GwentDataBase.db")) {
            String sql = "SELECT * FROM users";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("password"), rs.getString("emailAddress"));
                    user.setWonGame(rs.getInt("wonGame"));
                    // Convert the comma-separated string back to an ArrayList
                    String[] friendsArray = rs.getString("friends").split(",");
                    ArrayList<String> friends = new ArrayList<>(Arrays.asList(friendsArray));
                    user.setFriends(friends);
                    allUsers.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}