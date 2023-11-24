package com.dga.game.EquizPacket.Room.OpenRoom;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class OpenRoomRequest implements EquizPacket {
    private static final long serialVersionUID = -9159491280264274009L;
    public String roomName;
    public boolean requirePassword;
    public String roomPassword;
    public int roomPlayerLimit;

    public OpenRoomRequest() {

    }

    public OpenRoomRequest(String roomName, boolean requirePassword, String roomPassword, int roomPlayerLimit) {
        this.roomName = roomName;
        this.requirePassword = requirePassword;
        if (requirePassword) {
            this.roomPassword = roomPassword;
        }
        this.roomPlayerLimit = roomPlayerLimit;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    @Override
    public String getType() {
        return "open_room_request";
    }
}
