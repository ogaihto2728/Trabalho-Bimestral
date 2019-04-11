package com.ogaihto.trabalhobimestral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ogaihto.trabalhobimestral.entities.Task
import com.ogaihto.trabalhobimestral.ui.TaskAdapter
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plus.setOnClickListener{
            click()
        }

        adapter = TaskAdapter(mutableListOf(), this)
        ListTask.adapter = adapter
        ListTask.layoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )

    }

    private fun click() {
        val task = Task("New Task","")

        adapter.adicionarTask(task)

        plus.hide()
    }

    fun showPlus() {
        plus.show()
    }

}
