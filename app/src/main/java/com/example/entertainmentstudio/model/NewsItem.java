package com.example.entertainmentstudio.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.entertainmentstudio.Constants;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = Constants.DB_NAME)
public class NewsItem {
    public int uid;

    @PrimaryKey()
    @ColumnInfo(name = "title")
    @NonNull
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "image_url")
    public String imageUrl;

    @ColumnInfo(name = "is_saved")
    public Boolean isSaved;

    @ColumnInfo(name = "source_url")
    public String sourceUrl;

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

    public String getImageUrl() { return imageUrl; }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }
}

