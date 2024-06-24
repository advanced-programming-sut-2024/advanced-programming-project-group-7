package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    ServerSocket server;
    Socket socket;
    private DataInputStream receiveBuffer;
    private DataOutputStream sendBuffer;

    public void establishConnection(String address, int port) throws IOException {
        socket=new Socket(address,port);
        sendBuffer=new DataOutputStream(socket.getOutputStream());
        receiveBuffer=new DataInputStream(socket.getInputStream());
    }

    public void start(String IP) throws IOException {
        this.establishConnection(IP, 60000);
    }
}
