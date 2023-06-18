package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.apdapter.AdapterPagerSearch;
import com.example.myapplication.model.WordData;
import com.example.myapplication.viewModel.AddWordViewModel;
import com.example.myapplication.viewModel.FrgSearchViewModel;
import com.example.myapplication.viewModel.GrammarViewModel;

public class SearchActivity2 extends AppCompatActivity {
    AppCompatButton btnAddWord, btnBack;
    String token_login, idPerson;
    SharedPreferences sharedPreferences;
    TextView txtWord;
    ViewPager2 viewPager2;
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        sharedPreferences = this.getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login",null);
        idPerson = sharedPreferences.getString("IdPerson", null);
        WordData wordData = getIntent().getParcelableExtra("wordData");
        btnAddWord = findViewById(R.id.btnAddWord);
        btnBack = findViewById(R.id.btnBack);
        txtWord = findViewById(R.id.txtWord);
        txtWord.setText(wordData.getEn());
        viewPager2 = findViewById(R.id.viewPagerSearch);
        radioGroup = findViewById(R.id.radioGroup_search);
        radioGroup.check(R.id.radio_vi);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddWordViewModel addWordViewModel = new AddWordViewModel(SearchActivity2.this);
                addWordViewModel.addWordPerson(idPerson, wordData.getId());
            }
        });
        viewPager2.setAdapter(new AdapterPagerSearch(wordData, token_login, (ViewModelStoreOwner) this,(LifecycleOwner) this));
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId == R.id.radio_vi) {
                viewPager2.setCurrentItem(0);
            }
            else if(checkedId == R.id.radio_en){
                    viewPager2.setCurrentItem(1);
            }
            else if(checkedId == R.id.radio_pronoun) {
                viewPager2.setCurrentItem(2);
            }
            else{
                viewPager2.setCurrentItem(3);
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        radioGroup.check(R.id.radio_vi);
                        break;
                    case 1:
                        radioGroup.check(R.id.radio_en);
                        break;
                    case 2:
                        radioGroup.check(R.id.radio_pronoun);
                        break;
                    case 3:
                        radioGroup.check(R.id.radio_sponsor);
                        break;
                }
            }
        });
    }
}