package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.model.GetInforResponse;
import com.example.myapplication.viewModel.HomeViewModel;

public class GameVocabulary extends AppCompatActivity {
    private GetInforResponse inforResponse;
    HomeViewModel homeViewModel;
    String email, token_login;
    SharedPreferences sharedPreferences;
    AppCompatButton btnCompleteWord, btnArrangeWord, btnSelectWord, btnSovleWord, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_vocabulary);
        inforResponse = new GetInforResponse();
        sharedPreferences = this.getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login",null);
        email = sharedPreferences.getString("Email", null);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getInforUser(email, token_login);
        homeViewModel.getData().observe(this, new Observer<GetInforResponse>() {
            @Override
            public void onChanged(GetInforResponse getInforResponse) {
                inforResponse = getInforResponse;
            }
        });
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnCompleteWord = findViewById(R.id.btn_complete_word);
        btnCompleteWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameVocabulary.this, GameCompleteWord.class);
                putInfor(intent);
                startActivity(intent);
            }
        });
        btnArrangeWord = findViewById(R.id.btn_arrange_word);
        btnArrangeWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameVocabulary.this, GameArrangeWord.class);
                putInfor(intent);
                startActivity(intent);
            }
        });
        btnSelectWord = findViewById(R.id.btn_select_word);
        btnSelectWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(GameVocabulary.this, GameSelectWord.class);
                putInfor(intent);
                startActivity(intent);
            }
        });
        btnSovleWord = findViewById(R.id.btn_sovle_word);
        btnSovleWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameVocabulary.this, GameSovleitout.class);
                putInfor(intent);
                startActivity(intent);
            }
        });
    }
    public void putInfor(Intent intent){
        intent.putExtra("inforUser", inforResponse.getDataUser());
    }
}