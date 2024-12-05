package com.example.entertainmentstudio.ui.aboutUs;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.entertainmentstudio.R;
import com.example.entertainmentstudio.databinding.FragmentAboutUsBinding;

public class AboutUsFragment extends Fragment {

    private FragmentAboutUsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAboutUsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.tvAboutUsContent;
        binding.tvAboutUsContent.setText(Html.fromHtml(getString(R.string.about_us_content), Html.FROM_HTML_MODE_COMPACT));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}