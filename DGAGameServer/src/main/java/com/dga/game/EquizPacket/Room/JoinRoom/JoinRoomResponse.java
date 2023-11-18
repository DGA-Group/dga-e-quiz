package com.dga.game.EquizPacket.Room.JoinRoom;

import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.PacketResponse;

public class JoinRoomResponse implements EquizPacket {
    public PacketResponse status;
    public String message;
    public String roomId;
    public int playerCount;
    public int playerLimit;

    public JoinRoomResponse(PacketResponse status, String message, String roomId, int playerCount, int playerLimit) {
        this.status = status;
        this.message = message;
        this.roomId = roomId;
        this.playerCount = playerCount;
        this.playerLimit = playerLimit;
    }

    @Override
    public String getType() {
        return "join_room_response";
    }
}
