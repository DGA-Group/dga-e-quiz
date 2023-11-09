package com.dga.game.EquizPacket.Room.JoinRoom;

import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.PacketResponse;

public class JoinRoomResponse implements EquizPacket {
    public PacketResponse status;
    public String message;
    public String roomId;

    public JoinRoomResponse(PacketResponse status, String message, String roomId) {
        this.status = status;
        this.message = message;
        this.roomId = roomId;
    }

    @Override
    public String getType() {
        return "join_room_response";
    }
}
