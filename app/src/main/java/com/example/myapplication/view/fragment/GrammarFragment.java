package com.example.myapplication.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.model.Grammar;
import com.example.myapplication.view.SearchActivity2;
import com.example.myapplication.viewModel.FrgGrammarViewModel;

import java.util.ArrayList;
import java.util.List;

public class GrammarFragment extends Fragment {
    String token_login;
    SharedPreferences sharedPreferences;
    FrgGrammarViewModel frgGrammarViewModel;
    ListView listGrammar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_program, container, false);

        listGrammar = view.findViewById(R.id.listGrammar);
        frgGrammarViewModel = new FrgGrammarViewModel();
        sharedPreferences = getActivity().getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login",null);
        frgGrammarViewModel = new ViewModelProvider(this).get(FrgGrammarViewModel.class);
        frgGrammarViewModel.getGrammar(token_login);
        frgGrammarViewModel.geData().observe(getViewLifecycleOwner(), new Observer<List<Grammar>>() {
            @Override
            public void onChanged(List<Grammar> grammars) {
                List<String> grammarNames = new ArrayList<>();
                for (Grammar grammar : grammars) {
                    grammarNames.add(grammar.getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(),
                        R.layout.item_grammar,R.id.nameGrammar, grammarNames);
                listGrammar.setAdapter(adapter);
            }
        });
        listGrammar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String grammar = (String) listGrammar.getItemAtPosition(i);
                Intent intent = new Intent(getActivity(), com.example.myapplication.view.Grammar.class);
                intent.putExtra("grammar", grammar);
                startActivity(intent);
            }
        });
        return view;
    }
}