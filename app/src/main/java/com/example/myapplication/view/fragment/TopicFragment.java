package com.example.myapplication.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.view.TopicView;

public class TopicFragment extends Fragment {
    LinearLayout topicFamily, topicAnimal, topicOther;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topic, container, false);
        topicAnimal = view.findViewById(R.id.topic_animal);
        topicFamily = view.findViewById(R.id.topic_family);
        topicOther = view.findViewById(R.id.topic_other);
        topicAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });
        topicOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TopicView.class));
            }
        });
        return view;
    }
}