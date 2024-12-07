package com.example.entertainmentstudio.ui.newsDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.entertainmentstudio.R;
import com.example.entertainmentstudio.databinding.FragmentNewsDetailBinding;

public class NewsDetailFragment extends Fragment {

    private FragmentNewsDetailBinding binding;
    private String title;
    private String description;
    private String imageUrl;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNewsDetailBinding.inflate(inflater, container, false);
        if (getArguments() != null) {
            title = getArguments().getString("title");
            description = getArguments().getString("description");
            imageUrl = getArguments().getString("image");
        }

        if (title != null) {
            binding.tvNewsTitle.setText(title);
        }

        if (description != null) {
            binding.tvNewsDesc.setText(description);
        }

        Glide.with(requireActivity()) // Pass the context
                .load(imageUrl) // The URL of the image
                .placeholder(R.drawable.side_nav_bar)
                .centerCrop()// Optional placeholder
                .into(binding.newsImage);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}