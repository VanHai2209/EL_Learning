package com.example.myapplication;

import android.graphics.ImageDecoder;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;

import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ViewPager2 viewPager2;
    private Handler slideHandler = new Handler();
    VideoView videoViewUsers, videoViewWords, videoViewTopics;
    boolean isVideoPlaying = false;
    String videoPath_Users, videoPath_Topics, videoPath_Words;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager2 = view.findViewById(R.id.viewPager);
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
//        viewPager2.setClipToPadding(false);
//        viewPager2.setClipChildren(false);
//        viewPager2.setOffscreenPageLimit(5);
//        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
//
//        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
//        compositePageTransformer.addTransformer(new MarginPageTransformer(30));
//        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
//            @Override
//            public void transformPage(@NonNull View page, float position) {
//
//            }
//        });
//        viewPager2.setPageTransformer(compositePageTransformer);

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
    public void onResume() {
        super.onResume();
        slideHandler.postDelayed(slideRunnable, 3000);
    }
}
