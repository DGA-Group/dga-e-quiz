package com.dga.game;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ReadThread extends Thread {
    private Scanner sc = new Scanner(System.in);
    private Socket socket = null;
    private EquizClient client = null;
    ObjectOutputStream objectOutputStream = null;


    public ReadThread(Socket socket, EquizClient client) {
        this.socket = socket;
        this.client = client;
    }

    public void run() {
        while (socket.isConnected()) {
            try {
                String mess = sc.nextLine();
                EquizPacket request = ClientHelperRequest.handleRequest(mess);
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(request);
            } catch (IOException e) {
                try {
                    FileWriter writer = new FileWriter("log.txt", true);
                    String logMessage = LocalDateTime.now() + " - ReadThread: " + e.getMessage() + System.lineSeparator();
                    writer.write(logMessage);
                    writer.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                break;
            }
        }
    }
}
