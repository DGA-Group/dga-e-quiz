package com.dga.game.EquizPacket.Room.JoinRoom;

import com.dga.game.EquizPacket.EquizPacket;

public class JoinRoomRequest implements EquizPacket {
    private static final long serialVersionUID = -4509569079923381300L;
    public String roomId;
    public String roomPassword;

    public JoinRoomRequest(String roomId, String roomPassword) {
        this.roomId = roomId;
        this.roomPassword = roomPassword;
    }

    @Override
    public String getType() {
        return "join_room_request";
    }
}
