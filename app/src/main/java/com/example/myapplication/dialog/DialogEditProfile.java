package com.example.myapplication.dialog;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.model.GetInforResponse;
import com.example.myapplication.view.fragment.ProfileFragment;
import com.example.myapplication.viewModel.HomeViewModel;

public class DialogEditProfile extends AppCompatDialog {
    private LiveData<GetInforResponse> data;

    String email, token_login;
    SharedPreferences sharedPreferences;

    EditText txtUsername, txtName, txtPhone, txtAddress, txtBirthday;
    private DialogCallback callback;

    public DialogEditProfile(@NonNull Context context, LiveData<GetInforResponse> data, DialogCallback callback) {
        super(context);
        this.callback = callback;
        this.data = data;
    }

    public interface DialogCallback {
        void onCancelClicked();

        void onSaveClicked();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit_profile);
        txtUsername = findViewById(R.id.txtUserName);
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtAddress = findViewById(R.id.txtAddress);
        txtBirthday = findViewById(R.id.txtBirthday);
        GetInforResponse getInforResponse = data.getValue();
        if(getInforResponse !=  null){
            txtUsername.setText(getInforResponse.getDataUser().getUsername());
            txtName.setText(getInforResponse.getDataUser().getName());
            txtPhone.setText(getInforResponse.getDataUser().getTelephone());
            txtAddress.setText(getInforResponse.getDataUser().getAddress());
            txtBirthday.setText(getInforResponse.getDataUser().getBirthday());
        }
        sharedPreferences = getContext().getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login", null);
        email = sharedPreferences.getString("Email", null);


        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSaveClicked();
                dismiss();
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
}
