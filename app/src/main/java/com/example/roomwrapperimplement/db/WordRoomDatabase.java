package com.example.roomwrapperimplement.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomwrapperimplement.Word;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
    public abstract class WordRoomDatabase extends RoomDatabase {

        public abstract WordDao wordDao();

        private static WordRoomDatabase INSTANCE;

        public static WordRoomDatabase getDatabase(Context context) {
            if (INSTANCE == null) {
                synchronized (WordRoomDatabase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                WordRoomDatabase.class, "word_database")
                               // .allowMainThreadQueries()
                                .build();
                    }
                }
            }
            return INSTANCE;
        }

    }

