package com.project.comember.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum GameColor {
    INVALID(-1),
    RED(0),
    BLUE(1),
    YELLOW(2),
    GREEN(3);

    private int value;
    private static Map map = new HashMap<>();

    GameColor(int value) {
        this.value = value;
    }

    static {
        for (GameColor gameColor : GameColor.values()) {
            map.put(gameColor.value, gameColor);
        }
    }

    public static GameColor valueOf(int colorType) {
        return (GameColor)map.get(colorType);
    }

    public static GameColor getRandomColor() {
        //inclusive values
        int max = 3;
        int min = 0;

        Random r = new Random();
        int randomInt = r.nextInt((max - min) + 1) + min;
        return valueOf(randomInt);
    }

    public int getValue() {
        return value;
    }
}
