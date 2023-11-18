package com.dga.game.EquizPacket.Room.OpenRoom;

import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.PacketResponse;

public class OpenRoomResponse implements EquizPacket {
    public PacketResponse status;
    public String message;
    public String roomId;
    public String roomPassword;

    public OpenRoomResponse(PacketResponse status) {
        this.status = status;
    }

    public OpenRoomResponse(PacketResponse status, String message, String roomId, String roomPassword) {
        this.status = status;
        this.message = message;
        this.roomId = roomId;
        this.roomPassword = roomPassword;
    }

    @Override
    public String getType() {
        return "open_room_response";
    }
}
