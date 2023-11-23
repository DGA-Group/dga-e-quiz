package com.dga.game.GameMode;

import com.dga.game.ClientHandler;
import com.dga.game.DBHelper;
import com.dga.game.EquizPacket.Message.MessageResponse;
import com.dga.game.EquizPacket.PacketResponse;
import com.dga.game.Room;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class BlueTea extends TeaGame {
    private final Room hostRoom;
    private volatile Timer currentRoundTimer;
    private volatile String currentRoundWord;
    private volatile String currentRoundLongestWord;
    private volatile ClientHandler currentRoundWinner;

    private volatile boolean isRunning = true;
    private volatile Map<String, Integer> playerPoint;

    public BlueTea(Room hostRoom) {
        this.hostRoom = hostRoom;
    }

    private void nextRound() {
        if (hostRoom.currentWinner != null) {

            String winnerUsername = hostRoom.currentWinner.username;
            if (playerPoint.containsKey(winnerUsername)
                    && playerPoint.get(winnerUsername) >= 5) {
                isRunning = false;
            }

        }

        currentRoundTimer.abort();

        if (currentRoundWinner != null) {
            // Send response to client
            MessageResponse response = new MessageResponse(PacketResponse.OK,
                    0, "server", "Server",
                    "The winner of this round is " + currentRoundWinner.name);
            try {
                hostRoom.broadcast(response, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        currentRoundWinner = null;
        currentRoundWord = "";
        currentRoundLongestWord = "";

    }

    @Override
    public void play() throws IOException, InterruptedException {
        playerPoint = new HashMap<>();
        hostRoom.currentWinner = null;
        Thread.sleep(500);
        while (isRunning) {
            //Send word to the client.
            currentRoundWord = GameHelper.getRandomKeyword();
            MessageResponse messageResponse = new MessageResponse(PacketResponse.OK, 0,
                    "server", "Server", "Guess word: " + currentRoundWord);
            hostRoom.broadcast(messageResponse, null);

            // Wait for player word.
            currentRoundTimer = new Timer(10000);
            currentRoundTimer.start();
            currentRoundTimer.join();
            nextRound();

            // Wait 2 seconds after goto next round.
            new Timer(2000).run();
        }
    }

    @Override
    public void checkAnswer(String word, ClientHandler client) throws IOException {
        // Check if word is valid
        if (!isValidWord(word)) {
            return;
        }

        // Set the new longest word
        currentRoundWinner = client;
        currentRoundLongestWord = word;

        // Announce the good word!
        MessageResponse response = new MessageResponse(PacketResponse.OK,
                0, "server", "Server",
                client.name + " currently has the longest word : " + word);
        hostRoom.broadcast(response, null);

        // Add point to player.
        int currentPoint = 0;
        int highestPoint = 0;

        if (playerPoint.containsKey(client.username)) {
            currentPoint = playerPoint.get(client.username);
        }

        if (hostRoom.currentWinner != null) {
            String winnerUsername = hostRoom.currentWinner.username;
            if (playerPoint.containsKey(winnerUsername)) {
                highestPoint = playerPoint.get(winnerUsername);
            }
        }

        playerPoint.put(client.username, currentPoint + 1);

        // Check if this player is the top 1
        if (currentPoint + 1 > highestPoint) {
            hostRoom.currentWinner = client;
        }
    }

    private boolean isValidWord(String word) {
        if (!word.toLowerCase().contains(currentRoundWord.toLowerCase())) {
            return false;
        }

        boolean ret = false;
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        try {
            String sql = "SELECT word FROM av WHERE word = '" + word + "';";
            resultSet = DBHelper.executeQuerySqlite(sql);
            ret = resultSet.next();
            if (!currentRoundLongestWord.isEmpty()) {
                ret &= (currentRoundLongestWord.length() < word.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBHelper.closeQuery(resultSet, statement, connection);
            } catch (Exception ignore) {
            }
        }

        return ret;
    }
}
