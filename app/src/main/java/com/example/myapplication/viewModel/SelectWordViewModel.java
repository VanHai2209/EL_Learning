package com.example.myapplication.viewModel;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.SelectWordModel;
import com.example.myapplication.model.WordData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SelectWordViewModel extends ViewModel {
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
    private View preView;
    private static boolean isHold = false;
    public SelectWordViewModel(){
    }
    public void onClickItem(View view){
        if(count!=0){
            handleChangeColor(view);
            if(!isHold){
                this.preView = view;
                isHold = true;
            }
            else {
                if(preView != view && preView.getId() == view.getId()){
                    setDisableAndSuccess(view);
                    score = score +15;

                }
                else if(preView == view) {
                    handleChangeColor(view);
                    handleChangeColor(preView);
                }
                else{
                    handleChangeColor(view);
                    handleChangeColor(preView);
                    count --;
                }
                isHold = false;
            }
            Log.d("vanhai", "onClickItem: "+score);
            Log.d("vanhai", "onClickItem: "+count);
            scoreLive.setValue(score);
            countLive.setValue(count);
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
            view.setBackgroundColor(Color.parseColor("#D5C1C1"));
        }
    }
    public boolean isSelected(View view){
        Drawable background = view.getBackground();
        if(Color.parseColor("#D5C1C1") == ((ColorDrawable) background).getColor()){
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
    public List<WordData> randomWord(List<WordData> list){
        Collections.shuffle(list);
        List<WordData> radomWord = list.subList(0, 8);
        return radomWord;
    }
    public boolean checkEnd(String score, String heart ){
        if(score == "120" || heart == "0"){
            return true;
        }
        return false;
    }
}
