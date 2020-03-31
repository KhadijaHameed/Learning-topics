package com.example.roomwrapperimplement.db.queries;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomwrapperimplement.pojo.PersonInfo;

import java.util.List;

@Dao
public interface PersonInfoDao {

    @Insert
    void insert(PersonInfo personInfo);

    //@Update
   // void update(PersonInfo personInfo);
    @Query("UPDATE person_info_table SET first_name=:name WHERE first_name =:oldname")
    void update(String name, String oldname);

    @Delete
    void delete(PersonInfo personInfo);


    @Query("DELETE FROM person_info_table WHERE first_name=:word")
    void deleteRowByWord(String word);

    @Query("Delete from person_info_table")
    void deleteAllInfo();

   // @Query("Select * from person_info_table order by priority desc")
    @Query("Select * from person_info_table")
     LiveData<List<PersonInfo>> getAllInfo();

   // queries
   // allowing the insert of the same word multiple times by passing a
   // conflict resolution strategy

   /*
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void  oinsert(PersonInfo oldWord);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void  ninsert(NewWord mNewWord);


   //second table
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  void  sInsert(WordSecondTable sword);*/

  /*
    @Query("DELETE FROM word_table")
    void deleteAll();

    //@Query("SELECT * from word_table ORDER BY word ASC")
   // List<Word> getAlphabetizedWords();

 // @Update(onConflict = REPLACE)
  //void update(Word word);

   @Query("UPDATE word_table SET word=:word WHERE rowid =:id")
   void update(String word, int id);

   //delete row from id
    @Query("DELETE FROM word_table WHERE rowid =:id")
    void deleteRowById(int id);

    //delete row from word
    @Query("DELETE FROM word_table WHERE word=:word")
    void deleteRowByWord(String word);
*/



}