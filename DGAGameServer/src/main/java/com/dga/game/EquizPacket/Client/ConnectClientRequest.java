package com.dga.game.EquizPacket.Client;

import com.dga.game.EquizPacket.EquizPacket;

public class ConnectClientRequest implements EquizPacket {
    private static final long serialVersionUID = 5746327350016752515L;
    public int userId;
    public String username;
    public String name;

    public ConnectClientRequest(int userId, String username, String name) {
        this.userId = userId;
        this.username = username;
        this.name = name;
    }

    @Override
    public String getType() {
        return "connect_client_request";
    }
}
