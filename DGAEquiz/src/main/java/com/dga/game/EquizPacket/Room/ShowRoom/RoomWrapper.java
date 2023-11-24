package com.dga.game.EquizPacket.Room.ShowRoom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class RoomWrapper implements Serializable {
    private static final long serialVersionUID = -296635691212903955L;
    public String roomId;
    public String roomName;
    public String roomPassword;
    public int roomPlayerLimits;

    public RoomWrapper() {

    }

    public RoomWrapper(String roomId, String roomName, String roomPassword, int roomPlayerLimits) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomPassword = roomPassword;
        this.roomPlayerLimits = roomPlayerLimits;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}