package com.dga.game.EquizPacket.Message;

import com.dga.game.EquizPacket.EquizPacket;

public class MessageRequest implements EquizPacket {
    public String text;


    public MessageRequest(String text) {
        this.text = text;
    }

    @Override
    public String getType() {
        return "message_request";
    }
}