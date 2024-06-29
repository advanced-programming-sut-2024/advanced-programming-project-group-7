package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
            GameServer.onlineUsers.put(initialConnection, socket);
            dataOutputStream1.flush();
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
                } else if (parts1[0].equals("invite")) {
                    System.out.println(parts1);
                    if (GameServer.onlineUsers.containsKey(parts1[1])){
                        try {
                            DataOutputStream targetUser = new DataOutputStream(GameServer.onlineUsers.get(parts1[1]).getOutputStream());
                            targetUser.writeUTF(parts1[2] + ".invite");
                            System.out.println("part12: " +parts1[2]);
                            targetUser.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else if (parts1[0].equals("accept")) {
                    try {
                        DataOutputStream targetUser = new DataOutputStream(GameServer.onlineUsers.get(parts1[1]).getOutputStream());
                        targetUser.writeUTF(parts1[2] +".startGame");
                        targetUser.flush();
                        GameThread gameThread = new GameThread(GameServer.onlineUsers.get(parts1[1]), GameServer.onlineUsers.get(parts1[2]));
                        gameThread.start();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
