package com.example.roomwrapperimplement.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomwrapperimplement.db.queries.PersonInfoDao;
import com.example.roomwrapperimplement.pojo.PersonInfo;

@Database(entities = {PersonInfo.class}, version = 1, exportSchema = false)
    public abstract class WordRoomDatabase extends RoomDatabase {
        //db instance
        public abstract PersonInfoDao personInfoDao();

        private static WordRoomDatabase INSTANCE;

        public static WordRoomDatabase getInstance(Context context) {
            if (INSTANCE == null) {
                synchronized (WordRoomDatabase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                WordRoomDatabase.class, "personinfo_database")
                               // .allowMainThreadQueries()
                                .build();
                    }
                }
            }
            return INSTANCE;
        }

    }

