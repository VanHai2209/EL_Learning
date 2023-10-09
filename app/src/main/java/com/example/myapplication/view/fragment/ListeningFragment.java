package com.example.myapplication.view.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.model.WordData;
import com.example.myapplication.viewModel.AudioViewModel;

public class ListeningFragment extends Fragment {
    EditText txtAnswer;
    private WordData wordData;
    private String token;
    private TextAnswerListener textAnswerListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof TextAnswerListener) {
            textAnswerListener = (TextAnswerListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement TextAnswerListener");
        }
    }

    public static ListeningFragment newInstance(WordData wordData, String token){
        Bundle args = new Bundle();
        args.putParcelable("ARG_TEST_MODEL",wordData);
        args.putString("ARG_TOKEN",token);
        ListeningFragment fragment = new ListeningFragment();
        fragment.setArguments(args);
        return fragment;
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
        View view  =  inflater.inflate(R.layout.fragment_listening, container, false);
        AppCompatButton btnAudio = view.findViewById(R.id.btnAudio);
        txtAnswer = view.findViewById(R.id.txtAnswer);
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
        return view;
    }
    public void checkAnswer(){
        String enteredText = txtAnswer.getText().toString();
        if (enteredText.equalsIgnoreCase(wordData.getEn())) {
            textAnswerListener.isTrue();
        } else {
            textAnswerListener.isFalse();
        }
    }
}
