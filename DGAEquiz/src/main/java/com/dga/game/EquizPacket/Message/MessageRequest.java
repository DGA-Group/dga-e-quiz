package com.dga.game.EquizPacket.Message;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.Serial;

public class MessageRequest implements EquizPacket {
    @Serial
    private static final long serialVersionUID = -2552934271788998803L;
    public String text;

    public MessageRequest() {

    }

    public MessageRequest(String text) {
        this.text = text;
    }

    @Override
    public String getType() {
        return "message_request";
    }
}