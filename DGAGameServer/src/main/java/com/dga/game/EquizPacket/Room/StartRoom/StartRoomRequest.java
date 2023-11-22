package com.dga.game.EquizPacket.Room.StartRoom;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StartRoomRequest implements EquizPacket {
    private static final long serialVersionUID = -2317145088469874136L;
    public String gameMode;

    public StartRoomRequest() {

    }

    public StartRoomRequest(String gameMode) {
        this.gameMode = gameMode;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    @Override
    public String getType() {
        return "start_room_request";
    }
}
