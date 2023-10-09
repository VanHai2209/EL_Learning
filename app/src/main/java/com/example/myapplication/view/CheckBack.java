package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.apdapter.AdapterCheckBack;
import com.example.myapplication.model.MistakeModel;

import java.util.ArrayList;

public class CheckBack extends AppCompatActivity {
    AppCompatButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkback);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ArrayList<MistakeModel> items = new ArrayList<>();
        items.add(new MistakeModel("Listen", "bacon"));
        items.add(new MistakeModel("Speak", "father"));
        items.add(new MistakeModel("Speak", "cheese"));
        items.add(new MistakeModel("Listen", "cake"));
        items.add(new MistakeModel("Listen", "mother"));
        items.add(new MistakeModel("Listen", "hello"));
        items.add(new MistakeModel("Speak", "box"));
        items.add(new MistakeModel("Listen", "jellyfish"));
        AdapterCheckBack adapterCheckBack = new AdapterCheckBack(this, 0, items);
        ListView listView = findViewById(R.id.listview); // Thay R.id.listView bằng ID thật của ListView trong XML
        listView.setAdapter(adapterCheckBack);
    }
}