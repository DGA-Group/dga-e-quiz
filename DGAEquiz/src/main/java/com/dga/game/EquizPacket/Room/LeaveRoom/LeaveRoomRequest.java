package com.dga.game.EquizPacket.Room.LeaveRoom;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.Serial;

public class LeaveRoomRequest implements EquizPacket {
    @Serial
    private static final long serialVersionUID = -8817242625971454128L;
    public int userId;

    public LeaveRoomRequest() {

    }

    public LeaveRoomRequest(int userId) {
        this.userId = userId;
    }

    @Override
    public String getType() {
        return "leave_room_request";
    }
}
