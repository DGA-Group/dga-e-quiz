package com.dga.game.EquizPacket.Room.LeaveRoom;

import com.dga.game.EquizPacket.EquizPacket;

public class LeaveRoomResponse implements EquizPacket {
    public String username;
    public String message;
    public int playerCount;
    public int playerLimit;

    public LeaveRoomResponse(String username, String message, int playerCount, int playerLimit) {
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
