package com.dga.game.EquizPacket.Room.OpenRoom;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.Serial;

public class OpenRoomRequest implements EquizPacket {
    @Serial
    private static final long serialVersionUID = 934745767650458372L;
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

    @Override
    public String getType() {
        return "open_room_request";
    }
}
