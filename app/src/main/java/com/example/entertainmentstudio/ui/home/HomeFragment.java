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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entertainmentstudio.MyApp;
import com.example.entertainmentstudio.databinding.FragmentHomeBinding;
import com.example.entertainmentstudio.model.DummyDataGenerator;
import com.example.entertainmentstudio.model.NewsItem;
import com.example.entertainmentstudio.repository.NewsDao;
import com.example.entertainmentstudio.ui.adapter.NewsAdapter;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        NewsDao newsDao = ((MyApp) requireActivity().getApplication()).getDatabase().newsDao();
        HomeViewModel homeViewModel = new ViewModelProvider(this, new HomeViewModelFactory(newsDao))
                .get(HomeViewModel.class);

        RecyclerView recyclerView = binding.rvNews;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        NewsAdapter newsAdapter = new NewsAdapter();
        recyclerView.setAdapter(newsAdapter);
        List<NewsItem> dummyNewsItems = DummyDataGenerator.generateDummyNewsItems();
        newsAdapter.setNewsItems(dummyNewsItems);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}