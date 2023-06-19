package com.example.myapplication.viewModel;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.R;
import com.example.myapplication.data.api.ApiServiceClient;
import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.ApiResponse;
import com.example.myapplication.model.SelectWordModel;
import com.example.myapplication.model.WordData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameViewModel extends ViewModel {
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private int keyPosition;
    private MutableLiveData<Boolean> checkLive = new MutableLiveData<>();
    public LiveData<Boolean> getCheckLive (){
        return checkLive;
    }
    private MutableLiveData<Integer> countLive = new MutableLiveData<>();
    public LiveData<Integer> getCountLive(){
        return countLive;
    }
    private MutableLiveData<Integer> scoreLive = new MutableLiveData<>();
    public LiveData<Integer> getScoreLive(){
        return scoreLive;
    }
    private int count =3;
    private int score = 0;
    private char key;
    private View preView;
    private static boolean isHold = false;
    private UserRepository userRepository;
    public GameViewModel(){
        userRepository = new UserRepository();
    }
    public void onClickComplete(char keyUser){
        if(count != -1 ){
            if(keyUser == key){
                score = score +15;
                scoreLive.setValue(score);
            }
            else {
                count --;
                countLive.setValue(count);
            }
            checkLive.setValue(checkEnd(score, count));
        }
        else {

        }
    }
    public void onClickItem(View view){
        if(count != -1){
            handleChangeColor(view);
            if(!isHold){
                this.preView = view;
                isHold = true;
            }
            else {
                if(preView != view && preView.getId() == view.getId()){
                    setDisableAndSuccess(view);
                    score = score +15;
                    scoreLive.setValue(score);
                }
                else if(preView == view) {
                    handleChangeColor(view);
                    handleChangeColor(preView);
                }
                else{
                    handleChangeColor(view);
                    handleChangeColor(preView);
                    count --;
                    countLive.setValue(count);
                }
                isHold = false;
            }
            checkLive.setValue(checkEnd(score, count));
        }
        else {

        }
    }
    public void setDisableAndSuccess(View view){
        String randColor = getRandomColor();
        preView.setOnClickListener(null);
        preView.setBackgroundColor(Color.parseColor(randColor));
        view.setOnClickListener(null);
        view.setBackgroundColor(Color.parseColor(randColor));
    }
    public void handleChangeColor(View view){
        if(!isSelected(view)){
            view.setBackgroundColor(Color.parseColor("#FFFF00"));
        }
        else  {
            view.setBackgroundColor(Color.parseColor("#FBF8F8"));
        }
    }
    public boolean isSelected(View view){
        Drawable background = view.getBackground();
        if(Color.parseColor("#FBF8F8") == ((ColorDrawable) background).getColor()){
            return false;
        }
        return true;
    }
    public String getRandomColor(){
        Random random = new Random();
        String stringColor = "#";
        char characters[] = { '1', '2','3', '4', '5', '6', '7', '8', '9','0', 'A','B','C','D', 'E', 'F'};
        for(int i=0; i<6; i++){
            stringColor += characters[random.nextInt(16)];
        }
        return stringColor;
    }
    public ArrayList<SelectWordModel> randomList(ArrayList<SelectWordModel> list ){
        Collections.shuffle(list);
        int numberOfElements = 16;
        ArrayList<SelectWordModel> randomList = new ArrayList<>(list.subList(0, numberOfElements));
        return randomList;
    }
    public List<WordData> randomWord(List<WordData> list, int numberOfElements){
        Collections.shuffle(list);
        List<WordData> radomWord = list.subList(0, numberOfElements);
        return radomWord;
    }
    public boolean checkEnd(int score, int heart ){
        if(score == 120 || heart == -1){
            return true;
        }
        return false;
    }
    public void updateScore(String idPerson, String score, String token_login ){
        ApiServiceClient.setToken(token_login);
        userRepository.updateScore(idPerson, score, new UserRepository.IApiResponse() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {

            }

            @Override
            public void onFail(ApiResponse apiResponse) {

            }
        });
    }
    public void setAnimation(ImageView imageView, Context context){
        Animation fadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        imageView.startAnimation(fadeInAnimation);
        imageView.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fadeOutAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_out);
                imageView.startAnimation(fadeOutAnimation);
                imageView.setVisibility(View.GONE);
            }
        }, 800);
    }
    public String wordInserted(String word){
        char randomChar = getRandomChar(alphabet);
        key = randomChar;

        return insertChar(word, randomChar);
    }
    public String insertChar(String word, char randomChar){
        Random random = new Random();
        int position = random.nextInt(word.length() + 1);
        keyPosition = position;
        StringBuilder stringBuilder = new StringBuilder(word);
        stringBuilder.insert(position, randomChar);
        return stringBuilder.toString();
    }
    public String replaceWord(String word){
        char randomChar = getRandomChar(word);
        key = randomChar;
        String replacedWord = replaceChar(word, randomChar);
        return  replacedWord;
    }
    public char getRandomChar(String word) {
        Random random = new Random();
        int randomIndex = random.nextInt(word.length());
        return word.charAt(randomIndex);
    }

    public  String replaceChar(String word, char ch) {
        StringBuilder replacedWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (currentChar == ch) {
                replacedWord.append('_');
            } else {
                replacedWord.append(currentChar);
            }
        }
        return replacedWord.toString();
    }
}
