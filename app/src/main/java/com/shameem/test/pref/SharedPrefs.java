package com.shameem.test.pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPrefs {

    private static SharedPreferences preferences;
    private static final String TEST_PREFERENCE_FILE_KEY = "com_shameem_test_preference_key";

    public static void initializeSharedPrefs(Context context) {
        preferences = context.getSharedPreferences(TEST_PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        setPreferences(preferences);
    }

    public static SharedPreferences getPreferences() {
        return preferences;
    }

    public static void setPreferences(SharedPreferences preferences) {
        SharedPrefs.preferences = preferences;
    }

    public static void setString(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(String key, String def) {
        return preferences.getString(key, def);
    }

    public static void setInt(String key, int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(String key, int def) {
        return preferences.getInt(key, def);
    }

    public static void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(String key, boolean def) {
        return preferences.getBoolean(key, def);
    }

    public static void remove(String key) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void clearAllPrefs() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
    public static class Keys{
        //General Keys
        public static final String EPISODE_ID = "episode_id";
    }
}
