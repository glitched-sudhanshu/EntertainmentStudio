package com.example.entertainmentstudio.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.entertainmentstudio.MyApp;
import com.example.entertainmentstudio.databinding.FragmentHomeBinding;
import com.example.entertainmentstudio.model.NewsItem;
import com.example.entertainmentstudio.repository.NewsDao;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        NewsDao newsDao = ((MyApp) requireActivity().getApplication()).getDatabase().newsDao();
        HomeViewModel homeViewModel = new ViewModelProvider(this, new HomeViewModelFactory(newsDao))
                .get(HomeViewModel.class);

        // Pass the DAO to the ViewModel

        homeViewModel.getAllNewsItemsLiveData().observe(getViewLifecycleOwner(), newsItems -> {
            // Update UI with the list of news items

            for (NewsItem item : newsItems) {
                System.out.println("News Title: " + item.title);
                Log.d("roombdsetup", "onCreateView: ${item.title}" + item.title + newsItems.size()) ;
            }
        });

        // Insert a news item example
        NewsItem newsItem = new NewsItem();
        newsItem.title = "Sample News Title + 1";
        newsItem.description = "Sample Description";
        newsItem.imageUrl = "https://example.com";
        homeViewModel.insertNewsItem(newsItem);
        homeViewModel.fetchAllNewsItems();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}