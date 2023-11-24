package com.dga.game.EquizPacket.Room.StartRoom;

import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.PacketResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StartRoomResponse implements EquizPacket {
    private static final long serialVersionUID = -1876546223270730793L;
    public PacketResponse status;
    public String message;

    public StartRoomResponse(){

    }

    public StartRoomResponse(PacketResponse status, String message) {
        this.status = status;
        this.message = message;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    @Override
    public String getType() {
        return "start_room_response";
    }
}
