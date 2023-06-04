package com.example.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.VideoView;

import java.util.ArrayList;


public class RankFragment extends Fragment {
    ListView listView;
    AdapterRank adapterRank;
    ArrayList<ItemRank> itemRankArrayList = new ArrayList<>();
    VideoView videoViewRank;
    boolean isVideoPlaying = false;
    String videoPath_rank;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rank, container, false);
        videoViewRank = view.findViewById(R.id.video_view_rank);
        listView = view.findViewById(R.id.rankList);
        for(int i=0; i<20;i++){
            itemRankArrayList.add(new ItemRank(""+(i+1),"Vũ Hoài Anh", "20000"));
        }
        adapterRank = new AdapterRank(getActivity(), itemRankArrayList);
        listView.setAdapter(adapterRank);
        videoViewRank.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                isVideoPlaying = true;
                mediaPlayer.setLooping(true);
            }
        });
        videoViewRank.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if(isVideoPlaying){
                    videoViewRank.start();
                }
            }
        });
        videoPath_rank = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.rank;
        videoViewRank.setVideoPath(videoPath_rank);
        videoViewRank.start();
        return view;
    }
}