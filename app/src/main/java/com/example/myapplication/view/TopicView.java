package com.example.myapplication.view;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.model.Topic;
import com.example.myapplication.model.TopicResponse;
import com.example.myapplication.viewModel.TopicViewModel;

import java.util.ArrayList;
import java.util.List;

public class TopicView extends AppCompatActivity {
    String token_login;
    SharedPreferences sharedPreferences;
    TopicViewModel topicViewModel;
    ListView listTopic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_view);
        listTopic = findViewById(R.id.listTopic);
        sharedPreferences = this.getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login",null);

        topicViewModel = new TopicViewModel();
        topicViewModel = new ViewModelProvider(this).get(TopicViewModel.class);
        topicViewModel.listTopic(token_login);
        topicViewModel.getData().observe(this, new Observer<TopicResponse>() {
            @Override
            public void onChanged(TopicResponse topicResponse) {
                List<String> topicNames = new ArrayList<>();
                for(Topic topic : topicResponse.getList()){
                    topicNames.add(topic.getTopicName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(TopicView.this, R.layout.item_topic, R.id.nameTopic, topicNames);
                listTopic.setAdapter(adapter);
            }
        });
        listTopic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String topicName = (String) listTopic.getItemAtPosition(i);
                startActivity(new Intent(TopicView.this, TopicWordView.class).putExtra("topic", topicName));
            }
        });
    }
}