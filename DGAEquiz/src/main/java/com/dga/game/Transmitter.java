package com.dga.game;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class Transmitter extends Thread {
    private final Socket socket;
    ObjectOutputStream objectOutputStream = null;


    public Transmitter(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        while (socket.isConnected()) {
            try {
                EquizPacket request = ClientHelperRequest.handleRequest("");
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(request);
            } catch (Exception e) {
                break;
            }
        }
    }
}
