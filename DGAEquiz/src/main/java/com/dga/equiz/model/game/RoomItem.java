package com.dga.equiz.model.game;

public class RoomItem {
    private String roomId;
    private String roomName;
    private int currentPlayer;
    private int playerLimit;

    public RoomItem(String roomId, String roomName, int currentPlayer, int playerLimit) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.currentPlayer = currentPlayer;
        this.playerLimit = playerLimit;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getPlayerLimit() {
        return playerLimit;
    }

    public void setPlayerLimit(int playerLimit) {
        this.playerLimit = playerLimit;
    }
}
