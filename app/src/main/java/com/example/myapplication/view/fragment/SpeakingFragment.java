package com.example.myapplication.view.fragment;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.model.WordData;
import com.example.myapplication.viewModel.AudioViewModel;
import com.example.myapplication.viewModel.GrammarViewModel;

import java.util.ArrayList;

public class SpeakingFragment extends Fragment {
    String recognizedText;
    private static final int SPEECH_REQUEST_CODE = 123;
    private Animation btnSpeakAnimation;
    private WordData wordData;
    private String token;
    private TextAnswerListener textAnswerListener;
    public static SpeakingFragment newInstance(WordData wordData, String token){
        Bundle args = new Bundle();
        args.putParcelable("ARG_TEST_MODEL",wordData);
        args.putString("ARG_TOKEN",token);
        SpeakingFragment fragment = new SpeakingFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof TextAnswerListener) {
            textAnswerListener = (TextAnswerListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement TextAnswerListener");
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            wordData = getArguments().getParcelable("ARG_TEST_MODEL");
            token = getArguments().getString("ARG_TOKEN");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  =  inflater.inflate(R.layout.fragment_speaking, container, false);
        AppCompatButton btnAudio = view.findViewById(R.id.btnAudio);
        ImageView imageView = view.findViewById(R.id.image);
        updatePageImage(imageView);
        TextView txtEN = view.findViewById(R.id.txtWord);
        txtEN.setText(wordData.getEn());
        AppCompatButton btnSpeak = view.findViewById(R.id.btnSpeak);
        btnSpeakAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.micro_anim);
        AudioViewModel audioViewModel = new ViewModelProvider(this).get(AudioViewModel.class);
        audioViewModel.getAudioData(wordData.getAudio(), token);
        audioViewModel.getData().observe(getViewLifecycleOwner(), new Observer<MediaPlayer>() {
            @Override
            public void onChanged(MediaPlayer mediaPlayer) {
                btnAudio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mediaPlayer.start();
                    }
                });
            }
        });
        btnSpeak.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        btnSpeak.startAnimation(btnSpeakAnimation);
                        startSpeechToText();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        btnSpeak.clearAnimation();
                        break;
                }
                return true;
            }
        });
        return view;
    }
    private void startSpeechToText() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        try {
            startActivityForResult(intent, SPEECH_REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getActivity(), "Chuyển đổi giọng nói thành văn bản không khả dụng.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            recognizedText = result.get(0); // Lấy kết quả nhận dạng đầu tiên
            if(recognizedText.equalsIgnoreCase(wordData.getEn())){
                textAnswerListener.isTrue();
            }
            else {
                textAnswerListener.isFalse();
            }
        }
    }
    public void updatePageImage(ImageView image){
        GrammarViewModel grammarViewModel = new ViewModelProvider(getActivity()).get(GrammarViewModel.class);
        grammarViewModel.getImage(wordData.getEn()+".jpg", token);
        grammarViewModel.getImageGrammar().observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                image.setImageBitmap(bitmap);
            }
        });
    }
}
