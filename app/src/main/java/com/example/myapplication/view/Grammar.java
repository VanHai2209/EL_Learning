package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.viewModel.GrammarViewModel;

public class Grammar extends AppCompatActivity {
    ImageView imageGrammar, btnBack;
    TextView txtGrammar;
    String token_login;
    SharedPreferences sharedPreferences;
    GrammarViewModel grammarViewModel;
    String grammar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);
        imageGrammar = findViewById(R.id.imageGrammar);
        btnBack = findViewById(R.id.btnBack);
        txtGrammar = findViewById(R.id.txtGrammar);
        sharedPreferences = this.getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login",null);
        grammar = getIntent().getStringExtra("grammar");
        txtGrammar.setText(grammar);
        grammarViewModel = new ViewModelProvider(this).get(GrammarViewModel.class);
        grammarViewModel.getImage(grammar+".jpg",token_login);
        grammarViewModel.getImageGrammar().observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                imageGrammar.setImageBitmap(bitmap);
            }
        });
    }
}