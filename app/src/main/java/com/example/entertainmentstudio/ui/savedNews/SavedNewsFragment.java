package com.example.entertainmentstudio.ui.savedNews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.entertainmentstudio.databinding.FragmentSavedNewsBinding;

public class SavedNewsFragment extends Fragment {

    private FragmentSavedNewsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SavedNewsViewModel galleryViewModel =
                new ViewModelProvider(this).get(SavedNewsViewModel.class);

        binding = FragmentSavedNewsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}