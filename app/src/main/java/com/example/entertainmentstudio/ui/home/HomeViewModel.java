package com.example.entertainmentstudio.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.entertainmentstudio.model.NewsItem;
import com.example.entertainmentstudio.repository.NewsDao;
import com.example.entertainmentstudio.repository.NewsDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeViewModel extends ViewModel {

    private final NewsDao newsDao;
    private final ExecutorService executorService;

    private final MutableLiveData<List<NewsItem>> allNewsItemsLiveData = new MutableLiveData<>();

    public HomeViewModel(NewsDao newsDao) {
        this.newsDao = newsDao;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    // Method to insert a news item
    public void insertNewsItem(NewsItem newsItem) {
        executorService.execute(() -> {
            newsDao.insert(newsItem); // Insert the item
            fetchAllNewsItems(); // Refresh the list
        });
    }

    // Method to fetch all news items
    public void fetchAllNewsItems() {
        executorService.execute(() -> {
            List<NewsItem> newsItems = newsDao.getAll();
            allNewsItemsLiveData.postValue(newsItems); // Post the value to LiveData
        });
    }

    // Expose LiveData to observe the list
    public LiveData<List<NewsItem>> getAllNewsItemsLiveData() {
        return allNewsItemsLiveData;
    }
}