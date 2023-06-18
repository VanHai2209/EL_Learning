package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.apdapter.AdapterSuggest;
import com.example.myapplication.model.SearchWordResponse;
import com.example.myapplication.model.WordData;
import com.example.myapplication.viewModel.TopicViewModel;

import java.util.ArrayList;

public class TopicWordView extends AppCompatActivity {
    AppCompatButton btnBack;
    TextView txtNameTopic;
    TopicViewModel topicViewModel;
    AutoCompleteTextView searchView;
    ListView listView;
    AdapterSuggest adapterSuggest;
    String token_login, topic;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_word_view);
        sharedPreferences = this.getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login",null);
        topic = getIntent().getStringExtra("topic");
        txtNameTopic = findViewById(R.id.txtNameTopic);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.suggestionList);
        txtNameTopic.setText(getIntent().getStringExtra("topic"));
        topicViewModel = new ViewModelProvider(this).get(TopicViewModel.class);
        topicViewModel.searchWordTopic(topic, token_login);
        topicViewModel.getDataWord().observe(this, new Observer<SearchWordResponse>() {
            @Override
            public void onChanged(SearchWordResponse searchWordResponse) {
                ArrayList<WordData> suggestionArrayList = new ArrayList<>();
                for(WordData wordData : searchWordResponse.getListWord()){
                    suggestionArrayList.add(new WordData(wordData.getId(), wordData.getEn(), wordData.getVn(), wordData.getType(), wordData.getIPA(), wordData.getExample(), wordData.getImage(), wordData.getAudio(), wordData.getIdTopic()));
                }
                adapterSuggest = new AdapterSuggest(TopicWordView.this, suggestionArrayList);
                listView.setAdapter(adapterSuggest);
                adapterSuggest.getFilter().filter("");
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                WordData wordData = (WordData) listView.getItemAtPosition(i);
                Intent intent = new Intent(TopicWordView.this, SearchActivity2.class);
                intent.putExtra("wordData", wordData);
                startActivity(intent);
            }
        });
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Không cần thực hiện hành động trước khi văn bản thay đổi
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Thực hiện hành động khi văn bản thay đổi
                adapterSuggest.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Không cần thực hiện hành động sau khi văn bản thay đổi
            }
        });
    }
}