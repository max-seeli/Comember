package com.project.comember.sound;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

import com.project.comember.R;
import com.project.comember.game.GameColor;

import java.util.HashMap;
import java.util.Map;

public class GameSoundPlayer {

    private static SoundPool soundPool;
    private static HashMap<Object, Integer> soundPoolMap;



    public static void initSounds(Context context) {
        if (soundPool != null && soundPoolMap != null)
            return;

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(2)
                .setAudioAttributes(audioAttributes)
                .build();

        soundPoolMap = new HashMap(4);

        soundPoolMap.put(GameColor.BLUE, soundPool.load(context, R.raw.low, 1));
        soundPoolMap.put(GameColor.YELLOW, soundPool.load(context, R.raw.medium, 1));
        soundPoolMap.put(GameColor.GREEN, soundPool.load(context, R.raw.high, 1));
        soundPoolMap.put(GameColor.RED, soundPool.load(context, R.raw.ultra, 1));


    }

    public static void playSoundColor(Context context, GameColor color) {
        if (soundPool == null || soundPoolMap == null) {
            initSounds(context);
        }

        float volume = 1.0f;

        soundPool.play(soundPoolMap.get(color), volume, volume, 1, 0, 1f);
    }
}
