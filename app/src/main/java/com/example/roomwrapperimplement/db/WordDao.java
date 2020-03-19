package com.example.roomwrapperimplement.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomwrapperimplement.Word;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface WordDao {

  // queries
  // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void  insert(Word word);

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

    //delete row from id
    @Query("DELETE FROM word_table WHERE word=:word")
    void deleteRowByWord(String word);
}