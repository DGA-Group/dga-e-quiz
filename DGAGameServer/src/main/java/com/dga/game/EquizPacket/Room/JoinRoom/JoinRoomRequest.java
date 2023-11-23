package com.dga.game.EquizPacket.Room.JoinRoom;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.Serial;

public class JoinRoomRequest implements EquizPacket {
    @Serial
    private static final long serialVersionUID = -44558912805405332L;
    public String roomId;
    public String roomPassword;

    public JoinRoomRequest() {

    }

    public JoinRoomRequest(String roomId, String roomPassword) {
        this.roomId = roomId;
        this.roomPassword = roomPassword;
    }

    @Override
    public String getType() {
        return "join_room_request";
    }
}
