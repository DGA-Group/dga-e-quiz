package com.dga.game.EquizPacket.Room.ShowRoom;

import com.dga.game.EquizPacket.EquizPacket;

import java.util.ArrayList;
import java.util.List;


public class ShowRoomResponse implements EquizPacket {
    private static final long serialVersionUID = 2370608051129232922L;
    public List<RoomWrapper> roomList = new ArrayList<>();

    public ShowRoomResponse(List<RoomWrapper> roomList) {
        this.roomList = roomList;
    }

    @Override
    public String getType() {
        return "show_room_response";
    }
}