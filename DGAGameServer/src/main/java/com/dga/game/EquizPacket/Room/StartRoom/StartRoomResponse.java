package com.dga.game.EquizPacket.Room.StartRoom;

import com.dga.game.EquizPacket.EquizPacket;

public class StartRoomResponse implements EquizPacket {

    public StartRoomResponse() {

    }

    @Override
    public String getType() {
        return "start_room_response";
    }
}
