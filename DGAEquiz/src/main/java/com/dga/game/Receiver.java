package com.dga.game;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Receiver extends Thread {
    private Socket socket = null;
    ObjectInputStream objectInputStream = null;

    public Receiver(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        while (socket.isConnected()) {
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                EquizPacket packet = (EquizPacket) objectInputStream.readObject();
                ClientHelperResponse.handleResponse(packet);
            } catch (IOException | ClassNotFoundException e) { }
        }
    }
}
