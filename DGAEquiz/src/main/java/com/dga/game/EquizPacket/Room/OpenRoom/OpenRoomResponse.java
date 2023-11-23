package com.dga.game.EquizPacket.Room.OpenRoom;

import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.PacketResponse;

import java.io.Serial;

public class OpenRoomResponse implements EquizPacket {
    @Serial
    private static final long serialVersionUID = -6654321628719438566L;
    public PacketResponse status;
    public String message;
    public String roomId;
    public String roomPassword;

    public OpenRoomResponse() {

    }

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
