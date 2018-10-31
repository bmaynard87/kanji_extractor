package com.bmaynard.kanjiextractor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private KanjiDb db;

    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void extractKanji(View view) {
        inputText = (EditText) findViewById(R.id.inputText);
        String str = inputText.getText().toString();
        if(str.length() == 0) {
            //No input
            showToast("Please enter some text in the input field.");
        } else {
            if(containsKanji(str)) {
                //String contains kanji, so is valid
                ArrayList<String> extractedKanji = new ArrayList<>();
                //Loop through input string and add kanji to ArrayList
                for(int i = 0; i < str.length(); i++) {
                    String character = Character.toString(str.charAt(i));
                    if(isKanji(character)) {
                        extractedKanji.add(character);
                    }
                }

                //Create comma-separated string from ArrayList
                String joined = TextUtils.join(",", extractedKanji);

                //Instantiate DB
                db = new KanjiDb(this);

                //Get ArrayList populated from db with appropriate Kanji objects
                ArrayList<Kanji> kanjiArrayList = db.getKanjiFromString(joined);

                //Fill new ArrayList with sorted Kanji
                ArrayList<Kanji> sortedKanjiArrayList = new ArrayList<>();
                for(String ek : extractedKanji) {
                    //Log.i(TAG, ek);
                    for(Kanji k : kanjiArrayList) {
                        if(ek.equals(k.getLiteral())) {
                            sortedKanjiArrayList.add(k);
                        }
                    }
                }

                Intent intent = new Intent(MainActivity.this, KanjiListActivity.class);
                intent.putExtra("kanjiArrayList", sortedKanjiArrayList);
                startActivity(intent);

            } else {
                showToast("Input text doesn't contain any kanji.");
            }
        }
    }

    public boolean containsKanji(String str) {
        return str.matches(".*[\\p{Han}].*");
    }

    public boolean isKanji(String str) {
        return str.matches("[\\p{Han}]");
    }

    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
