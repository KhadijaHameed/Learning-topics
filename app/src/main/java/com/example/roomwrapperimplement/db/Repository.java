package com.example.roomwrapperimplement.db;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.roomwrapperimplement.db.queries.PersonInfoDao;
import com.example.roomwrapperimplement.pojo.NewWord;
import com.example.roomwrapperimplement.pojo.PersonInfo;

import java.util.List;

public class  Repository {

    private WordRoomDatabase database;
    private  PersonInfoDao personInfoDao;
    private LiveData<List<PersonInfo>> allpersOinInfoList;
    // crud method
    public Repository(Context context){
        database = WordRoomDatabase.getInstance(context);
        personInfoDao = database.personInfoDao();
       allpersOinInfoList = personInfoDao.getAllInfo();
    }

     public void insert (PersonInfo personInfo){
     AsyncTask.execute(() -> database.personInfoDao().insert(personInfo));
    }

    public void update(String newName, String oldNAme){
    AsyncTask.execute(() -> database.personInfoDao().update(newName , oldNAme));
    }

    public void delete(PersonInfo personInfo){
    AsyncTask.execute(() -> database.personInfoDao().deleteRowByWord(personInfo.getFirstName()));
    }

    public LiveData<List<PersonInfo>> getAllInfo(){
        AsyncTask.execute(() -> database.personInfoDao().getAllInfo());
        return allpersOinInfoList;
    }

    public void deleteAllInfo(){
        AsyncTask.execute(() -> database.personInfoDao().deleteAllInfo());
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
  /*  public void ninsert (NewWord mNewWord){
       AsyncTask.execute(() -> database.wordDao().ninsert(mNewWord));
    }*/
}

