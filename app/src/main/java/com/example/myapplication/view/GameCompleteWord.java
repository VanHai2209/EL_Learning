package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class GameCompleteWord extends AppCompatActivity {
    EditText answer;
    List<ImageView> listHeart;
    ImageView imgCorrect, imgInCorrect, imgNext, heart1, heart2, heart3;
    TextView score, userName, txtQuestion, numberQuestion;
    int count = 0;
    GameViewModel gameViewModel;
    private String token_login, idPerson;
    SharedPreferences sharedPreferences;
    ArrayList<WordData> list = new ArrayList<>();
    Button btnSubmit, btnSkip;
    AppCompatButton btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_complete_word);
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        sharedPreferences = getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login",null);
        idPerson = sharedPreferences.getString("IdPerson", null);
        score = findViewById(R.id.score);
        heart1 = findViewById(R.id.heart1);
        heart2 = findViewById(R.id.heart2);
        heart3 = findViewById(R.id.heart3);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSkip = findViewById(R.id.btnSkip);
        btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameViewModel.dialogExit(GameCompleteWord.this);
            }
        });
        imgCorrect = findViewById(R.id.imageCorrect);
        imgCorrect.setVisibility(View.INVISIBLE);
        imgInCorrect = findViewById(R.id.imageIncorrect);
        imgInCorrect.setVisibility(View.INVISIBLE);
        imgNext = findViewById(R.id.nextQuestion);
        imgNext.setVisibility(View.INVISIBLE);
        userName = findViewById(R.id.userName);
        answer = findViewById(R.id.editText2);
        txtQuestion = findViewById(R.id.textView11);
        numberQuestion = findViewById(R.id.textView12);
        UserData userData = getIntent().getParcelableExtra("inforUser");
        userName.setText(userData.getName());
        listHeart = new ArrayList<>();
        listHeart.add(heart1);
        listHeart.add(heart2);
        listHeart.add(heart3);
        FrgMyListViewModel frgMyListViewModel = new ViewModelProvider(this).get(FrgMyListViewModel.class);
        frgMyListViewModel.listPersonWord(idPerson, token_login);
        frgMyListViewModel.getData().observe(this, new Observer<SearchWordResponse>() {
            @Override
            public void onChanged(SearchWordResponse searchWordResponse) {
                for(WordData wordData : gameViewModel.randomWord(searchWordResponse.getListWord(), 8)){
                    list.add(wordData);
                }
                txtQuestion.setText(gameViewModel.replaceWord(list.get(count).getEn()));
                numberQuestion.setText("Question "+(count+1));
            }
        });
        gameViewModel.getCountLive().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer != -1){
                    listHeart.get(integer).setVisibility(View.INVISIBLE);
                }
                gameViewModel.setAnimation(imgInCorrect, GameCompleteWord.this);
                checkLive();
                if(count <7){
                    changQuestion();
                }
                else {
                    openDialog();
                }
            }
        });
        gameViewModel.getScoreLive().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                gameViewModel.setAnimation(imgCorrect, GameCompleteWord.this);
                score.setText(integer.toString());
                checkLive();
                if(count <7){
                    changQuestion();
                }
                else {
                    openDialog();
                }
            }
        });
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count<7){
                    gameViewModel.setAnimation(imgNext, GameCompleteWord.this );
                    changQuestion();
                }
                else {
                    openDialog();
                }
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = answer.getText().toString();
                char keyChar;
                if(key.equals("")){
                    Toast.makeText(GameCompleteWord.this, "Please fill in the answer", Toast.LENGTH_SHORT).show();
                }
                else {
                    keyChar = key.charAt(0);
                    gameViewModel.onClickComplete(keyChar);
                }
            }
        });
    }
    public void changQuestion(){
            count ++;
            txtQuestion.setText(gameViewModel.replaceWord(list.get(count).getEn()));
            numberQuestion.setText("Question "+ (count+1));
            answer.setText("");
    }
    public void checkLive(){
        gameViewModel.getCheckLive().observe(GameCompleteWord.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    openDialog();
                }
            }
        });
    }
    public void openDialog(){
        DialogGame dialogGame = new DialogGame(GameCompleteWord.this, score.getText().toString(), new DialogGame.DialogCallback() {
            @Override
            public void onSaveClicked() {
                gameViewModel.updateScore(idPerson, score.getText().toString(), token_login);
                finish();
            }

            @Override
            public void onRePlayClicked() {
                Intent intent = getIntent();
                startActivity(intent);
                finish();
            }
        });
        dialogGame.setCanceledOnTouchOutside(false);
        dialogGame.show();
    }

    @Override
    public void onBackPressed() {

    }
}