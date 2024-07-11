package model;

import controller.GameServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class OngoingGame {
    public final Boolean isPublic;
    private final String type;
    public String player1;
    public String player2;
    public transient ArrayList<Socket> watchers = new ArrayList<>();
    public ArrayList<String> moves = new ArrayList<>();

    public OngoingGame(String player1, String player2, Boolean isPublic, String type) {
        this.isPublic = isPublic;
        this.player1 = player1;
        this.player2 = player2;
        this.type = type;
    }

    public void sendChat(String string) {
        for (Socket socket : watchers) {
            System.out.println("string for watchers: " + string);
            try {
                DataOutputStream targetUser = new DataOutputStream(socket.getOutputStream());
                targetUser.writeUTF(string);
                targetUser.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void saveMove(String move){
        sendNewMoveToAll(move);
        moves.add(move);
        System.out.println("new move added: "+move);
    }

    public void addNewWatcher(Socket socket) throws IOException {
        DataOutputStream targetUser = new DataOutputStream(socket.getOutputStream());
        for (String move: moves) {
            try {
                targetUser.writeUTF(move+".oldMove");
                targetUser.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        watchers.add(socket);
    }
    public synchronized void sendNewMoveToAll(String move) {
        for (Socket watcher : watchers) {
            if (watcher.isConnected()) {
                try {
                    DataOutputStream targetUser = new DataOutputStream(watcher.getOutputStream());
                    targetUser.writeUTF(move);
                    targetUser.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void finish() {
        FinishedGame finishedGame = new FinishedGame(player1, player2, moves);
        for (User user : GameServer.allUsers) {
            if (user.getUsername().equals(player1) || user.getUsername().equals(player2)) {
                user.finishedGames.add(finishedGame);
            }
        }
    }
}
