package com.dga.game.GameMode;

import java.util.Random;

public class GameHelper {
    private static Random rand = new Random();
    public static String[] keywords = {
            "ary", "ion", "iso", "eso", "ack", "cos", "pop", "rba", "sly", "nab", "opi",
            "rit", "occ", "ssa", "uri", "wer", "oco", "pre", "bar", "ros", "ial", "aga",
            "ied", "scr", "err", "asi", "nad", "ert", "nca", "usn", "ber", "nsi", "ery",
            "dip", "pri", "phe", "nve", "ons", "ode", "ctr", "gan", "axi", "ign", "typ",
            "ned", "nit", "sid", "pop", "ose", "icr", "ime", "nre", "boa", "iat", "psy",
            "hio", "rmo", "tur", "adr", "epe", "olo", "pli", "ist", "umb", "fic", "bit",
            "ict", "gle", "oat", "ter", "ify", "sis", "ond", "icu", "ged", "ula", "rde",
            "hee", "dec", "cul", "sts", "der", "ago", "amp", "lop", "ors", "git", "eac"
    };

    public static String getRandomKeyword() {
        int index = rand.nextInt(keywords.length);
        return keywords[index];
    }
}
