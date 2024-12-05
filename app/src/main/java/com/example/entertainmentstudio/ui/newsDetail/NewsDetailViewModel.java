package com.example.entertainmentstudio.ui.newsDetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsDetailViewModel  extends ViewModel {

    private final MutableLiveData<String> mText;

    public NewsDetailViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}