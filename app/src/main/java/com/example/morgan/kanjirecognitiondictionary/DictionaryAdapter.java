package com.example.morgan.kanjirecognitiondictionary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.morgan.kanjirecognitiondictionary.util.Pair;

import java.util.ArrayList;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.ViewHolder> {

    ArrayList<Pair<ArrayList<Pair<String, String>>, ArrayList<String>>> dict;

    public DictionaryAdapter(ArrayList<Pair<ArrayList<Pair<String, String>>, ArrayList<String>>> dict) {
        this.dict = dict;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.dictionary_body, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Pair<ArrayList<Pair<String, String>>, ArrayList<String>> info =  dict.get(i);

        ArrayList<Pair<String, String>> words = info.getFirst();
        ArrayList<String> defns = info.getSecond();
        String word = "";
        String defn = "";
        for (int j = 0; j < words.size(); j++) {
            Pair<String, String> pair = words.get(j);
            word += pair.getFirst() + " (" + pair.getSecond() + ")\n";
        }
        for (int j = 0; j < defns.size(); j++) {
            defn += defns.get(j);
            if (j != defns.size() - 1) {
                defn += ", ";
            }
        }

        TextView w = viewHolder.word;
        w.setText(word);
        LinearLayout linearLayout = viewHolder.linearLayout;
        TextView d = (TextView) linearLayout.getChildAt(0);
        d.setText(defn);
    }

    @Override
    public int getItemCount() {
        return dict.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView word;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            word = (TextView) itemView.findViewById(R.id.word_name);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.definition_layout);
        }
    }
}
