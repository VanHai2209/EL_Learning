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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.dialog.DialogGame;
import com.example.myapplication.dialog.DialogTest;
import com.example.myapplication.model.Grammar;
import com.example.myapplication.model.TestModel;
import com.example.myapplication.model.TestResponse;
import com.example.myapplication.model.UserData;
import com.example.myapplication.view.fragment.QuestionTestFragment;
import com.example.myapplication.viewModel.FrgGrammarViewModel;
import com.example.myapplication.viewModel.GameViewModel;
import com.example.myapplication.viewModel.TestViewModel;

import java.util.ArrayList;
import java.util.List;

public class GameMiniTestStart extends AppCompatActivity implements QuestionTestFragment.OnAnswerSelectedListener {

    ImageView imgCorrect, imgInCorrect;
    GameViewModel gameViewModel;
    AppCompatButton btnClose;
    TextView txtScore, txtUserName;
    Button btnCheck;
    private static final long DELAY_TIME = 500; // Thời gian trễ (2 giây)
    String token_login, idPerson;
    SharedPreferences sharedPreferences;
    FrgGrammarViewModel frgGrammarViewModel;
    TestViewModel testViewModel;
    int score = 0 ;
    boolean checkAnswer, isSelected;
    int countQuestion = 0 ;

    List<TestModel> listTest = new ArrayList<>();
    List<TestModel> randomListTest = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        sharedPreferences = this.getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login",null);
        idPerson = sharedPreferences.getString("IdPerson", null);
        btnCheck = findViewById(R.id.btnCheck);
        btnClose = findViewById(R.id.btnClose);
        txtScore = findViewById(R.id.score);
        txtUserName = findViewById(R.id.userName);
        UserData userData = getIntent().getParcelableExtra("inforUser");
        txtUserName.setText(userData.getName());
        imgCorrect = findViewById(R.id.imageCorrect);
        imgCorrect.setVisibility(View.INVISIBLE);
        imgInCorrect = findViewById(R.id.imageIncorrect);
        imgInCorrect.setVisibility(View.INVISIBLE);
        txtScore.setText(""+score);
        gameViewModel = new GameViewModel();
        testViewModel = new ViewModelProvider(this).get(TestViewModel.class);
        frgGrammarViewModel = new ViewModelProvider(this).get(FrgGrammarViewModel.class);
        frgGrammarViewModel.getGrammar(token_login);
        frgGrammarViewModel.geData().observe(this, new Observer<List<Grammar>>() {
            @Override
            public void onChanged(List<Grammar> grammars) {
                for (Grammar grammar : grammars){
                    testViewModel.getTest(grammar.getName(), token_login);
                }

            }
        });
        testViewModel.getData().observe(GameMiniTestStart.this, new Observer<TestResponse>() {
            @Override
            public void onChanged(TestResponse testResponse) {
                listTest.addAll(testResponse.getListTest());
                randomListTest = testViewModel.randomList(listTest);

            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameViewModel.dialogExit(GameMiniTestStart.this);
            }
        });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Tạo instance của fragment
                QuestionTestFragment questionTestFragment = QuestionTestFragment.newInstance(randomListTest.get(countQuestion));

                // Thêm fragment vào container
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                fragmentTransaction.replace(R.id.question_container, questionTestFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                btnCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countQuestion ++;
                        if(checkAnswer){
                            score = score + 15;
                            txtScore.setText(""+score);
                            gameViewModel.setAnimation(imgCorrect, getApplicationContext());
                            checkAnswer = false;
                            changeQuestion();
                        }
                        else {
                            gameViewModel.setAnimation(imgInCorrect, getApplicationContext());
                            openDialogTest(randomListTest.get((countQuestion-1)).getKeyCorrect());
                        }

                    }
                });

            }
        }, DELAY_TIME);

    }

    @Override
    public void onAnswerSelected(boolean isCorrect, boolean isSelected) {
        checkAnswer = isCorrect;
        this.isSelected = isSelected;
    }

    @Override
    public void onBackPressed() {

    }
    public void openDialog(){
        DialogGame dialogGame = new DialogGame(GameMiniTestStart.this, txtScore.getText().toString(), new DialogGame.DialogCallback() {
            @Override
            public void onSaveClicked() {
                gameViewModel.updateScore(idPerson, txtScore.getText().toString(), token_login);
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
    public void openDialogTest(String answer){
        DialogTest dialogTest = new DialogTest(GameMiniTestStart.this, answer, new DialogTest.DialogCallback() {
            @Override
            public void onClicked() {
                changeQuestion();
            }
        });
        dialogTest.setCanceledOnTouchOutside(false);
        dialogTest.show();
    }
    public void changeQuestion(){
        if(countQuestion <= 14){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // Tạo instance của fragment
            QuestionTestFragment questionTestFragment = QuestionTestFragment.newInstance(randomListTest.get(countQuestion));

            // Thêm fragment vào container
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
            fragmentTransaction.replace(R.id.question_container, questionTestFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else {
            openDialog();
        }
    }
}