package com.dga.game;

import com.dga.game.EquizPacket.Client.ConnectClientRequest;
import com.dga.game.EquizPacket.EquizPacket;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    public Socket socket;
    public EquizServer server;
    public ObjectInputStream objectInputStream = null;
    public ObjectOutputStream objectOutputStream = null;
    public Room currentRoom;
    public String username;

    public ClientHandler(Socket socket, EquizServer server) {
        this.socket = socket;
        this.server = server;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            // objectInputStream = new ObjectInputStream(socket.getInputStream());
            ConnectClientRequest connectRequest = (ConnectClientRequest) objectInputStream.readObject();
            this.username = connectRequest.username;

            while (socket.isConnected()) {
                // objectInputStream = new ObjectInputStream(socket.getInputStream());
                EquizPacket packetRequest = (EquizPacket) objectInputStream.readObject();
                EquizPacket packetResponse = ServerHelper.handleRequest(packetRequest, this);
                sendPacket(packetResponse);
            }
        } catch (Exception e) {
            System.out.println("Client " + socket.getInetAddress().getHostAddress() + " disconnected!");
        } finally {
            close();
        }
    }

    public void sendPacket(EquizPacket packet) throws IOException {
        // objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(packet);
    }

    public void close() {
        try {

            if (objectInputStream != null) {
                objectInputStream.close();
            }

            if (objectOutputStream != null) {
                objectOutputStream.close();
            }

            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Unable to close client socket: " + socket.toString());
        }
    }
}
