package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import javafx.application.Platform;
import model.Card;
import model.Game;
import model.User;
import model.factions.Monsters;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.HashMap;

public class GameServer extends Thread {

    private Socket newSoc;
    public static HashMap<String, Socket> onlineUsers = new HashMap<>();

    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        gameServer.start();
    }

    @Override
    public void start() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/", new MyHandler()); // Map root context to our handler
            server.createContext("/command", new CommandHandler()); // Map /command context to our handler
            server.setExecutor(null); // Use the default executor
            server.start();
            System.out.println("HTTP server is running on port 8000");
            System.out.println(server.getAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ServerSocket serverSocket = new ServerSocket(34800)) {
            System.out.println("Server is listening on port 34800");
            while (true) {
                newSoc = serverSocket.accept();
                UserThread userThread = new UserThread(newSoc);
                userThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "<html><head><title>My Server</title><script>function sendCommand() { fetch('/command', { method: 'POST' }).then(response => response.text()).then(data => console.log(data)).catch(error => console.error('Error:', error)); }</script></head><body><h1>Hello, World!</h1><button onclick=\"sendCommand()\">Click Me</button></body></html>";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

    class CommandHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                System.out.println("Hello, World!");
                String response = "Command received";
                exchange.sendResponseHeaders(200, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }
}
