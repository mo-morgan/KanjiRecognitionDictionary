package com.example.morgan.kanjirecognitiondictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.morgan.kanjirecognitiondictionary.util.Pair;

import java.util.ArrayList;

public class DictionaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.dict_recycler_view);

        ArrayList<Pair<ArrayList<Pair<String, String>>, ArrayList<String>>> dict;
        Bundle bundle = getIntent().getExtras();
        dict = (ArrayList<Pair<ArrayList<Pair<String, String>>, ArrayList<String>>>) bundle.getSerializable("value");

        DictionaryAdapter adapter = new DictionaryAdapter(dict);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
