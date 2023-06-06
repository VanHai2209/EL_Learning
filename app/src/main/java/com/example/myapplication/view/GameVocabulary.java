package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class GameVocabulary extends AppCompatActivity {
    AppCompatButton btnCompleteWord, btnArrangeWord, btnSelectWord, btnSovleWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_vocabulary);
        btnCompleteWord = findViewById(R.id.btn_complete_word);
        btnCompleteWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameVocabulary.this, GameCompleteWord.class));
            }
        });
        btnArrangeWord = findViewById(R.id.btn_arrange_word);
        btnArrangeWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameVocabulary.this, GameArrangeWord.class));
            }
        });
        btnSelectWord = findViewById(R.id.btn_select_word);
        btnSelectWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameVocabulary.this, GameSelectWord.class));
            }
        });
        btnSovleWord = findViewById(R.id.btn_sovle_word);
        btnSovleWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameVocabulary.this, GameSovleitout.class));
            }
        });
    }
}