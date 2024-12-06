package com.example.entertainmentstudio.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.entertainmentstudio.Constants;

@Entity(tableName = Constants.DB_NAME)
public class NewsItem {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "image_url")
    public String imageUrl;
}
