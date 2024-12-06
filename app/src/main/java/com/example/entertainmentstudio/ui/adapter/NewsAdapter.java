package com.example.entertainmentstudio.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entertainmentstudio.databinding.NewsItemBoxLayoutBinding;
import com.example.entertainmentstudio.databinding.NewsItemLayoutBinding;
import com.example.entertainmentstudio.model.NewsItem;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_DEFAULT = 0;
    private static final int TYPE_ALTERNATE = 1;

    private List<NewsItem> newsItems;

    @SuppressLint("NotifyDataSetChanged")
    public void setNewsItems(List<NewsItem> newsItems) {
        this.newsItems = newsItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 5 == 0) {
            return TYPE_ALTERNATE;
        }
        return TYPE_DEFAULT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ALTERNATE) {
            NewsItemBoxLayoutBinding binding = NewsItemBoxLayoutBinding.inflate(
                    LayoutInflater.from(parent.getContext()), parent, false);
            return new AlternateNewsViewHolder(binding);
        } else {
            NewsItemLayoutBinding binding = NewsItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.getContext()), parent, false);
            return new DefaultNewsViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ALTERNATE) {
            AlternateNewsViewHolder alternateHolder = (AlternateNewsViewHolder) holder;
            NewsItem firstItem = newsItems.get(position);
            alternateHolder.binding.tvNewsTitleFirst.setText(firstItem.getTitle());

            // Second item (Check bounds to avoid IndexOutOfBoundsException)
            if (position + 1 < newsItems.size()) {
                NewsItem secondItem = newsItems.get(position + 1);
                alternateHolder.binding.tvNewsTitleSecond.setText(secondItem.getTitle());
                alternateHolder.binding.cvSecond.setVisibility(View.VISIBLE);
            } else {
                alternateHolder.binding.cvSecond.setVisibility(View.INVISIBLE);
            }
        } else {
            DefaultNewsViewHolder defaultHolder = (DefaultNewsViewHolder) holder;
            NewsItem item = newsItems.get(position);
            defaultHolder.binding.tvNewsTitle.setText(item.getTitle());
            defaultHolder.binding.tvNewsDesc.setText(item.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return newsItems == null ? 0 : newsItems.size();
    }

    static class DefaultNewsViewHolder extends RecyclerView.ViewHolder {
        private final NewsItemLayoutBinding binding;

        public DefaultNewsViewHolder(@NonNull NewsItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    static class AlternateNewsViewHolder extends RecyclerView.ViewHolder {
        private final NewsItemBoxLayoutBinding binding;

        public AlternateNewsViewHolder(@NonNull NewsItemBoxLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
