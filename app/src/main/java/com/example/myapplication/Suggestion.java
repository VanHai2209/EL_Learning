package com.example.myapplication;

public class Suggestion {
    String wordFind, wordTranslate, wordPronoun;

    public Suggestion(String wordFind, String wordTranslate, String wordPronoun) {
        this.wordFind = wordFind;
        this.wordTranslate = wordTranslate;
        this.wordPronoun = wordPronoun;
    }

    public String getWordFind() {
        return wordFind;
    }

    public String getWordTranslate() {
        return wordTranslate;
    }

    public String getWordPronoun() {
        return wordPronoun;
    }
}
