package com.dga.game;

import java.util.Set;

public class ServerCleaner extends Thread {
    private Set<ClientHandler> clientList;
    private Set<Room> roomList;

    public ServerCleaner(Set<ClientHandler> clientList, Set<Room> roomList) {
        this.clientList = clientList;
        this.roomList = roomList;
    }

    public void run() {
        while (true) {
            cleanRoomList();
            cleanClientList();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {

            }

            System.out.println("Current active user: " + clientList.size());
            System.out.println("Current active room: " + roomList.size());
            System.out.println();
        }
    }

    private void cleanRoomList() {
        for (Room room : roomList) {
            room.playerList.removeIf(x -> x.socket.isClosed());
        }

        roomList.removeIf(x -> {
            if (x.playerList.size() == 0) {
                x.abort();
                return true;
            } else {
                return false;
            }
        });
    }

    private void cleanClientList() {
        clientList.removeIf(x -> x.socket.isClosed());
    }


}
