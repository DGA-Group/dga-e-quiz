package com.dga.game.EquizPacket.Room.ShowRoom;

import java.io.Serializable;

public class RoomWrapper implements Serializable {
    public String roomId;
    public String roomName;
    public String roomPassword;
    public int roomPlayerLimits;

    public RoomWrapper(String roomId, String roomName, String roomPassword, int roomPlayerLimits) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomPassword = roomPassword;
        this.roomPlayerLimits = roomPlayerLimits;
    }
}