package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.myapplication.R;
import com.example.myapplication.apdapter.AdapterSuggest;
import com.example.myapplication.dialog.DialogCorrect;
import com.example.myapplication.dialog.DialogGame;
import com.example.myapplication.dialog.DialogTest;
import com.example.myapplication.model.SearchWordResponse;
import com.example.myapplication.model.WordData;
import com.example.myapplication.view.fragment.ListeningFragment;
import com.example.myapplication.view.fragment.QuestionTestFragment;
import com.example.myapplication.view.fragment.TextAnswerListener;
import com.example.myapplication.viewModel.FrgMyListViewModel;
import com.example.myapplication.viewModel.GameViewModel;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Listening extends AppCompatActivity implements TextAnswerListener {
    ListeningFragment listeningFragment;
    private static final long DELAY_TIME = 500; // Thời gian trễ (2 giây)
    Button btnCheck;
    AppCompatButton btnClose;
    TextView txtQuestion;
    int countQuestion = 0;
    GameViewModel gameViewModel;
    private String token_login, idPerson;
    SharedPreferences sharedPreferences;
    ArrayList<WordData> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening);
        btnCheck = findViewById(R.id.btnCheck);
        btnClose = findViewById(R.id.btnClose);
        txtQuestion = findViewById(R.id.textQuestion);
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        sharedPreferences = getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login",null);
        idPerson = sharedPreferences.getString("IdPerson", null);
        FrgMyListViewModel frgMyListViewModel;
        frgMyListViewModel = new ViewModelProvider(this).get(FrgMyListViewModel.class);
        frgMyListViewModel.listPersonWord(idPerson, token_login);
        frgMyListViewModel.getData().observe(this, new Observer<SearchWordResponse>() {
            @Override
            public void onChanged(SearchWordResponse searchWordResponse) {
                for(WordData wordData : searchWordResponse.getListWord()){
                    list.add(new WordData(wordData.getId(), wordData.getEn(), wordData.getVn(), wordData.getType(), wordData.getIPA(), wordData.getExample(), wordData.getImage(), wordData.getAudio(), wordData.getIdTopic()));
                }

            }
        });
        Collections.shuffle(list, new Random());
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeQuestion();
                btnCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countQuestion ++;
                        listeningFragment.checkAnswer();

                    }
                });
            }
        }, DELAY_TIME);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameViewModel gameViewModel = new GameViewModel();
                gameViewModel.dialogExit(Listening.this);
            }
        });
    }

    public void changeQuestion(){
        if(countQuestion <= 9){
            txtQuestion.setText("Question "+ (countQuestion+1));
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            listeningFragment = ListeningFragment.newInstance(list.get(countQuestion), token_login);
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
            fragmentTransaction.replace(R.id.question_container, listeningFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else{
            openDialog();
        }
    }
    public void openDialog(){
        DialogGame dialogGame = new DialogGame(Listening.this, new DialogGame.DialogCallback() {
            @Override
            public void onSaveClicked() {
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
    public void openDialogCorrect(){
        DialogCorrect dialogCorrect = new DialogCorrect(Listening.this, new DialogCorrect.DialogCallback() {
            @Override
            public void onClicked() {
                changeQuestion();
            }
        });
        dialogCorrect.setCanceledOnTouchOutside(false);
        dialogCorrect.show();
    }
    public void openDialogAnswer(String answer){
        DialogTest dialogTest = new DialogTest(Listening.this, answer, new DialogTest.DialogCallback() {
            @Override
            public void onClicked() {
                changeQuestion();
            }
        });
        dialogTest.setCanceledOnTouchOutside(false);
        dialogTest.show();
    }
    @Override
    public void isTrue() {
        openDialogCorrect();
    }

    @Override
    public void isFalse() {
        openDialogAnswer(list.get(countQuestion-1).getEn());
    }

    @Override
    public void noData() {

    }

    @Override
    public void onBackPressed() {

    }
}