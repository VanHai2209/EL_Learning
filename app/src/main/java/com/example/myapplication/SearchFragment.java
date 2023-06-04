package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    ListView listView;
    AdapterSuggest adapterSuggest;
    ArrayList<Suggestion> suggestionArrayList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        listView = view.findViewById(R.id.suggestionList);
        for(int i=0; i<20; i++){
            suggestionArrayList.add(new Suggestion("hello","xin chào","/həˈlō/"));
        }
        adapterSuggest = new AdapterSuggest(getActivity(), suggestionArrayList);
        listView.setAdapter(adapterSuggest);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(), SearchActivity2.class));
            }
        });
        return view;
    }
}