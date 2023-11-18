package com.dga.game;

public class Main {
    public static void main(String[] args) {
        EquizServer server = new EquizServer();
        Thread mainThread = new Thread(server);
        mainThread.start();
    }
}
