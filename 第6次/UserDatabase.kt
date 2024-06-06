package com.example.zxy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[User::class],version=1,exportSchema=false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun UserDao(): UserDao

    companion object {
        @Volatile
        private var Instance: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, UserDatabase::class.java, "student_database")
                    .build().also{ Instance =it }
            }
        }
    }
}