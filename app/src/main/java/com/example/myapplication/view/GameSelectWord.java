package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.dialog.DialogGame;
import com.example.myapplication.model.SearchWordResponse;
import com.example.myapplication.model.SelectWordModel;
import com.example.myapplication.model.UserData;
import com.example.myapplication.model.WordData;
import com.example.myapplication.viewModel.FrgMyListViewModel;
import com.example.myapplication.viewModel.GameViewModel;

import java.util.ArrayList;
import java.util.List;

public class GameSelectWord extends AppCompatActivity {
    List<ImageView> listHeart;
    ImageView imageView, imageIncorrect, heart1, heart2, heart3;
    TextView score, userName;
    GameViewModel gameViewModel;
    private String token_login, idPerson;
    SharedPreferences sharedPreferences;
    ArrayList<SelectWordModel> list = new ArrayList<>();;
    ArrayList<SelectWordModel> randomList = new ArrayList<>();
    GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select_word);
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        sharedPreferences = getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login",null);
        idPerson = sharedPreferences.getString("IdPerson", null);
        gridLayout = findViewById(R.id.grid_layout);
        score = findViewById(R.id.score);
        heart1 = findViewById(R.id.heart1);
        heart2 = findViewById(R.id.heart2);
        heart3 = findViewById(R.id.heart3);
        userName = findViewById(R.id.userName);
        UserData userData = getIntent().getParcelableExtra("inforUser");
        userName.setText(userData.getName());
        listHeart = new ArrayList<>();
        listHeart.add(heart1);
        listHeart.add(heart2);
        listHeart.add(heart3);
        imageView = findViewById(R.id.imageAnim);
        imageView.setVisibility(View.INVISIBLE);
        imageIncorrect = findViewById(R.id.imageIncorrect);
        imageIncorrect.setVisibility(View.INVISIBLE);
        FrgMyListViewModel frgMyListViewModel = new ViewModelProvider(this).get(FrgMyListViewModel.class);
        frgMyListViewModel.listPersonWord(idPerson, token_login);
        frgMyListViewModel.getData().observe(this, new Observer<SearchWordResponse>() {
            @Override
            public void onChanged(SearchWordResponse searchWordResponse) {
                ArrayList<SelectWordModel> list1 = new ArrayList<>();

                for(WordData wordData : gameViewModel.randomWord(searchWordResponse.getListWord(), 8)){
                    list1.add(new SelectWordModel(wordData.getId(), wordData.getEn()));
                    list1.add(new SelectWordModel(wordData.getId(), wordData.getVn()));
                }
                list = list1;
                randomList = gameViewModel.randomList(list);
                for(int i =0; i < gridLayout.getChildCount(); i++){
                    String text = randomList.get(i).getText();
                    ((TextView) gridLayout.getChildAt(i)).setText(text);
                    gridLayout.getChildAt(i).setId(Integer.parseInt(randomList.get(i).getId()));
                    gridLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            gameViewModel.onClickItem(view);
                        }
                    });
                }
            }
        });
        gameViewModel.getCountLive().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer >= 0){
                    listHeart.get(integer).setVisibility(View.INVISIBLE);
                }
                gameViewModel.setAnimation(imageIncorrect, GameSelectWord.this);
                checkLive();
            }
        });
        gameViewModel.getScoreLive().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                gameViewModel.setAnimation(imageView, GameSelectWord.this);
                score.setText(integer.toString());
                checkLive();
            }
        });


    }
    public void checkLive(){
        gameViewModel.getCheckLive().observe(GameSelectWord.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    DialogGame dialogGame = new DialogGame(GameSelectWord.this, score.getText().toString(), new DialogGame.DialogCallback() {
                        @Override
                        public void onSaveClicked() {
                            gameViewModel.updateScore(idPerson, score.getText().toString(), token_login);
                            finish();
                        }

                        @Override
                        public void onRePlayClicked() {
                            startActivity(new Intent(GameSelectWord.this, GameSelectWord.class));
                            finish();
                        }
                    });
                    dialogGame.setCanceledOnTouchOutside(false);
                    dialogGame.show();
                }
            }
        });
    }

}