package com.example.myapplication.model;

public class MistakeModel {
    private String nameMistake;
    private String word;

    public MistakeModel(String nameMistake, String word) {
        this.nameMistake = nameMistake;
        this.word = word;
    }

    public String getNameMistake() {
        return nameMistake;
    }

    public String getWord() {
        return word;
    }
}
