package com.dga.game.EquizPacket.Room.JoinRoom;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JoinRoomRequest implements EquizPacket {
    private static final long serialVersionUID = -44558912805405332L;
    public String roomId;
    public String roomPassword;

    public JoinRoomRequest() {

    }

    public JoinRoomRequest(String roomId, String roomPassword) {
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
        return "join_room_request";
    }
}
