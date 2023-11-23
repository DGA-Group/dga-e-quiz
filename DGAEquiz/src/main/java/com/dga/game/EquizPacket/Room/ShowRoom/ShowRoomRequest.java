package com.dga.game.EquizPacket.Room.ShowRoom;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.Serial;

public class ShowRoomRequest implements EquizPacket {

    @Serial
    private static final long serialVersionUID = 888084826994961033L;

    public ShowRoomRequest() {

    }

    @Override
    public String getType() {
        return "show_room_request";
    }
}
