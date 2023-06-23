package com.example.myapplication.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
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
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.apdapter.AdapterSuggest;
import com.example.myapplication.model.SearchWordResponse;
import com.example.myapplication.model.WordData;
import com.example.myapplication.view.GameSovleitout;
import com.example.myapplication.view.SearchActivity2;
import com.example.myapplication.view.TopicWordView;
import com.example.myapplication.viewModel.FrgMyListViewModel;
import com.example.myapplication.viewModel.FrgSearchViewModel;
import com.example.myapplication.viewModel.WordViewModel;

import java.util.ArrayList;

public class MyListFragment extends Fragment {
    AutoCompleteTextView searchView;
    ListView listView;
    AdapterSuggest adapterSuggest;
    ProgressBar progressBar;
    private static final String TokenLogin = "token_login";
    private static final String IdPerson = "id_person";

    private String getTokenLogin;
    private String getIdPerson;

    public MyListFragment() {
        // Required empty public constructor
    }

    public static MyListFragment newInstance(String token_login, String idPerson) {
        MyListFragment fragment = new MyListFragment();
        Bundle args = new Bundle();
        args.putString(TokenLogin, token_login);
        args.putString(IdPerson, idPerson);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            getTokenLogin = getArguments().getString(TokenLogin);
            getIdPerson = getArguments().getString(IdPerson);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_list, container, false);
        listView = view.findViewById(R.id.suggestionList);
        searchView = view.findViewById(R.id.searchView);
        progressBar = view.findViewById(R.id.progreesBar);
        FrgMyListViewModel frgMyListViewModel;
        frgMyListViewModel = new ViewModelProvider(this).get(FrgMyListViewModel.class);
        frgMyListViewModel.listPersonWord(getIdPerson, getTokenLogin);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                frgMyListViewModel.getData().observe(getViewLifecycleOwner(), new Observer<SearchWordResponse>() {
                    @Override
                    public void onChanged(SearchWordResponse searchWordResponse) {
                        ArrayList<WordData> suggestionArrayList = new ArrayList<>();
                        for(WordData wordData : searchWordResponse.getListWord()){
                            suggestionArrayList.add(new WordData(wordData.getId(), wordData.getEn(), wordData.getVn(), wordData.getType(), wordData.getIPA(), wordData.getExample(), wordData.getImage(), wordData.getAudio(), wordData.getIdTopic()));
                        }
                        adapterSuggest = new AdapterSuggest(getActivity(), suggestionArrayList);
                        listView.setAdapter(adapterSuggest);
                        adapterSuggest.getFilter().filter("");
                        progressBar.setVisibility(View.INVISIBLE);
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