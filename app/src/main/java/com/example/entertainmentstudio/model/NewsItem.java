package com.example.entertainmentstudio.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.entertainmentstudio.Constants;

import java.util.ArrayList;
import java.util.List;

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

    @ColumnInfo(name = "is_saved")
    public Boolean isSaved;

    // Getters and setters
    public int getId() {
        return uid;
    }

    public void setId(int id) {
        this.uid = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsSaved(Boolean isSaved) {
        this.isSaved = isSaved;
    }

    public Boolean getIsSaved() {
        return isSaved;
    }
}

