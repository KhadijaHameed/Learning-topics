package com.example.roomwrapperimplement.db;

import android.app.Application;
import android.app.ListActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomwrapperimplement.pojo.PersonInfo;

import java.util.List;

public class PersonInfoViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<PersonInfo>> personInfoModelList;

    public PersonInfoViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        personInfoModelList = repository.getAllInfo();
    }


    public void insert(PersonInfo personInfo){
        repository.insert(personInfo);
    }

    public void update(String newN , String oldN ){
        repository.update(newN, oldN);
    }

    public void delete(PersonInfo personInfo){
            repository.delete(personInfo);
    }

    public LiveData<List<PersonInfo>> getAlldata(){
      return personInfoModelList;
    }

    public void deleteAllData(){
        repository.deleteAllInfo();
    }
}
