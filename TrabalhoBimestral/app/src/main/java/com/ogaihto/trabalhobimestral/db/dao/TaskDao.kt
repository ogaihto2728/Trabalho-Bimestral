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
    fun insert(task: Task): Long

    @Query("update task set title = :title, description = :description, completed = :completed where id = :id")
    fun update(id: Int, title: String, description: String, completed: Boolean)

    @Delete
    fun remove(task: Task)
}