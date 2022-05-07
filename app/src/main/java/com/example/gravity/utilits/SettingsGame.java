package com.example.gravity.utilits;

import android.content.SharedPreferences;

import com.example.my_framework.CoreFW;

public class SettingsGame {

    public static int[] mDistance = {9999, 5555, 4444, 3333, 1111};


    public static void saveSetting(CoreFW coreFW) {
        SharedPreferences.Editor editor = coreFW.getSharedPreferences().edit();
        editor.clear();
        for (int i = 0; i < 5; i++) {
            editor.putInt("passedDistance" + i, mDistance[i]);
        }
        editor.apply();
    }

    public static void loadSettings(CoreFW coreFW) {
        for (int i = 0; i < 5; i++) {
            mDistance[i] = coreFW.getSharedPreferences().getInt("passedDistance" + i, mDistance[i]);
        }
    }

    public static void addDistance(int values) {
        for (int i = 0; i < 5; i++) {
            if (mDistance[i] < values) {
                mDistance[i] = values;
                break;
            }
        }
    }
}
