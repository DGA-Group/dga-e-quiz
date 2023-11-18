package com.dga.game.EquizPacket.Message;

import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.PacketResponse;

public class MessageResponse implements EquizPacket {
    public PacketResponse status;
    public int userId;
    public String username;
    public String name;
    public String text;

    public MessageResponse(PacketResponse status) {
        this.status = status;
    }

    public MessageResponse(PacketResponse status, int userId, String username, String name, String text) {
        this.status = status;
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.text = text;
    }

    @Override
    public String getType() {
        return "message_response";
    }
}
