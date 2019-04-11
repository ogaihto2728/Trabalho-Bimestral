package com.ogaihto.trabalhobimestral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.ogaihto.trabalhobimestral.db.AppDatabase
import com.ogaihto.trabalhobimestral.db.dao.TaskDao
import com.ogaihto.trabalhobimestral.entities.Task
import com.ogaihto.trabalhobimestral.ui.TaskAdapter
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    lateinit var adapter: TaskAdapter
    lateinit var taskdao: TaskDao
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "tasks.db").allowMainThreadQueries().build()

        taskdao = db.taskDao()

        plus.setOnClickListener{
            click()
        }


        adapter = TaskAdapter(taskdao.getAll().toMutableList(), this)
        ListTask.adapter = adapter
        ListTask.layoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )

    }

    private fun click() {
        val task = Task(getString(R.string.new_task),"")

        adapter.adicionarTask(task)

        plus.hide()
    }

    fun showPlus() {
        plus.show()
    }

    fun removeTask(task: Task) {
        taskdao.remove(task)
    }

    fun insertTask(task: Task) {
        task.id = taskdao.insert(task).toInt()
    }

    fun updateTask(task: Task) {
        taskdao.update(task.id, task.title, task.desc, task.completed)
    }

    fun findById(id: Int) {

    }

}
