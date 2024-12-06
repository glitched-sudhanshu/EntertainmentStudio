package com.example.entertainmentstudio.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entertainmentstudio.databinding.NewsItemLayoutBinding;
import com.example.entertainmentstudio.model.NewsItem;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<NewsItem> newsItems;

    @SuppressLint("NotifyDataSetChanged")
    public void setNewsItems(List<NewsItem> newsItems) {
        this.newsItems = newsItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsItemLayoutBinding binding = NewsItemLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsItem item = newsItems.get(position);
        holder.binding.tvNewsTitle.setText(item.getTitle());
        holder.binding.tvNewsDesc.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return newsItems == null ? 0 : newsItems.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        private final NewsItemLayoutBinding binding;

        public NewsViewHolder(@NonNull NewsItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

