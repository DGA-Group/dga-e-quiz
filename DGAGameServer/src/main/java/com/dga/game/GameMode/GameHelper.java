package com.dga.game.GameMode;

import java.util.Random;

public class GameHelper {
    private static Random rand = new Random();
    public static String[] keywords = {
            "abc",
            "ijk"
    };

    public static String getRandomKeyword() {
        int index = rand.nextInt(keywords.length);
        return keywords[index];
    }
}
