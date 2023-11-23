package com.dga.game.EquizPacket.Room.StartRoom;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.Serial;

public class StartRoomRequest implements EquizPacket {
    @Serial
    private static final long serialVersionUID = -2317145088469874136L;
    public String gameMode;

    public StartRoomRequest() {

    }

    public StartRoomRequest(String gameMode) {
        this.gameMode = gameMode;
    }

    @Override
    public String getType() {
        return "start_room_request";
    }
}
