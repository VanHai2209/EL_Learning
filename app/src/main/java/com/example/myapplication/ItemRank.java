package com.example.myapplication;

public class ItemRank {
    String sttRank, nameRank, pointRank;
    public ItemRank(String sttRank, String nameRank, String pointRank){
        this.sttRank = sttRank;
        this.nameRank = nameRank;
        this.pointRank = pointRank;
    }

    public String getSttRank() {
        return sttRank;
    }

    public String getNameRank() {
        return nameRank;
    }

    public String getPointRank() {
        return pointRank;
    }
}
