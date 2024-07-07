package model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class OngoingGame {
    private final Boolean isPublic;
    private final String type;
    public Socket socket1;
    public String player1;
    public String player2;
    public Socket socket2;
    public ArrayList<Socket> watchers = new ArrayList<>();
    public ArrayList<String> moves = new ArrayList<>();

    public OngoingGame(String player1, String player2, Boolean isPublic, String type) {
        this.isPublic = isPublic;
        this.player1 = player1;
        this.player2 = player2;
        this.type = type;
    }

    public void saveMove(String move){
        sendNewMoveToAll(move);
        moves.add(move);
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
            try {
                DataOutputStream targetUser = new DataOutputStream(watcher.getOutputStream());
                targetUser.writeUTF(move+".newMove");
                targetUser.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
