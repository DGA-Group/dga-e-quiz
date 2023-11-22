package com.dga.game.EquizPacket.Room.ShowRoom;

import com.dga.game.EquizPacket.EquizPacket;

public class ShowRoomRequest implements EquizPacket {
    private static final long serialVersionUID = 888084826994961033L;

    @Override
    public String getType() {
        return "show_room_request";
    }
}
