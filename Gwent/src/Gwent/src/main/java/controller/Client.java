package controller;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

public class Client {
    private final String serverName = "192.168.112.96";
    private final int serverPort = 8090;


    public void start() {
        try (Socket socket = new Socket(serverName, serverPort);
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {

            while (true) {
                System.out.print("Enter command: ");
                String commandToSend = consoleReader.readLine();
                if (commandToSend.equalsIgnoreCase("exit")) {
                    break;
                }

                dataOutputStream.writeUTF(commandToSend);
                dataOutputStream.flush();

                String response = dataInputStream.readUTF();
                System.out.println("Server response: " + response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}


