package com.dga.game;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class EquizServer implements Runnable {
    ServerSocket server;
    ExecutorService pool = Executors.newCachedThreadPool();

    private Set<ClientHandler> clientList = new HashSet<>();
    private Set<Room> roomList = new HashSet<>();

    @Override
    public void run() {
        try {
            server = new ServerSocket(54321);
            System.out.println("Success initialize equiz server at port 54321...");
        } catch (IOException e) {
            System.out.println("Unable to start server! Maybe the port is occupied!");
            e.printStackTrace();
            return;
        }

        new ServerHandler(server, clientList).start();
        new ServerCleaner(clientList, roomList).start();

        ServerHelper.setServer(this);

        while (true) {
            try {
                Socket socket = server.accept();
                ClientHandler client = new ClientHandler(socket, this);
                System.out.println("New client from: " + socket.getInetAddress().getHostAddress());
                clientList.add(client);
                pool.execute(client);
            } catch (IOException e) {
                System.out.println("Unable to accept new client!");
            }
        }

    }

    public String addRoom(String roomName, String roomPassword, int roomPlayerLimit) {
        String roomId = RandomStringUtils.randomNumeric(10);
        Room newRoom = new Room(roomId, roomName, roomPassword, roomPlayerLimit);
        roomList.add(newRoom);
        pool.execute(newRoom);
        System.out.println("Room " + roomId + " is opened!");
        return roomId;
    }

    public Room getRoom(String roomId) throws Exception {
        for (Room room : roomList) {
            if (room.roomId.equals(roomId)) {
                return room;
            }
        }
        throw new Exception("There is no room with id: " + roomId);
    }

    public Set<Room> getRoomList() {
        return roomList;
    }

    public void close() {
        try {
            pool.shutdown();
            while (!pool.isTerminated());
            server.close();
            Thread.currentThread().interrupt();
        } catch (IOException e) {

        }
    }
}
