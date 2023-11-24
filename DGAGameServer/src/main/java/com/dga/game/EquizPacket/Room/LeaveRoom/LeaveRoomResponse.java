package com.dga.game.EquizPacket.Room.LeaveRoom;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LeaveRoomResponse implements EquizPacket {
    private static final long serialVersionUID = 9063323918315911064L;
    public int userId;
    public String message;
    public int playerCount;
    public int playerLimit;

    public LeaveRoomResponse() {

    }

    public LeaveRoomResponse(int userId, String message, int playerCount, int playerLimit) {
        this.userId = userId;
        this.message = message;
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
        return "leave_room_response";
    }
}
