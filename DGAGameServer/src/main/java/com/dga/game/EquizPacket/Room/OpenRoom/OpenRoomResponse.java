package com.dga.game.EquizPacket.Room.OpenRoom;

import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.PacketResponse;

public class OpenRoomResponse implements EquizPacket {
    public PacketResponse status;
    public String message;
    public String roomId;

    public OpenRoomResponse(PacketResponse status) {
        this.status = status;
    }

    public OpenRoomResponse(PacketResponse status, String message, String roomId) {
        this.status = status;
        this.message = message;
        this.roomId = roomId;
    }

    @Override
    public String getType() {
        return "open_room_response";
    }
}
