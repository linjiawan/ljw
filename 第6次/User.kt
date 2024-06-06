package com.example.zxy.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    @ColumnInfo(name = "name")
    val Name:String
)
