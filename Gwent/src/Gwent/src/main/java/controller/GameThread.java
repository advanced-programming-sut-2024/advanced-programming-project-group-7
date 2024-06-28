package controller;

import model.Card;
import model.Game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GameThread extends  Thread{
    private final Socket socket2;
    private final Socket socket1;

    private int turn = 0;
    public GameThread(Socket socket1, Socket socket2) {
        this.socket1 = socket1;
        this.socket2 = socket2;
    }
    @Override
    public void run() {
        try {
            DataInputStream dataInputStream1 = new DataInputStream(socket1.getInputStream());
            DataOutputStream dataOutputStream1 = new DataOutputStream(socket1.getOutputStream());
            DataInputStream dataInputStream2 = new DataInputStream(socket2.getInputStream());
            DataOutputStream dataOutputStream2 = new DataOutputStream(socket2.getOutputStream());

            while (true) {
                String command1 = dataInputStream1.readUTF(); // Read the command from the client
                String[] parts1 = command1.split(":");
                String command2 = dataInputStream2.readUTF(); // Read the command from the client
                String[] parts2 = command2.split(":");

                if (turn % 2 == 1) {
                    if (parts1[0].equals("card")) {
                        dataOutputStream2.writeUTF("card1 is" + parts1[1]);
                    }
                } else if (turn % 2 == 0) {
                    if (parts2[0].equals("card")) {
                        dataOutputStream1.writeUTF("card2 is" + parts1[1]);
                    }
                }
                dataOutputStream1.flush();
                dataOutputStream2.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
