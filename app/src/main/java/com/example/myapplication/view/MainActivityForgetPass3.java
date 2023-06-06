package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.viewModel.UpdatePassViewModel;

public class MainActivityForgetPass3 extends AppCompatActivity {
    private UpdatePassViewModel updatePassViewModel;
    EditText updatePass, confirmUpdatePass;
    AppCompatButton btnConfrimPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_forget_pass3);
        updatePassViewModel = new UpdatePassViewModel(this);
        btnConfrimPass = findViewById(R.id.btnConfrimPass);
        updatePass = findViewById(R.id.updatePass);
        confirmUpdatePass = findViewById(R.id.updatePassConfirm);
        btnConfrimPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(updatePassViewModel.checkConfirmPass(updatePass.getText().toString(), confirmUpdatePass.getText().toString())){
                    updatePassViewModel.updatePass(updatePass.getText().toString());
                }
                else{
                    Toast.makeText(MainActivityForgetPass3.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}