package com.dga.game.EquizPacket.Room.JoinRoom;

import com.dga.game.EquizPacket.EquizPacket;

public class JoinRoomRequest implements EquizPacket {
    public String roomId;

    public JoinRoomRequest(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String getType() {
        return "join_room_request";
    }
}
