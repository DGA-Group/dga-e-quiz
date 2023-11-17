package com.dga.game;

import com.dga.game.EquizPacket.EquizPacket;
import com.dga.game.EquizPacket.Message.MessageResponse;
import com.dga.game.EquizPacket.PacketResponse;
import com.dga.game.EquizPacket.Room.StartRoom.StartRoomResponse;
import com.dga.game.GameMode.RedTea;
import com.dga.game.GameMode.TeaGame;

import java.io.IOException;
import java.util.*;

public class Room implements Runnable {
    public String roomId;
    public String roomName;
    public String roomPassword;
    public int roomPlayerLimits;
    public Set<ClientHandler> playerList = new HashSet<>();

    private volatile boolean isRunning = false;
    private volatile boolean isClose = false;
    private final Object lock = new Object();

    public TeaGame currentGameMode = null;
    public ClientHandler currentWinner = null;
    public List<MessageResponse> messageHistory = new ArrayList<>();
    public Map<String, Integer> playerPoint = new HashMap<>();

    public Room(String roomId, String roomName, String roomPassword, int roomPlayerLimits) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomPassword = roomPassword;
        this.roomPlayerLimits = roomPlayerLimits;
    }

    public void addPlayer(ClientHandler client) {
        playerList.add(client);
        System.out.println("Player " + client.socket.getInetAddress().toString() + " joined the room");

        ServerHelper.callFuncDelay(() -> {
            for (MessageResponse message : messageHistory) {
                client.sendPacket(message);
            }
        }, 500);

    }

    public void broadcast(EquizPacket packet, ClientHandler except) throws IOException {
        if (packet instanceof MessageResponse) {
            MessageResponse response = (MessageResponse) packet;
            messageHistory.add(response);
        }

        for (ClientHandler player : playerList) {
            if (player == except) continue;
            player.sendPacket(packet);
        }
    }

    public void startGame(String gameMode, ClientHandler host) throws IOException {
        synchronized (lock) {
            StartRoomResponse response = new StartRoomResponse();
            host.sendPacket(response);
            isRunning = true;
            switch (gameMode) {
                case "red_tea":
                    currentGameMode = new RedTea(this);
                    break;
                default:
                    break;
            }
            lock.notifyAll();
        }
    }

    public void endGame() {
        isRunning = false;
        currentWinner = null;
        currentGameMode = null;
        playerPoint.clear();
    }

    public void abort() {
        isClose = true;
    }

    @Override
    public void run() {
        try {
            while (!isClose) {

                // Pause game until game is started
                synchronized (lock) {
                    while (!isRunning | currentGameMode == null) {
                        lock.wait();
                    }
                }

                // =============Handle game ============
                currentGameMode.play();

                // =============Announce the player =============
                MessageResponse winnerMessage = new MessageResponse(PacketResponse.OK,
                        0, "server", "Server",
                        "Time's up! The winner is " + currentWinner.name);
                broadcast(winnerMessage, null);
                endGame();
            }

        } catch (InterruptedException | IOException e) {
            abort();
        }
    }

    /**
     * Check answer for current game.
     *
     * @param answer the answer as String.
     * @param client the client that send the answer.
     */
    public void checkAnswer(String answer, ClientHandler client) throws IOException {
        // Check if valid answer
        String[] words = answer.split(" ");
        if (words.length != 1) {
            return;
        }

        // ======= Check answer
        if (currentGameMode == null) {
            return;
        }
        currentGameMode.checkAnswer(words[0], client);
    }
}
