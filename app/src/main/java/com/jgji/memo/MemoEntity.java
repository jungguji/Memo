package com.jgji.memo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "memo")
public class MemoEntity {

    @PrimaryKey(autoGenerate = true)
    private final long id;
    private final String memo;

    public MemoEntity(long id, String memo) {
        this.id = id;
        this.memo = memo;
    }


}
