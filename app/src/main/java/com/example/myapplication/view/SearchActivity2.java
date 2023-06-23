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
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.apdapter.AdapterPagerSearch;
import com.example.myapplication.model.WordData;
import com.example.myapplication.viewModel.WordViewModel;

public class SearchActivity2 extends AppCompatActivity {
    private boolean isFirstCheck = true ;
    AppCompatButton btnBack;
    CheckBox checkBoxWordPerson;
    String token_login, idPerson;
    SharedPreferences sharedPreferences;
    TextView txtWord;
    ViewPager2 viewPager2;
    RadioGroup radioGroup;
    WordViewModel wordViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        sharedPreferences = this.getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login",null);
        idPerson = sharedPreferences.getString("IdPerson", null);
        WordData wordData = getIntent().getParcelableExtra("wordData");
        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        wordViewModel.setContext(this);
        checkBoxWordPerson = findViewById(R.id.checkboxWord);
        btnBack = findViewById(R.id.btnBack);
        txtWord = findViewById(R.id.txtWord);
        txtWord.setText(wordData.getEn());
        viewPager2 = findViewById(R.id.viewPagerSearch);
        radioGroup = findViewById(R.id.radioGroup_search);
        radioGroup.check(R.id.radio_vi);
        wordViewModel.checkExistPersonWord(idPerson, wordData.getId(), token_login);
        wordViewModel.geData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s.equals("Word exist")){
                    checkBoxWordPerson.setChecked(true);
                }
                else {
                    isFirstCheck = false;
                }
            }
        });
        checkBoxWordPerson.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (isFirstCheck){
                    isFirstCheck = false;
                    return;
                }
                if(b){
                    wordViewModel.addWordPerson(idPerson, wordData.getId(), token_login);
                }
                else{
                    Toast.makeText(SearchActivity2.this, "Delete Person Word Successfully", Toast.LENGTH_SHORT).show();
//                    wordViewModel.deletePersonWord(idPerson, wordData.getId(), token_login);
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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