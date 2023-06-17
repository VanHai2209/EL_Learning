package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.dialog.DialogGame;
import com.example.myapplication.model.SearchWordResponse;
import com.example.myapplication.model.SelectWordModel;
import com.example.myapplication.model.WordData;
import com.example.myapplication.viewModel.FrgMyListViewModel;
import com.example.myapplication.viewModel.SelectWordViewModel;

import java.util.ArrayList;

public class GameSelectWord extends AppCompatActivity {
    TextView score, heart;
    SelectWordViewModel selectWordViewModel;
    private String token_login, idPerson;
    SharedPreferences sharedPreferences;
    ArrayList<SelectWordModel> list = new ArrayList<>();;
    ArrayList<SelectWordModel> randomList = new ArrayList<>();
    GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select_word);
        selectWordViewModel = new ViewModelProvider(this).get(SelectWordViewModel.class);
        sharedPreferences = getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login",null);
        idPerson = sharedPreferences.getString("IdPerson", null);
        gridLayout = findViewById(R.id.grid_layout);
        score = findViewById(R.id.score);
        heart = findViewById(R.id.heart);
        FrgMyListViewModel frgMyListViewModel = new ViewModelProvider(this).get(FrgMyListViewModel.class);
        frgMyListViewModel.listPersonWord(idPerson, token_login);
        frgMyListViewModel.getData().observe(this, new Observer<SearchWordResponse>() {
            @Override
            public void onChanged(SearchWordResponse searchWordResponse) {
                ArrayList<SelectWordModel> list1 = new ArrayList<>();

                for(WordData wordData : selectWordViewModel.randomWord(searchWordResponse.getListWord())){
                    list1.add(new SelectWordModel(wordData.getId(), wordData.getEn()));
                    list1.add(new SelectWordModel(wordData.getId(), wordData.getVn()));
                }
                list = list1;
                randomList = selectWordViewModel.randomList(list);
                for(int i =0; i < gridLayout.getChildCount(); i++){
                    String text = randomList.get(i).getText();
                    ((TextView) gridLayout.getChildAt(i)).setText(text);
                    gridLayout.getChildAt(i).setId(Integer.parseInt(randomList.get(i).getId()));
                    gridLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            selectWordViewModel.onClickItem(view);
                        }
                    });
                }
            }
        });
        selectWordViewModel.getCountLive().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                heart.setText(integer.toString());
            }
        });
        selectWordViewModel.getScoreLive().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                score.setText(integer.toString());
            }
        });
        if(selectWordViewModel.checkEnd(score.getText().toString(), heart.getText().toString())){
            DialogGame dialogGame = new DialogGame(this, score.getText().toString(), new DialogGame.DialogCallback() {
                @Override
                public void onSaveClicked() {

                }

                @Override
                public void onRePlayClicked() {

                }
            });
        }

    }

}