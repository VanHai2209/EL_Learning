package com.example.myapplication.model;

public class UserData {
    private String email;
    private String username;
    private String name;
    private String address;
    private String telephone;
    private String gender;
    private String birthday;

    public UserData(String username, String name, String email, String gender, String birthday, String address, String telephone) {
        this.email = email;
        this.username = username;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.gender = gender;
        this.birthday = birthday;
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
}
