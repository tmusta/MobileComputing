package com.example.mobilecomputing

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Remind::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reminderDao() : RemindDao

}