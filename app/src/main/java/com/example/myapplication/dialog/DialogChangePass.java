package com.example.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.myapplication.R;
import com.example.myapplication.data.api.ApiServiceClient;
import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.ApiResponse;

public class DialogChangePass extends Dialog {
    private DialogCallback callback;
    public DialogChangePass(Context context, DialogCallback callback){
        super(context);
        this.callback = callback;
    }
    public interface DialogCallback{
        void onCancelClicked();
        void onSaveClicked();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_change_pass);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);
        EditText edOldPass = findViewById(R.id.oldPass);
        EditText edNewPass = findViewById(R.id.newPass);
        EditText edReNewpass = findViewById(R.id.reNewpass);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPass = edOldPass.getText().toString();
                String newPass = edNewPass.getText().toString();
                String reNewpass = edReNewpass.getText().toString();
                if(!checkPass(newPass, reNewpass)){
                    Toast.makeText(getContext(), "Pass doesn't matched", Toast.LENGTH_SHORT).show();
                }
                else {
                    String token_login, email;
                    SharedPreferences sharedPreferences;
                    sharedPreferences = getContext().getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
                    token_login = sharedPreferences.getString("Token_Login", null);
                    email = sharedPreferences.getString("Email", null);
                    ApiServiceClient.setToken(token_login);
                    UserRepository userRepository = new UserRepository();
                    userRepository.changePassword(email, oldPass, newPass, new UserRepository.IApiResponse() {
                        @Override
                        public void onSuccess(ApiResponse apiResponse) {
                            Toast.makeText(getContext(), apiResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
                            dismiss();
                        }

                        @Override
                        public void onFail(ApiResponse apiResponse) {
                            Toast.makeText(getContext(), apiResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                callback.onSaveClicked();

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onCancelClicked();
                dismiss();
            }
        });
    }
    public boolean checkPass(String newPass, String reNewpass){
        if(newPass.equals(reNewpass)){
            return true;
        }
        return false;
    }
}
