package com.example.myapplication.model;

public class RankModel {
    private String myrank;
    private String username;
    private String totalScore;
    private String email;

    public RankModel(String myrank, String username, String totalScore, String email) {
        this.myrank = myrank;
        this.username = username;
        this.totalScore = totalScore;
        this.email = email;
    }

    public String getMyrank() {
        return myrank;
    }

    public void setMyrank(String myrank) {
        this.myrank = myrank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
