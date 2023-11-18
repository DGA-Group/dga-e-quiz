package com.dga.game.GameMode;

import com.dga.game.ClientHandler;

import java.io.IOException;

public abstract class TeaGame {
    public abstract void play() throws IOException, InterruptedException;

    public abstract void checkAnswer(String word, ClientHandler client) throws IOException;
}
