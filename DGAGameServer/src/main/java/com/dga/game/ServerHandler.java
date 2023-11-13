package com.dga.game;

import java.net.ServerSocket;
import java.util.Scanner;
import java.util.Set;

public class ServerHandler extends Thread {
    private ServerSocket server;
    private Set<ClientHandler> clientList;

    private Scanner sc = new Scanner(System.in);
    private String command;

    public ServerHandler(ServerSocket server, Set<ClientHandler> clientList) {
        this.server = server;
        this.clientList = clientList;
    }

    public void run() {
        while (true){
            System.out.print("Server command line: ");
            command = sc.nextLine();
            if (command.equals("/quit")) {
                quit();
            }
        }
    }

    private void quit() {
        for (ClientHandler client : clientList) {
            client.close();
        }
        try {
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
