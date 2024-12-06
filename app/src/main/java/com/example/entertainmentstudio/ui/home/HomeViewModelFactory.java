package com.example.entertainmentstudio.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.entertainmentstudio.repository.NewsDao;

public class HomeViewModelFactory implements ViewModelProvider.Factory {

    private final NewsDao newsDao;

    public HomeViewModelFactory(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(newsDao);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
