package com.dga.game;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientListener extends Thread {
    private Socket socket;
    private ObjectInputStream objectInputStream;

    public ClientListener(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            while (socket.isConnected()) {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                EquizPacket packet = (EquizPacket) objectInputStream.readObject();
                ClientHelperResponse.handleResponse(packet);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing ObjectInputStream: " + e.getMessage());
            }
        }
    }
}
