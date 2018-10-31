package com.bmaynard.kanjiextractor;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class KanjiDisplayActivity extends AppCompatActivity {

    public static final String TAG = "KanjiDisplayActivity";

    Kanji kanji;

    KanjiDb db;

    TextView kanjiTextView;

    LinearLayout meaningLayout;
    LinearLayout readingLayout;
    LinearLayout miscInfoLayout;

    boolean viewingStrokeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji_display);

        kanji = (Kanji) getIntent().getSerializableExtra("kanji");

        kanjiTextView = (TextView) findViewById(R.id.kanjiTextView);
        kanjiTextView.setText(kanji.getLiteral());
        viewingStrokeOrder = false;

        meaningLayout = (LinearLayout) findViewById(R.id.meaningLayout);

        ArrayList<String> meaningArrayList = new ArrayList<>(Arrays.asList(kanji.getMeaning().split("\\s*,\\s*")));
        for(String m : meaningArrayList) {
            TextView meaningTextView = new TextView(this);
            meaningTextView.setText(m);
            meaningLayout.addView(meaningTextView);
        }

        readingLayout = (LinearLayout) findViewById(R.id.readingLayout);

        String readings = kanji.getOnyomi() + "," + kanji.getKunyomi();

        ArrayList<String> readingArrayList = new ArrayList<>(Arrays.asList(readings.split("\\s*,\\s*")));
        for(String r : readingArrayList) {
            TextView readingTextView = new TextView(this);
            readingTextView.setText(r);
            readingLayout.addView(readingTextView);
        }

        class MiscInfoList {
            public String radical, grade, strokeCount, freq, jlpt;

            public MiscInfoList(Kanji k) {
                this.radical = k.getRadical();
                this.strokeCount = Integer.toString(k.getStrokeCount());
                this.grade = Integer.toString(k.getGrade());
                this.freq = Integer.toString(k.getFreq());
                this.jlpt = Integer.toString(k.getJlpt());
            }
        }

        miscInfoLayout = (LinearLayout) findViewById(R.id.miscInfoLayout);
        MiscInfoList miscInfoList = new MiscInfoList(kanji);

        if( ! miscInfoList.radical.equals("")) {
            TextView radicalTextView = new TextView(this);
            radicalTextView.setText("Radical: " + miscInfoList.radical);
            miscInfoLayout.addView(radicalTextView);
        }

        if( ! miscInfoList.strokeCount.equals("")) {
            TextView strokeCountTextView = new TextView(this);
            strokeCountTextView.setText("Stroke Count: " + miscInfoList.strokeCount);
            miscInfoLayout.addView(strokeCountTextView);
        }

        if( ! miscInfoList.grade.equals("")) {
            TextView gradeTextView = new TextView(this);
            gradeTextView.setText("Grade: " + miscInfoList.grade);
            miscInfoLayout.addView(gradeTextView);
        }

        if( ! miscInfoList.freq.equals("")) {
            TextView freqTextView = new TextView(this);
            freqTextView.setText("Frequency Rank: " + miscInfoList.freq);
            miscInfoLayout.addView(freqTextView);
        }

        if( ! miscInfoList.jlpt.equals("")) {
            TextView jlptTextView = new TextView(this);
            jlptTextView.setText("JLPT Level: N" + miscInfoList.jlpt);
            miscInfoLayout.addView(jlptTextView);
        }
    }

    public void toggleKanjiDisplayStrokeOrder(View view) {
        if( ! viewingStrokeOrder) {
            Typeface strokeOrderTypeface = Typeface.createFromAsset(getAssets(), "strokeorders.ttf");
            kanjiTextView.setTypeface(strokeOrderTypeface);
            viewingStrokeOrder = true;
        } else {
            kanjiTextView.setTypeface(Typeface.DEFAULT);
            viewingStrokeOrder = false;
        }
    }
}
