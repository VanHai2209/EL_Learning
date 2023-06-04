package com.example.myapplication.model;

public class UserDataSignup {
    private String email;
    private String password;
    private String username;
    private String name;
    private String address;
    private String telephone;
    private String gender;
    private String birthday;
    private String token;

    public UserDataSignup(String email, String password, String username, String name, String address, String telephone, String gender, String birthday, String token) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.gender = gender;
        this.birthday = birthday;
        this.token = token;
    }

    // Getter Methods

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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

    public String getToken() {
        return token;
    }

    // Setter Methods

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setToken(String token) {
        this.token = token;
    }
}
