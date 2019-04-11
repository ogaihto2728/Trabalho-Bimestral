package com.ogaihto.trabalhobimestral.ui

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ogaihto.trabalhobimestral.MainActivity
import com.ogaihto.trabalhobimestral.R
import com.ogaihto.trabalhobimestral.entities.Task
import kotlinx.android.synthetic.main.activity_item_task.view.*

class TaskAdapter(
    private var tasks: MutableList<Task>,
    private var context: MainActivity, private var focusedIndex: Int = -1):
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    fun adicionarTask(task: Task) {
        tasks.add(task)
        focusedIndex = tasks.size - 1
        notifyItemInserted(tasks.size - 1)
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
        holder.preencherView(task, position)
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun preencherView(task: Task, position: Int) {
            for (t in tasks)
                print(tasks.indexOf(t).toString() + " ")
            itemView.editTitle.setText(task.title)
            itemView.editDesc.setText(task.desc)
            itemView.txtTitle.text = if (task.id != 0) task.title else task.title + "(${context.getString(R.string.not_saved)})"

            if (position == focusedIndex) {
                if (task.id == 0) {
                    //Item focado, mas não adicionado ao banco de dados ainda.
                    itemView.txtTitle.visibility = View.GONE
                    itemView.editTitle.visibility = View.VISIBLE
                    itemView.editDesc.visibility = View.VISIBLE
                    itemView.btExcluir.visibility = View.VISIBLE
                    itemView.btSalvar.visibility = View.VISIBLE
                    itemView.btExcluir.setImageResource(R.drawable.ic_cancel_grey_24dp)

                    itemView.btExcluir.setOnClickListener {
                        tasks.remove(task)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, tasks.size)
                        focusedIndex = -1
                        context.showPlus()
                    }

                    itemView.btSalvar.setOnClickListener {
                        task.title = itemView.editTitle.text.toString()
                        task.desc = itemView.editDesc.text.toString()
                        //Substituir essa linha por uma inserção no banco de dados.
                        task.id = 2

                        notifyItemChanged(position)
                        focusedIndex = -1
                        context.showPlus()
                    }

                } else {
                    //Item focado, já adicionado ao banco de dados.
                    itemView.txtTitle.visibility = View.GONE
                    itemView.editTitle.visibility = View.VISIBLE
                    itemView.editDesc.visibility = View.VISIBLE
                    itemView.btExcluir.visibility = View.VISIBLE
                    itemView.btSalvar.visibility = View.VISIBLE
                    itemView.btExcluir.setImageResource(R.drawable.ic_delete)

                    itemView.btExcluir.setOnClickListener {
                        //Remover do banco de dados.
                        tasks.remove(task)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, tasks.size)
                        focusedIndex = -1
                        context.showPlus()
                    }

                    itemView.btSalvar.setOnClickListener {
                        task.title = itemView.editTitle.text.toString()
                        task.desc = itemView.editDesc.text.toString()
                        //Atualizar no banco de dados.
                        notifyItemChanged(position)
                        focusedIndex = -1
                        context.showPlus()
                    }
                }
            } else {
                //Item não focado.
                itemView.txtTitle.visibility = View.VISIBLE
                itemView.editTitle.visibility = View.GONE
                itemView.editDesc.visibility = View.GONE
                itemView.btExcluir.visibility = View.GONE
                itemView.btSalvar.visibility = View.GONE

            }
            itemView.setOnClickListener {
                //Define focusedIndex para o item,

                var oldFocus = focusedIndex
                if (position != focusedIndex)
                    focusedIndex = position
                if (oldFocus >= 0)
                    notifyItemChanged(oldFocus)
                notifyItemChanged(position)
            }

        }
    }
}
