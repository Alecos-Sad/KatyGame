package com.example.my_framework;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

public class AudioFW {

    AssetManager assetManager;
    SoundPool soundPool;


    public AudioFW(Activity activity) {

        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        assetManager = activity.getAssets();
        soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);

    }

    public MusicFW newMusic(String filename) {
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            assetFileDescriptor = assetManager.openFd(filename);
            return new MusicFW(assetFileDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Невозможно загрузить музыку");
        }
    }

    public SoundFW newSound(String filename) {
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            assetFileDescriptor = assetManager.openFd(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sound = soundPool.load(assetFileDescriptor, 0);
        return new SoundFW(sound, soundPool);
    }
}
