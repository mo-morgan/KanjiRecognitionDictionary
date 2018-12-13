package com.example.morgan.kanjirecognitiondictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ResultsActivity extends AppCompatActivity {
    /**
     * Intent key that should contain an int from R.strings with the activity
     * label.
     */
    public final static String EXTRA_LABEL = "label";

    /**
     * Intent key that should contain a string array with possible match kanji
     * (first in array = best match).
     */
    public final static String EXTRA_MATCHES = "matches";

    /**
     * Intent key that should contain an int from R.strings with the 'not one of
     * these' button label.
     */
    public final static String EXTRA_OTHERLABEL = "otherlabel";

    /**
     * Intent key that should contain an int indicating which result in the
     * matches array to start from.
     */
    public final static String EXTRA_STARTFROM = "startfrom";
    
    /**
     * Intent key that should contain an boolean; true indicates that the smaller
     * kanji grid is used.
     */
    public final static String EXTRA_SHOWMORE = "showmore";

    /**
     * Intent key that should contain an array of strings for kanji which were
     * already shown and should be skipped.
     */
    public final static String EXTRA_ALREADYSHOWN = "alreadyshown";

    /**
     * Current algorithm used in intent.
     */
    public final static String EXTRA_ALGO = "algo";

    /**
     * Number of kanji shown in top count screen.
     */
    public final static int TOP_COUNT = 7;

    /**
     * Number of kanji shown in more count screen.
     */
    public final static int MORE_COUNT = 12;

    private final static int[] ALL_IDS =
            {
                    R.id.no1, R.id.no2, R.id.no3, R.id.no4, R.id.no5
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
    }
}
