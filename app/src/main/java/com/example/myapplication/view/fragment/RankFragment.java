package com.example.myapplication.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.VideoView;

import com.example.myapplication.model.ItemRank;
import com.example.myapplication.R;
import com.example.myapplication.apdapter.AdapterRank;
import com.example.myapplication.model.RankModel;
import com.example.myapplication.model.RankResponse;
import com.example.myapplication.viewModel.FrgRankViewModel;

import java.util.ArrayList;


public class RankFragment extends Fragment {
    FrgRankViewModel frgRankViewModel;
    String token_login;
    SharedPreferences sharedPreferences;
    ListView listView;
    AdapterRank adapterRank;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rank, container, false);
        listView = view.findViewById(R.id.rankList);
        sharedPreferences = getActivity().getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login",null);
        frgRankViewModel = new ViewModelProvider(this).get(FrgRankViewModel.class);
        frgRankViewModel.getListRank(token_login);
        frgRankViewModel.getData().observe(getViewLifecycleOwner(), new Observer<RankResponse>() {
            @Override
            public void onChanged(RankResponse rankResponse) {
                ArrayList<RankModel> arrayList = new ArrayList<>();
                for(RankModel rankModel : rankResponse.getListUsers()){
                    if(rankModel.getMyrank() != null){
                        arrayList.add(new RankModel(rankModel.getMyrank(), rankModel.getUsername(), rankModel.getTotalScore(), rankModel.getEmail()));
                    }
                }
                adapterRank = new AdapterRank(getActivity(), arrayList, token_login);
                listView.setAdapter(adapterRank);
            }
        });

        return view;
    }
}