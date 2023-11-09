package com.dga.game;

import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.Message.MessageResponse;
import com.dga.game.EquizPacket.Room.JoinRoom.JoinRoomResponse;
import com.dga.game.EquizPacket.Room.OpenRoom.OpenRoomResponse;
import com.dga.game.EquizPacket.Room.ShowRoom.RoomWraper;
import com.dga.game.EquizPacket.Room.ShowRoom.ShowRoomResponse;

import java.util.List;

public class ClientHelperResponse {
    public static void handleResponse(EquizPacket packet) {
        switch (packet.getType()) {
            case "message_response":
                handleMessageResponse((MessageResponse) packet);
                break;
            case "open_room_response":
                handleOpenRoomResponse((OpenRoomResponse) packet);
                break;
            case "join_room_response":
                handleJoinRoomResponse((JoinRoomResponse) packet);
                break;
            case "show_room_response":
                handleShowRoomResponse((ShowRoomResponse) packet);
                break;
            case "start_room_response":
                break;
            default:
                break;
        }
    }

    private static void handleMessageResponse(MessageResponse packet) {
        System.out.println(packet.text);
    }

    private static void handleOpenRoomResponse(OpenRoomResponse packet) {
        System.out.println(packet.message);
    }

    private static void handleJoinRoomResponse(JoinRoomResponse packet) {
        System.out.println(packet.message);
    }

    private static void handleShowRoomResponse(ShowRoomResponse packet) {
        List<RoomWraper> roomWrapers = packet.roomList;
        System.out.println("RoomID\t\tRoomName\t\tRoomPlayerLimit\t\t");
        for (RoomWraper room : roomWrapers) {
            System.out.println(room.roomId + "\t\t" + room.roomName + "\t\t" + room.roomPlayerLimits);
        }
    }

}
