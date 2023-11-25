package com.dga.game;

import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.Room.LeaveRoom.LeaveRoomResponse;

import java.io.IOException;
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
            room.playerList.removeIf(x -> {
                if (x.socket.isClosed()) {
                    EquizPacket packet = new LeaveRoomResponse(x.userId, x.username,
                            "User " + x.name + " has left the room.",
                            room.playerList.size() - 1, room.roomPlayerLimits);
                    try {
                        room.broadcast(packet, x);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            });
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
