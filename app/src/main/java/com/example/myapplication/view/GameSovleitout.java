package com.example.myapplication.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dialog.DialogGame;
import com.example.myapplication.model.SearchWordResponse;
import com.example.myapplication.model.UserData;
import com.example.myapplication.model.WordData;
import com.example.myapplication.viewModel.FrgMyListViewModel;
import com.example.myapplication.viewModel.GameViewModel;

import java.util.ArrayList;
import java.util.List;

public class GameSovleitout extends AppCompatActivity {
    List<ImageView> listHeart;
    ImageView imgCorrect, imgInCorrect, imgNext, heart1, heart2, heart3;
    TextView score, userName, numberQuestion;
    String keyWord;
    int count = 0;
    int countHeart = 3;
    int countScore = 0;
    Button buttonChar, btnSubmit, btnReturn, btnSkip;
    LinearLayout container;
    private String token_login, idPerson;
    SharedPreferences sharedPreferences;
    GameViewModel gameViewModel;
    ArrayList<WordData> list = new ArrayList<>();
    ArrayList<String> listWordSovle = new ArrayList<>();
   StringBuilder remainingLetters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        sharedPreferences = getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login",null);
        idPerson = sharedPreferences.getString("IdPerson", null);
        UserData userData = getIntent().getParcelableExtra("inforUser");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_sovleitout);
        userName = findViewById(R.id.userName);
        userName.setText(userData.getName());
        container = findViewById(R.id.container_layout);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnReturn = findViewById(R.id.btnReturn);
        btnSkip = findViewById(R.id.btnSkip);
        score = findViewById(R.id.score);
        heart1 = findViewById(R.id.heart1);
        heart2 = findViewById(R.id.heart2);
        heart3 = findViewById(R.id.heart3);
        imgCorrect = findViewById(R.id.imageCorrect);
        imgCorrect.setVisibility(View.INVISIBLE);
        imgInCorrect = findViewById(R.id.imageIncorrect);
        imgInCorrect.setVisibility(View.INVISIBLE);
        imgNext = findViewById(R.id.nextQuestion);
        imgNext.setVisibility(View.INVISIBLE);
        numberQuestion = findViewById(R.id.textView12);
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
                numberQuestion.setText("Question "+ (count+1));
                keyWord = list.get(count).getEn();
                String wordInserted = gameViewModel.wordInserted(list.get(count).getEn());
                listWordSovle.add(wordInserted);
                setWordToButton(wordInserted);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count<7){
                    count ++;
                    if(remainingLetters.toString().equals(keyWord) ){
                        gameViewModel.setAnimation(imgCorrect, getApplicationContext());
                        countScore = countScore + 15;
                        score.setText(""+countScore);
                        changeQuestion();
                    }
                    else {
                        countHeart --;
                        if(countHeart > -1 ){
                            gameViewModel.setAnimation(imgInCorrect, getApplicationContext());
                            if(countHeart == 0){
                                AlertDialog.Builder builder = new AlertDialog.Builder(GameSovleitout.this);
                                builder.setTitle("Warning");
                                builder.setMessage("Out of your hearts");

                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        dialog.dismiss();
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();

                            }
                            listHeart.get(countHeart).setVisibility(View.INVISIBLE);
                            changeQuestion();
                        }
                        else {
                            openDialog();
                        }

                    }
                }
                else {
                    if(remainingLetters.toString().equals(keyWord) ){
                        countScore = countScore + 15;
                        score.setText(""+countScore);
                    }
                    openDialog();
                }

            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = listWordSovle.get(count);
                container.removeAllViews();
                setWordToButton(word);
            }
        });
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count<=6){
                    gameViewModel.setAnimation(imgNext, getApplicationContext());
                    count ++;
                    changeQuestion();
                }
                else {
                    Toast.makeText(GameSovleitout.this, "Last Question", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void openDialog(){
        DialogGame dialogGame = new DialogGame(GameSovleitout.this, score.getText().toString(), new DialogGame.DialogCallback() {
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
    public void setWordToButton(String text){
        remainingLetters = new StringBuilder(text);
        int buttonWidth = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            buttonChar = new Button(GameSovleitout.this);
            buttonChar.setText(String.valueOf(letter));
//            buttonChar.setBackgroundColor(Color.parseColor("#FFFF00"));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    buttonWidth,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            buttonChar.setLayoutParams(layoutParams);
            buttonChar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button clickedButton = (Button) view;
                    String buttonText = clickedButton.getText().toString();
                    container.removeView(clickedButton);
                    remainingLetters.deleteCharAt(remainingLetters.indexOf(buttonText));
                }
            });
            container.addView(buttonChar);
        }
    }
    public void changeQuestion(){
        container.removeAllViews();
        numberQuestion.setText("Question "+ (count+1));
        keyWord = list.get(count).getEn();
        String wordInserted = gameViewModel.wordInserted(list.get(count).getEn());
        listWordSovle.add(wordInserted);
        setWordToButton(wordInserted);
    }
}