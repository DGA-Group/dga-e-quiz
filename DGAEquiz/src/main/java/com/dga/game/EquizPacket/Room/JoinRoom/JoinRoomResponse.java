package com.dga.game.EquizPacket.Room.JoinRoom;

import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.PacketResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JoinRoomResponse implements EquizPacket {
    private static final long serialVersionUID = -7887831732206286341L;
    public PacketResponse status;
    public String message;
    public String roomId;
    public int playerCount;
    public int playerLimit;

    public JoinRoomResponse() {

    }

    public JoinRoomResponse(PacketResponse status, String message, String roomId, int playerCount, int playerLimit) {
        this.status = status;
        this.message = message;
        this.roomId = roomId;
        this.playerCount = playerCount;
        this.playerLimit = playerLimit;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    @Override
    public String getType() {
        return "join_room_response";
    }
}
