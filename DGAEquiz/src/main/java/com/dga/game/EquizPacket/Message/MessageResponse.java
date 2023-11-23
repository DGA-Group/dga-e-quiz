package com.dga.game.EquizPacket.Message;

import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.PacketResponse;

import java.io.Serial;

public class MessageResponse implements EquizPacket {
    @Serial
    private static final long serialVersionUID = 6131545497749990430L;
    public PacketResponse status;
    public int userId;
    public String username;
    public String name;
    public String text;

    public MessageResponse() {

    }

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
