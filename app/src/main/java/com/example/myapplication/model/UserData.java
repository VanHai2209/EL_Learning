package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class UserData implements Parcelable {
    protected UserData(Parcel in){
        email = in.readString();
        username = in.readString();
        name = in.readString();
        telephone = in.readString();
        address = in.readString();
        gender = in.readString();
        birthday = in.readString();
    }
    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(username);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(telephone);
        dest.writeString(gender);
        dest.writeString(birthday);

    }

    private String email;
    private String username;
    private String name;
    private String address;
    private String telephone;
    private String gender;
    private String birthday;
    private int myrank;
    private int totalUser;

    public UserData(String username, String name, String email, String gender, String birthday, String address, String telephone, int myrank, int totalUser) {
        this.email = email;
        this.username = username;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.gender = gender;
        this.birthday = birthday;
        this.myrank = myrank;
        this.totalUser = totalUser;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getMyrank() {
        return myrank;
    }

    public void setMyrank(int myrank) {
        this.myrank = myrank;
    }

    public int getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(int totalUser) {
        this.totalUser = totalUser;
    }
}
