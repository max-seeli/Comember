package com.project.comember.game;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class GameStatistics {

    public static final String FILENAME_PREFIX = "game_statistics_";

    //Each game mode has it's own file with the name "game_statistics_game_mode_name"
    //Each type of information has a certain position in the file divided by commas
    private enum ValueType {
        HIGH_SCORE(0),
        AVERAGE_SCORE(1),
        LAST_SCORE(2),
        NUMBER_GAMES(3);

        private final int value;

        ValueType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }


    public static int getHighScore(Context context, GameMode gameMode) {
        return (int) getValue(context, gameMode, ValueType.HIGH_SCORE);
    }

    public static float getAverageScore(Context context, GameMode gameMode) {
        float avgValue = getValue(context, gameMode, ValueType.AVERAGE_SCORE);
        return (float) (Math.round(avgValue * 100.0) / 100.0);
    }

    public static int getLastScore(Context context, GameMode gameMode) {
        return (int) getValue(context, gameMode, ValueType.LAST_SCORE);
    }

    public static void setNewScore(Context context, GameMode gameMode, int score) {
        float[] newScores = calculateNewScores(score, getOldScores(context, gameMode));

        writeNewScores(context, gameMode, newScores);
    }

    private static float[] calculateNewScores(int score, float[] oldScores) {
        float[] newScores = new float[4];

        float oldHighScore = oldScores[0];
        float oldAvgScore = oldScores[1];
        float numberGamesPlayed = oldScores[3];

        //highScore
        if (score > oldHighScore)
            newScores[0] = score;
        else
            newScores[0] = oldHighScore;

        //averageScore
        newScores[1] = (oldAvgScore * numberGamesPlayed + score) / (numberGamesPlayed + 1);

        //lastScore
        newScores[2] = score;

        //numberGamesPlayed
        newScores[3] = numberGamesPlayed + 1;

        return newScores;
    }

    private static float getValue(Context context, GameMode gameMode, ValueType valueType) {
        return getOldScores(context, gameMode)[valueType.getValue()];
    }

    private static float[] getOldScores(Context context, GameMode gameMode) {
        String fileContent = readFile(context, fileNameOfGameMode(gameMode));
        String[] commaSplitFileContent = fileContent.split(",");

        float[] scores = new float[4];
        for (int i = 0; i < 4; i++) {
            try {
                scores[i] = Float.parseFloat(commaSplitFileContent[i]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
                scores[i] = 0f;
            }
        }
        return scores;
    }

    private static void writeNewScores(Context context, GameMode gameMode, float[] newScores) {
        StringBuilder serializedNewScores = new StringBuilder();

        for (float score : newScores) {
            serializedNewScores.append(score).append(",");
        }

        writeFile(context, fileNameOfGameMode(gameMode), serializedNewScores.toString());
    }

    private static String fileNameOfGameMode(GameMode gameMode) {
        return FILENAME_PREFIX + gameMode.name();
    }


    private static String readFile(Context context, String filename) {

        StringBuilder content = new StringBuilder();
        try {
            InputStream inputStream = context.openFileInput(filename);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null)
                content.append(line).append("\n");
            inputStream.close();
            bufferedReader.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return content.toString();
    }

    private static void writeFile(Context context, String filename, String fileContent) {

        try {
            OutputStream outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContent.getBytes());

            outputStream.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
