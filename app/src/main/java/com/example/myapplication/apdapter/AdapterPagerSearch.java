package com.example.myapplication.apdapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.WordData;
import com.example.myapplication.viewModel.AudioViewModel;
import com.example.myapplication.viewModel.GrammarViewModel;

import java.util.Arrays;
import java.util.List;

public class AdapterPagerSearch extends RecyclerView.Adapter<MyViewHolder> {
    private ViewModelStoreOwner viewModelStoreOwner;
    private LifecycleOwner lifecycleOwner;
    private String token;
    private WordData wordData;
    private final List<Integer> pages;
    public AdapterPagerSearch(WordData wordData, String token, ViewModelStoreOwner viewModelStoreOwner, LifecycleOwner lifecycleOwner){
        this.viewModelStoreOwner = viewModelStoreOwner;
        this.lifecycleOwner = lifecycleOwner;
        this.wordData = wordData;
        this.token = token;
        pages = Arrays.asList(
                R.layout.page_search_vi,
                R.layout.page_search_en,
                R.layout.page_search_pronoun,
                R.layout.page_search_sponsor
        );
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new MyViewHolder(view, viewModelStoreOwner, lifecycleOwner);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        switch (position){
            case 0:
                holder.updatePageVi(wordData);
                break;
            case 1:
                holder.updatePageEn(wordData);
                break;
            case 2:
                holder.updatePagePronoun(wordData, token);
                break;
            case 3:
                holder.updatePageImage(wordData, token);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return pages.get(position);
    }

}
class MyViewHolder extends RecyclerView.ViewHolder {
    MediaPlayer mediaPlayer;
    private LifecycleOwner lifecycleOwner;
    private ViewModelStoreOwner viewModelStoreOwner;
    TextView txtVi;
    TextView txtEn, txtType, txtEx;
    TextView txtIPA;
    ImageView image;
    AppCompatButton btnAudio;
    public MyViewHolder(@NonNull View itemView, ViewModelStoreOwner viewModelStoreOwner, LifecycleOwner lifecycleOwner) {
        super(itemView);
        this.viewModelStoreOwner = viewModelStoreOwner;
        this.lifecycleOwner = lifecycleOwner;
        txtVi = itemView.findViewById(R.id.txtVi);
        txtEn = itemView.findViewById(R.id.txtEn);
        txtType = itemView.findViewById(R.id.txtType);
        txtEx = itemView.findViewById(R.id.txtEx);
        txtIPA = itemView.findViewById(R.id.txtIPA);
        image = itemView.findViewById(R.id.imageSearch);
        btnAudio = itemView.findViewById(R.id.btnAudio);
    }

    public void updatePageVi(WordData wordData){
        txtVi.setText(wordData.getVn());
    }
    public void updatePageEn(WordData wordData){
        txtEn.setText(wordData.getEn());
        txtEx.setText(wordData.getExample());
        txtType.setText(wordData.getType());
    }
    public void updatePagePronoun(WordData wordData, String token){
        AudioViewModel audioViewModel = new ViewModelProvider(viewModelStoreOwner).get(AudioViewModel.class);
        audioViewModel.getAudioData(wordData.getAudio(), token);
        audioViewModel.getData().observe(lifecycleOwner, new Observer<MediaPlayer>() {
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
        if(wordData.getIPA() != null){
            txtIPA.setText(wordData.getIPA());
        }
        else {

        }

    }
    public void updatePageImage(WordData wordData, String token){
        GrammarViewModel grammarViewModel = new ViewModelProvider(viewModelStoreOwner).get(GrammarViewModel.class);
        grammarViewModel.getImage(wordData.getEn()+".jpg", token);
        grammarViewModel.getImageGrammar().observe(lifecycleOwner, new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                image.setImageBitmap(bitmap);
            }
        });
    }
}


