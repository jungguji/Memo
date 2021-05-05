package com.jgji.memo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MemoEntity.class}, version = 2)
abstract class MemoDatabase extends RoomDatabase {
    public abstract MemoDAO memoDAO();

    public static MemoDatabase INSTANCE = null;

    public static MemoDatabase getInstance(Context context) {
        synchronized (MemoDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                        , MemoDatabase.class, "memo.db")
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }

        return INSTANCE;
    }
}
