package com.project.comember.game;

import java.util.HashMap;
import java.util.Map;

public enum GameMode {
    CLASSIC(0),
    SPINNING(1),
    NOREPEAT(2);

    private final int value;
    private static final Map<Integer, GameMode> map = new HashMap<>();

    GameMode(int value) {
        this.value = value;
    }

    static {
        for (GameMode gameMode : GameMode.values()) {
            map.put(gameMode.value, gameMode);
        }
    }

    public static GameMode valueOf(int colorType) {
        return (GameMode) map.get(colorType);
    }

    public int getValue() {
        return value;
    }
}
