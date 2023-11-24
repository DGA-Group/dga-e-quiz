package com.dga.game.EquizPacket.Room.ShowRoom;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ShowRoomRequest implements EquizPacket {
    private static final long serialVersionUID = 888084826994961033L;

    public ShowRoomRequest() {

    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    @Override
    public String getType() {
        return "show_room_request";
    }
}
