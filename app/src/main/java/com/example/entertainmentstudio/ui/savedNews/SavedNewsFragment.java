package com.example.entertainmentstudio.ui.savedNews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entertainmentstudio.MyApp;
import com.example.entertainmentstudio.R;
import com.example.entertainmentstudio.databinding.FragmentSavedNewsBinding;
import com.example.entertainmentstudio.model.NewsItem;
import com.example.entertainmentstudio.repository.NewsDao;
import com.example.entertainmentstudio.ui.adapter.NewsAdapter;
import com.example.entertainmentstudio.ui.home.HomeViewModel;
import com.example.entertainmentstudio.ui.home.HomeViewModelFactory;

public class SavedNewsFragment extends Fragment {

    private FragmentSavedNewsBinding binding;
    private HomeViewModel homeViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewsDao newsDao = ((MyApp) requireActivity().getApplication()).getDatabase().newsDao();
        homeViewModel = new ViewModelProvider(this, new HomeViewModelFactory(newsDao))
                .get(HomeViewModel.class);
        homeViewModel.fetchAllLikedNewsItem();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSavedNewsBinding.inflate(inflater, container, false);

        RecyclerView recyclerView = binding.rvNews;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        NewsAdapter newsAdapter = new NewsAdapter(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(NewsItem item, int position) {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                Bundle bundle = new Bundle();
                bundle.putString("title", item.getTitle());
                bundle.putString("description", item.getDescription());
                bundle.putString("image", item.getImageUrl());
                navController.navigate(R.id.action_nav_home_to_nav_news_detail, bundle);
            }

            @Override
            public void onItemSaveClick(NewsItem item, int position) {
                homeViewModel.likeNewsItem(item);
            }
        }, requireContext());
        recyclerView.setAdapter(newsAdapter);
        homeViewModel.getAllLikedNewsItems().observe(getViewLifecycleOwner(), newsItems -> {
            newsAdapter.setNewsItems(newsItems);
            if (newsItems == null || newsItems.isEmpty()) {
                binding.viewEmptyState.setVisibility(View.VISIBLE);
                binding.cvList.setVisibility(View.GONE);
            } else {
                binding.viewEmptyState.setVisibility(View.GONE);
                binding.cvList.setVisibility(View.VISIBLE);
            }
        });

        binding.letReadBtn.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.nav_home);
        });


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}