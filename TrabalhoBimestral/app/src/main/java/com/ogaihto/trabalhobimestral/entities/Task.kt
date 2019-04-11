package com.ogaihto.trabalhobimestral.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @ColumnInfo(name= "title")
    var title: String,
    @ColumnInfo(name = "description")
    var desc: String,
    @ColumnInfo(name = "completed")
    var completed: Boolean = false
){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}