package com.dga.game.EquizPacket.Client;

import com.dga.game.EquizPacket.EquizPacket;

import java.io.Serial;

public class ConnectClientRequest implements EquizPacket {
    @Serial
    private static final long serialVersionUID = -1710162420085684728L;
    public int userId;
    public String username;
    public String name;

    public ConnectClientRequest() {

    }

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
