package com.dga.game.EquizPacket.Room.StartRoom;

import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.PacketResponse;

import java.io.Serial;

public class StartRoomResponse implements EquizPacket {
    @Serial
    private static final long serialVersionUID = -1876546223270730793L;
    public PacketResponse status;
    public String message;

    public StartRoomResponse(){

    }

    public StartRoomResponse(PacketResponse status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String getType() {
        return "start_room_response";
    }
}
