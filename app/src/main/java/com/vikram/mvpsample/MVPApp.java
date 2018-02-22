package com.vikram.mvpsample;

import android.support.multidex.MultiDexApplication;

import com.vikram.mvpsample.utils.AppSharedPreferenceManager;

/**
 * Created by VIKRAM R on 20/02/2018.
 */

public class MVPApp extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        AppSharedPreferenceManager.init(this);
    }
}
