package com.dga.game;

import com.dga.equiz.utils.MyObjectInputStream;
import com.dga.game.EquizPacket.EquizPacket;

import java.io.IOException;
import java.net.Socket;

public class ClientListener extends Thread {
    private final Socket socket;
    private MyObjectInputStream objectInputStream;

    public ClientListener(Socket socket) {
        this.socket = socket;
        try {
            objectInputStream = new MyObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (socket.isConnected()) {
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
