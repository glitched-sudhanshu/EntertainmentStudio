package com.example.entertainmentstudio.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.entertainmentstudio.model.NewsItem;

@Database(entities = {NewsItem.class}, version = 1)
public abstract class NewsDatabase extends RoomDatabase {

    // Abstract method to provide DAO
    public abstract NewsDao newsDao();

    // Singleton instance
    private static volatile NewsDatabase INSTANCE;

    // Method to get the database instance
    public static NewsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NewsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            NewsDatabase.class,
                            "memo_diary_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}

