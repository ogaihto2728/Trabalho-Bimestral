package com.ogaihto.trabalhobimestral.ui

import android.content.Intent
import android.content.res.Resources
import android.graphics.Paint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
        var oldFocus = focusedIndex
        focusedIndex = tasks.size - 1
        notifyItemInserted(tasks.size - 1)
        if (oldFocus >= 0)
            notifyItemChanged(oldFocus)
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

            println(task.id)
            itemView.editTitle.setText(task.title)
            itemView.editDesc.setText(task.desc)
            itemView.txtTitle.text = if (task.id != 0) task.title else task.title + " (${context.getString(R.string.not_saved)})"
            itemView.txtCompleted.text = if (task.completed) context.getString(R.string.completed) else if (task.id != 0) context.getString(R.string.wip) else ""

            if (position == focusedIndex) {
                if (task.id == 0) {
                    //Item focado, mas não adicionado ao banco de dados ainda.
                    itemView.txtTitle.visibility = View.GONE
                    itemView.txtCompleted.visibility = View.GONE
                    itemView.btShare.visibility = View.GONE
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

                        context.insertTask(task)

                        notifyItemChanged(position)
                        focusedIndex = -1
                        context.showPlus()
                    }

                } else {
                    //Item focado, já adicionado ao banco de dados.
                    itemView.txtTitle.visibility = View.GONE
                    itemView.txtCompleted.visibility = View.GONE
                    itemView.btShare.visibility = View.GONE
                    itemView.editTitle.visibility = View.VISIBLE
                    itemView.editDesc.visibility = View.VISIBLE
                    itemView.btExcluir.visibility = View.VISIBLE
                    itemView.btSalvar.visibility = View.VISIBLE
                    itemView.btExcluir.setImageResource(R.drawable.ic_delete)

                    itemView.btExcluir.setOnClickListener {

                        context.removeTask(task)

                        tasks.remove(task)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, tasks.size)
                        focusedIndex = -1
                    }

                    itemView.btSalvar.setOnClickListener {
                        task.title = itemView.editTitle.text.toString()
                        task.desc = itemView.editDesc.text.toString()

                        context.updateTask(task)

                        notifyItemChanged(position)
                        focusedIndex = -1
                    }
                }
            } else {
                //Item não focado.
                itemView.txtTitle.visibility = View.VISIBLE
                itemView.txtCompleted.visibility = View.VISIBLE
                itemView.txtCompleted.setTextColor(ContextCompat.getColor(context, if (task.completed) R.color.completed else R.color.wip))
                itemView.txtCompleted.setTypeface(null, if (task.completed) Typeface.BOLD else Typeface.NORMAL)
                itemView.btShare.visibility = if (task.completed) View.VISIBLE else View.GONE
                itemView.editTitle.visibility = View.GONE
                itemView.editDesc.visibility = View.GONE
                itemView.btExcluir.visibility = View.GONE
                itemView.btSalvar.visibility = View.GONE

                if (task.id > 0)
                    itemView.setOnLongClickListener {
                        if (task.id != 0) {
                            task.completed = !task.completed
                            context.updateTask(task)
                            notifyItemChanged(position)
                        }
                        return@setOnLongClickListener true
                    }

                itemView.setOnClickListener {
                    //Define focusedIndex para o item.

                    if (position != focusedIndex) {
                        var oldFocus = focusedIndex
                        focusedIndex = position
                        if (oldFocus >= 0)
                            notifyItemChanged(oldFocus)
                        notifyItemChanged(position)
                    }
                }

            }

            itemView.btShare.setOnClickListener {
                //Compartilhar
                if (task.completed) {
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    with(shareIntent) {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, context.getString(R.string.share_text) + task.title)
                    }
                    context.startActivity(shareIntent)

                }

            }

        }
    }
}
