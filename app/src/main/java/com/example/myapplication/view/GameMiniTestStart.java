package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Grammar;
import com.example.myapplication.model.TestModel;
import com.example.myapplication.model.TestResponse;
import com.example.myapplication.view.fragment.QuestionTestFragment;
import com.example.myapplication.viewModel.FrgGrammarViewModel;
import com.example.myapplication.viewModel.TestViewModel;

import java.util.ArrayList;
import java.util.List;

public class GameMiniTestStart extends AppCompatActivity {
    Button btnCheck;
    private static final long DELAY_TIME = 500; // Thời gian trễ (2 giây)
    String token_login;
    SharedPreferences sharedPreferences;
    FrgGrammarViewModel frgGrammarViewModel;
    TestViewModel testViewModel;
    int score = 0 ;

    List<TestModel> listTest = new ArrayList<>();
    List<TestModel> randomListTest = new ArrayList<>();
    List<String> answer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        sharedPreferences = this.getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login",null);
        btnCheck = findViewById(R.id.btnCheck);
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
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Tạo instance của fragment
                QuestionTestFragment questionTestFragment = new QuestionTestFragment();

                // Thêm fragment vào container
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.question_container, questionTestFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                btnCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        // Tạo instance của fragment
                        QuestionTestFragment questionTestFragment = new QuestionTestFragment();

                        // Thêm fragment vào container
                        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                        fragmentTransaction.replace(R.id.question_container, questionTestFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });
//                for (int j = 0; j < 60; j++) {
//                    answer.add("");
//                    LinearLayout questionLayout = new LinearLayout(GameMiniTestStart.this);
//                    questionLayout.setOrientation(LinearLayout.VERTICAL);
//
//                    TextView txtQuestion = createTextView("Question " + (j + 1) + ": " + randomListTest.get(j).getName());
//                    questionLayout.addView(txtQuestion);
//
//                    RadioGroup radioGroup = createRadioGroup(randomListTest.get(j));
//                    radioGroup.setId(j);
//
//                    questionLayout.addView(radioGroup);
//
//                    container.addView(questionLayout);
//                    onChangeRadio(radioGroup);
//                }

            }
        }, DELAY_TIME);

    }
    private void onChangeRadio(RadioGroup radioGroup){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedRadioButton = radioGroup.findViewById(i);
                if(selectedRadioButton!=null){
                    answer.set(radioGroup.getId(), selectedRadioButton.getText().toString());
                }
            }
        });
    }
    private TextView createTextView(String text) {
        TextView textView = new TextView(GameMiniTestStart.this);
        textView.setText(text);
        return textView;
    }
    private RadioButton createRadioButton(String text) {
        RadioButton radioButton = new RadioButton(GameMiniTestStart.this);
        radioButton.setText(text);
        return radioButton;
    }
    private RadioGroup createRadioGroup(TestModel testItem) {
        RadioGroup radioGroup = new RadioGroup(GameMiniTestStart.this);

        RadioButton keyA = createRadioButton(testItem.getKeyA());
        radioGroup.addView(keyA);

        RadioButton keyB = createRadioButton(testItem.getKeyB());
        radioGroup.addView(keyB);

        RadioButton keyC = createRadioButton(testItem.getKeyC());
        radioGroup.addView(keyC);

        RadioButton keyD = createRadioButton(testItem.getKeyD());
        radioGroup.addView(keyD);

        return radioGroup;
    }

}