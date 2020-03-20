package com.example.roomwrapperimplement.db;

import android.content.Context;
import android.os.AsyncTask;

import com.example.roomwrapperimplement.NewWord;
import com.example.roomwrapperimplement.OldWord;

public class Repository {

    private WordRoomDatabase database;
    WordDao wordDao;
    // crud method
    public Repository(Context application){
        database = WordRoomDatabase.getDatabase(application);
    }
   public void insert (OldWord oldWord){
     AsyncTask.execute(() -> database.wordDao().oinsert(oldWord));
}

   /* public void updateWord(Word word, int id){
      AsyncTask.execute(() -> database.wordDao().update(word.getWord(), id));
    }

    public void deleteRowbyID(int id){
        AsyncTask.execute(() -> database.wordDao().deleteRowById(id));
    }

    public void deleteRowbySpecificWord(Word word){
        AsyncTask.execute(() -> database.wordDao().deleteRowByWord(word.getWord()));
    }*/

    //second table
    public void ninsert (NewWord mNewWord){
       AsyncTask.execute(() -> database.wordDao().ninsert(mNewWord));
    }
}

