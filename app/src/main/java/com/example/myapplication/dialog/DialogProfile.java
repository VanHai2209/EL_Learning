package com.example.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.LiveData;

import com.example.myapplication.R;
import com.example.myapplication.model.GetInforResponse;
import com.example.myapplication.model.RankModel;

public class DialogProfile extends Dialog {
    private RankModel rankModel;
    private DialogCallback callback;
    public DialogProfile(Context context,RankModel rankModel, DialogCallback callback){
        super(context);
        this.callback = callback;
        this.rankModel = rankModel;
    }
    public interface DialogCallback{
        public void onOkeClicked();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_profile);
        TextView txtUsername = findViewById(R.id.txtName);
        txtUsername.setText(rankModel.getUsername());
        TextView txtEmail = findViewById(R.id.txtEmail);
        txtEmail.setText(maskingData(rankModel.getEmail()));
        TextView txtRank = findViewById(R.id.txtRank);
        txtRank.setText(rankModel.getMyrank());
        TextView txtScore = findViewById(R.id.txtScore);
        txtScore.setText(rankModel.getTotalScore());
        Button btnOke = findViewById(R.id.btnOke);
        btnOke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onOkeClicked();
                dismiss();
            }
        });
    }
    public String maskingData(String email ){
        int atIndex = email.indexOf("@");
        if (atIndex > 1) {
            String firstCharacter = email.substring(0, 1);
            String maskedEmail = firstCharacter + "*****" + email.substring(atIndex);
            return maskedEmail;
        } else {
            return email;
        }
    }
}
