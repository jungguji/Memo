package com.jgji.memo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "memo")
public class MemoEntity {

    @PrimaryKey(autoGenerate = true)
    private final Long id;
    private final String memo;

    public MemoEntity(Long id, String memo) {
        this.id = id;
        this.memo = memo;
    }

    public Long getId() {
        return this.id;
    }

    public String getMemo() {
        return this.memo;
    }
}
