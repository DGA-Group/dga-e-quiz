package com.dga.game;

import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.PacketResponse;
import com.dga.game.EquizPacket.Message.MessageRequest;
import com.dga.game.EquizPacket.Message.MessageResponse;
import com.dga.game.EquizPacket.Room.JoinRoom.JoinRoomRequest;
import com.dga.game.EquizPacket.Room.JoinRoom.JoinRoomResponse;
import com.dga.game.EquizPacket.Room.LeaveRoom.LeaveRoomRequest;
import com.dga.game.EquizPacket.Room.LeaveRoom.LeaveRoomResponse;
import com.dga.game.EquizPacket.Room.OpenRoom.OpenRoomRequest;
import com.dga.game.EquizPacket.Room.OpenRoom.OpenRoomResponse;
import com.dga.game.EquizPacket.Room.ShowRoom.RoomWrapper;
import com.dga.game.EquizPacket.Room.ShowRoom.ShowRoomRequest;
import com.dga.game.EquizPacket.Room.ShowRoom.ShowRoomResponse;
import com.dga.game.EquizPacket.Room.StartRoom.StartRoomRequest;
import com.dga.game.EquizPacket.Room.StartRoom.StartRoomResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ServerHelper {
    private static EquizServer server;

    public static void setServer(EquizServer serverToHelp) {
        if (server != null) {
            server.close();
        }
        server = serverToHelp;
    }

    public static EquizPacket handleRequest(EquizPacket packet, ClientHandler client) {
        EquizPacket response = null;

        switch (packet.getType()) {
            case "message_request":
                handleMessage((MessageRequest) packet, client);
                break;
            case "open_room_request":
                response = handleOpenRoom((OpenRoomRequest) packet);
                break;
            case "join_room_request":
                response = handleJoinRoom((JoinRoomRequest) packet, client);
                break;
            case "show_room_request":
                response = handleShowRoom((ShowRoomRequest) packet);
                break;
            case "start_room_request":
                response = handleStartGame((StartRoomRequest) packet, client);
                break;
            case "leave_room_request":
                handleLeaveRoom((LeaveRoomRequest) packet, client);
            default:
                break;
        }
        return response;
    }

    private static MessageResponse handleMessage(MessageRequest message, ClientHandler client) {
        MessageResponse response;
        Room targetRoom = client.currentRoom;
        try {
            response = new MessageResponse(PacketResponse.OK, client.userId, client.username, client.name, message.text);
            targetRoom.broadcast(response, client);
            targetRoom.checkAnswer(message.text, client);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response = new MessageResponse(PacketResponse.ERROR);
        }
        return response;
    }

    private static OpenRoomResponse handleOpenRoom(OpenRoomRequest packet) {
        String roomId = server.addRoom(packet.roomName, packet.roomPassword, packet.roomPlayerLimit);
        try {
            Room room = server.getRoom(roomId);
            ShowRoomRequest showRoomRequest = new ShowRoomRequest();
            ShowRoomResponse showRoomResponse = handleShowRoom(showRoomRequest);
            room.broadcast(showRoomResponse, null);
        } catch (Exception ignore) { }
        return new OpenRoomResponse(PacketResponse.OK, "You have opened room " + roomId, roomId, packet.roomPassword);
    }

    private static JoinRoomResponse handleJoinRoom(JoinRoomRequest packet, ClientHandler client) {
        String roomId = packet.roomId;
        JoinRoomResponse response;
        try {
            // Find room by id and add player to room
            Room room = server.getRoom(roomId);
            room.addPlayer(client);
            client.currentRoom = room;

            // Broad cast join room response to all player in current room.
            int playerCount = room.playerList.size();
            String message = "Client -" + client.name + "- has join room!";
            response = new JoinRoomResponse(PacketResponse.OK, message, roomId, playerCount, room.roomPlayerLimits);
            room.broadcast(response, client);
        } catch (Exception e) {
            response = new JoinRoomResponse(PacketResponse.ERROR, "Unable to join room " + roomId
                    , null, 0, 0);
        }
        return response;
    }

    private static ShowRoomResponse handleShowRoom(ShowRoomRequest packet) {
        Set<Room> roomList = server.getRoomList();
        List<RoomWrapper> roomWrappers = new ArrayList<>();
        for (Room room : roomList) {
            RoomWrapper roomWrapper = new RoomWrapper(room.roomId, room.roomName,
                    room.roomPassword, room.roomPlayerLimits);
            roomWrappers.add(roomWrapper);
        }
        return new ShowRoomResponse(roomWrappers);
    }

    private static StartRoomResponse handleStartGame(StartRoomRequest packet, ClientHandler client) {
        try {
            client.currentRoom.startGame(packet.gameMode, client);
        } catch (IOException e) {

        }
        return new StartRoomResponse();
    }

    private static void handleLeaveRoom(LeaveRoomRequest packet, ClientHandler client) {
        Room room = client.currentRoom;
        room.playerList.remove(client);
        LeaveRoomResponse response = new LeaveRoomResponse(client.userId, "User " + client.name + " has left the chat."
                , room.playerList.size(), room.roomPlayerLimits);
        try {
            room.broadcast(response, client);
        }catch (Exception ignore){}
    }

    public static void callFuncDelay(Event func, long millisecond) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(millisecond);
                func.handle();
            } catch (InterruptedException | IOException ignored) {
            }

        });
        thread.start();
    }
}
