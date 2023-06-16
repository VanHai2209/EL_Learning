package com.example.myapplication.apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.example.myapplication.model.ItemRank;
import com.example.myapplication.R;

import java.util.ArrayList;

public class AdapterRank extends ArrayAdapter<ItemRank> {
    public AdapterRank(@NonNull Context context, ArrayList<ItemRank> itemRanks) {
        super(context, 0, itemRanks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ItemRank itemRank = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rank_item, parent, false);
        }
        TextView sttRank = convertView.findViewById(R.id.rank_stt);
        TextView nameRank = convertView.findViewById(R.id.rank_name);
        TextView pointRank = convertView.findViewById(R.id.rank_point);
        AppCompatButton btnRank = convertView.findViewById(R.id.rank_button);
        sttRank.setText(itemRank.getSttRank());
        nameRank.setText(itemRank.getNameRank());
        pointRank.setText(itemRank.getPointRank());
        return convertView;
    }
}
