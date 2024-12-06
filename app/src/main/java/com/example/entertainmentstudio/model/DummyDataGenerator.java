package com.example.entertainmentstudio.model;

import java.util.ArrayList;
import java.util.List;

public class DummyDataGenerator {

    public static List<NewsItem> generateDummyNewsItems() {
        List<NewsItem> newsItems = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            NewsItem item = new NewsItem();
            item.uid = i; // Primary key, autogenerated in the database
            item.title = "News Title " + i;
            item.description = "This is the description for news item number " + i + ".";
            item.imageUrl = "https://example.com/image" + i + ".jpg";
            item.isSaved = i % 2 == 0; // Alternate saved state for demo purposes

            newsItems.add(item);
        }

        return newsItems;
    }
}