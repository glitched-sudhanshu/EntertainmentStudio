package com.example.entertainmentstudio.ui.savedNews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entertainmentstudio.databinding.FragmentSavedNewsBinding;
import com.example.entertainmentstudio.model.DummyDataGenerator;
import com.example.entertainmentstudio.model.NewsItem;
import com.example.entertainmentstudio.ui.adapter.NewsAdapter;

import java.util.List;

public class SavedNewsFragment extends Fragment {

    private FragmentSavedNewsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SavedNewsViewModel galleryViewModel =
                new ViewModelProvider(this).get(SavedNewsViewModel.class);

        binding = FragmentSavedNewsBinding.inflate(inflater, container, false);

        RecyclerView recyclerView = binding.rvNews;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        NewsAdapter newsAdapter = new NewsAdapter();
        recyclerView.setAdapter(newsAdapter);
        List<NewsItem> dummyNewsItems = DummyDataGenerator.generateDummyNewsItems();
        newsAdapter.setNewsItems(dummyNewsItems);


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}