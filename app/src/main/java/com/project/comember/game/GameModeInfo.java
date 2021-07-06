package com.project.comember.game;

public class GameModeInfo {

    private final int imageSource;
    private final String name;
    private final String info;
    private final int highScore;
    private final float avgScore;
    private final int lastScore;
    private final int actionId;

    public GameModeInfo(int imageSource, String name, String info, int highScore, float avgScore, int lastScore, int actionId) {
        this.imageSource = imageSource;
        this.name = name;
        this.info = info;
        this.highScore = highScore;
        this.avgScore = avgScore;
        this.lastScore = lastScore;
        this.actionId = actionId;
    }

    public int getImageSource() {
        return imageSource;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public int getHighScore() {
        return highScore;
    }

    public float getAvgScore() {
        return avgScore;
    }

    public int getLastScore() {
        return lastScore;
    }

    public int getActionId() {
        return actionId;
    }
}
