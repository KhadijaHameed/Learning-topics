package com.example.roomwrapperimplement;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "second_word_table")
public class OldWord {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "second_word")
    private String mWord;

    public OldWord(String word) {this.mWord = word;}

    public String getWord(){return this.mWord;}
}


