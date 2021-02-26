package com.project.comember.game;

import java.util.HashMap;
import java.util.Map;

public enum GameStatus {
    IDLE(0),
    HIGHLIGHTING(1),
    PLAYING(2),
    OVER(3);

    private int value;
    private static Map map = new HashMap<>();

    GameStatus(int value) {
        this.value = value;
    }

    static {
        for (GameStatus gameStatus : GameStatus.values()) {
            map.put(gameStatus.value, gameStatus);
        }
    }

    public static GameStatus valueOf(int colorType) {
        return (GameStatus)map.get(colorType);
    }

    public int getValue() {
        return value;
    }
}
