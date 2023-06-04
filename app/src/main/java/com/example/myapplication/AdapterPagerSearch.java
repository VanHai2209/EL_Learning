package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdapterPagerSearch extends RecyclerView.Adapter<MyViewHolder> {
    private final List<Integer> pages = Arrays.asList(
            R.layout.page_search_vi,
            R.layout.page_search_en,
            R.layout.page_search_pronoun,
            R.layout.page_search_sponsor
    );

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // bind data to ViewHolder
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return pages.get(position);
    }
}
class MyViewHolder extends RecyclerView.ViewHolder {
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
