package com.project.comember.game;

import java.util.HashMap;
import java.util.Map;

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

    public int getValue() {
        return value;
    }
}
