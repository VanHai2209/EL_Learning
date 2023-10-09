package com.example.myapplication.apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.model.MistakeModel;

import java.util.ArrayList;

public class AdapterCheckBack extends ArrayAdapter<MistakeModel> {
    private Context mContext;
    private ArrayList<MistakeModel> mItems;
    public AdapterCheckBack(@NonNull Context context, int resource, ArrayList<MistakeModel> mistakeModels)  {
        super(context, resource, mistakeModels);
        this.mContext = context;
        this.mItems = mistakeModels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Lấy dữ liệu cho mục tại vị trí position
        MistakeModel currentItem = getItem(position);

        // Sử dụng LayoutInflater để nạp layout cho mục
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_mistake, parent, false);
        }

        // Tìm các thành phần trong layout của mục
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtWord = convertView.findViewById(R.id.txtWord);

        // Đặt giá trị cho các thành phần dựa trên dữ liệu của mục
        txtName.setText(currentItem.getNameMistake());
        txtWord.setText(currentItem.getWord());

        // Trả về convertView (layout của mục đã được cập nhật)
        return convertView;
    }
}
