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

public class GameMiniTest extends AppCompatActivity {
    private GetInforResponse inforResponse;
    HomeViewModel homeViewModel;
    String email, token_login;
    SharedPreferences sharedPreferences;
    AppCompatButton btnStart, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_mini_test);
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
        btnStart = findViewById(R.id.btn_start_minitest);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameMiniTest.this, GameMiniTestStart.class);
                intent.putExtra("inforUser", inforResponse.getDataUser());
                startActivity(intent);
            }
        });
    }
}