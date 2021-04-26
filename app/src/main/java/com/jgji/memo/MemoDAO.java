package com.jgji.memo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface MemoDAO {

    @Insert(onConflict = REPLACE)
    void insert(MemoEntity memoEntity);

    @Query("SELECT * FROM memo")
    List<MemoEntity> getAll();

    @Delete
    void delete(MemoEntity memoEntity);
}
