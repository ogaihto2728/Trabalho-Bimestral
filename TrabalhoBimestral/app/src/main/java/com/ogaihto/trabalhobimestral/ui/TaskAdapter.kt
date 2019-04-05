package com.ogaihto.trabalhobimestral.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ogaihto.trabalhobimestral.R
import com.ogaihto.trabalhobimestral.entities.Task
import kotlinx.android.synthetic.main.activity_item_task.view.*

class TaskAdapter(
    private var tasks: MutableList<Task>,
    private var listener: TaskListListener):
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    fun adicionarTask(task: Task) {
        tasks.add(0, task)
        notifyItemInserted(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.activity_item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount() = tasks.size

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.preencherView(task)
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun preencherView(task: Task) {
            itemView.editTitle.setText(task.title)
            itemView.editDesc.setText(task.desc)
//            itemView.editTitle.visibility = View.GONE
//            itemView.btExcluir.setOnClickListener {
//                with(this@TaskAdapter) {
//                    val posicao = tasks.indexOf(task)
//                    tasks.removeAt(posicao)
//                    notifyItemRemoved(posicao)
//                    listener.removedTask(task)
//                }
//            }

        }
    }
}
