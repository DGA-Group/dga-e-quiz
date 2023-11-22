package com.dga.game.EquizPacket.Room.StartRoom;

import com.dga.game.EquizPacket.EquizPacket;

public class StartRoomRequest implements EquizPacket {
    private static final long serialVersionUID = -1589558824726555402L;
    public String gameMode;

    public StartRoomRequest(String gameMode) {
        this.gameMode = gameMode;
    }

    @Override
    public String getType() {
        return "start_room_request";
    }
}
