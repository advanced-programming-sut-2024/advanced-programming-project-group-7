package model;

import javax.imageio.spi.ServiceRegistry;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
    DataInputStream input;
    DataOutputStream output;
    public Client serverClient;
    public boolean isReady;
    public Server otherServer;


    private  Socket socket;
    public static ArrayList<Server> servers=new ArrayList<>();

    public Server(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            String command;

            while (true) {
                command = input.readUTF();
                if(command.equals("im ready"));{

                }
              //  sendAll(command,socket);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendAll(String command,Socket socket) throws IOException {
        for (Server server:servers){
            if(! server.socket.equals(socket)) {
                server.output.writeUTF(command);
                System.out.println("sent");
            }
//            server.serverClient.getMessageFromOtherClient();
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(35000);
            Socket clientSocket;
            while (true) {
                clientSocket = serverSocket.accept();
                Server storeServer = new Server(clientSocket);
                System.out.println("no no no");
//                storeServer.serverClient=socket.getRemoteSocketAddress();
//                if (!Client.clients.isEmpty()) {
//                    for (Client client : Client.clients) {
//                        if (client.socket.equals(socket)) {
//                            storeServer.serverClient = client;
//                            System.out.println("client added to array");
//                        }else System.out.println("why?");
//                    }
//                }else System.out.println("not added");
                servers.add(storeServer);
                if (servers.size()==2){
                    storeServer.otherServer=servers.get(0);
                    storeServer.otherServer.otherServer=servers.get(1);
                    System.out.println("connected bitch");
                }
                System.out.println("new client");
                storeServer.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void checkIsReady(Server server){
        new Thread(new Runnable() {
            @Override
            public void run( ) {
                if(isReady==true&&otherServer.isReady==true){
                    //                        server.sendAll("enter the real deall",socket);
                    System.out.println("both are now ready");
                }
            }
        }).start();
    }
}
