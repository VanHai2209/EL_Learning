package com.example.myapplication.apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.Suggestion;

import java.util.ArrayList;

public class AdapterSuggest extends ArrayAdapter<Suggestion> {
    public AdapterSuggest(Context context, ArrayList<Suggestion> suggestions) {
        super(context, 0, suggestions);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Suggestion suggestion = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.suggestion_list, parent, false);
        }

        // Lookup view for data population
        TextView wordFind = convertView.findViewById(R.id.word_find);
        TextView wordTranslate = convertView.findViewById(R.id.word_translate);
        TextView wordPronoun = convertView.findViewById(R.id.word_pronoun);
        // Populate the data into the template view using the data object
        wordFind.setText(suggestion.getWordFind());
        wordTranslate.setText(suggestion.getWordTranslate());
        wordPronoun.setText(suggestion.getWordPronoun());
        // Return the completed view to render on screen
        return convertView;
    }
}
