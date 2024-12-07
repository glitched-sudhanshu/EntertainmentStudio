package com.example.entertainmentstudio.ui.help;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.entertainmentstudio.R;
import com.example.entertainmentstudio.databinding.FragmentHelpBinding;

public class HelpFragment extends Fragment {

    private FragmentHelpBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHelpBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.textHelp.setText(Html.fromHtml(getString(R.string.help_us_text), Html.FROM_HTML_MODE_COMPACT));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
