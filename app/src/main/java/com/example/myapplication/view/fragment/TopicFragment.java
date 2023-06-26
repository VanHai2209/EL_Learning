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
import com.example.myapplication.view.TopicWordView;

public class TopicFragment extends Fragment {
    LinearLayout topicFamily, topicAnimal, topicTraveling, topicFood, topicShopping, topicOther;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topic, container, false);
        topicAnimal = view.findViewById(R.id.topic_animal);
        topicFood = view.findViewById(R.id.topic_food);
        topicTraveling = view.findViewById(R.id.topic_traveling);
        topicShopping = view.findViewById(R.id.topic_shopping);
        topicFamily = view.findViewById(R.id.topic_family);
        topicOther = view.findViewById(R.id.topic_other);
        topicAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topicName = "Animal";
                startActivity(new Intent(getActivity(), TopicWordView.class).putExtra("topic", topicName));
            }

        });
        topicFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topicName = "Family";
                startActivity(new Intent(getActivity(), TopicWordView.class).putExtra("topic", topicName));
            }
        });
        topicShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topicName = "Shopping";
                startActivity(new Intent(getActivity(), TopicWordView.class).putExtra("topic", topicName));
            }
        });
        topicFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topicName = "Food";
                startActivity(new Intent(getActivity(), TopicWordView.class).putExtra("topic", topicName));
            }
        });
        topicTraveling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topicName = "Travel";
                startActivity(new Intent(getActivity(), TopicWordView.class).putExtra("topic", topicName));
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