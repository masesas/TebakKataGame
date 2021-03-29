package com.example.tebakkatagame.Utils;

import java.util.Random;

public class StringUtils {
    public static String setRandomNumber() {
        Random rand = new Random();
        return String.valueOf((rand.nextInt(9) + 1));
    }

    public static String setRandomNumber(int scale, int increment) {
        Random rand = new Random();
        return String.valueOf((rand.nextInt(scale) + increment));
    }

}
