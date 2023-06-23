package com.example.myapplication.dialog;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.data.api.ApiServiceClient;
import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.model.ApiResponse;
import com.example.myapplication.model.GetInforResponse;

public class DialogEditProfile extends AppCompatDialog {
    private LiveData<GetInforResponse> data;

    String token_login;
    SharedPreferences sharedPreferences;

    EditText txtUsername, txtName, txtPhone, txtAddress, txtBirthday;
    RadioGroup radioGroup;
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
        radioGroup = findViewById(R.id.group_gender);
        txtUsername = findViewById(R.id.txtUserName);
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtAddress = findViewById(R.id.txtAddress);
        txtBirthday = findViewById(R.id.txtBirthday);
        GetInforResponse getInforResponse = data.getValue();
        if(getInforResponse !=  null){
            if (getInforResponse.getDataUser().getGender().equals("1")){
                radioGroup.check(R.id.radio_male);
            }
            else {
                radioGroup.check(R.id.radio_female);
            }
            txtUsername.setText(getInforResponse.getDataUser().getUsername());
            txtName.setText(getInforResponse.getDataUser().getName());
            txtPhone.setText(getInforResponse.getDataUser().getTelephone());
            txtAddress.setText(getInforResponse.getDataUser().getAddress());
            txtBirthday.setText(getInforResponse.getDataUser().getBirthday());
        }
        sharedPreferences = getContext().getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login", null);

        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = getInforResponse.getDataUser().getEmail();
                String username = txtUsername.getText().toString();
                String name = txtName.getText().toString();
                String address = txtAddress.getText().toString();
                String telephone = txtPhone.getText().toString();
                String birthday = txtBirthday.getText().toString();
                String gender;
                if(radioGroup.getCheckedRadioButtonId() == R.id.radio_male){
                    gender = "1";
                }
                else {
                    gender = "2";
                }
                UserRepository userRepository = new UserRepository();
                ApiServiceClient.setToken(token_login);
                userRepository.updateUser(email, username, name, address, telephone, gender, birthday, new UserRepository.IApiResponse() {
                    @Override
                    public void onSuccess(ApiResponse apiResponse) {
                        Toast.makeText(getContext(),apiResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
                        dismiss();
                    }

                    @Override
                    public void onFail(ApiResponse apiResponse) {
                        Toast.makeText(getContext(),apiResponse.getErrMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
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
}
