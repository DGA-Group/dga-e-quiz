package com.dga.game.EquizPacket;

public class BaseResponse {
    public boolean success;
    public PacketResponse status;

    public BaseResponse(boolean success, PacketResponse status) {
        this.success = success;
        this.status = status;
    }
}
