package com.bmaynard.kanjiextractor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class KanjiListActivity extends AppCompatActivity {

    private static final String TAG = "KanjiListActivity";

    private ArrayList<Kanji> kanjiArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji_list);

        kanjiArrayList  = (ArrayList<Kanji>) getIntent().getSerializableExtra("kanjiArrayList");

        ListView kanjiListView = (ListView) findViewById(R.id.kanjiListView);

        ArrayAdapter<Kanji> arrayAdapter = new ArrayAdapter<Kanji>(
                this,
                android.R.layout.simple_list_item_1,
                kanjiArrayList
        );

        kanjiListView.setAdapter(arrayAdapter);

        kanjiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(KanjiListActivity.this, KanjiDisplayActivity.class);

                Kanji kanjiItem = (Kanji) parent.getItemAtPosition(position);
                intent.putExtra("kanji", kanjiItem);

                startActivity(intent);
            }
        });
    }
}
