package com.dga.game.EquizPacket.Room.ShowRoom;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;


public class ShowRoomResponse implements EquizPacket {
    private static final long serialVersionUID = 2370608051129232922L;
    public List<RoomWrapper> roomList;

    public ShowRoomResponse() {

    }

    public ShowRoomResponse(List<RoomWrapper> roomList) {
        this.roomList = roomList;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    @Override
    public String getType() {
        return "show_room_response";
    }
}