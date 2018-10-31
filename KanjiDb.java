package com.bmaynard.kanjiextractor;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class KanjiDb extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "kanji.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;
    private SQLiteQueryBuilder qb;

    public KanjiDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        qb = new SQLiteQueryBuilder();
    }

    public ArrayList<Kanji> getKanjiFromString(String kanjiStr) {
        ArrayList<Kanji> kanjiArrayList = new ArrayList<>();

        String[] kanjiArr = kanjiStr.split(",");
        String queryStr = "SELECT * FROM kanji WHERE ";

        for(int i = 0; i < kanjiArr.length; i++) {
            String literal = kanjiArr[i].trim();
            if(kanjiArr.length > 1) {
                if(i > 0) {
                    queryStr += " OR literal = '" + literal +"'";
                } else {
                    queryStr += "literal = '" + literal +"'";
                }
            } else {
                queryStr += "literal = '" + literal + "'";
            }
        }

        db = getReadableDatabase();
        Cursor c = db.rawQuery(queryStr, null);
        c.moveToFirst();
        db.close();

        while( ! c.isAfterLast()) {
            kanjiArrayList.add(new Kanji(c));
            c.moveToNext();
        }

        return kanjiArrayList;
    }

}
