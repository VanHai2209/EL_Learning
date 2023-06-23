package com.example.myapplication.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.myapplication.R;
import com.example.myapplication.model.SearchWordResponse;
import com.example.myapplication.model.WordData;
import com.example.myapplication.view.SearchActivity2;
import com.example.myapplication.apdapter.AdapterSuggest;
import com.example.myapplication.viewModel.FrgSearchViewModel;
import com.example.myapplication.viewModel.GrammarViewModel;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    AutoCompleteTextView searchView;
    FrgSearchViewModel frgSearchViewModel;
    String token_login;
    SharedPreferences sharedPreferences;
    ListView listView;
    AdapterSuggest adapterSuggest;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        listView = view.findViewById(R.id.suggestionList);
        searchView = view.findViewById(R.id.searchView);
        progressBar = view.findViewById(R.id.progreesBar);
        sharedPreferences = getActivity().getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login",null);
        frgSearchViewModel = new ViewModelProvider(this).get(FrgSearchViewModel.class);
        frgSearchViewModel.listWord(token_login);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                frgSearchViewModel.getData().observe(getViewLifecycleOwner(), new Observer<SearchWordResponse>() {
                    @Override
                    public void onChanged(SearchWordResponse searchWordResponse) {
                        ArrayList<WordData> suggestionArrayList = new ArrayList<>();
                        for(WordData wordData : searchWordResponse.getListWord()){
                            suggestionArrayList.add(new WordData(wordData.getId(), wordData.getEn(), wordData.getVn(), wordData.getType(), wordData.getIPA(), wordData.getExample(), wordData.getImage(), wordData.getAudio(), wordData.getIdTopic()));
                        }
                        adapterSuggest = new AdapterSuggest(getActivity(), suggestionArrayList);
                        listView.setAdapter(adapterSuggest);
                        progressBar.setVisibility(View.INVISIBLE);
                        adapterSuggest.getFilter().filter("");
                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        WordData wordData = (WordData) listView.getItemAtPosition(i);
                        Intent intent = new Intent(getActivity(), SearchActivity2.class);
                        intent.putExtra("wordData", wordData);
                        startActivity(intent);
                    }
                });
                searchView.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        // Không cần thực hiện hành động trước khi văn bản thay đổi
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        // Thực hiện hành động khi văn bản thay đổi
                        adapterSuggest.getFilter().filter(charSequence.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        // Không cần thực hiện hành động sau khi văn bản thay đổi
                    }
                });
            }
        },500);


        return view;
    }
}