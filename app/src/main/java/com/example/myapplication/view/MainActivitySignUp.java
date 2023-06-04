package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.myapplication.R;
import com.example.myapplication.viewModel.RegisterViewModel;

public class MainActivitySignUp extends AppCompatActivity {
    RegisterViewModel signUpViewModel;
    Button btnSignUp;
    EditText txtEmail, txtPassword,txtRePass, txtUsername, txtName, txtAddress, txtPhone;
    String stringGender, stringBirthday;
    DatePicker birthday;
    RadioGroup gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signUpViewModel = new RegisterViewModel(this);

        txtEmail = findViewById(R.id.txtGmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtRePass = findViewById(R.id.txtRepsswd);
        txtUsername = findViewById(R.id.txtUserName);
        txtName = findViewById(R.id.txtName);
        txtAddress = findViewById(R.id.txtAddress);
        txtPhone = findViewById(R.id.txtPhone);
        birthday = findViewById(R.id.date_picker_birthday);
        gender = findViewById(R.id.radio_group_gender);
        btnSignUp = findViewById(R.id.btnSignUp);

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                stringGender = signUpViewModel.checkRadio(i);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBirthday = birthday.getDayOfMonth()+"-"+birthday.getMonth()+"-"+birthday.getYear();
                signUpViewModel.register(txtEmail.getText().toString(),txtPassword.getText().toString(),txtUsername.getText().toString(),txtName.getText().toString(),txtAddress.getText().toString(),txtPhone.getText().toString(),stringGender, stringBirthday);
            }
        });
    }
}