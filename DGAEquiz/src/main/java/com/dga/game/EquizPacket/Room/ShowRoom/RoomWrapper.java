package com.dga.game.EquizPacket.Room.ShowRoom;

import java.io.Serial;
import java.io.Serializable;

public class RoomWrapper implements Serializable {
    @Serial
    private static final long serialVersionUID = 6403801267701411227L;
    public String roomId;
    public String roomName;
    public String roomPassword;
    public int roomPlayerCount;
    public int roomPlayerLimits;

    public RoomWrapper() {

    }

    public RoomWrapper(String roomId, String roomName, String roomPassword, int roomPlayerCount, int roomPlayerLimits) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomPassword = roomPassword;
        this.roomPlayerCount = roomPlayerCount;
        this.roomPlayerLimits = roomPlayerLimits;
    }
}