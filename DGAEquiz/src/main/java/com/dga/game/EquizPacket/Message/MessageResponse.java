package com.dga.game.EquizPacket.Message;

import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.PacketResponse;

public class MessageResponse implements EquizPacket {
    public PacketResponse status;
    public String username;
    public String text;

    public MessageResponse(PacketResponse status) {
        this.status = status;
    }

    public MessageResponse(PacketResponse status, String username, String text) {
        this.status = status;
        this.username = username;
        this.text = text;
    }

    @Override
    public String getType() {
        return "message_response";
    }
}
