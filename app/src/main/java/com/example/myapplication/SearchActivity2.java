package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class SearchActivity2 extends AppCompatActivity {
    ViewPager2 viewPager2;
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        viewPager2 = findViewById(R.id.viewPagerSearch);
        radioGroup = findViewById(R.id.radioGroup_search);
        radioGroup.check(R.id.radio_vi);
        viewPager2.setAdapter(new AdapterPagerSearch());
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