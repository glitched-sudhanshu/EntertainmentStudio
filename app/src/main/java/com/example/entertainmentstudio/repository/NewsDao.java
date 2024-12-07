package com.example.entertainmentstudio.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.entertainmentstudio.Constants;
import com.example.entertainmentstudio.model.NewsItem;

import java.util.List;

@Dao
public interface NewsDao {
    @Query("SELECT * FROM " + Constants.DB_NAME)
    List<NewsItem> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(NewsItem item);

    @Delete
    void delete(NewsItem item);

    @Query("UPDATE " + Constants.DB_NAME + " SET is_saved = :isSaved WHERE uid = :id")
    void updateSavedStatus(int id, boolean isSaved);

    @Query("SELECT * FROM " + Constants.DB_NAME + " WHERE is_saved = 1")
    List<NewsItem> getSavedNews();
}
