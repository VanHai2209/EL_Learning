package com.example.myapplication.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.R;

public class TopicFragment extends Fragment {
    LinearLayout topicFamily, topicAnimal;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topic, container, false);
        topicAnimal = view.findViewById(R.id.topic_animal);
        topicFamily = view.findViewById(R.id.topic_family);
        topicAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });
        return inflater.inflate(R.layout.fragment_topic, container, false);
    }
}