package com.example.myapplication.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.myapplication.R;
import com.example.myapplication.apdapter.SlideAdapter;
import com.example.myapplication.model.SlideItem;
import com.example.myapplication.model.GetInforResponse;
import com.example.myapplication.viewModel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    LinearLayout rank_layout, topic_layout, search_layout;
    HomeViewModel homeViewModel;
    String email, token_login;
    SharedPreferences sharedPreferences;
    ViewPager2 viewPager2;
    private Handler slideHandler = new Handler();
    VideoView videoViewUsers, videoViewWords, videoViewTopics;
    boolean isVideoPlaying = false;
    TextView txtUsername, txtRank;
    String videoPath_Users, videoPath_Topics, videoPath_Words;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        sharedPreferences = getActivity().getSharedPreferences("EL_Learning", Context.MODE_PRIVATE);
        token_login = sharedPreferences.getString("Token_Login",null);
        email = sharedPreferences.getString("Email", null);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getInforUser(email, token_login);
        homeViewModel.getData().observe(getViewLifecycleOwner(), new Observer<GetInforResponse>() {
            @Override
            public void onChanged(GetInforResponse getInforResponse) {
                txtUsername.setText("Hi,"+getInforResponse.getDataUser().getUsername());
                txtRank.setText("Your Rank: "+getInforResponse.getDataUser().getMyrank()+"/"+getInforResponse.getDataUser().getTotalUser());
            }
        });
        viewPager2 = view.findViewById(R.id.viewPager);
        txtUsername = view.findViewById(R.id.textView8);
        txtRank = view.findViewById(R.id.textView10);
        rank_layout = view.findViewById(R.id.rank_layout);
        rank_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onClicked("Rank");
                }
            }
        });
        search_layout = view.findViewById(R.id.search_layout);
        search_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onClicked("Dictionary");
                }
            }
        });
        topic_layout = view.findViewById(R.id.topic_layout);
        topic_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onClicked("Topic");
                }
            }
        });

        List<SlideItem> slideItems = new ArrayList<>();
        slideItems.add(new SlideItem(R.drawable.carousel1));
        slideItems.add(new SlideItem(R.drawable.carousel2));
        slideItems.add(new SlideItem(R.drawable.carousel4));
        slideItems.add(new SlideItem(R.drawable.carousel3));
        viewPager2.setAdapter(new SlideAdapter(slideItems, viewPager2));

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.postDelayed(slideRunnable, 9000);
            }
        });

        videoViewUsers = view.findViewById(R.id.video_view_users);
        videoViewTopics = view.findViewById(R.id.video_view_topics);
        videoViewWords = view.findViewById(R.id.video_view_words);

        // set video path

        videoViewUsers.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                isVideoPlaying = true;
                mp.setLooping(true);
            }
        });

        videoViewUsers.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (isVideoPlaying) {
                    videoViewUsers.start();
                }
            }
        });
        videoPath_Users = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.users;
        videoViewUsers.setVideoPath(videoPath_Users);
        videoViewUsers.start();

        videoViewTopics.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                isVideoPlaying = true;
                mp.setLooping(true);
            }
        });

        videoViewTopics.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (isVideoPlaying) {
                    videoViewTopics.start();
                }
            }
        });
        videoPath_Topics = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.topics;
        videoViewTopics.setVideoPath(videoPath_Topics);
        videoViewTopics.start();

        videoViewWords.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                isVideoPlaying = true;
                mp.setLooping(true);
            }
        });

        videoViewWords.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (isVideoPlaying) {
                    videoViewWords.start();
                }
            }
        });
        videoPath_Words = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.words;
        videoViewWords.setVideoPath(videoPath_Words);
        videoViewWords.start();

        return view;
    }
    private  Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(slideRunnable);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (OnFragmentInteractionListener) context;
    }

    @Override
    public void onResume() {
        super.onResume();
        slideHandler.postDelayed(slideRunnable, 3000);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
