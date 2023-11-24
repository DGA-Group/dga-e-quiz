package com.dga.game.EquizPacket.Room.OpenRoom;

import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.PacketResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class OpenRoomResponse implements EquizPacket {
    private static final long serialVersionUID = 7663424830294911895L;
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

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    @Override
    public String getType() {
        return "open_room_response";
    }
}
