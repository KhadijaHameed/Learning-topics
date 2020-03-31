package com.example.roomwrapperimplement.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/*

@Entity(tableName = "second_table")
 public class WordSecondTable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "second_table_word")
    private String secondTableWord;

    public  WordSecondTable(String word) {
        this.secondTableWord = word;
    }

    public String secondTableGetWord(){return this.secondTableWord;}
}
*/


@Entity(tableName = "sword_table")
public class NewWord {
    public NewWord(String mWord) {this.mWord = mWord;}

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "sword")
    private String mWord;

    //multiple columns in one table
    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;


   public String getWord(){return this.mWord;}


}


