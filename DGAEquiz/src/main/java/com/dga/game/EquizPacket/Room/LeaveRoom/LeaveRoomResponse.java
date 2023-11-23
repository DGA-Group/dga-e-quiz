package com.dga.game.EquizPacket.Room.LeaveRoom;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.Serial;

public class LeaveRoomResponse implements EquizPacket {
    @Serial
    private static final long serialVersionUID = 9063323918315911064L;
    public int userId;
    public String message;
    public int playerCount;
    public int playerLimit;

    public LeaveRoomResponse() {

    }

    public LeaveRoomResponse(int userId, String message, int playerCount, int playerLimit) {
        this.userId = userId;
        this.message = message;
        this.playerCount = playerCount;
        this.playerLimit = playerLimit;
    }

    @Override
    public String getType() {
        return "leave_room_response";
    }
}
