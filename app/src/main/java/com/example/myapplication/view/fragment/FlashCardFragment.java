package com.example.myapplication.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.view.CheckBack;
import com.example.myapplication.view.Listening;
import com.example.myapplication.view.Speaking;


public class FlashCardFragment extends Fragment {
    LinearLayout listening, speaking, checkBack;
    private static final String TokenLogin = "token_login";
    private static final String IdPerson = "id_person";

    // TODO: Rename and change types of parameters
    private String getTokenLogin;
    private String getIdPerson;

    public FlashCardFragment() {
        // Required empty public constructor
    }

    public static FlashCardFragment newInstance(String token_login, String idPerson) {
        FlashCardFragment fragment = new FlashCardFragment();
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
        View view = inflater.inflate(R.layout.fragment_flash_card, container, false);
        listening = view.findViewById(R.id.listening);
        speaking = view.findViewById(R.id.speaking);
        checkBack = view.findViewById(R.id.checkback);
        listening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Listening.class));
            }
        });
        speaking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Speaking.class));
            }
        });
        checkBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CheckBack.class));
            }
        });
        return view;
    }
}