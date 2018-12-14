package com.example.morgan.kanjirecognitiondictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DictionaryActivity extends AppCompatActivity {

    public final static String DICT = "dictionary";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
    }
}
