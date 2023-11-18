package com.dga.game.EquizPacket.Room.LeaveRoom;

import com.dga.game.EquizPacket.EquizPacket;

public class LeaveRoomRequest implements EquizPacket {
    public int userId;

    public LeaveRoomRequest(int userId){
        this.userId = userId;
    }

    @Override
    public String getType() {
        return "leave_room_request";
    }
}
