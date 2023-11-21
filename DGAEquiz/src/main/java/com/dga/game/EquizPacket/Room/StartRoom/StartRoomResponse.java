package com.dga.game.EquizPacket.Room.StartRoom;

import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.PacketResponse;

public class StartRoomResponse implements EquizPacket {
    public PacketResponse status;
    public String message;

    public StartRoomResponse(PacketResponse status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String getType() {
        return "start_room_response";
    }
}
