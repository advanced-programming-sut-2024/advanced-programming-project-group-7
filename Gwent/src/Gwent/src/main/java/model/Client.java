package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

//public class Client  {
//    ServerSocket server;
//    Socket socket;
//    private DataInputStream receiveBuffer;
//    private DataOutputStream sendBuffer;
//
//    public void establishConnection(String address, int port) throws IOException {
//        socket=new Socket(address,port);
//        sendBuffer=new DataOutputStream(socket.getOutputStream());
//        receiveBuffer=new DataInputStream(socket.getInputStream());
//    }
//
//
//    public void start(String IP) throws IOException {
//        this.establishConnection(IP, 60000);
//        sendBuffer.writeUTF("aleik salam");
//    }
//
//    public void sendButton() throws IOException {
//        sendBuffer.writeUTF("im ready");
//    }
//}


public class Client {
    public static ArrayList<Client> clients = new ArrayList<>();

    static {
        Client clientTest = new Client();
        Socket socket1 = new Socket();
        clientTest.socket = socket1;

        clients.add(clientTest);
    }
    Socket socket;
    DataOutputStream sendBuffer;
    DataInputStream receiveBuffer;
    private boolean isReady;

    private void establishConnection(String address, int port) {
        try {
            socket = new Socket(address, port);
            sendBuffer = new DataOutputStream(socket.getOutputStream());
            receiveBuffer = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.err.println("unable to initialize socket");
            throw new RuntimeException(e);
        }
    }

    public boolean endConnection() {
        if (socket == null) return true;
        try {
            socket.close();
            receiveBuffer.close();
            sendBuffer.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String command) {
        try {
            sendBuffer.writeUTF(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String receiveMessage() {
        try {
            return receiveBuffer.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start(String IP) throws IOException {
        this.establishConnection(IP, 35000);
        Scanner scanner = new Scanner(System.in);
        String input;
        this.getMessageFromOtherClient();
        while (true) {
            this.sendMessage(scanner.nextLine());

        }
    }
    public void sendButton() throws IOException {
        sendBuffer.writeUTF("im ready");
        this.isReady=true;
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        clients.add(client);
//        System.out.println(clients.size());
        client.start("127.0.0.1");
    }
    public void getMessageFromOtherClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String message;
                while (true) {
                    try {
                        message = receiveBuffer.readUTF();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(message);
                }
            }
        }).start();
    }
}


