package com.dga.game.EquizPacket.Room.ShowRoom;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.util.List;


public class ShowRoomResponse implements EquizPacket {
    @Serial
    private static final long serialVersionUID = -3671318777867721526L;
    public List<RoomWrapper> roomList;

    public ShowRoomResponse() {

    }

    public ShowRoomResponse(List<RoomWrapper> roomList) {
        this.roomList = roomList;
    }

    @Override
    public String getType() {
        return "show_room_response";
    }
}