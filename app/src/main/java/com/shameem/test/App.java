package com.shameem.test;

import androidx.multidex.MultiDexApplication;

import com.shameem.test.pref.SharedPrefs;


public class App extends MultiDexApplication {

    public static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPrefs.initializeSharedPrefs(this);
    }

}
