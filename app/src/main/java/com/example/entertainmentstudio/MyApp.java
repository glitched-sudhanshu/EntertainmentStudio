package com.example.entertainmentstudio;

import android.app.Application;

import com.example.entertainmentstudio.repository.NewsDatabase;

public class MyApp extends Application {
    private static NewsDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = NewsDatabase.getDatabase(this);
    }

    public NewsDatabase getDatabase() {
        return database;
    }
}
