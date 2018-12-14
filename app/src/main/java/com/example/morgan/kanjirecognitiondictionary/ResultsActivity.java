package com.example.morgan.kanjirecognitiondictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import com.example.morgan.kanjirecognitiondictionary.KanjiDrawing.*;
import com.example.morgan.kanjirecognitiondictionary.util.KanjiInfo;

import java.util.Arrays;
import java.util.HashSet;

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
    public final static int TOP_COUNT = 5;

    /**
     * Number of kanji shown in more count screen.
     */
    public final static int MORE_COUNT = 12;

    private final static int[] ALL_IDS =
            {
                    R.id.no1, R.id.no2, R.id.no3, R.id.no4, R.id.no5, R.id.no6,
                    R.id.no7, R.id.no8, R.id.no9, R.id.no10, R.id.no11, R.id.no12
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DrawnStroke[] strokes = DrawnStroke.loadFromIntent(getIntent());

        String[] matches = getIntent().getStringArrayExtra(EXTRA_MATCHES);
        HashSet<String> shown = new HashSet<String>(Arrays.asList(
                getIntent().getStringArrayExtra(EXTRA_ALREADYSHOWN)));
        int startFrom = getIntent().getIntExtra(EXTRA_STARTFROM, 0);
        int label = getIntent().getIntExtra(EXTRA_LABEL, 0);
        int otherLabel = getIntent().getIntExtra(EXTRA_OTHERLABEL, 0);
        boolean showMore = getIntent().getBooleanExtra(EXTRA_SHOWMORE, false);
        final KanjiInfo.MatchAlgorithm algo =
                KanjiInfo.MatchAlgorithm.valueOf(getIntent().getStringExtra(EXTRA_ALGO));

        setTitle(getString(label).replace("#", strokes.length + ""));
        setContentView(showMore ? R.layout.more_results : R.layout.activity_results);
//        ((Button)findViewById(R.id.other)).setText(getString(otherLabel)); // unecessary?

        int[] ids = new int[showMore ? MORE_COUNT : TOP_COUNT];
        System.arraycopy(ALL_IDS, 0, ids, 0, ids.length);

        int index = -startFrom;
        int buttonIndex = 0;

        for (int match = 0; match < matches.length; match++) {
            // Skip matches already shown
            if (shown.contains(matches[match])) {
                continue;
            }

            // See if this is one to draw
            if (index >= 0) {
                Button button = (Button) findViewById(ids[buttonIndex++]);
                button.setText(matches[match]);
                final Intent data = new Intent();
                final int ranking = match + 1;
                data.putExtra(MainActivity.EXTRA_KANJI, matches[match]);
                button.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // put into editText of main
                        finish();
                    }
                });

                // Stop if we filled all the buttons
                if(buttonIndex >= ids.length)
                {
                    break;
                }
            }

            index++;
        }

        // Clear all the unused buttons
        for(; buttonIndex<ids.length; buttonIndex++)
        {
            Button button = (Button) findViewById(ids[buttonIndex]);
            button.setText(" ");
            button.setEnabled(false);
        }

        if (!showMore) {
            Button other = (Button) findViewById(R.id.other);
            other.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!MainActivity.tryMore(ResultsActivity.this, getIntent())) {
                        setResult(RESULT_OK);
                        finish();
                    }
                }
            });
        }

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(checkQuit(data))
        {
            return;
        }
        if(resultCode == RESULT_OK)
        {
            setResult(RESULT_OK, data);
            finish();
        }
    }

    private boolean checkQuit(Intent intent)
    {
        if(intent != null && intent.getAction() != null
                && intent.getAction().equals("QUIT"))
        {
            quit();
            return true;
        }
        else
        {
            return false;
        }
    }

    private void quit()
    {
        Intent intent = new Intent("QUIT");
        setResult(RESULT_OK, intent);
        finish();
    }
}
