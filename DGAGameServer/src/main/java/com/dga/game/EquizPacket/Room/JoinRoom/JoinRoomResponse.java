package com.dga.game.EquizPacket.Room.JoinRoom;

import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.PacketResponse;

import java.io.Serial;

public class JoinRoomResponse implements EquizPacket {
    @Serial
    private static final long serialVersionUID = -8736847754734395185L;
    public PacketResponse status;
    public int userId;
    public String username;
    public String message;
    public String roomId;
    public int playerCount;
    public int playerLimit;

    public JoinRoomResponse() {

    }

    public JoinRoomResponse(PacketResponse status, int userId, String username,
                            String message, String roomId, int playerCount, int playerLimit) {
        this.status = status;
        this.userId = userId;
        this.username = username;
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
