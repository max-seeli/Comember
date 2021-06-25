package com.project.comember.game;

public class GameMode {

    private int imageSource;
    private String name;
    private String info;
    private int highScore;
    private int avgScore;
    private int lastScore;
    private int actionId;

    public GameMode(int imageSource, String name, String info, int highScore, int avgScore, int lastScore, int actionId) {
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

    public void setImageSource(int imageSource) {
        this.imageSource = imageSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(int avgScore) {
        this.avgScore = avgScore;
    }

    public int getLastScore() {
        return lastScore;
    }

    public void setLastScore(int lastScore) {
        this.lastScore = lastScore;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }
}
