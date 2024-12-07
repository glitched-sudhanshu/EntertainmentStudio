package com.example.entertainmentstudio.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.entertainmentstudio.model.NewsItem;
import com.example.entertainmentstudio.repository.NewsDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeViewModel extends ViewModel {

    private final NewsDao newsDao;
    private final ExecutorService executorService;

    private final MutableLiveData<List<NewsItem>> allNewsItems = new MutableLiveData<>();
    private final MutableLiveData<List<NewsItem>> allLikedNewsItems = new MutableLiveData<>();

    public HomeViewModel(NewsDao newsDao) {
        this.newsDao = newsDao;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    // Method to insert a news item
    public void insertNewsItem(NewsItem newsItem) {
        executorService.execute(() -> {
            Log.d("insertData", "vm method: title: " + newsItem.title);
            newsDao.insert(newsItem); // Insert the item
            fetchAllNewsItems(); // Refresh the list
        });
    }

    // Method to fetch all news items
    public void fetchAllNewsItems() {
        executorService.execute(() -> {
            List<NewsItem> newsItems = newsDao.getAll();
            allNewsItems.postValue(newsItems);
            fetchAllLikedNewsItem(); // Post the value to LiveData
        });
    }

    public void fetchAllLikedNewsItem() {
        executorService.execute(() -> {
            List<NewsItem> newsItems = newsDao.getSavedNews();
            allLikedNewsItems.postValue(newsItems);
        });
    }

    public void likeNewsItem(NewsItem item) {
        executorService.execute(() -> {
            Log.d("saveItem", "likeNewsItem: like " + item.isSaved);
            boolean isLiked = item.isSaved;
            newsDao.updateSavedStatus(item.getId(), !isLiked == true);
            fetchAllNewsItems();
        });
    }

    // Expose LiveData to observe the list
    public LiveData<List<NewsItem>> getAllNewsItems() {
        return allNewsItems;
    }

    public LiveData<List<NewsItem>> getAllLikedNewsItems() {
        return allLikedNewsItems;
    }
}