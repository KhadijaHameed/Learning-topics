package com.example.roomwrapperimplement.db;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.roomwrapperimplement.Word;

import java.util.List;

public class Repository {

    private WordRoomDatabase database;
    WordDao wordDao;
    // crud method
    public Repository(Context application){
        database = WordRoomDatabase.getDatabase(application);
    }
    public void insert (Word word){
        AsyncTask.execute(() -> database.wordDao().insert(word));
    }

    public void updateWord(Word word, int id){
      AsyncTask.execute(() -> database.wordDao().update(word.getWord(), id));
    }

    public void deleteRowbyID(int id){
        AsyncTask.execute(() -> database.wordDao().deleteRowById(id));
    }

    public void deleteRowbySpecificWord(Word word){
        AsyncTask.execute(() -> database.wordDao().deleteRowByWord(word.getWord()));
    }
}

