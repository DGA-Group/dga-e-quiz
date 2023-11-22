package com.dga.game.EquizPacket.Room.LeaveRoom;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LeaveRoomRequest implements EquizPacket {
    private static final long serialVersionUID = 1207612134281479841L;
    public int userId;

    public LeaveRoomRequest() {

    }

    public LeaveRoomRequest(int userId) {
        this.userId = userId;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    @Override
    public String getType() {
        return "leave_room_request";
    }
}
