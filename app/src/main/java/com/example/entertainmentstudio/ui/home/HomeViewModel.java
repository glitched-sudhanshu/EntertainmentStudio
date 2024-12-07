package com.example.entertainmentstudio.ui.home;

import android.util.Log;

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
            Log.d("insertData", "vm method: title: " + newsItem.title);
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

    public void likeNewsItem(NewsItem item) {
        executorService.execute(() -> {
            Log.d("saveItem", "likeNewsItem: like " + item.isSaved);
            boolean isLiked = item.isSaved;
            newsDao.updateSavedStatus(item.getId(), !isLiked == true);
            fetchAllNewsItems();
        });
    }

    // Expose LiveData to observe the list
    public LiveData<List<NewsItem>> getAllNewsItemsLiveData() {
        return allNewsItemsLiveData;
    }
}