package com.bmaynard.kanjiextractor;

import android.database.Cursor;
import java.io.Serializable;

public class Kanji implements Serializable {

    private int id;
    private String literal;
    private String literalEnc;
    private String radical;
    private int grade;
    private int strokeCount;
    private int freq;
    private int jlpt;
    private String onyomi;
    private String onyomiEnc;
    private String kunyomi;
    private String kunyomiEnc;
    private String meaning;
    private String nanori;
    private String nanoriEnc;

    public Kanji(Cursor c) {

        id = c.getInt(c.getColumnIndex("_id"));
        literal = c.getString(c.getColumnIndex("literal"));
        literalEnc = c.getString(c.getColumnIndex("literal_enc"));
        radical = c.getString(c.getColumnIndex("radical"));
        grade = c.getInt(c.getColumnIndex("grade"));
        strokeCount = c.getInt(c.getColumnIndex("stroke_count"));
        freq = c.getInt(c.getColumnIndex("freq"));
        jlpt = c.getInt(c.getColumnIndex("jlpt"));
        onyomi = c.getString(c.getColumnIndex("onyomi"));
        onyomiEnc = c.getString(c.getColumnIndex("onyomi_enc"));
        kunyomi = c.getString(c.getColumnIndex("kunyomi"));
        kunyomiEnc = c.getString(c.getColumnIndex("kunyomi_enc"));
        meaning = c.getString(c.getColumnIndex("meaning"));
        nanori = c.getString(c.getColumnIndex("nanori"));
        nanoriEnc = c.getString(c.getColumnIndex("nanori_enc"));
    }

    public int getId() {
        return id;
    }

    public String getLiteral() {
        return literal;
    }

    public String getLiteralEnc() {
        return literalEnc;
    }

    public String getRadical() {
        return radical;
    }

    public int getGrade() {
        return grade;
    }

    public int getStrokeCount() {
        return strokeCount;
    }

    public int getFreq() {
        return freq;
    }

    public int getJlpt() {
        return jlpt;
    }

    public String getOnyomi() {
        return onyomi;
    }

    public String getOnyomiEnc() {
        return onyomiEnc;
    }

    public String getKunyomi() {
        return kunyomi;
    }

    public String getKunyomiEnc() {
        return kunyomiEnc;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getNanori() {
        return nanori;
    }

    public String getNanoriEnc() {
        return nanoriEnc;
    }

    public String toString() {
        String kanjiString = "";

        kanjiString = getLiteral();

        return kanjiString;
    }

}
