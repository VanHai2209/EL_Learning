package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class TestModel implements Parcelable {
    protected TestModel(Parcel in){
        name = in.readString();
        keyA = in.readString();
        keyB = in.readString();
        keyC = in.readString();
        keyD = in.readString();
        keyCorrect = in.readString();
    }
    public static final Creator<TestModel> CREATOR = new Creator<TestModel>() {
        @Override
        public TestModel createFromParcel(Parcel parcel) {
            return new TestModel(parcel);
        }

        @Override
        public TestModel[] newArray(int i) {
            return new TestModel[i];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(keyA);
        parcel.writeString(keyB);
        parcel.writeString(keyC);
        parcel.writeString(keyD);
        parcel.writeString(keyCorrect);
    }

    String name;
    String keyA;
    String keyB;
    String keyC;
    String keyD;
    String keyCorrect;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyA() {
        return keyA;
    }

    public void setKeyA(String keyA) {
        this.keyA = keyA;
    }

    public String getKeyB() {
        return keyB;
    }

    public void setKeyB(String keyB) {
        this.keyB = keyB;
    }

    public String getKeyC() {
        return keyC;
    }

    public void setKeyC(String keyC) {
        this.keyC = keyC;
    }

    public String getKeyD() {
        return keyD;
    }

    public void setKeyD(String keyD) {
        this.keyD = keyD;
    }

    public String getKeyCorrect() {
        return keyCorrect;
    }

    public void setKeyCorrect(String keyCorrect) {
        this.keyCorrect = keyCorrect;
    }
}
