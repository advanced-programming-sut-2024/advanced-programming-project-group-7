package controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class StoreServer extends Thread {
    private static Map<String, int[] > inventory = new ConcurrentHashMap<>();
    private static Map<String, Customer> customers = new ConcurrentHashMap<>();
    private Socket socket;
    private Customer currentCustomer;

    public StoreServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String command = dataInputStream.readUTF(); // Read the command from the client
                String[] parts = command.split(":");

                if (parts[0].equals("register") && parts.length == 4) {
                    String id = parts[1];
                    if (isValidId(id)) {
                        dataOutputStream.writeUTF("id exists");
                        continue;
                    }
                    String name = parts[2];
                    if (!isValidName(name)) {
                        dataOutputStream.writeUTF("invalid name");
                        continue;
                    }
                    String  money = parts[3];
                    if (!isValidMoney(money)) {
                        dataOutputStream.writeUTF("invalid money");
                        continue;
                    }
                    int moneyInt = Integer.parseInt(money);
                    Customer newCustomer = new Customer(name, id, moneyInt);
                    setCurrentCustomer(newCustomer);
                    customers.put(id, newCustomer);
                    dataOutputStream.writeUTF("registered");
                } else if (parts[0].equals("login") && parts.length == 2) {
                    String id = parts[1];
                    if (!isValidId(id)) {
                        dataOutputStream.writeUTF("id does not exist");
                        continue;
                    }
                    setCurrentCustomer(customers.get(id));
                    dataOutputStream.writeUTF("logged in");
                } else if (parts[0].equals("logout")) {
                    if (getCurrentCustomer() == null) {
                        dataOutputStream.writeUTF("login first");
                        continue;
                    }
                    setCurrentCustomer(null);
                    dataOutputStream.writeUTF("logged out");
                } else if (parts[0].equals("get price") && parts.length == 2) {
                    String shoeName = parts[1];
                    if (!isValidProductName(shoeName)) {
                        dataOutputStream.writeUTF("invalid product");
                        continue;
                    }
                    dataOutputStream.writeUTF(String.valueOf(getPrice(shoeName)));
                } else if (parts[0].equals("get quantity") && parts.length == 2) {
                    String shoeName = parts[1];
                    if (!isValidProductName(shoeName)) {
                        dataOutputStream.writeUTF("invalid product");
                        continue;
                    }
                    dataOutputStream.writeUTF(String.valueOf(getQuantity(shoeName)));
                } else if (parts[0].equals("get money")) {
                    if (getCurrentCustomer() == null) {
                        dataOutputStream.writeUTF("login first");
                        continue;
                    }
                    getCustomerMoney(dataOutputStream);
                } else if (parts[0].equals("charge") && parts.length == 2) {
                    if (!isValidMoney(parts[1])) {
                        dataOutputStream.writeUTF("invalid money");
                        continue;
                    }
                    int money = Integer.parseInt(parts[1]);
                    if (getCurrentCustomer() == null) {
                        dataOutputStream.writeUTF("login first");
                        continue;
                    }
                    getCurrentCustomer().setMoney(money);
                    dataOutputStream.writeUTF("charged");
                } else if (parts[0].equals("purchase") && parts.length == 3) {
                    if (getCurrentCustomer() == null) {
                        dataOutputStream.writeUTF("login first");
                        continue;
                    }
                    String shoeName = parts[1];
                    if (!isValidProductName(shoeName)) {
                        dataOutputStream.writeUTF("invalid product");
                        continue;
                    }
                    if (!isValidQuantity(parts[2])) {
                        dataOutputStream.writeUTF("invalid amount");
                        continue;
                    }
                    int quantity = Integer.parseInt(parts[2]);
                    if (inventory.get(shoeName)[0] < quantity) {
                        dataOutputStream.writeUTF("not enough shoes");
                        continue;
                    }
                    int cost = quantity * inventory.get(shoeName)[1];
                    chargeCustomer(cost, dataOutputStream);
                    purchaseProduct(shoeName, quantity, dataOutputStream);
                    dataOutputStream.writeUTF("took your money");
                } else
                    dataOutputStream.writeUTF("invalid command");
                dataOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidId(String id) {
        return customers.containsKey(id);
    }


    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+");
    }

    private boolean isValidMoney(String moneyStr) {
        return moneyStr.matches("\\d+");
    }

    private boolean isValidProductName(String productName) {
        return inventory.containsKey(productName);
    }

    private boolean isValidQuantity(String quantityStr) {
        return quantityStr.matches("\\d+");
    }

    private void chargeCustomer(int chargeAmount, DataOutputStream dataOutputStream) throws IOException {
        synchronized (this) {
            if (getCurrentCustomer().getMoney() >= chargeAmount) {
                getCurrentCustomer().setMoney(-chargeAmount);
            } else {
                dataOutputStream.writeUTF("Charge failed. Insufficient balance.");
            }
        }
    }
    public void setCurrentCustomer(Customer currentCustomer) {
        synchronized (this) {
            this.currentCustomer = currentCustomer;
        }
    }
    public Customer getCurrentCustomer() {
        synchronized (this){
            return currentCustomer;
        }
    }

    private int getPrice(String productName) {
        return inventory.get(productName)[1];
    }

    private int getQuantity(String productName) {
        return inventory.get(productName)[0];
    }

    private void purchaseProduct(String productName, int quantity, DataOutputStream dataOutputStream) throws IOException {
        synchronized (this) {
            inventory.replace(productName,
                    new int[]{inventory.get(productName)[0] - quantity, inventory.get(productName)[1]});
        }
    }

    private void getCustomerMoney(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(String.valueOf(getCurrentCustomer().getMoney()));
    }

    public static void main(String[] args) {
        inventory.put("shoe1", new int[]{5, 10});
        inventory.put("shoe2", new int[]{5, 20});
        inventory.put("shoe3", new int[]{5, 30});

        try (ServerSocket serverSocket = new ServerSocket(8090)) {
            System.out.println("Server is listening on port 8090");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                StoreServer serverThread = new StoreServer(socket);
                serverThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Customer {
    private String name;
    private String id;
    private int money;

    public Customer(String name, String id, int money) {
        this.name = name;
        this.id = id;
        this.money = money;
    }


    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money += money;
    }
}
