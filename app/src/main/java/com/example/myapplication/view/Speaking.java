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
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.dialog.DialogCorrect;
import com.example.myapplication.dialog.DialogGame;
import com.example.myapplication.dialog.DialogTest;
import com.example.myapplication.model.SearchWordResponse;
import com.example.myapplication.model.WordData;
import com.example.myapplication.view.fragment.SpeakingFragment;
import com.example.myapplication.view.fragment.TextAnswerListener;
import com.example.myapplication.viewModel.FrgMyListViewModel;
import com.example.myapplication.viewModel.GameViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Speaking extends AppCompatActivity implements TextAnswerListener {
    SpeakingFragment speakingFragment;
    private static final long DELAY_TIME = 500;
    AppCompatButton btnClose;
    TextView txtQuestion;
    int countQuestion = 0;
    GameViewModel gameViewModel;
    private String token_login, idPerson;
    SharedPreferences sharedPreferences;
    List<WordData> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaking);
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
                Collections.shuffle(list);
            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeQuestion();
            }
        }, DELAY_TIME);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameViewModel gameViewModel = new GameViewModel();
                gameViewModel.dialogExit(Speaking.this);
            }
        });
    }
    public void changeQuestion(){
        if(countQuestion <= 9){
            txtQuestion.setText("Question "+ (countQuestion+1));
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            speakingFragment = SpeakingFragment.newInstance(list.get(countQuestion), token_login);
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
            fragmentTransaction.replace(R.id.question_container, speakingFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            countQuestion ++ ;
        }
        else {
            openDialog();
        }
    }
    public void openDialog(){
        DialogGame dialogGame = new DialogGame(Speaking.this, new DialogGame.DialogCallback() {
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
        DialogCorrect dialogCorrect = new DialogCorrect(Speaking.this, new DialogCorrect.DialogCallback() {
            @Override
            public void onClicked() {
                changeQuestion();
            }
        });
        dialogCorrect.setCanceledOnTouchOutside(false);
        dialogCorrect.show();
    }
    public void openDialogAnswer(String ipa){
        DialogTest dialogTest = new DialogTest(Speaking.this, ipa, new DialogTest.DialogCallback() {
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
        openDialogAnswer(list.get(countQuestion-1).getIPA());
    }

    @Override
    public void noData() {

    }

    @Override
    public void onBackPressed() {

    }
}