package controller;

import javafx.application.Platform;
import model.OngoingGame;
import model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class UserThread extends Thread {
    public Socket socket;
    private DataInputStream dataInputStream1;
    private DataOutputStream dataOutputStream1;

    public UserThread(Socket socket1) {
        this.socket =socket1;
    }


    @Override
    public void run(){
        try {
            dataInputStream1 = new DataInputStream(socket.getInputStream());
            dataOutputStream1 = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream1 = new DataInputStream(socket.getInputStream());
            String initialConnection = dataInputStream1.readUTF();
            String[] splits = initialConnection.split(":");
            if (splits[0].equals("init")) {
                boolean legit = splits[4].matches("\\S+@\\S+.com");
                if (legit) {
                    GameServer.allUsers.add(new User(splits[1], splits[2], splits[3], splits[4]));
                    dataOutputStream1.writeUTF("registered." + splits[1] + "." + splits[2] + "." + splits[3] + "." + splits[4]);
                    GameServer.onlineUsers.put(splits[1], socket);
                    dataOutputStream1.flush();
                    System.out.println(GameServer.allUsers.toString() + "all users be like");
                } else {
                    System.out.println("reached here though");
                    dataOutputStream1.writeUTF("unsuccessful." + splits[1]);
                    dataOutputStream1.flush();
                }
            } else if (splits[0].equals("log")) {
                boolean legit = false;
                for (User user : GameServer.allUsers) {
                    if (user.getUsername().equals(splits[1]) && user.getPassword().equals(splits[2])) {
                        dataOutputStream1.writeUTF("logged." + user.getUsername()+"."+ user.getPassword()+
                                "."+user.getNickname()+"."+user.getEmailAddress());
                        GameServer.onlineUsers.put(splits[1], socket);
                        dataOutputStream1.flush();
                        legit = true;
                        break;
                    }
                }
                if (!legit) {
                    dataOutputStream1.writeUTF("unsuccessful." + splits[1]);
                    dataOutputStream1.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            String command1 = null; // Read the command from the client
            try {
                command1 = dataInputStream1.readUTF();
                System.out.println("command1: " + command1);
                String[] parts1 = command1.split(":");
                if (parts1[0].equals("req")) {
                    if (GameServer.onlineUsers.containsKey(parts1[1])){
                        try {
                            DataOutputStream targetUser = new DataOutputStream(GameServer.onlineUsers.get(parts1[1]).getOutputStream());
                            targetUser.writeUTF(parts1[2]);
                            targetUser.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else if (parts1[0].equals("init")) {
                    boolean legit = parts1[4].matches("\\S+@\\S+.com");
                    if (legit) {
                        GameServer.allUsers.add(new User(parts1[1], parts1[2], parts1[3], parts1[4]));
                        dataOutputStream1.writeUTF("registered." + parts1[1]+"."+ parts1[2] +"."+ parts1[3] +"."+ parts1[4]);
                        GameServer.onlineUsers.put(parts1[1], socket);
                        dataOutputStream1.flush();
                        System.out.println(GameServer.allUsers.toString() + "all users be like");
                    } else {
                        dataOutputStream1.writeUTF("unsuccessful." + parts1[1]);
                        dataOutputStream1.flush();
                    }
                } else if (parts1[0].equals("log")) {
                    boolean legit = false;
                    for (User user : GameServer.allUsers) {
                        if (user.getUsername().equals(parts1[1]) && user.getPassword().equals(parts1[2])) {
                            dataOutputStream1.writeUTF("logged." + user.getUsername()+"."+ user.getPassword()+
                                    "."+user.getNickname()+"."+user.getEmailAddress());
                            GameServer.onlineUsers.put(parts1[1], socket);
                            dataOutputStream1.flush();
                            legit = true;
                            break;
                        }
                    }
                    if (!legit) {
                        dataOutputStream1.writeUTF("unsuccessful." + parts1[1]);
                        dataOutputStream1.flush();
                    }

                } else if (parts1[0].equals("rejectInvite")) {
                    try {
                        DataOutputStream targetUser = new DataOutputStream(GameServer.onlineUsers.get(parts1[1]).getOutputStream());
                        targetUser.writeUTF("rejectInvite.freeUp");
                        targetUser.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (parts1[0].equals("searchPlayer")) {
                    boolean found = false;
                    for (User user : GameServer.allUsers) {
                        if (user.getUsername().equals(parts1[1])) {
                            dataOutputStream1.writeUTF("searchResult." + user.getUsername()+"."+user.getRank()+"."+user.getWonGame());
                            dataOutputStream1.flush();
                            found = true;
                        }
                    }
                    if (!found) {
                        dataOutputStream1.writeUTF("unsuccessful." + parts1[1]);
                        dataOutputStream1.flush();
                    }
                } else if (parts1[0].equals("request accepted")) {
                    for (User user : GameServer.allUsers) {
                        if (user.getUsername().equals(parts1[1])) {
                            user.addFriend(parts1[2]);
                        } else if (user.getUsername().equals(parts1[2])) {
                            user.addFriend(parts1[1]);
                        }
                    }
                } else if (parts1[0].equals("invite")) {
                    if (GameServer.onlineUsers.containsKey(parts1[1]) && GameServer.onlineUsers.get(parts1[1]).isConnected()){
                        try {
                            DataOutputStream targetUser = new DataOutputStream(GameServer.onlineUsers.get(parts1[1]).getOutputStream());
                            targetUser.writeUTF(parts1[2] + ".invite."+parts1[3]);
                            targetUser.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        try {
                            DataOutputStream targetUser = new DataOutputStream(GameServer.onlineUsers.get(parts1[2]).getOutputStream());
                            targetUser.writeUTF("rejectInvite.freeUp");
                            targetUser.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else if (parts1[0].equals("accept")) {
                    try {
                        GameServer.ongoingGames.put(parts1[1]+"-"+parts1[2],
                                new OngoingGame(parts1[1], parts1[2], Boolean.valueOf(parts1[3]), "friendly"));
                        DataOutputStream targetUser = new DataOutputStream(GameServer.onlineUsers.get(parts1[1]).getOutputStream());
                        targetUser.writeUTF(parts1[2] +".startGame");
                        targetUser.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (parts1[0].equals("random")) {
                    String dude = GameServer.randomChallenger;
                    if (dude == null)
                        GameServer.randomChallenger = parts1[1];
                    else
                        try {
                            DataOutputStream targetUser = new DataOutputStream(GameServer.onlineUsers.get(parts1[1]).getOutputStream());
                            targetUser.writeUTF(dude +".startGame");
                            targetUser.flush();
                            DataOutputStream random = new DataOutputStream(GameServer.onlineUsers.get(dude).getOutputStream());
                            random.writeUTF(parts1[1] +".startGame");
                            random.flush();
                            GameServer.randomChallenger = null;
                            GameServer.ongoingGames.put(dude+"-"+parts1[1],
                                    new OngoingGame(dude, parts1[1], true, "random"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                } else if (parts1[0].equals("emoji")) {
                    try {
                        DataOutputStream targetUser = new DataOutputStream(GameServer.onlineUsers.get(parts1[1]).getOutputStream());
                        targetUser.writeUTF(parts1[2] +".emoji");
                        targetUser.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (parts1[0].equals("cup")) {
                    if (GameServer.cupPlayers.size() < 4)
                        GameServer.cupPlayers.add(parts1[1]);
                    CupGame cupGame = new CupGame();
                    //todo cup
                } else if (parts1[0].equals("card")) {
                    try {
                        DataOutputStream targetUser = new DataOutputStream(GameServer.onlineUsers.get(parts1[1]).getOutputStream());
                        targetUser.writeUTF(parts1[2]);
                        targetUser.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (parts1[0].equals("reaction")) {
                    try {
                        DataOutputStream targetUser = new DataOutputStream(GameServer.onlineUsers.get(parts1[1]).getOutputStream());
                        targetUser.writeUTF(parts1[2]);
                        targetUser.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (parts1[0].equals("chat")) {
                    try {
                        DataOutputStream targetUser = new DataOutputStream(GameServer.onlineUsers.get(parts1[1]).getOutputStream());
                        targetUser.writeUTF(parts1[2]);
                        targetUser.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (parts1[0].equals("pass")) {
                    try {
                        DataOutputStream targetUser = new DataOutputStream(GameServer.onlineUsers.get(parts1[1]).getOutputStream());
                        targetUser.writeUTF(parts1[2]);
                        targetUser.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else
                    System.out.println("what was that mate?");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}