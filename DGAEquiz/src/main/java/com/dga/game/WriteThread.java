package com.dga.game;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.time.LocalDateTime;

public class WriteThread extends Thread {
    private Socket socket = null;
    private EquizClient client = null;
    InputStream inputStream = null;
    ObjectInputStream objectInputStream = null;

    public WriteThread(Socket socket, EquizClient client) {
        this.socket = socket;
        this.client = client;
    }

    public void run() {
        while (socket.isConnected()) {
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                EquizPacket packet = (EquizPacket) objectInputStream.readObject();
                ClientHelperResponse.handleResponse(packet);
            } catch (IOException | ClassNotFoundException e) {
                try {
                    FileWriter writer = new FileWriter("log.txt", true);
                    String logMessage = LocalDateTime.now() + " - ReadThread: " + e.getMessage() + System.lineSeparator();
                    writer.write(logMessage);
                    writer.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
