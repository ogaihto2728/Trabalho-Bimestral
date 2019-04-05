package com.ogaihto.trabalhobimestral.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ogaihto.trabalhobimestral.db.dao.TaskDao
import com.ogaihto.trabalhobimestral.entities.Task

@Database(entities = arrayOf(Task::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}