package com.example.myapplication.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class GameArrangeWord extends AppCompatActivity {
    EditText answer;
    List<ImageView> listHeart;
    ImageView imgCorrect, imgInCorrect, imgNext, heart1, heart2, heart3;
    TextView score, userName, txtQuestion, numberQuestion;
    Button btnSubmit, btnSkip;
    AppCompatButton btnClose;
    int count = 0;
    int countScore = 0;
    int countHeart = 3;
    String keyWord;
    GameViewModel gameViewModel;
    private String token_login, idPerson;
    SharedPreferences sharedPreferences;
    ArrayList<WordData> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_arrange_word);
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
                gameViewModel.dialogExit(GameArrangeWord.this);
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
                changeQuestion();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(count<7){
                    count ++;
                    if(answer.getText().toString().equals(keyWord) ){
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
                                AlertDialog.Builder builder = new AlertDialog.Builder(GameArrangeWord.this);
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
                    if(answer.getText().toString().equals(keyWord) ){
                        countScore = countScore + 15;
                        score.setText(""+countScore);
                    }
                    openDialog();
                }
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
                    Toast.makeText(GameArrangeWord.this, "Last Question", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void changeQuestion(){
        answer.setText("");
        numberQuestion.setText("Question "+(count+1));
        keyWord = list.get(count).getEn();
        String question = gameViewModel.insertRandomWord(list.get(count).getEn());
        txtQuestion.setText(question);
    }
    public void openDialog(){
        DialogGame dialogGame = new DialogGame(GameArrangeWord.this, score.getText().toString(), new DialogGame.DialogCallback() {
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