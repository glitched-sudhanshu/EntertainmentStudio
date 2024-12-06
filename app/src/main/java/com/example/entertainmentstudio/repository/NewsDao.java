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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NewsItem user);

    @Delete
    void delete(NewsItem user);
}
