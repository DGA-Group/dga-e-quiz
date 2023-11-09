package com.dga.game.EquizPacket.Room.ShowRoom;

import com.dga.game.EquizPacket.EquizPacket;

public class ShowRoomRequest implements EquizPacket {
    @Override
    public String getType() {
        return "show_room_request";
    }
}
