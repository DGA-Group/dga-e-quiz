package com.dga.game.EquizPacket.Room.LeaveRoom;

import com.dga.game.EquizPacket.EquizPacket;

public class LeaveRoomResponse implements EquizPacket {
    private static final long serialVersionUID = -5089630660928349540L;
    public int userId;
    public String message;
    public int playerCount;
    public int playerLimit;

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
