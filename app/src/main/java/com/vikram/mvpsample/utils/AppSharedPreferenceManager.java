package com.vikram.mvpsample.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by VIKRAM R on 20/02/2018.
 */

public class AppSharedPreferenceManager {
    static SharedPreferences mSharedPref;
    static SharedPreferences.Editor prefsEditor;

    public AppSharedPreferenceManager() {

    }

    public static void init(Context context) {
        if (mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public static String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void write(String key, String value) {
        prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
        prefsEditor.commit();
    }

    public static boolean read(String key, boolean defValue) {
        return mSharedPref.getBoolean(key, defValue);
    }

    public static void write(String key, boolean value) {
        prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.apply();
        prefsEditor.commit();
    }

    public static Integer read(String key, int defValue) {
        return mSharedPref.getInt(key, defValue);
    }

    public static void write(String key, Integer value) {
        prefsEditor = mSharedPref.edit();
        prefsEditor.putInt(key, value);
        prefsEditor.apply();
        prefsEditor.commit();
    }

    public static Long read(String key, long defValue) {
        return mSharedPref.getLong(key, defValue);
    }

    public static void write(String key, long value) {
        prefsEditor = mSharedPref.edit();
        prefsEditor.putLong(key, value);
        prefsEditor.apply();
        prefsEditor.commit();
    }

    public static void clear() {
        prefsEditor = mSharedPref.edit();
        prefsEditor.clear();
        prefsEditor.apply();
        prefsEditor.commit();
    }
}