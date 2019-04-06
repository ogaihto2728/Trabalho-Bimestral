package com.ogaihto.trabalhobimestral.ui

import com.ogaihto.trabalhobimestral.entities.Task

interface TaskListListener {
    fun removedTask(task: Task)
    fun updatedTask(task: Task)
    fun addedTask(task: Task)
}