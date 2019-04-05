package com.ogaihto.trabalhobimestral.db.dao

import androidx.room.*
import com.ogaihto.trabalhobimestral.entities.Task

@Dao
interface TaskDao {
    @Query("select * from task")
    fun getAll(): List<Task>

    @Query("select * from task where id = :id")
    fun findById(id: Int): Task?

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun remove(task: Task)
}