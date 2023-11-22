package com.dga.game.EquizPacket.Room.LeaveRoom;

import com.dga.game.EquizPacket.EquizPacket;

public class LeaveRoomRequest implements EquizPacket {
    private static final long serialVersionUID = 1207612134281479841L;
    public int userId;

    public LeaveRoomRequest(int userId){
        this.userId = userId;
    }

    @Override
    public String getType() {
        return "leave_room_request";
    }
}
