package com.dga.game.EquizPacket.Room.OpenRoom;

import com.dga.game.EquizPacket.EquizPacket;

public class OpenRoomRequest implements EquizPacket {
    public String roomName;
    public String roomPassword;
    public int roomPlayerLimit;

    public OpenRoomRequest(String roomName, String roomPassword, int roomPlayerLimit) {
        this.roomName = roomName;
        this.roomPassword = roomPassword;
        this.roomPlayerLimit = roomPlayerLimit;
    }

    @Override
    public String getType() {
        return "open_room_request";
    }
}
