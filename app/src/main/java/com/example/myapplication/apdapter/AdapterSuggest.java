package com.example.myapplication.apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.WordData;

import java.util.ArrayList;

public class AdapterSuggest extends ArrayAdapter<WordData> implements Filterable {
    private ArrayList<WordData> suggestions;
    private ArrayList<WordData> filteredSuggestions;

    public AdapterSuggest(Context context, ArrayList<WordData> suggestions) {
        super(context, 0, suggestions);
        this.suggestions = suggestions;
        this.filteredSuggestions = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return filteredSuggestions.size();
    }

    @Override
    public WordData getItem(int position) {
        return filteredSuggestions.get(position);
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((WordData) resultValue).getEn();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null) {
                filteredSuggestions.clear();
                for (WordData suggestion : suggestions) {
                    if (suggestion.getEn().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        filteredSuggestions.add(suggestion);
                    }
                }
                filterResults.values = filteredSuggestions;
                filterResults.count = filteredSuggestions.size();
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results != null && results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    };
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        WordData suggestion = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.suggestion_list, parent, false);
        }

        // Lookup view for data population
        TextView wordFind = convertView.findViewById(R.id.word_find);
        TextView wordTranslate = convertView.findViewById(R.id.word_translate);
        TextView wordPronoun = convertView.findViewById(R.id.word_pronoun);
        // Populate the data into the template view using the data object
        wordFind.setText(suggestion.getEn());
        wordTranslate.setText(suggestion.getVn());
        if(suggestion.getIPA() != null){
            wordPronoun.setText(suggestion.getIPA());
        }
        else {
            wordPronoun.setText("null");
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
