package model;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket server;

    public Server(int port) {
        try {
            this.server = new ServerSocket(port);
        } catch (IOException var3) {
            IOException e = var3;
            e.printStackTrace();
        }

    }

    public void listen() throws IOException {
        while(true) {
            Socket socket = this.server.accept();
            this.handleConnection(socket);
        }
    }

    private void handleConnection(Socket socket) {
        System.out.println("vasl");
    }

    public static void main(String[] args) {
        Server server = new Server(60000);

        try {
            server.listen();
        } catch (Exception var3) {
            Exception e = var3;
            System.out.println("Server encountered a problem!");
            e.printStackTrace();
        }

    }
}
