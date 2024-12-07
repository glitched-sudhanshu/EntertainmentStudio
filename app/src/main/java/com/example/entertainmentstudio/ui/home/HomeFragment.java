package com.example.entertainmentstudio.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    private HomeViewModel homeViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NewsDao newsDao = ((MyApp) requireActivity().getApplication()).getDatabase().newsDao();
        homeViewModel = new ViewModelProvider(this, new HomeViewModelFactory(newsDao))
                .get(HomeViewModel.class);
        List<NewsItem> dummyNewsItems = DummyDataGenerator.generateDummyNewsItems();
        for (int i = 0; i < 7; i++) {
            Log.d("insertData", "onCreate: index -> " + i + " title: " + dummyNewsItems.get(i).title);
            homeViewModel.insertNewsItem(dummyNewsItems.get(i));
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.rvNews;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        NewsAdapter newsAdapter = new NewsAdapter(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(NewsItem item, int position) {
                Log.d("NewsAdapter", "onItemClick: ");
            }

            @Override
            public void onItemSaveClick(NewsItem item, int position) {
                Log.d("Save", "onItemSaveClick: ");
                homeViewModel.likeNewsItem(item);
            }
        }, requireContext());

        recyclerView.setAdapter(newsAdapter);
        homeViewModel.getAllNewsItems().observe(getViewLifecycleOwner(), newsAdapter::setNewsItems);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}