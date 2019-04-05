package com.ogaihto.trabalhobimestral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ogaihto.trabalhobimestral.R.id.ListTask
import com.ogaihto.trabalhobimestral.R.id.plus
import com.ogaihto.trabalhobimestral.entities.Task
import com.ogaihto.trabalhobimestral.ui.TaskAdapter
import com.ogaihto.trabalhobimestral.ui.TaskListListener
import kotlinx.android.synthetic.main.activity_main.*
import androidx.coordinatorlayout.widget.CoordinatorLayout



class MainActivity : AppCompatActivity(), TaskListListener {
    override fun removedTask(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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
        val task = Task("","")

        adapter.adicionarTask(task)

        plus.hide()
    }
}
