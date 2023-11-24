package com.dga.game.EquizPacket.Message;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MessageRequest implements EquizPacket {
    private static final long serialVersionUID = -2552934271788998803L;
    public String text;

    public MessageRequest() {

    }

    public MessageRequest(String text) {
        this.text = text;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    @Override
    public String getType() {
        return "message_request";
    }
}