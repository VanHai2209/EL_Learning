package com.example.myapplication.model;

import java.util.List;

public class SearchWordResponse extends ApiResponse{
    List<WordData> listWord;

    public List<WordData> getListWord() {
        return listWord;
    }

    public void setListWord(List<WordData> listWord) {
        this.listWord = listWord;
    }
}
