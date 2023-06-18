package com.example.myapplication.apdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.dialog.DialogProfile;
import com.example.myapplication.model.GetInforResponse;
import com.example.myapplication.model.ItemRank;
import com.example.myapplication.R;
import com.example.myapplication.model.RankModel;
import com.example.myapplication.model.RankResponse;
import com.example.myapplication.viewModel.HomeViewModel;

import java.util.ArrayList;

public class AdapterRank extends ArrayAdapter<RankModel> {
    String token;
    Context context;
    public AdapterRank(@NonNull Context context, ArrayList<RankModel> rankModels, String token) {
        super(context, 0, rankModels);
        this.context = context;
        this.token = token;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        RankModel rankModel= getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rank_item, parent, false);
        }
        TextView sttRank = convertView.findViewById(R.id.rank_stt);
        TextView nameRank = convertView.findViewById(R.id.rank_name);
        TextView pointRank = convertView.findViewById(R.id.rank_point);
        AppCompatButton btnRank = convertView.findViewById(R.id.rank_button);
        btnRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogProfile dialogProfile = new DialogProfile(context, rankModel, new DialogProfile.DialogCallback() {
                    @Override
                    public void onOkeClicked() {

                    }
                });
                dialogProfile.setCanceledOnTouchOutside(false);
                dialogProfile.show();
            }
        });
        sttRank.setText(rankModel.getMyrank());
        nameRank.setText(rankModel.getUsername());
        pointRank.setText(rankModel.getTotalScore());
        return convertView;
    }
}
