package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.viewModel.RegisterViewModel;

public class MainActivitySignUp extends AppCompatActivity {
    private RegisterViewModel signUpViewModel;
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
                if(signUpViewModel.checkField(txtEmail.getText().toString(),txtPassword.getText().toString(),txtRePass.getText().toString(),txtUsername.getText().toString(),txtName.getText().toString(),txtAddress.getText().toString(),txtPhone.getText().toString(),stringGender)){
                    if(signUpViewModel.checkConfirmPass(txtPassword.getText().toString(),txtRePass.getText().toString())){
                        stringBirthday =birthday.getYear()+"-"+birthday.getMonth()+ "-"+birthday.getDayOfMonth();
                        signUpViewModel.register(txtEmail.getText().toString(),txtPassword.getText().toString(),txtUsername.getText().toString(),txtName.getText().toString(),txtAddress.getText().toString(),txtPhone.getText().toString(),stringGender, stringBirthday);
                    }
                    else {
                        Toast.makeText(MainActivitySignUp.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivitySignUp.this, "Fields must not be left blank", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}