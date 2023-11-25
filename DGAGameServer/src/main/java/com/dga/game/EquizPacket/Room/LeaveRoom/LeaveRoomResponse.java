package com.dga.game.EquizPacket.Room.LeaveRoom;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.Serial;

public class LeaveRoomResponse implements EquizPacket {
    @Serial
    private static final long serialVersionUID = -6193053053445748811L;
    public int userId;
    public String username;
    public String message;
    public int playerCount;
    public int playerLimit;

    public LeaveRoomResponse() {

    }

    public LeaveRoomResponse(int userId, String username, String message, int playerCount, int playerLimit) {
        this.userId = userId;
        this.username = username;
        this.message = message;
        this.playerCount = playerCount;
        this.playerLimit = playerLimit;
    }

    @Override
    public String getType() {
        return "leave_room_response";
    }
}
