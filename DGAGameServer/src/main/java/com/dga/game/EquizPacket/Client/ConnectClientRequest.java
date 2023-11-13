package com.dga.game.EquizPacket.Client;

import com.dga.game.EquizPacket.EquizPacket;

public class ConnectClientRequest implements EquizPacket {
    public String username;

    public ConnectClientRequest(String username) {
        this.username = username;
    }

    @Override
    public String getType() {
        return "connect_client_request";
    }
}
